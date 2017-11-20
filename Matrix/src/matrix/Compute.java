package matrix;

import java.util.Scanner;
import java.lang.Math;

/**
 * This is my own project, designed to compute matrix values and perform matrix
 * operations in accordance with the University of Utah's MATH 2270 content
 * 
 * @author Joshua Cragun
 *
 */

public class Compute {
	/**
	 * User Interface
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int dim1;
		int dim2;
		int[][] matrix;
		System.out.print("Enter row and column numbers: ");
		Scanner reader = new Scanner(System.in);
		dim1 = reader.nextInt();
		dim2 = reader.nextInt();
		// reader will be used again
		matrix = matrixBuilder(dim1, dim2);
		for (int i = 0; i < dim1; i++) {
			for (int j = 0; j < dim2; j++) {
				System.out.print("Please enter element at row " + (i + 1) + " and column " + (j + 1) + ": ");
				int temp = reader.nextInt();
				matrix[i][j] = temp;
				System.out.print("\n");
			}
		}
		System.out.println("Your matrix: ");
		matrix = matrixMod(1, 1, dim1, dim2, matrix); 
		for (int i = 0; i < dim1 - 1; i++) {
			System.out.print("|");
			for (int j = 0; j < dim2 - 1; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.print("|\n");
		}
		System.out.println("What would you like to compute?\n(d = determinant, r = rank, exit = exit)");
	}

	/**
	 * Builds the matrix
	 * 
	 * @param dim1
	 * @param dim2
	 * @return
	 */
	public static int[][] matrixBuilder(int dim1, int dim2) {
		return new int[dim1][dim2];
	}
	/**
	 * Returns an identical matrix to the parent, expect the specified row and column (starting from 1) is removed.
	 * @param row - row number to be removed
	 * @param col - column number to be removed
	 * @param dim1 - number of rows of parent
	 * @param dim2 - number of columns of parent
	 * @param parent - source matrix
	 * @return
	 */
	public static int[][] matrixMod(int row, int col, int dim1, int dim2, int[][] parent) {
		int[][] result = new int[dim1 -1][dim2 - 1];
		for (int i = 0; i < dim1; i++) {
			if (i == row - 1) {
				i ++;
			}
			for (int j = 0; j < dim2; j++) {
				if (j == col - 1) {
					j++;
				}
				System.out.println("Replacing elt" + i + " " + j);
				result[i][j] = parent[i][j];
			}
		}
		return result;
	}
}
