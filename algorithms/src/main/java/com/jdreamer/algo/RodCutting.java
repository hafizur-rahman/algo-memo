package com.jdreamer.algo;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
 * http://www.columbia.edu/~cs2035/courses/csor4231.F11/dynamic.pdf
 */
public class RodCutting {
    /**
     * Problem Description:
     * <p/>
     * Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n.
     * Determine the maximum value obtainable by cutting up the rod and selling the pieces.
     */
    private static int cutRod(int[] price, int rodLength) {
        if (rodLength > price.length)
            throw new IllegalArgumentException("Rod length is greater than price array size!");

        final int[] val = new int[rodLength + 1];

        // Build the table val[] in bottom up manner and return the last entry from the table
        for (int i = 1; i <= rodLength; i++) {
            int maxVal = Integer.MIN_VALUE;

            for (int j = 1; j < i; j++) {
                maxVal = Math.max(maxVal, price[j] + val[i - j - 1]);
            }

            val[i] = maxVal;
        }

        return val[rodLength];
    }

    public static void main(String[] args) {
        int[] price = {1, 5, 8, 9, 10, 17, 17, 20};

        System.out.println("Maximum obtainable value is: " + cutRod(price, 8));
    }
}
