package messageThreadExtends;

public class Driver {
	public static void main(String[] args) {
		int times = 1000;

		MessageThread msg1 = new MessageThread("Hello", times);
		MessageThread msg2 = new MessageThread("Bienvenidos", times);

		msg1.start();
		msg2.start();

		System.out.println("Driver done");
	}
}
