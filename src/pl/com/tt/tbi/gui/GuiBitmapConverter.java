package pl.com.tt.tbi.gui;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JTable;

import pl.com.tt.tbi.model.BrickPlacement;
import pl.com.tt.tbi.model.Coordinates;
import pl.com.tt.tbi.model.brick.Brick;
import pl.com.tt.tbi.model.pixelmap.PixelMap;

public class GuiBitmapConverter {

	public static PixelMap toBitmap(JTable table) {
		int width = table.getColumnCount();
		int height = table.getRowCount();
		PixelMap bitmap = new PixelMap(width, height);
		
		for (int x=0; x<width; x++){
			for (int y=0; y<height; y++){
				if (table.getValueAt(y, x) != null){
					bitmap.fillPixel(x, y);
				}
			}
		}
		
		return bitmap;
	}
	
	public static JTetrisTable toTable(Map<BrickPlacement, Brick> bitmap, int width, int height){
		JColorfulTetrisTable table = new JColorfulTetrisTable(width, height);
		for (Entry<BrickPlacement, Brick> entry: bitmap.entrySet()){
			Brick brick = entry.getValue();
			BrickPlacement placement = entry.getKey();
			for (Coordinates coords: placement.getCalculatedCoords()){
				table.setPixelAt(coords.getX(), coords.getY(), brick.getColor());
			}
		}
		return table;
	}
	
	public static JTetrisTable toTable(PixelMap bitmap){
		JTetrisTable table = new JTetrisTable(bitmap.getWidth(), bitmap.getHeight());
		for (int x=0; x<bitmap.getWidth(); x++){
			for (int y=0; y<bitmap.getHeight(); y++){
				if(bitmap.isPixelSetAt(x, y)){
					table.setPixelAt(x, y);
				}
			}
		}
		return table;
	}

	public static BufferedImage toImage(JTetrisTable table){
        Dimension size = table.getSize();
        BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        table.paint(g2);
        return image;
	}
}
