package a8;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ConwayGame {
	public static void main(String[] args) {
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Conway's Game o' Life");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_frame.setLayout(new BorderLayout());
		
		int size = -1;
		try {
			size = Integer.parseInt((String)JOptionPane.showInputDialog(
				main_frame,
                "Input Size (10 - 500)",
                "",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "10"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(main_frame,
			    "Invalid Input");
		}
		if(size < 10 || size > 501) {
			JOptionPane.showMessageDialog(main_frame, "Invalid Input (10 - 500)");
		} else {
			ConwayModel model = new ConwayModel(size);
			ConwayView view = new ConwayView(size, main_frame);
			ConwayController controller = new ConwayController(model, view);
			
			main_frame.setContentPane(view);

			main_frame.pack();
			main_frame.setVisible(true);	
		}
	}
}
