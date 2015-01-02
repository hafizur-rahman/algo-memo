package com.jdreamer.algo.dp;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-11-egg-dropping-puzzle/
 */
public class EggDrop {
    /**
     * Function to get minimum number of trails needed in worst case with n eggs and k floors
     */
    private static int eggDrop(int n, int k) {
        // Minimum number of trials needed for i eggs and j floors
        int[][] memo = new int[n + 1][k + 1];

        // Base cases: we need one trial for 1st floor and 0 trials for 0th floor.
        for (int i = 1; i <= n; i++) {
            memo[i][1] = 1;
            memo[i][0] = 0;
        }

        // Base cases: we always need j trials for 1 egg and jth floors.
        for (int j = 1; j <= k; j++) {
            memo[1][j] = j;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                memo[i][j] = Integer.MAX_VALUE;

                for (int x = 1; x <= j; x++) {
                    // When we drop an egg from a floor x, there can be two cases:
                    // (1) The egg breaks (2) The egg doesn’t break.
                    //
                    // 1) If the egg breaks after dropping from xth floor,
                    //    then we only need to check for floors lower than x with remaining eggs;
                    //    so the problem reduces to x-1 floors and n-1 eggs
                    //
                    // 2) If the egg doesn’t break after dropping from the xth floor,
                    //    then we only need to check for floors higher than x;
                    //    so the problem reduces to k-x floors and n eggs.
                    //
                    // Since we need to minimize the number of trials in worst case, we take the maximum of two cases.
                    int val = 1 + Math.max(memo[i - 1][x - 1], memo[i][j - x]);

                    if (val < memo[i][j]) {
                        memo[i][j] = val;
                    }
                }
            }
        }

        return memo[n][k];
    }

    public static void main(String[] args) {
        int n = 2, k = 100;

        System.out.printf("Minimum number of trials in worst case with %d eggs and %d floors is %d.", n, k, eggDrop(n, k));
    }
}
