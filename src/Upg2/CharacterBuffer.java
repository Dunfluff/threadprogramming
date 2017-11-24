package Upg2;

import java.util.concurrent.Semaphore;

public class CharacterBuffer {

	private Character buffer;
	private boolean hasChar;

	public CharacterBuffer() {
		buffer = null;
		hasChar = false;
	}

	/**
	 * puts char including mutex
	 * 
	 * @param in
	 *            char to put
	 * @throws InterruptedException
	 */
	public synchronized boolean put(char in) {
		if (hasChar) {
			return false;
		}
		buffer = in;
		hasChar = true;
		return true;
	}

	/**
	 * polls char and removes it including mutex
	 * 
	 * @return loaded char
	 * @throws InterruptedException
	 */
	public synchronized Character poll() {
		if (hasChar) {
			hasChar = false;
			return buffer;
		}
		return null;
	}

	/**
	 * Sets char
	 * 
	 * @param in
	 *            char to set
	 */
	public void set(char in) {
		buffer = in;
	}

	/**
	 * Returns and does NOT remove char
	 * 
	 * @return saved char
	 */
	public char get() {
		return buffer;
	}

	/**
	 * @return if buffer is empty
	 */
	public boolean isEmpty() {
		return buffer == null;
	}
}
