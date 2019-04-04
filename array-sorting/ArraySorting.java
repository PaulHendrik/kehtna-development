import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ArraySorting {
	
	public static int getRandom(int min, int max) {
		Random random = new Random();
		int randomNumber = random.nextInt((max - min) + 1) + min;
		return randomNumber;
	}
	
	/*public static void swap(ArrayList<Integer> list, int val1, int val2) {
		int aux = list.get(val1);
		list.get(val1) = list.get(val2);
		list.get(val1) = aux;
	}*/
	
	public static void main(String[]args) {
		ArrayList<Integer> numbersArray = new ArrayList<Integer>();
		
		for (int i = 0; i < 15; i++) {
			int randomNumber = getRandom(1, 100);
			numbersArray.add(randomNumber);
		}
		
		/*for (int i = 0; i < numbersArray.size() - 1; i++) {
			for (int j = i + 1; j > numbersArray.size(); j++) {
				if (numbersArray.get(i) > numbersArray.get(j)) {
					//swap(numbersArray, i, j);
				}
			}
		}*/
		System.out.println("Unsorted array: " + numbersArray);
		Collections.sort(numbersArray);
		System.out.println("Sorted array: " + numbersArray);
		int min = Collections.min(numbersArray);
		int max = Collections.max(numbersArray);
		System.out.println("Smallest value: " + min);
		System.out.println("Biggest value: " + max);
	}
}