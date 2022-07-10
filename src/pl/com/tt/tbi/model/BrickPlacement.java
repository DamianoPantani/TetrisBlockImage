package pl.com.tt.tbi.model;

import java.util.ArrayList;
import java.util.List;

public class BrickPlacement {

	private Coordinates coords;
	private BrickRotation rotation;
	
	public BrickPlacement(Coordinates coords, BrickRotation rotation){
		this.coords = coords;
		this.rotation = rotation;
	}

	public Coordinates getCoords() {
		return coords;
	}

	public void setCoords(Coordinates coords) {
		this.coords = coords;
	}

	public BrickRotation getRotation() {
		return rotation;
	}

	public void setRotation(BrickRotation rotation) {
		this.rotation = rotation;
	}
	
	public List<Coordinates> getCalculatedCoords(){
		List<Coordinates> fillingPixels = rotation.getAllFilledPoints();
		int posX = coords.getX();
		int posY = coords.getY();
		
		List<Coordinates> coords = new ArrayList<>();
		for (Coordinates fillingPixel: fillingPixels){
			coords.add(new Coordinates(fillingPixel.getX()+posX, fillingPixel.getY()+posY));
		}
		return coords;
		
	}
	
	@Override
	public String toString(){
		return coords.toString();
	}
	
}
