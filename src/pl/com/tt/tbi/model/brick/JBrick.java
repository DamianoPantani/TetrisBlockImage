package pl.com.tt.tbi.model.brick;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import pl.com.tt.tbi.model.BrickRotation;
import pl.com.tt.tbi.model.Coordinates;

public class JBrick implements Brick {

	private List<BrickRotation> ROTATIONS = Arrays.asList(
								new BrickRotation(
										new Coordinates(0,1),
										new Coordinates(1,1),
										new Coordinates(2,1),
										new Coordinates(2,0)),
								new BrickRotation(
										new Coordinates(0,0),
										new Coordinates(1,0),
										new Coordinates(1,1),
										new Coordinates(1,2)),
								new BrickRotation(
										new Coordinates(0,1),
										new Coordinates(0,0),
										new Coordinates(1,0),
										new Coordinates(2,0)),
								new BrickRotation(
										new Coordinates(1,2),
										new Coordinates(0,2),
										new Coordinates(0,1),
										new Coordinates(0,0)));

	@Override
	public List<BrickRotation> getAllRotations() {
		return ROTATIONS;
	}

	@Override
	public Color getColor() {
		return Color.pink;
	}

}
