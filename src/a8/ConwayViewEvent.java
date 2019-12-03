package a8;

abstract public class ConwayViewEvent {
	public boolean isSquareEvent() {
		return false;
	}
	
	public boolean isResetEvent() {
		return false;
	}
	
	public boolean isRandomEvent() {
		return false;
	}
	
	public boolean isPauseEvent() {
		return false;
	}
	
	public boolean isPlayEvent() {
		return false;
	}
	
	public boolean isStepEvent() {
		return false;
	}
	public boolean isSettingsEvent() {
		return false;
	}
}

class SquareEvent extends ConwayViewEvent {
	private int x, y;
	public SquareEvent(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isSquareEvent() {
		return true;
	}
}

class ResetEvent extends ConwayViewEvent {
	public boolean isResetEvent() {
		return true;
	}
}

class RandomEvent extends ConwayViewEvent {
	public boolean isRandomEvent() {
		return true;
	}
}

class PauseEvent extends ConwayViewEvent {
	public boolean isPauseEvent() {
		return true;
	}
}

class PlayEvent extends ConwayViewEvent {
	public boolean isPlayEvent() {
		return true;
	}
}

class StepEvent extends ConwayViewEvent {
	public boolean isStepEvent() {
		return true;
	}
}

class SettingsEvent extends ConwayViewEvent {
	private int delay, survivelow, survivehigh, birthlow, birthhigh;
	private boolean torus;
	public SettingsEvent(int delay, int survivelow, int survivehigh, int birthlow, int birthhigh, boolean torus) {
		if(delay < 0) throw new RuntimeException("Bad Delay");
		if(survivelow > survivehigh || birthlow > birthhigh ||
		   birthhigh > 8 || survivehigh > 8 ||
		   birthlow < 0 || survivelow < 0) throw new RuntimeException("Bad Params");
		this.delay = delay;
		this.survivelow = survivelow;
		this.survivehigh = survivehigh;
		this.birthlow = birthlow;
		this.birthhigh = birthhigh;
		this.torus = torus;
	}
	public int getDelay() {
		return delay;
	}
	public int getSLow() {
		return survivelow;
	}
	public int getSHigh() {
		return survivehigh;
	}
	public int getBLow() {
		return birthlow;
	}
	public int getBHigh() {
		return birthhigh;
	}
	public boolean getTorus() {
		return torus;
	}
	
	public boolean isSettingsEvent() {
		return true;
	}
}
