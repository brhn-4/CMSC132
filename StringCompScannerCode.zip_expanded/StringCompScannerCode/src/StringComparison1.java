
public class StringComparison1 {
	public static void main(String[] args) {
		String katniss = "Katniss";
		String peeta = "Peeta";
		String katniss2 = "Katniss";

		System.out.println(katniss.compareTo(peeta));
		System.out.println(peeta.compareTo(katniss));
		System.out.println(katniss.compareTo(katniss2));

		/* This is how you need to compare for equality */
		/* Do NOT compare using == */
		System.out.println(katniss.equals(katniss2));
	}

}