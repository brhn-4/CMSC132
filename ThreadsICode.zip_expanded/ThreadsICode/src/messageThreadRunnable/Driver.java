package messageThreadRunnable;

public class Driver {
	public static void main(String[] args) {
		int times = 1000;

		DisplayMessageTask msg1Task = new DisplayMessageTask("Hello", times);
		Thread msg1Tread = new Thread(msg1Task);

		DisplayMessageTask msg2Task = new DisplayMessageTask("Bienvenidos", times);
		Thread msg2Thread = new Thread(msg2Task);

		/* Using lambda */
		Thread msg3Thread = new Thread(() -> {
			for (int i = 1; i <= 100; i++)
				System.out.println("Hola");
		});
		
		msg1Tread.start();
		msg2Thread.start();
		msg3Thread.start();

		System.out.println("Driver done");
	}
}
