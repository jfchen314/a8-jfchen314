package a8;

public class ConwayController implements ConwayObserver, ConwayViewListener {

	private ConwayModel model;
	private ConwayView view;
	private BackgroundTimer bt;
	private int delay;
	
	public ConwayController(ConwayModel model, ConwayView view) {
		this.model = model;
		this.view = view;
				
		delay = 200;
		
		bt = new BackgroundTimer(model, delay);
		view.addConwayViewListener(this);
		model.addObserver(this);
	}
	
	@Override
	public void handleConwayViewEvent(ConwayViewEvent e) {
		if(e.isPauseEvent()) {
			bt.halt();
			try {
				bt.join();
			} catch (InterruptedException e1) {
			}
		} else if(e.isPlayEvent()) {
			bt = new BackgroundTimer(model, delay);
			bt.start();
		} else if(e.isRandomEvent()) {
			model.randomize();
		} else if(e.isResetEvent()) {
			model.reset();
		} else if(e.isSquareEvent()) {
			SquareEvent s = (SquareEvent) e;
			model.toggleSpot(s.getX(), s.getY());
		} else if(e.isStepEvent()) {
			model.step();
		} else if(e.isSettingsEvent()) {
			SettingsEvent s = (SettingsEvent) e;
			delay = s.getDelay();
			bt.setDelay(delay);
			model.setParams(s.getSLow(), s.getSHigh(), s.getBLow(), s.getBHigh(), s.getTorus());
		}
		
	}

	@Override
	public void update(boolean[][] map) {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				view.setSpot(i, j, map[i][j]);
			}
		}
	}

}
