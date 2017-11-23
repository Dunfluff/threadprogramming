package upg1;

import java.sql.Date;
import java.util.Random;

import javax.swing.JLabel;

public class Controller {
	private moveTextThread mtThread;
	private JLabel movText;
	private boolean isAlive;
	private boolean movStart;
	private Random rand;
	private JLabel clock;
	private boolean timeStart;
	private TThreadGo timeThread;

	/**
	 * 
	 */
	public Controller() {
		isAlive = true;
		movStart = false;
		timeStart = false;
		rand = new Random();
	}

	/**
	 * Starts Moving Text Thread if it is not currently running.
	 * @param movText = text to be moved
	 */
	public void displayStart(JLabel movText) {

		if (!movStart) {
			movStart = true;
			mtThread = new moveTextThread(this);
			this.movText = movText;
			mtThread.start();
		}
	}

	/**
	 * Stops Moving Text Thread
	 */
	public void displayStop() {
		if (movStart) {
			mtThread.kill();
			movStart = false;
		}
	}
	

	/**
	 * Starts Time Thread if it is not currently running.
	 * @param clock = text that needs to be changed
	 */
	public void timeThreadStart(JLabel clock) {
		this.clock = clock;
		if (!timeStart) {
			timeStart = true;
			timeThread = new TThreadGo(this);
			timeThread.start();
		}

	}

	/**
	 * Stops Time Thread
	 */
	public void timeThreadStop() {
		if (timeStart) {
			timeThread.kill();
			timeStart = false;
		}

	}

	/**
	 * @return if controller is alive
	 */
	public synchronized boolean isAlive() {
		return isAlive;
	}

	/**
	 * Kills all threads
	 */
	public synchronized void kill() {
		isAlive = false;
	}

	/**
	 * Creates new x and y coordinates and moves JLabel provided from thread start
	 */
	public synchronized void movText() {
		int x = rand.nextInt(160) + 10;
		int y = rand.nextInt(169) + 19;
		movText.setBounds(x, y, 50, 50);
	}

	/**
	 * Updates text in clock JLabel provided from tread start, updates to current time.
	 */
	public synchronized void tick() {
		clock.setText(new Date(System.currentTimeMillis()).toGMTString());
	}

}
