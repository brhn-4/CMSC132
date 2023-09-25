import java.util.Scanner;

public class Scanner1 {
	/**
	 * Shows basic use of the scanner
	 */
	public static void main(String[] args) {
		Scanner keyboardInput = new Scanner(System.in);
		int first, second;

		System.out.print("Type your two favorite integers: ");
		first = keyboardInput.nextInt();
		second = keyboardInput.nextInt();

		System.out.println("The first number you typed was " + first);
		System.out.println("The second number you typed was " + second);

		int sum = first + second;
		int product = first * second;
		System.out.println("Their sum is " + sum);
		System.out.println("Their product is " + product);

		keyboardInput.close();
	}
}
