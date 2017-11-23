package Upg2;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Checker extends Thread {

	private JLabel status;
	private JPanel result;
	private Reader reader;
	private JButton clear;
	private JButton run;
	private JLabel transmitted;
	private JLabel received;

	/**
	 * Checks statuses for Reader thread and checks results at end.
	 * 
	 * @param lblStatus
	 * @param pnlRes
	 * @param red
	 * @param btnClear
	 * @param btnRun
	 * @param lblTrans
	 * @param lblRec
	 */
	public Checker(JLabel lblStatus, JPanel pnlRes, Reader red, JButton btnClear, JButton btnRun, JLabel lblTrans,
			JLabel lblRec) {
		status = lblStatus;
		result = pnlRes;
		reader = red;
		clear = btnClear;
		run = btnRun;
		transmitted = lblTrans;
		received = lblRec;
	}

	public void run() {
		status.setText("Running");
		result.setBackground(Color.RED);
		clear.setEnabled(false);
		while (reader.isAlive()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (transmitted.getText().equals(received.getText())) {
			result.setBackground(Color.GREEN);
			status.setText("SUCCESS");
		} else {
			result.setBackground(Color.RED);
			status.setText("FAIL");
		}
		clear.setEnabled(true);
	}
}
