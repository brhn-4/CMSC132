package message;

public class Message {
	private String msg;
	private int times;

	public Message(String msg, int times) {
		this.msg = msg;
		this.times = times;
	}

	public void print() {
		for (int i = 0; i < times; i++) {
			System.out.println(msg);
		}
	}
}
