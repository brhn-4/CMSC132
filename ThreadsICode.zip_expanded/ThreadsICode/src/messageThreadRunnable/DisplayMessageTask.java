package messageThreadRunnable;

public class DisplayMessageTask implements Runnable {
	private String msg;
	private int times;

	public DisplayMessageTask(String msg, int times) {
		this.msg = msg;
		this.times = times;
	}

	public void run() {
		for (int i = 0; i < times; i++) {
			System.out.println(msg);
		}
	}
}
