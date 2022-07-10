package pl.com.tt.tbi;

import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import pl.com.tt.tbi.gui.window.MainWindow;
import pl.com.tt.tbi.gui.window.WindowInitializer;

public class GuiApplicationRunner {

	public static void main(String argc[]) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					new WindowInitializer().init(new MainWindow());
				} catch (Exception exc) {
					exc.printStackTrace();
					JOptionPane.showMessageDialog(null, "Can't load the application\nProblems with graphic", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
