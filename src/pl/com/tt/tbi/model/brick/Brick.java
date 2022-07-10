package pl.com.tt.tbi.model.brick;

import java.awt.Color;
import java.util.List;

import pl.com.tt.tbi.model.BrickRotation;

public interface Brick {

	List<BrickRotation> getAllRotations();
	Color getColor();
	
}
