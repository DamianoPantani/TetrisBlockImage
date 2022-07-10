package pl.com.tt.tbi.algorithm.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.com.tt.tbi.algorithm.SolvingAlgorithm;
import pl.com.tt.tbi.algorithm.callback.Callback;
import pl.com.tt.tbi.brickinitiator.GreedyBrickInitiator;
import pl.com.tt.tbi.model.BrickPlacement;
import pl.com.tt.tbi.model.brick.Brick;
import pl.com.tt.tbi.model.pixelmap.PixelMap;

public class GreedyAlgorithm implements SolvingAlgorithm<Callback> {

	private List<Brick> allBricks = new GreedyBrickInitiator().initializeBricks();
	private List<Brick> usedBricks = new ArrayList<>();
	private List<Integer> coverPertenageTresholds = Arrays.asList(100, 80, 60, 50, 30);

	@Override
	public Map<BrickPlacement, Brick> solve(PixelMap wantedBitmap, Callback callback){
		Map<BrickPlacement, Brick> resultingBitmap = new HashMap<>();
		GreedyPlacementChooser placementChooser = new GreedyPlacementChooser(wantedBitmap);
		
		for (Integer minimumCoverPertenage : coverPertenageTresholds) {
			for (Brick brick : allBricks) {
				if (usedBricks.contains(brick)){
					continue;
				}
				BrickPlacement bestPlacement = placementChooser.getBestPlacement(brick, minimumCoverPertenage);
				if (bestPlacement != null){
					resultingBitmap.put(bestPlacement, brick);
					usedBricks.add(brick);
					wantedBitmap.substract(bestPlacement);
				}
			}
		}
		return resultingBitmap;
	}
	
}
