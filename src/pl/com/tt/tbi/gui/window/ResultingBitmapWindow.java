package pl.com.tt.tbi.gui.window;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import pl.com.tt.tbi.gui.GuiBitmapConverter;
import pl.com.tt.tbi.gui.JTetrisTable;
import pl.com.tt.tbi.gui.window.popup.ImageSaverPopup;

public class ResultingBitmapWindow extends AbstractTetrisWindow {

	private static final long serialVersionUID = 1L;
	private JTetrisTable table;
	JScrollPane tableScrollPane = new JScrollPane();

	public ResultingBitmapWindow(JTetrisTable table) {
		super("Resulting Bitmap", 640, 480);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.table = table;
	}

	@Override
	public void initElements() {
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		updateTable(table);

		JPanel optionsPanel = new JPanel();
		optionsPanel.setBounds(10, 400, 620, 40);
		optionsPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		optionsPanel.setLayout(null);
		
		JButton saveImageButton = new JButton("Save Image");
		saveImageButton.setBounds(10, 10, 100, 20);
		saveImageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
	            try {
	            	ImageSaverPopup imageSaver = new ImageSaverPopup();
	            	if (imageSaver.showSaveDialog(null) == ImageSaverPopup.APPROVE_OPTION){
						BufferedImage image = GuiBitmapConverter.toImage(table);
				        imageSaver.saveToSelectedFile(image);
	            	}
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Cannot save image: "+e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		
		optionsPanel.add(saveImageButton);
		
		contentPane.add(tableScrollPane);
		contentPane.add(optionsPanel);
	}

	public void updateTable(JTetrisTable newTable) {
		this.table = newTable;
		Container contentPane = getContentPane();
		contentPane.remove(tableScrollPane);
		tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBounds(10, 10, 620, 380);
		contentPane.add(tableScrollPane);
		contentPane.repaint();
	}
	
}
