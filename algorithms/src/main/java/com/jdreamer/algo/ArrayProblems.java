package com.jdreamer.algo;

/**
 *
 */
public class ArrayProblems {

    /**
     * Question 6:
     * <p/>
     * An array contains n numbers ranging from 0 to n-1. There are some numbers duplicated in the array. It is not
     * clear how many numbers are duplicated or how many times a number gets duplicated. How do you find a duplicated
     * number in the array? For example, if an array of length 7 contains the numbers {2, 3, 1, 0, 2, 5, 3}, the
     * implemented function (or method) should return either 2 or 3.
     */
    public static void question6(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < 0 || numbers[i] > numbers.length - 1) {
                throw new IllegalArgumentException("Invalid number: " + numbers[i]);
            }

            // If number is not at the right index, swap it.
            while (numbers[i] != i) {
                // But if the place is already occupied, we found a duplicate!
                if (numbers[i] == numbers[numbers[i]]) {
                    System.out.println("Duplicate: " + numbers[i]);
                    break;
                }

                // swap numbers[i] and numbers[numbers[i]]
                int temp = numbers[i];
                numbers[i] = numbers[temp];
                numbers[temp] = temp;
            }
        }
    }

    /**
     * Question 7
     * <p/>
     * In a 2-D matrix, every row is increasingly sorted from left to right, and the last number in each row is not
     * greater than the first number of the next row. A sample matrix follows. Please implement a function to check
     * whether a number is in such a matrix or not. It returns true if it tries to find the number 7 in the sample
     * matrix, but it returns false if it tries to find the number 12.
     * <pre>
     *  1  3  5
     *  7  9 11
     * 13 15 17
     * </pre>
     */
    public static void question7(int[][] matrix, int key) {
        // Hint: Since each row in the matrix is sorted and the first number of a row is guaranteed to be greater than
        // or equal to the last number of the preceding row, the matrix can be viewed as a 1-D sorted array.

        int rows = matrix.length;
        int cols = matrix[0].length;
        int start = 0;
        int end = rows * cols - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int row = mid / cols;
            int col = mid % cols;
            int v = matrix[row][col];

            if (key == v) {
                System.out.println("Found: " + key);
                return;
            }

            if (key > v) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println("Not found: " + key);
    }

    /**
     * Question 8
     * <p/>
     * In a 2-D matrix, every row is increasingly sorted from left to right, and every column is increasingly sorted
     * from top to bottom. Please implement a function to check whether a number is in such a matrix or not. For
     * example, all rows and columns are increasingly sorted in the following matrix. It returns true if it tries to
     * find number 7, but it returns false if it tries to find number 5.
     * <p/>
     * <pre>
     * 1  2  8  9
     * 2  4  9 12
     * 4  7 10 13
     * 6  8 11 15
     * </pre>
     */
    public static void question8(int[][] matrix, int key) {
        // Hint: The number at the top right corner is selected in each round of searching, and it is compared with the
        // target value. When it is the same as the target value, it stops to search. If it is greater than the target
        // value, the last column in the remaining sub-matrix is removed. If it is less than the target value, the first
        // row in the remaining sub-matrix is removed. Therefore, it reduces the sub-matrix by a row or a column if
        // the target value is not at the top right corner.
        int row = 0;
        int col = matrix[0].length - 1;

        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == key) {
                System.out.println("Found: " + key);
                return;
            }

            if (matrix[row][col] > key) {
                col--;
            } else {
                row++;
            }
        }

        System.out.println("Not found: " + key);
    }

    public static void main(String[] args) {
        question6(new int[]{2, 3, 1, 0, 2, 5, 3});

        int[][] matrix = new int[][]{
                {1, 3, 5},
                {7, 9, 11},
                {13, 15, 17}
        };
        question7(matrix, 7);
        question7(matrix, 12);

        int[][] matrix2 = new int[][]{
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        question8(matrix2, 7);
        question8(matrix2, 5);
    }
}
