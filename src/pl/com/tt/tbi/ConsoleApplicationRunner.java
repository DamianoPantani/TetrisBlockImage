package pl.com.tt.tbi;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.com.tt.tbi.algorithm.callback.Callback;
import pl.com.tt.tbi.algorithm.greedy.GreedyAlgorithm;
import pl.com.tt.tbi.model.BrickPlacement;
import pl.com.tt.tbi.model.brick.Brick;
import pl.com.tt.tbi.model.pixelmap.TrollFacePixelMap;

public class ConsoleApplicationRunner {

	private static final Logger logger = LogManager.getLogger(ConsoleApplicationRunner.class);
	
	public static void main(String... args) {

		logger.debug("Starting algorithm");
		new GreedyAlgorithm().solve(new TrollFacePixelMap(), new Callback() {
			@Override
			public void notifyAlgorithmFinished(Map<BrickPlacement, Brick> results) {
				logger.debug("Algorithm finished");
				for (BrickPlacement placement: results.keySet()){
					logger.info("Place "+placement.getRotation()+" at "+placement.getCoords());
				}
			}


		});

	}


}
