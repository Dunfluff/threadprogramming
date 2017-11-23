package Upg2;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Writer extends Thread {

	private CharacterBuffer cb;
	private JLabel lblTrans;
	private JTextArea listR;
	private StringBuilder sHolder;
	private StringBuilder sOut;
	private boolean sync;
	private boolean dead;

	/**
	 * Writer thread with sync if enabled or Async if not
	 * 
	 * @param cb
	 * @param lblTrans
	 * @param listR
	 * @param string
	 * @param b
	 */
	public Writer(CharacterBuffer cb, JLabel lblTrans, JTextArea listR, String string, boolean b) {
		this.cb = cb;
		this.lblTrans = lblTrans;
		this.listR = listR;
		sHolder = new StringBuilder(string);
		sOut = new StringBuilder();
		sync = b;
		dead = false;
	}

	/*
	 * Runs Writer thread with sync if enabled or Async if not
	 */
	public void run() {
		int i = 0;
		while (i < sHolder.length()) {
			if (!sync) {
				char temp = sHolder.charAt(i);
				System.out.println(temp);
				cb.set(temp);
				sOut.append(temp);
				listR.append("Writing: " + temp + System.lineSeparator());
				lblTrans.setText(sOut.toString());
			} else {
				char temp = sHolder.charAt(i);
				while (!cb.isEmpty()) {
					listR.append("Data exists. Writer waits" + System.lineSeparator());
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				try {
					cb.put(temp);
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
			i++;
		}
	}

}
