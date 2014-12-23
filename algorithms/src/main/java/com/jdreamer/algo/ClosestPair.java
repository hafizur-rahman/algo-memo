package com.jdreamer.algo;

/**
 * http://www.geeksforgeeks.org/given-two-sorted-arrays-number-x-find-pair-whose-sum-closest-x/
 */
public class ClosestPair {
    private static void closestPair(int[] a1, int[] a2, int expectedSum) {
        int i = 0;
        int j = a2.length - 1;

        int[] resultIndex = new int[] {i, j};
        int minDiff = Integer.MAX_VALUE;

        while (i < a1.length && j >= 0) {
            int sum = a1[i] + a2[j];
            int diff = Math.abs(expectedSum - sum);

            if (diff < minDiff) {
                minDiff = diff;

                resultIndex[0] = i;
                resultIndex[1] = j;
            }

            if (sum > expectedSum) {
                j--;
            } else {
                i++;
            }
        }

        System.out.println(String.format("Sum expected: %d, min diff: %d, x1: %d, x2: %d",
                expectedSum, minDiff, a1[resultIndex[0]], a2[resultIndex[1]]));
    }

    public static void main(String[] args) {
        int a[][] = {{2, 6, 12, 34},
                {1, 2, 3, 7},
                {8, 10, 30, 35}};

        closestPair(a[0], a[1], 20);
        closestPair(a[0], a[1], 45);
        closestPair(a[0], a[1], 3);
    }
}
