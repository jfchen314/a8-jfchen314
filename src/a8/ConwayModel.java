package a8;

import java.util.ArrayList;
import java.util.List;

public class ConwayModel {
	private boolean[][] map;
	private List<ConwayObserver> observers;
	
	public ConwayModel(int size) {
		observers = new ArrayList<ConwayObserver>();
		
		map = new boolean[size][size];
		reset();
	}
	
	public boolean[][] getMap() {
		return map;
	}
	
	public void toggleSpot(int x, int y) {
		map[x][y] = !map[x][y];
		notifyObservers();
	}
	
	public void step() {
		boolean[][] newmap = new boolean[map.length][map.length];
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				newmap[i][j] = checkLife(i, j);
			}
		}
		map = newmap;
		notifyObservers();
	}
	
	private boolean checkLife(int x, int y) {
		int count = 0;
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++){
				if(x + i < 0 || x + i >= map.length ||
				   y + j < 0 || y + j >= map.length) continue;
				count += (map[x + i][y + j]) ? 1 : 0;
			}
		}
		return (count == 3 || (count == 4 && map[x][y]));
	}
	
	public void reset() {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				map[i][j] = false;
			}
		}
		notifyObservers();
	}
	
	public void randomize() {
		reset();
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				map[i][j] = Math.random() > 0.5;
			}
		}
		notifyObservers();
	}
	
	public void addObserver(ConwayObserver o) {
		observers.add(o);
	}
	
	public void removeObserver(ConwayObserver o) {
		observers.remove(o);
	}
	
	private void notifyObservers() {
		for (ConwayObserver o : observers) {
			o.update(map);
		}
	}
}
