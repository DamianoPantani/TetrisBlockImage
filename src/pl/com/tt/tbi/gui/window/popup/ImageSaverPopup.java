package pl.com.tt.tbi.gui.window.popup;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageSaverPopup extends OverwritingFileChooser {

	public static final String BITMAP_FILE_EXTENSION = "bmp";
	private static final long serialVersionUID = 1L;
	
	public ImageSaverPopup() throws IOException {
		setDialogTitle("Save image");
		FileNameExtensionFilter f = new FileNameExtensionFilter("Bitmap File", BITMAP_FILE_EXTENSION);
		addChoosableFileFilter(f);
		setFileFilter(f);
		setFileSelectionMode(FILES_ONLY);
	}

	public void saveToSelectedFile(BufferedImage bufferedImage) throws IOException {
		File file = getSelectedFile();
		if (!file.getName().endsWith("."+BITMAP_FILE_EXTENSION)){
			file = new File(file.getPath()+"."+BITMAP_FILE_EXTENSION);
		}
		ImageIO.write(bufferedImage, "bmp", file);
	}
	
}
