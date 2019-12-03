package a8;

import java.util.ArrayList;
import java.util.List;

public class ConwayModel {
	private boolean[][] map;
	private List<ConwayObserver> observers;
	private int survivelow, survivehigh, birthlow, birthhigh;
	private boolean torus;
	
	public ConwayModel(int size) {
		observers = new ArrayList<ConwayObserver>();
		survivelow = 2;
		survivehigh = 3;
		birthlow = 3;
		birthhigh = 3;
		
		torus = false;
		
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
	
	public void setParams(int survivelow, int survivehigh, int birthlow, int birthhigh, boolean torus) {
		this.survivelow = survivelow;
		this.survivehigh = survivehigh;
		this.birthlow = birthlow;
		this.birthhigh = birthhigh;
		
		this.torus = torus;
	}
	
	private boolean checkLife(int x, int y) {
		int count = 0;
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++){
				if(i == 0 && j == 0) continue;
				int newx = x + i;
				int newy = y + j;
				if(torus) {
					if(newx == map.length) newx = 0;
					if(newy == map.length) newy = 0;
					if(newx == -1) newx = map.length - 1;
					if(newy == -1) newy = map.length - 1;
				} else {
					if(newx < 0 || newx >= map.length ||
					   newy < 0 || newy >= map.length) continue;
				}
				
				count += (map[newx][newy]) ? 1 : 0;
			}
		}
		if(map[x][y]) {
			return count >= survivelow && count <= survivehigh;
		}
		return count >= birthlow && count <= birthhigh;
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
