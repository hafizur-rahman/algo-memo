package com.jdreamer.algo.sort;

/**
 * http://www.geeksforgeeks.org/sort-n-numbers-range-0-n2-1-linear-time/
 * http://algs4.cs.princeton.edu/lectures/51StringSorts-2x2.pdf
 */
public class NumericRadixSort {

    public static void sort(int[] a) {
        final int N = a.length;
        final int[] aux = new int[N];

        for (int d : new int[] {1, N}) {
            int[] count = new int[N + 1];

            for (int i = 0; i < N; i++)
                count[ (a[i] / d) % N + 1]++;

            for (int r = 0; r < N; r++)
                count[r + 1] += count[r];

            for (int i = 0; i < N; i++)
                aux[count[(a[i] / d) % N]++] = a[i];

            for (int i = 0; i < N; i++)
                a[i] = aux[i];
        }
    }

    public static void main(String[] args) {
        int[] A = {40, 12, 45, 32, 33, 1, 22};
        sort(A);

        for (int a: A)
            System.out.println(a);
    }
}
