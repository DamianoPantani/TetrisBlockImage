package pl.com.tt.tbi.model.brick;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import pl.com.tt.tbi.model.BrickRotation;

public class EmptyBrick implements Brick {

	private List<BrickRotation> ROTATIONS = Arrays.asList(new BrickRotation());
	
	@Override
	public List<BrickRotation> getAllRotations() {
		return ROTATIONS;
	}

	@Override
	public Color getColor() {
		return Color.white;
	}

}
