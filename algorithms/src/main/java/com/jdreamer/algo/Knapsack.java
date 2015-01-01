package com.jdreamer.algo;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
 */
public class Knapsack {

    // Returns the maximum value that can be put in a knapsack of capacity W
    // Time Complexity: O(NW) where N is the number of items and W is the capacity of knapsack.
    private static int knapsack(int[] prices, int[] weights, int W) {
        int N = prices.length;

        // Contains the max value for a given capacity
        int[][] K = new int[W + 1][N + 1];

        for (int n = 1; n <= N; n++) {
            for (int w = 1; w <= W; w++) {
                if (weights[n - 1] <= w) {
                    K[w][n] = Math.max(prices[n - 1] + K[w - weights[n - 1]][n - 1], K[w - 1][n - 1]);
                } else {
                    K[w][n] = K[w - 1][n - 1];
                }
            }
        }

        return K[W][N];
    }

    public static void main(String[] args) {
        int[] prices = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int W = 50;

        System.out.println(knapsack(prices, weights, W));
    }
}
