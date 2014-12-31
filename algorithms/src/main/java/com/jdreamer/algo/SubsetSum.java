package com.jdreamer.algo;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-subset-sum-problem/
 * https://www.cs.cmu.edu/~ckingsf/class/02713-s13/lectures/lec15-subsetsum.pdf
 */
public class SubsetSum {

    public static void main(String[] args) {
        int set[] = {3, 34, 4, 12, 5, 2};

        checkSubsetSum(set, 9);
        checkSubsetSum(set, 22);
        checkSubsetSum(set, 25);
    }

    private static void checkSubsetSum(int[] set, int sum) {
        if (isSubsetSum(set, sum)) {
            System.out.println("Found a subset with given sum: " + sum);
        } else {
            System.out.println("No subset with given sum: " + sum);
        }
    }

    private static boolean isSubsetSum(int[] set, int sum) {
        final boolean[][] subset = new boolean[sum + 1][set.length + 1];

        // For sum=0, flag is true
        Arrays.fill(subset[0], true);

        for (int i = 1; i <= sum; i++) {
            for (int j = 1; j <= set.length; j++) {
                subset[i][j] = subset[i][j - 1] ||
                        (i >= set[j - 1] && subset[i - set[j - 1]][j - 1]);
            }
        }

        if (subset[sum][set.length]) {
            int j = sum;
            for (int i = set.length; i > 0; i--) {
                if (!subset[j][i - 1]) {
                    System.out.println(set[i - 1]);

                    j -= set[i - 1];
                }
            }
        }

        return subset[sum][set.length];
    }
}

