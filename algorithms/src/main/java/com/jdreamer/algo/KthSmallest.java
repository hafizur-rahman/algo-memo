package com.jdreamer.algo;

import edu.princeton.cs.introcs.StdRandom;

/**
 * http://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array/
 */
public class KthSmallest {

    // http://www.cs.princeton.edu/courses/archive/fall14/cos226/lectures/23Quicksort-2x2.pdf
    private static int partition(int[] a, int lo, int hi) {
        int i = lo, j = hi + 1;

        // Algorithm:
        // 1. Repeat until i and j pointers cross.
        // 1.1. Scan i from left to right so long as (a[i] < a[lo])
        // 1.2. Scan j from right to left so long as (a[j] > a[lo])
        // 1.3. Exchange a[i] with a[j].
        //
        // 2. When pointers cross.
        // 2.1. Exchange a[lo] with a[j].
        while (true) {
            // Find item on left to swap
            while (a[++i] < a[lo]) {
                if (i == hi) break;
            }

            // Find item on right to swap
            while (a[--j] > a[lo]) {
                if (j == lo) break;
            }

            // Check if pointers cross
            if (i >= j) break;

            swap(a, i, j);
        }

        swap(a, lo, j);
        return j;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static int kthSmallest(int[] a, int k) {
        StdRandom.shuffle(a);

        int lo = 0;
        int hi = a.length - 1;

        while (hi > lo) {
            int j = partition(a, lo, hi);

            if (j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                return a[k];
            }
        }

        return a[k];
    }

    public static void sort(int[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 19, 20, 128, 190};

        int kthSmallest = kthSmallest(a, 4);
        System.out.println(kthSmallest);
    }
}
