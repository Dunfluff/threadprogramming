package Upg2;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Reader extends Thread {

	private CharacterBuffer cb;
	private JLabel lblTrans;
	private JTextArea listR;
	private StringBuilder sOut;
	private boolean sync;
	private boolean dead;
	private int size;

	/**
	 * Reader thread with sync if enabled or Async if not
	 * 
	 * @param cb
	 * @param lblTrans
	 * @param listR
	 * @param b
	 * @param size
	 */
	public Reader(CharacterBuffer cb, JLabel lblTrans, JTextArea listR, boolean b, int size) {
		this.cb = cb;
		this.lblTrans = lblTrans;
		this.listR = listR;
		sync = b;
		dead = false;
		sOut = new StringBuilder();
		this.size = size;
	}

	/*
	 * Runs Reader thread with sync if enabled or Async if not
	 */
	public void run() {
		while (!dead) {
			if (!sync) {
				char temp = cb.get();
				sOut.append(temp);
				listR.append("Reading: " + temp + System.lineSeparator());
				lblTrans.setText(sOut.toString());
			} else {
				while (cb.isEmpty()) {
					listR.append("No data. Reader waits" + System.lineSeparator());
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				char temp = 0;
				try {
					temp = cb.poll();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sOut.append(temp);
				listR.append("Writing: " + temp + System.lineSeparator());
				lblTrans.setText(sOut.toString());
			}
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (sOut.length() == size) {
				kill();
			}
		}
	}

	public void kill() {
		dead = true;
	}

}
