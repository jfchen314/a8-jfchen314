package a8;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ConwayView extends JPanel implements ActionListener, BoardListener {

	private ConwayBoard board;
	private List<ConwayViewListener> listeners;
	private JPanel button_panel;
	private int size;
	private JFrame main_frame;
	
	public ConwayView(int size, JFrame main_frame) {
		this.size = size;
		this.main_frame = main_frame;
		
		setLayout(new BorderLayout());
		
		board = new ConwayBoard(size);
		board.addListener(this);
		
		button_panel = new JPanel();
		button_panel.setLayout(new GridLayout(1,3));
		
		listeners = new ArrayList<>();
		
		
		button_panel.add(new JButton("Settings"));
		button_panel.add(new JButton("Clear"));
		button_panel.add(new JButton("Pause"));
		button_panel.add(new JButton("Random"));
		button_panel.add(new JButton("Step"));
		
		
		for(Component c: button_panel.getComponents()) {
			JButton b = (JButton) c;
			b.addActionListener(this);
		}
		
		add(board, BorderLayout.CENTER);
		add(button_panel, BorderLayout.SOUTH);
		
		this.setFocusable(true);
		this.grabFocus();
	}
	
	public void setSpot(int x, int y, boolean state) {
		board.set(x, y, state);
	}
	
	public void fireEvent(ConwayViewEvent e) {
		for (ConwayViewListener l : listeners) {
			l.handleConwayViewEvent(e);
		}
	}

	public void addConwayViewListener(ConwayViewListener l) {
		listeners.add(l);
	}
	
	public void removeConwayViewListener(ConwayViewListener l) {
		listeners.remove(l);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		switch(button.getText()) {
		case "Clear":
			System.out.println("Clear");
			fireEvent(new ResetEvent());
			break;
		case "Pause":
			System.out.println("Pause");
			fireEvent(new PauseEvent());
			break;
		case "Random":
			System.out.println("Random");
			fireEvent(new RandomEvent());
			break;
		case "Step":
			System.out.println("Step");
			fireEvent(new StepEvent());
			break;
		case "Settings":
			System.out.println("Settings");
			try {
				fireEvent(new SettingsEvent(
						Integer.parseInt((String)JOptionPane.showInputDialog(main_frame, "Survive Low", "", JOptionPane.PLAIN_MESSAGE,null,null,"2")),
						Integer.parseInt((String)JOptionPane.showInputDialog(main_frame, "Survive High", "", JOptionPane.PLAIN_MESSAGE,null,null,"3")),
						Integer.parseInt((String)JOptionPane.showInputDialog(main_frame, "Birth Low", "", JOptionPane.PLAIN_MESSAGE,null,null,"3")),
						Integer.parseInt((String)JOptionPane.showInputDialog(main_frame, "Birth High", "", JOptionPane.PLAIN_MESSAGE,null,null,"3")),
						Boolean.parseBoolean((String)JOptionPane.showInputDialog(main_frame, "Torus? (true/false)", "", JOptionPane.PLAIN_MESSAGE,null,null,"false"))
					));

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(main_frame,
				    "Invalid Parameters");
			}
			break;
		}
	}

	@Override
	public void boardClicked(int x, int y) {
		System.out.println("Square");
		fireEvent(new SquareEvent(x, y));
	}
}
