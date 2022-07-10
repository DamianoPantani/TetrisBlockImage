package pl.com.tt.tbi.algorithm.acs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.com.tt.tbi.model.BrickPlacement;
import pl.com.tt.tbi.model.brick.Brick;
import pl.com.tt.tbi.model.pixelmap.PixelMap;

public class Ant implements Comparable<Ant> {

	private double solutionValue = 0;
	List<ACSBrickPlacement> route = new ArrayList<>();
	private PixelMap antBitmap;
	private List<Brick> usedBricks = new ArrayList<>();
	
	public Ant(PixelMap bitmap) {
		this.antBitmap = bitmap;
	}

	public double getSolutionValue(){
		return this.solutionValue;
	}
	
	public ACSBrickPlacement getCurrentPosition(){
		return route.get(route.size()-1);
	}
	
	public ACSBrickPlacement getPreviousPosition() {
		return route.get(route.size()-2);
	}
	
	public void goTo(ACSBrickPlacement verticle, double movementValue){
		route.add(verticle);
		solutionValue += movementValue;	
		antBitmap.fill(verticle);
		usedBricks.add(verticle.getBrickReference());
	}
	
	public boolean isVisited(ACSBrickPlacement verticle){	
		return usedBricks.contains(verticle.getBrickReference()) || route.contains(verticle);
	}
	
	public int getVisitedCount(){
		return route.size();
	}
	
	public List<ACSBrickPlacement> getRoute(){
		return route;
	}

	public Map<BrickPlacement, Brick> toBrickMap() {
		Map<BrickPlacement, Brick> result = new HashMap<>();
		for(ACSBrickPlacement verticle: route){
			result.put(verticle, verticle.getBrickReference());
		}
		return result;
	}
	
	public ACSBrickPlacement getInitialVerticle() {
		return route.get(0);
	}

	public PixelMap getCurrentPixelMap() {
		return antBitmap;
	}

	@Override
	public int compareTo(Ant other) {
		if (this.solutionValue < other.solutionValue) return -1;
		if (this.solutionValue > other.solutionValue) return 1;
		return 0;
	}
	
}
