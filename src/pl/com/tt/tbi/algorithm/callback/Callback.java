package pl.com.tt.tbi.algorithm.callback;

import java.util.Map;

import pl.com.tt.tbi.model.BrickPlacement;
import pl.com.tt.tbi.model.brick.Brick;

public interface Callback {

	void notifyAlgorithmFinished(Map<BrickPlacement, Brick> results);
	
}
