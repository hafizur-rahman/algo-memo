package com.jdreamer.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
 * http://www.columbia.edu/~cs2035/courses/csor4231.F11/dynamic.pdf
 * http://www.radford.edu/~nokie/classes/360/dp-rod-cutting.html
 */
public class RodCutting {
    /**
     * Problem Description:
     * <p/>
     * Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n.
     * Determine the maximum value obtainable by cutting up the rod and selling the pieces.
     */
    private static List<Integer> cutRod(int[] price, int rodLength) {
        if (rodLength > price.length)
            throw new IllegalArgumentException("Rod length is greater than price array size!");

        final int[] val = new int[rodLength + 1];
        final int[] cuts = new int[rodLength + 1];

        // Build the table val[] in bottom up manner and return the last entry from the table
        for (int i = 1; i <= rodLength; i++) {
            int maxVal = Integer.MIN_VALUE;

            for (int j = 1; j <= i; j++) {
                // price is 0 indexed, so adjust properly
                int tmp = price[j - 1] + val[i - j];
                if (tmp > maxVal) {
                    maxVal = tmp;

                    cuts[i] = j;
                }
            }

            val[i] = maxVal;
        }

        final List<Integer> sizes = new ArrayList<Integer>();
        int n = rodLength;
        while (n > 0) {
            sizes.add(cuts[n] - 1);

            n = n - cuts[n];
        }
        return sizes;
    }

    public static void main(String[] args) {
        final int[] price = {1, 5, 8, 9, 10, 17, 17, 20};
        final List<Integer> sizes = cutRod(price, 8);

        int totalPrice = 0;
        for (int s : sizes) {
            totalPrice += price[s];
        }

        System.out.println("Cut sizes: " + sizes + ", total price: " + totalPrice);
    }
}
