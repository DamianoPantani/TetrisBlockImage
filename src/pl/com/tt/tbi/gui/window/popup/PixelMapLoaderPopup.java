package pl.com.tt.tbi.gui.window.popup;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import pl.com.tt.tbi.model.pixelmap.PixelMap;

public class PixelMapLoaderPopup extends AbstractPixelMapPopup {

	private static final long serialVersionUID = 1L;
	
	public PixelMapLoaderPopup() throws IOException{
		super("Load bitmap file");
	}

	public PixelMap loadBitmapFromSelectedFile() throws IOException, ClassNotFoundException {
   		FileInputStream fin = new FileInputStream(getSelectedFile());
		ObjectInputStream ois = new ObjectInputStream(fin);
		PixelMap bitmap = (PixelMap) ois.readObject();
		ois.close();
		return bitmap;
	}

}
