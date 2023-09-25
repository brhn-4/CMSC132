
public class StringComparison2 {
	public static void main(String[] args) {
		String katniss = "Katniss";
		String katniss2 = "Katniss";

		/* Another approach to create string */
		String mockingjay = new String("Katniss");

		/* WRONG WAY TO COMPARE STRINGS, use equals (katniss.equals(katniss2)) */
		System.out.println(katniss == katniss2);

		/* WRONG WAY TO COMPARE STRINGS, use equals */
		System.out.println(katniss == mockingjay);
	}
}