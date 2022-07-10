package pl.com.tt.tbi.algorithm.greedy;

import java.util.List;

import pl.com.tt.tbi.model.BrickPlacement;
import pl.com.tt.tbi.model.BrickRotation;
import pl.com.tt.tbi.model.Coordinates;
import pl.com.tt.tbi.model.brick.Brick;
import pl.com.tt.tbi.model.pixelmap.PixelMap;

public class GreedyPlacementChooser {

	private final int POINTS_FOR_FILLING = 2;
	private final int POINTS_FOR_UNFILLING = -1;
	
	private PixelMap wantedBitmap;

	public GreedyPlacementChooser(PixelMap wantedBitmap) {
		this.wantedBitmap = wantedBitmap;
	}

	public BrickPlacement getBestPlacement(Brick brick, Integer minimumCoverPertenage) {
		for (int y = 0; y < wantedBitmap.getHeight(); y++) {
			for (int x = 0; x < wantedBitmap.getWidth(); x++) {
				for (BrickRotation rotation: brick.getAllRotations()){
					int placementPoints = calculatePoint(rotation, x, y);
					if (placementPoints >= minimumCoverPertenage){
						return new BrickPlacement(new Coordinates(x,y), rotation);
					}
				}
			}
		}
		return null;
	}

	private int calculatePoint(BrickRotation rotation, int x, int y) {
		List<Coordinates> filledCoords = rotation.getAllFilledPoints();
		int points = 0;
		for (Coordinates filledCoord: filledCoords){
			int bitmapX = filledCoord.getX()+x;
			int bitmapY = filledCoord.getY()+y;
			if (wantedBitmap.isPixelRestrictedAt(bitmapX, bitmapY)){
				return -100;
			}
			points += wantedBitmap.isPixelSetAt(bitmapX, bitmapY) ? POINTS_FOR_FILLING : POINTS_FOR_UNFILLING;
		}
		return 100*points/POINTS_FOR_FILLING/filledCoords.size();
	}

}
