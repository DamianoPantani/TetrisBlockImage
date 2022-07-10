package pl.com.tt.tbi.model.pixelmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.com.tt.tbi.algorithm.acs.ACSBrickPlacement;
import pl.com.tt.tbi.model.BrickPlacement;
import pl.com.tt.tbi.model.Coordinates;

public class PixelMap implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int MIN_WIDTH = 5;
	public static final int MIN_HEIGHT = 5;
	public static final int MAX_WIDTH = 40;
	public static final int MAX_HEIGHT = 40;
	
	private static final Logger logger = LogManager.getLogger(PixelMap.class);
	
	private ArrayList<List<Pixel>> pixelMap = new ArrayList<List<Pixel>>();
	private int width;
	private int height;
	
	public PixelMap(int width, int height){
		
		logger.trace("Initializing "+this.getClass().getSimpleName()+" with size "+width+"x"+height);
		
		if (width < MIN_WIDTH || width > MAX_WIDTH || height < MIN_HEIGHT || height > MAX_HEIGHT){
			throw new IllegalArgumentException(String.format("Wrong resolution %ix%i. Should be between %ix%i and %ix%i",
					width, height, MIN_WIDTH, MIN_HEIGHT, MAX_WIDTH, MAX_HEIGHT));
		}
		for (int i=0; i<height; i++){
			Pixel[] row = new Pixel[width];
			Arrays.setAll(row, pixel -> new Pixel());
			pixelMap.add(Arrays.asList(row));
		}
		
		this.width = width;
		this.height = height;
		logger.debug("Array has size "+pixelMap.size()+"x"+pixelMap.get(0).size());
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void fillPixel(int x, int y){
		pixelMap.get(y).get(x).setFilled(true);
	}
	
	public void removePixel(int x, int y) {
		pixelMap.get(y).get(x).setFilled(false);
	}

	public void restrictPixel(int x, int y) {
		pixelMap.get(y).get(x).setRestricted(true);
	}
	
	public void unrestrictPixel(int x, int y) {
		pixelMap.get(y).get(x).setRestricted(false);
	}
	
	public boolean isPixelSetAt(int x, int y){
		return pixelMap.get(y).get(x).isFilled();
	}
	
	public boolean isPixelRestrictedAt(int x, int y){
		if (x<0||x>=width||y<0||y>=height){
			return true;
		}
		return pixelMap.get(y).get(x).isRestricted();
	}

	public void substract(BrickPlacement brickPlacement) {
		List<Coordinates> coords = brickPlacement.getCalculatedCoords();
		for (Coordinates coord: coords){
			removePixel(coord.getX(), coord.getY());
			restrictPixel(coord.getX(), coord.getY());
		}
	}
	
	public void fill(ACSBrickPlacement brickPlacement) {
		List<Coordinates> coords = brickPlacement.getCalculatedCoords();
		for (Coordinates coord: coords){
			fillPixel(coord.getX(), coord.getY());
			restrictPixel(coord.getX(), coord.getY());
		}
	}

	public boolean canBrickBePlaced(BrickPlacement verticle) {
		for (Coordinates filledCoord : verticle.getCalculatedCoords()) {
			if (isPixelRestrictedAt(filledCoord.getX(), filledCoord.getY())) {
				return false;
			}
		}
		return true;
	}

	public int getCoveragePoints(BrickPlacement verticle, int pointsForCovering, int pointsForMissing) {
		int coverage = 0;
		for (Coordinates filledCoord : verticle.getCalculatedCoords()) {
			coverage += isPixelSetAt(filledCoord.getX(), filledCoord.getY()) ? pointsForCovering : pointsForMissing;
		}
		return 100 * coverage / pointsForCovering / verticle.getCalculatedCoords().size();
	}
}
