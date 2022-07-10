package pl.com.tt.tbi.gui.window;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pl.com.tt.tbi.algorithm.acs.ACSSolvingAlgorithm;
import pl.com.tt.tbi.algorithm.acs.Ant;
import pl.com.tt.tbi.algorithm.callback.ACSCallback;
import pl.com.tt.tbi.gui.GuiBitmapConverter;
import pl.com.tt.tbi.gui.JColorfulTetrisTable;
import pl.com.tt.tbi.gui.JTetrisTable;
import pl.com.tt.tbi.gui.window.popup.PixelMapLoaderPopup;
import pl.com.tt.tbi.gui.window.popup.PixelMapSaverPopup;
import pl.com.tt.tbi.model.BrickPlacement;
import pl.com.tt.tbi.model.brick.Brick;
import pl.com.tt.tbi.model.pixelmap.PixelMap;
import pl.com.tt.tbi.model.pixelmap.TrollFacePixelMap;

public class MainWindow extends AbstractTetrisWindow {

	private static final long serialVersionUID = 1L;
	
	ACSSolvingAlgorithm solvingAlgorithm = new ACSSolvingAlgorithm();
	JTetrisTable table = GuiBitmapConverter.toTable(new TrollFacePixelMap());
	JScrollPane tableScrollPane;
	
	public MainWindow() {
		super("Tetris Bitmap", 640, 530);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void initElements() {
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		JPanel launchPanel = new JPanel();
		launchPanel.setLayout(null);
		launchPanel.setBounds(10, 10, 620, 40);
		launchPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(10, 60, 620, 380);
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

		JProgressBar progressBar = new JProgressBar();
		progressBar.setVisible(false);
		progressBar.setMaximum(100);
		progressBar.setMinimum(0);
		progressBar.setBounds(400, 10, 100, 10);
		
		JButton launchButton = new JButton("Start");
		JButton stopButton = new JButton("Stop");

		launchButton.setBounds(10, 10, 100, 20);
		launchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ACSSolvingAlgorithm solvingAlgorithm = new ACSSolvingAlgorithm();
				PixelMap wantedBitmap = GuiBitmapConverter.toBitmap(table);
				ResultingBitmapWindow resultsWindow = new ResultingBitmapWindow(new JColorfulTetrisTable(wantedBitmap.getWidth(), wantedBitmap.getHeight()));
				new WindowInitializer().init(resultsWindow);
				
				progressBar.setVisible(true);
				progressBar.setValue(0);
				launchButton.setVisible(false);
				stopButton.setVisible(true);
				
				solvingAlgorithm.solveInAnotherThread(wantedBitmap, new ACSCallback() {
					Ant previousBestAnt;
					@Override
					public void notifyAntFinished(Ant ant) {
						//TODO progressbar
						progressBar.setValue(40);
						repaint();
						System.out.println("Ant result - "+ant.getSolutionValue());
					}

					@Override
					public void notifyCycleFinished(Ant bestAnt) {
						if (previousBestAnt != bestAnt){
							resultsWindow.updateTable(GuiBitmapConverter.toTable(bestAnt.toBrickMap(), wantedBitmap.getWidth(), wantedBitmap.getHeight()));
							previousBestAnt = bestAnt;
							System.out.println("Best result - "+bestAnt.getSolutionValue());
						}
					}

					@Override
					public void notifyAlgorithmFinished(Map<BrickPlacement, Brick> results) {
						progressBar.setVisible(false);
						progressBar.setValue(0);
						stopButton.setVisible(false);
						launchButton.setVisible(true);
						resultsWindow.setVisible(true);
					}
				});

			}
		});
		
		stopButton.setBounds(10, 10, 100, 20);
		stopButton.setVisible(false);
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				solvingAlgorithm.interrupt();
				stopButton.setVisible(false);
				launchButton.setVisible(true);
			}
		});


		
		JSlider widthSlider = new JSlider(PixelMap.MIN_WIDTH, PixelMap.MAX_WIDTH, 16);
		JSlider heightSlider = new JSlider(PixelMap.MIN_HEIGHT, PixelMap.MAX_HEIGHT, 13);
		JLabel sizeLabel = new JLabel(widthSlider.getValue()+" x "+heightSlider.getValue(), SwingConstants.CENTER);
		sizeLabel.setBounds(230, 10, 50, 20);
		widthSlider.setBounds(120, 10, 100, 20);
		widthSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int width = widthSlider.getValue();
				int height = heightSlider.getValue();
				table.setColumnsCount(width);
				sizeLabel.setText(width+" x "+height);
			}
		});

		heightSlider.setBounds(290, 10, 100, 20);
		heightSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int width = widthSlider.getValue();
				int height = heightSlider.getValue();
				table.setRowsCount(height);
				sizeLabel.setText(width+" x "+height);
			}
		});
		
		JButton clearButton = new JButton("Clear");
		clearButton.setBounds(400, 10, 100, 20);
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				table.clearAllPixels();
			}
		});
		
		tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBounds(0, 0, 620, 380);

		JPanel settingsPanel = new JPanel();
		settingsPanel.setLayout(null);
		settingsPanel.setBounds(10, 450, 620, 40);
		settingsPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		JButton loadBitmapButton = new JButton("Load Bitmap");
		loadBitmapButton.setBounds(10, 10, 100, 20);
		loadBitmapButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					PixelMapLoaderPopup bitmapLoader = new PixelMapLoaderPopup();
					if (bitmapLoader.showOpenDialog(null) == PixelMapLoaderPopup.APPROVE_OPTION) {
						PixelMap bitmap = bitmapLoader.loadBitmapFromSelectedFile();
						table = GuiBitmapConverter.toTable(bitmap);
						mainPanel.remove(tableScrollPane);
						tableScrollPane = new JScrollPane(table);
						tableScrollPane.setBounds(0, 0, 620, 380);
						mainPanel.add(tableScrollPane);
						mainPanel.repaint();
			        }

				} catch (ClassNotFoundException | IOException e1) {
					JOptionPane.showMessageDialog(null, "Cannot load bitmap: "+e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton saveBitmapButton = new JButton("Save Bitmap");
		saveBitmapButton.setBounds(120, 10, 100, 20);
		saveBitmapButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					PixelMap bitmap = GuiBitmapConverter.toBitmap(table);
					PixelMapSaverPopup bitmapSaver = new PixelMapSaverPopup();
					if (bitmapSaver.showSaveDialog(null) == PixelMapSaverPopup.APPROVE_OPTION) {
			            bitmapSaver.saveToSelectedFile(bitmap);
			        }
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Cannot save bitmap: "+e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		launchPanel.add(launchButton);
		launchPanel.add(stopButton);
		launchPanel.add(widthSlider);
		launchPanel.add(sizeLabel);
		launchPanel.add(heightSlider);
		launchPanel.add(clearButton);
		launchPanel.add(progressBar);

		mainPanel.add(tableScrollPane);

		settingsPanel.add(loadBitmapButton);
		settingsPanel.add(saveBitmapButton);
		
		contentPane.add(mainPanel);
		contentPane.add(launchPanel);
		contentPane.add(settingsPanel);
	}


}
