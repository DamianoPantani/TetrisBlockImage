package pl.com.tt.tbi.algorithm.acs;

import java.util.List;
import java.util.Map;

import pl.com.tt.tbi.algorithm.SolvingAlgorithm;
import pl.com.tt.tbi.algorithm.acs.params.ChineseSignACSParams;
import pl.com.tt.tbi.algorithm.acs.params.TrollfaceACSParams;
import pl.com.tt.tbi.algorithm.callback.ACSCallback;
import pl.com.tt.tbi.brickinitiator.SimpleBrickInitiator;
import pl.com.tt.tbi.model.BrickPlacement;
import pl.com.tt.tbi.model.brick.Brick;
import pl.com.tt.tbi.model.cloner.Cloner;
import pl.com.tt.tbi.model.pixelmap.PixelMap;

public class ACSSolvingAlgorithm implements SolvingAlgorithm<ACSCallback> {

	
	private Ant bestAnt;
	private boolean interrupted;
	private static final TrollfaceACSParams PARAMS = new TrollfaceACSParams();
	public void solveInAnotherThread(PixelMap wantedBitmap, ACSCallback callback) {
		Thread newThread = new Thread(new Runnable(){
			@Override
			public void run() {
				Map<BrickPlacement, Brick> results = solve(wantedBitmap, callback);
				callback.notifyAlgorithmFinished(results);
			}
		});
		newThread.setPriority(Thread.MAX_PRIORITY);
		newThread.start();
	}

	public void interrupt() {
		this.interrupted = true;
	}
	
	@Override
	public Map<BrickPlacement, Brick> solve(PixelMap wantedBitmap, ACSCallback callback){
		List<ACSBrickPlacement> globalPraph = ACSBrickPlacement.toVerticles(new SimpleBrickInitiator().initializeBricks(), wantedBitmap, PARAMS.INITIAL_PHEROMONE);

		for (int cycleNumber = 0; cycleNumber < PARAMS.CYCLES_NUMBER; cycleNumber++) {
			for (int antNumber = 0; antNumber < PARAMS.ANTS_IN_CYCLE; antNumber++) {
				Ant ant = new Ant(Cloner.clone(wantedBitmap));
				do {
					ACSBrickPlacement nextVertex = getMovementDecision(globalPraph, ant);
					if(nextVertex == null){
						break;
					}
					nextVertex.reducePheromone(PARAMS.PHEROMONE_EATING);
					ant.goTo(nextVertex, nextVertex.getHeuristicValue());
				} while (true);
				callback.notifyAntFinished(ant);
				bestAnt = getBetter(bestAnt, ant);
				if(interrupted){break;}
			}
			callback.notifyCycleFinished(bestAnt);
			evaporatePheromoneAtBestRoute();
			addPheromoneAtBestRoute();
			if(interrupted){break;}
		}
		return bestAnt.toBrickMap();
	}

	public ACSBrickPlacement getMovementDecision(List<ACSBrickPlacement> verticles, Ant ant) {
		if ((Math.random() * 100 + 0.01) <= PARAMS.RANDOMNESS) {
			return verticles.get((int) (Math.random() * verticles.size()));
		}

		ACSBrickPlacement candidate = null;
		double maxPoints = Double.MIN_VALUE;

		for (ACSBrickPlacement verticle : verticles) {
			if (!ant.isVisited(verticle) && ant.getCurrentPixelMap().canBrickBePlaced(verticle)) {
				double probabl =
						Math.pow(verticle.getPheromoneAmmount(), PARAMS.ALPHA) *
						Math.pow(verticle.getHeuristicValue(), PARAMS.BETA);
				if (probabl > maxPoints) {
					maxPoints = probabl;
					candidate = verticle;
				}
			}
		}
		return candidate;
	}

	private Ant getBetter(Ant ant1, Ant ant2) {
		return ant1 == null ? ant2 :
			   ant2 == null ? ant1 :
			   ant2.compareTo(ant1) == 1 ? ant2 : ant1;
	}

	public void evaporatePheromoneAtBestRoute() {
		List<ACSBrickPlacement> route = bestAnt.getRoute();
		for (ACSBrickPlacement placement : route) {
			placement.reducePheromone(PARAMS.EVAPORATION);
		}
	}

	public void addPheromoneAtBestRoute() {
		double portion = PARAMS.Q * bestAnt.getSolutionValue();
		List<ACSBrickPlacement> route = bestAnt.getRoute();
		for (ACSBrickPlacement placement : route) {
			placement.addPheromone(portion);
		}
	}

}
