package pl.com.tt.tbi.algorithm.callback;

import pl.com.tt.tbi.algorithm.acs.Ant;

public interface ACSCallback extends Callback {

	void notifyAntFinished(Ant ant);
	void notifyCycleFinished(Ant bestAnt);
	
}
