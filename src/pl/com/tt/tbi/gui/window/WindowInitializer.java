package pl.com.tt.tbi.gui.window;

public class WindowInitializer {

	public void init(AbstractTetrisWindow window){
		window.setLocationRelativeTo(null);
		window.initElements();
		window.setResizable(false);
		window.setVisible(true);
		window.requestFocus();
	}
	
}
