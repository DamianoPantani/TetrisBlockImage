package pl.com.tt.tbi.gui.window.popup;

import java.io.IOException;

import javax.swing.filechooser.FileNameExtensionFilter;

public abstract class AbstractPixelMapPopup extends OverwritingFileChooser {

	public static final String BITMAP_FILE_EXTENSION = "tbb";
	private static final long serialVersionUID = 1L;
	
	public AbstractPixelMapPopup(String dialogTitle) throws IOException {
		setDialogTitle(dialogTitle);
		FileNameExtensionFilter f = new FileNameExtensionFilter("Tetris Brick Bitmap File", BITMAP_FILE_EXTENSION);
		addChoosableFileFilter(f);
		setFileFilter(f);
		setFileSelectionMode(FILES_ONLY);
	}
    
}
