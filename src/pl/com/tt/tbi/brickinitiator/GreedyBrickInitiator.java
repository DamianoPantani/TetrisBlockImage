package pl.com.tt.tbi.brickinitiator;

import java.util.ArrayList;
import java.util.List;

import pl.com.tt.tbi.model.brick.Brick;
import pl.com.tt.tbi.model.brick.IBrick;
import pl.com.tt.tbi.model.brick.JBrick;
import pl.com.tt.tbi.model.brick.LBrick;
import pl.com.tt.tbi.model.brick.OBrick;
import pl.com.tt.tbi.model.brick.SBrick;
import pl.com.tt.tbi.model.brick.TBrick;
import pl.com.tt.tbi.model.brick.ZBrick;
import pl.com.tt.tbi.settings.InputBricksSettings;

public class GreedyBrickInitiator implements BrickInitiator {

	@Override
	public List<Brick> initializeBricks() {
		List<Brick> fillingBricks = new ArrayList<>();
		for (int n = 0; n < InputBricksSettings.O_BRICKS_COUNT; n++) {
			fillingBricks.add(new OBrick());
		}
		for (int n = 0; n < InputBricksSettings.I_BRICKS_COUNT; n++) {
			fillingBricks.add(new IBrick());
		}
		for (int n = 0; n < InputBricksSettings.J_BRICKS_COUNT; n++) {
			fillingBricks.add(new JBrick());
		}
		for (int n = 0; n < InputBricksSettings.L_BRICKS_COUNT; n++) {
			fillingBricks.add(new LBrick());
		}
		for (int n = 0; n < InputBricksSettings.T_BRICKS_COUNT; n++) {
			fillingBricks.add(new TBrick());
		}
		for (int n = 0; n < InputBricksSettings.S_BRICKS_COUNT; n++) {
			fillingBricks.add(new SBrick());
		}
		for (int n = 0; n < InputBricksSettings.Z_BRICKS_COUNT; n++) {
			fillingBricks.add(new ZBrick());
		}
		
		return fillingBricks;
		
	}

}
