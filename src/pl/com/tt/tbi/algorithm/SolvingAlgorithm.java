package pl.com.tt.tbi.algorithm;

import java.util.Map;

import pl.com.tt.tbi.algorithm.callback.Callback;
import pl.com.tt.tbi.model.BrickPlacement;
import pl.com.tt.tbi.model.brick.Brick;
import pl.com.tt.tbi.model.pixelmap.PixelMap;

public interface SolvingAlgorithm<C extends Callback> {

	Map<BrickPlacement, Brick> solve(PixelMap wantedBitmap, C callback);

}