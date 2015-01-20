package com.jdreamer.algo.basic;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Scanner;

/**
 * http://www.geeksforgeeks.org/counting-inversions/
 */
public class CountingInversions {
    private static long countInversions(int[] a) {
        int[] aux = new int[a.length];

        return mergeSort(a, aux, 0, a.length - 1);
    }

    private static long mergeSort(int[] a, int[] aux, int left, int right) {
        long count = 0;
        if (right > left) {
            int mid = (left + right) / 2;

            count += mergeSort(a, aux, left, mid);
            count += mergeSort(a, aux, mid + 1, right);
            count += merge(a, aux, left, mid + 1, right);
        }

        return count;
    }

    private static long merge(int[] a, int[] aux, int left, int mid, int right) {
        long count = 0;

        int i = left;
        int j = mid;
        int k = left;

        while (i <= mid - 1 && j <= right) {
            if (a[i] < a[j]) {
                aux[k++] = a[i++];
            } else {
                aux[k++] = a[j++];

                count += (mid - i);
            }
        }

        // Copy the remaining elements of left subarray (if there are any) to aux
        while (i <= mid - 1) {
            aux[k++] = a[i++];
        }

        // Copy the remaining elements of right subarray (if there are any) to aux
        while (j <= right) {
            aux[k++] = a[j++];
        }

        // Copy back the merged elements to original array
        for (i = left; i <= right; i++) {
            a[i] = aux[i];
        }

        return count;
    }

    public static void main(String[] args) {
        int[] items = {1, 3, 5, 2, 4, 6};

        System.out.println(countInversions(items));

        if (args.length > 0) {
            String fileName = args[0];

            Scanner scanner = null;
            try {
                scanner = new Scanner(new File(fileName));

                int[] nums = new int [100000];
                int i = 0;
                while(scanner.hasNextInt()){
                    nums[i++] = scanner.nextInt();
                }

                System.out.println(countInversions(nums));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(scanner);
            }
        }
    }

}
