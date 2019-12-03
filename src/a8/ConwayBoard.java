package a8;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class ConwayBoard extends JPanel implements MouseListener {
	private static final int PANELSIZE = 800;
	private boolean[][] map;
	private List<BoardListener> listeners;
	
	public ConwayBoard(int size) {
		map = new boolean[size][size];
		
		listeners = new ArrayList<>();
		
		setBackground(Color.BLACK);
		
		Dimension preferred_size = new Dimension(PANELSIZE, PANELSIZE);
		
		setPreferredSize(preferred_size);
		
		addMouseListener(this);
	}
	
	public void set(int x, int y, boolean state) {
		map[x][y] = state;
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int iter = PANELSIZE / map.length;
		
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.setColor(Color.WHITE);
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				if(map[i][j]) g2d.fillRect(iter * i, iter * j, 
						                   iter, iter);
			}
		}
	}

	public void addListener(BoardListener l) {
		listeners.add(l);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int iter = PANELSIZE / map.length;
		for(BoardListener l : listeners) {
			l.boardClicked(e.getX() / iter, e.getY() / iter);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {		
	}
}
