package messageThreadExtends;

public class MessageThread extends Thread {
	private String msg;
	private int times;

	public MessageThread(String msg, int times) {
		this.msg = msg;
		this.times = times;
	}

	/* We could have called print() in run */
	public void run() {
		for (int i = 0; i < times; i++) {
			System.out.println(msg);
		}
	}
}
