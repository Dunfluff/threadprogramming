package Upg2;

import java.util.concurrent.Semaphore;

public class CharacterBuffer {

	private Character buffer;
	private Semaphore mutex;

	public CharacterBuffer() {
		buffer = null;
		mutex = new Semaphore(1);
	}

	/**
	 * puts char
	 * 
	 * @param in
	 *            char to put
	 * @throws InterruptedException
	 */
	public void put(char in) throws InterruptedException {
		mutex.acquire();
		buffer = in;
		mutex.release();
	}

	/**
	 * polls char and removes it
	 * 
	 * @return loaded char
	 * @throws InterruptedException
	 */
	public char poll() throws InterruptedException {
		mutex.acquire();
		char temp = buffer;
		buffer = null;
		mutex.release();
		return temp;
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
