package message;

public class Driver {
	public static void main(String[] args) {
		int times = 100;
		
		Message msg1 = new Message("Hello", times);	
		Message msg2 = new Message("Bienvenidos", times);
		
		msg1.print();
		msg2.print();
	}
}
