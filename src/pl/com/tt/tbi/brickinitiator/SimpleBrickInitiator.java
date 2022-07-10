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

public class SimpleBrickInitiator implements BrickInitiator {

	@Override
	public List<Brick> initializeBricks() {

		List<Brick> fillingBricks = new ArrayList<>();
		List<Brick> o = new ArrayList<>();
		List<Brick> i = new ArrayList<>();
		List<Brick> j = new ArrayList<>();
		List<Brick> l = new ArrayList<>();
		List<Brick> t = new ArrayList<>();
		List<Brick> s = new ArrayList<>();
		List<Brick> z = new ArrayList<>();

		for (int n = 0; n < InputBricksSettings.O_BRICKS_COUNT; n++) {
			o.add(new OBrick());
		}
		for (int n = 0; n < InputBricksSettings.I_BRICKS_COUNT; n++) {
			i.add(new IBrick());
		}
		for (int n = 0; n < InputBricksSettings.J_BRICKS_COUNT; n++) {
			j.add(new JBrick());
		}
		for (int n = 0; n < InputBricksSettings.L_BRICKS_COUNT; n++) {
			l.add(new LBrick());
		}
		for (int n = 0; n < InputBricksSettings.T_BRICKS_COUNT; n++) {
			t.add(new TBrick());
		}
		for (int n = 0; n < InputBricksSettings.S_BRICKS_COUNT; n++) {
			s.add(new SBrick());
		}
		for (int n = 0; n < InputBricksSettings.Z_BRICKS_COUNT; n++) {
			z.add(new ZBrick());
		}
		
		while(!o.isEmpty() || !i.isEmpty() || !j.isEmpty() || !l.isEmpty() || !t.isEmpty() || !s.isEmpty() || !z.isEmpty()){
			if (!o.isEmpty()){
				fillingBricks.add(o.remove(0));
			}
			if (!i.isEmpty()){
				fillingBricks.add(i.remove(0));
			}
			if (!j.isEmpty()){
				fillingBricks.add(j.remove(0));
			}
			if (!l.isEmpty()){
				fillingBricks.add(l.remove(0));
			}
			if (!t.isEmpty()){
				fillingBricks.add(t.remove(0));
			}
			if (!s.isEmpty()){
				fillingBricks.add(s.remove(0));
			}
			if (!z.isEmpty()){
				fillingBricks.add(z.remove(0));
			}
		}
		
		return fillingBricks;
		
	}
	
}
