package pl.com.tt.tbi.gui.window.popup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import pl.com.tt.tbi.model.pixelmap.PixelMap;

public class PixelMapSaverPopup extends AbstractPixelMapPopup {

	private static final long serialVersionUID = 1L;
	
	public PixelMapSaverPopup() throws IOException {
		super("Save bitmap file");
	}

	public void saveToSelectedFile(PixelMap bitmap) throws IOException {
		File file = getSelectedFile();
		if (!file.getName().endsWith("."+BITMAP_FILE_EXTENSION)){
			file = new File(file.getPath()+"."+BITMAP_FILE_EXTENSION);
		}
		FileOutputStream fout = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fout); 
		oos.writeObject(bitmap);
		oos.flush();
		oos.close();
		
	}

}
