import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class MatrixApp {
	
	private static int rowNr = 5;	// Number of rows
	private static int colNr = 5;	// Number of columns
	private static int squareSize = 10;
	
	public void buildMatrix(int row, int col) {
		System.out.println("Rows: " + row + "\n" + "Columns: " + col);
		int[][] matrix = new int[row][col];
		List<Integer> tempList = new ArrayList<Integer>();
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = getRandom(0, 9);
				tempList.add(matrix[i][j]);
			}
		}
		
		System.out.println(Arrays.deepToString(matrix)
		.replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
		
		System.out.println("Unsorted 1D list: " + tempList);
		Collections.sort(tempList);
		System.out.println("Sorted 1D list: " + tempList);
		
		int index = 0;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {				
				matrix[i][j] = tempList.get(index);
				index++;
			}
		}
		System.out.println(Arrays.deepToString(matrix)
		.replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
	}
	
	public static int getRandom(int min, int max) {
		Random random = new Random();
		int randomNumber = random.nextInt((max - min) + 1) + min;
		return randomNumber;
	}
	
	public void buildDiagonalMatrix(int size) {
		int[][] matrix = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == j) {
					matrix[i][j] = 1;
				} else if (i > j) {
					matrix[i][j] = 0;
				} else {
					matrix[i][j] = 2;
				}
			}
		}
		System.out.println(Arrays.deepToString(matrix)
		.replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
	}
	
	public static void main(String[]args) {
		MatrixApp app = new MatrixApp();
		app.buildMatrix(rowNr, colNr);
		System.out.println("\n" + "Diagonal ones with size " + squareSize + ": ");
		app.buildDiagonalMatrix(squareSize);
	}
}