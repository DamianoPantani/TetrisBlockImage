package pl.com.tt.tbi.gui.window;

import javax.swing.JFrame;

public abstract class AbstractTetrisWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public abstract void initElements();
	
	public AbstractTetrisWindow(String name, int width, int height){
		super(name);
		pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(getX() - width / 2, getY() - height / 2);
		setSize(width, height);
		getContentPane().setBounds(0, 0, width, height);
	}
	
}
