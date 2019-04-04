import java.util.Scanner;

public class TextCompressor {
	public static void main(String[]args) {
		Scanner question = new Scanner(System.in);
		System.out.println("Enter a sentence: ");
		String sentence = question.nextLine();
		//String sentence = "Aias sadas saia.";
		String output = "";
		char character = sentence.charAt(0);
		int counter = 1;
		
		for (int i = 1; i < sentence.length(); i++) {
			if (sentence.charAt(i) == character) {
				counter++;
				if (i == sentence.length() - 1) {
					output += counter + Character.toString(character);
				}
			} else {
				output += (counter == 1) ? Character.toString(character) : counter + Character.toString(character);
				counter = 1;
				character = sentence.charAt(i);
				if (i == sentence.length() - 1) {
					output += Character.toString(character);
				}
			}
		}
		if (output.equals("")) {
			output += sentence.charAt(0);
		}
		System.out.println(output);
	}
}