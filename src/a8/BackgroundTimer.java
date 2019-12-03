package a8;

import javax.swing.SwingUtilities;

public class BackgroundTimer extends Thread {
	private boolean done;
	private ConwayModel cc;
	private int delay;
	
	public BackgroundTimer(ConwayModel cc, int delay) {
		this.cc = cc;
		done = false;
		this.delay = delay;
	}

	public void halt() {
		done = true;
	}
	
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	public void run() {		
		while (!done) {
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
			}
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					cc.step();
				}
			});
		}
	}
}
