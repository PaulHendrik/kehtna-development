import java.util.Scanner;

public class Calculator {

	public static int add(int val1, int val2) {
		int result = val1 + val2;
		return result;
	}
	
	public static int subtract(int val1, int val2) {
		int result = val1 - val2;
		return result;
	}
	
	public static int multiply(int val1, int val2) {
		int result = val1 * val2;
		return result;
	}
	
	public static float divide(int val1, int val2) {
		float result = (float) val1 / val2;
		return result;
	}

	public static void main(String[]args) {
		Scanner q1 = new Scanner(System.in);
		System.out.println("Do you want to add, subtract, multiply or divide (+, -, *, /):");
		String action = q1.nextLine();
		
		Scanner q2 = new Scanner(System.in);
		System.out.println("Insert first value:");
		String value1 = q2.nextLine();
		
		Scanner q3 = new Scanner(System.in);
		System.out.println("Insert second value:");
		String value2 = q2.nextLine();
		
		if (action.equals("+")) {
			int result = add(Integer.parseInt(value1), Integer.parseInt(value2));
			System.out.println("Your addition result is: " + result);
		}
		
		if (action.equals("-")) {
			int result = subtract(Integer.parseInt(value1), Integer.parseInt(value2));
			System.out.println("Your subtraction result is: " + result);
		}
		
		if (action.equals("*")) {
			int result = multiply(Integer.parseInt(value1), Integer.parseInt(value2));
			System.out.println("Your multiplication result is: " + result);
		}
		
		if (action.equals("/")) {
			float result = divide(Integer.parseInt(value1), Integer.parseInt(value2));
			System.out.println("Your division result is: " + result);
		}
	}
}