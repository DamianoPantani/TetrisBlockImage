package pl.com.tt.tbi.algorithm.acs;

import java.util.ArrayList;
import java.util.List;

import pl.com.tt.tbi.algorithm.acs.params.ACSParams;
import pl.com.tt.tbi.model.BrickPlacement;
import pl.com.tt.tbi.model.BrickRotation;
import pl.com.tt.tbi.model.Coordinates;
import pl.com.tt.tbi.model.brick.Brick;
import pl.com.tt.tbi.model.pixelmap.PixelMap;

public class ACSBrickPlacement extends BrickPlacement {

	private int heuristicValue;
	private double pheromoneAmmount;
	private Brick brickReference;

	public static List<ACSBrickPlacement> toVerticles(List<Brick> allBricks, PixelMap bitmap, double initialPheromoneAmmount) {
		List<ACSBrickPlacement> verticles = new ArrayList<>();
		for (int x = 0; x < bitmap.getWidth(); x++) {
			for (int y = 0; y < bitmap.getHeight(); y++) {
				for (Brick brick : allBricks) {
					for (BrickRotation rotation : brick.getAllRotations()) {
						ACSBrickPlacement verticle = new ACSBrickPlacement(new Coordinates(x, y), rotation);
						if(bitmap.canBrickBePlaced(verticle)){
							verticle.brickReference = brick;
							verticle.pheromoneAmmount = initialPheromoneAmmount;
							verticle.heuristicValue = bitmap.getCoveragePoints(verticle, ACSParams.POINTS_FOR_FILLING, ACSParams.POINTS_FOR_UNFILLING);
							verticles.add(verticle);
						}
					}

				}
			}
		}
		return verticles;
	}

	public ACSBrickPlacement(Coordinates coords, BrickRotation rotation) {
		super(coords, rotation);
	}

	public void reducePheromone(double pheromoneEating) {
		pheromoneAmmount = pheromoneAmmount * (100 - pheromoneEating) / 100;
	}

	public void addPheromone(double portion) {
		pheromoneAmmount += portion;
	}

	public double getPheromoneAmmount() {
		return pheromoneAmmount;
	}

	public Brick getBrickReference() {
		return brickReference;
	}
	
	public int getHeuristicValue() {
		return heuristicValue;
	}
	
	@Override
	public String toString() {
		return brickReference.getClass().getSimpleName() + " at " + getCoords();
	}

}
