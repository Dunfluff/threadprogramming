package upg1;


public class TThreadGo extends Thread {
	private long t, diff;

	private Controller man;

	private boolean kill;

	/**
	 * Thread to update clock time every second.
	 * @param man Controller containing clock methods
	 */
	public TThreadGo(Controller man) {
		this.man = man;
		kill = false;
	}
	/**
	 * Kills thread
	 */
	public void kill() {
		kill = true;
	}
	/* 
	 * Updates time in Clock, safe from prolonged sleeps through thread locks
	 */
	@Override
	public void run() {
		t = System.currentTimeMillis();
		while (man.isAlive()&& !kill) {
			man.tick();
			t += 1000;
			diff = t - System.currentTimeMillis();
			if (diff > 0) {
				try {
					Thread.sleep(diff);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}