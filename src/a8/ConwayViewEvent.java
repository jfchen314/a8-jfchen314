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
	
	public boolean isStepEvent() {
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

class StepEvent extends ConwayViewEvent {
	public boolean isStepEvent() {
		return true;
	}
}
