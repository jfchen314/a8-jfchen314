package a8;

public class ConwayController implements ConwayObserver, ConwayViewListener {

	private ConwayModel model;
	private ConwayView view;
	
	public ConwayController(ConwayModel model, ConwayView view) {
		this.model = model;
		this.view = view;
		
		view.addConwayViewListener(this);
		model.addObserver(this);
	}
	
	@Override
	public void handleConwayViewEvent(ConwayViewEvent e) {
		if(e.isPauseEvent()) {
			
		} else if(e.isRandomEvent()) {
			model.randomize();
		} else if(e.isResetEvent()) {
			model.reset();
		} else if(e.isSquareEvent()) {
			SquareEvent s = (SquareEvent) e;
			model.toggleSpot(s.getX(), s.getY());
		} else if(e.isStepEvent()) {
			model.step();
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
