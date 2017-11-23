package upg1;

public class moveTextThread extends Thread {

	Controller cont;
	boolean kill;
	
	/**
	 * Thread for moving text
	 * @param cont Controller containing mov methods
	 */
	public moveTextThread(Controller cont) {
		this.cont = cont;
		kill = false;
	}
	
	/* 
	 * Runs controller move text method and then sleeps for 200 milliseconds
	 */
	@Override
	public void run() {
		while (cont.isAlive() && !kill) {
			cont.movText();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * kills thread.
	 */
	public void kill() {
		kill = true;
	}
}