package com.jdreamer.algo.basic;

/**
 * http://www.geeksforgeeks.org/counting-inversions/
 */
public class CountingInversions {
    private static int countInversions(int[] a) {
        int[] aux = new int[a.length];

        return mergeSort(a, aux, 0, a.length - 1);
    }

    private static int mergeSort(int[] a, int[] aux, int left, int right) {
        int count = 0;
        if (right > left) {
            int mid = (left + right) / 2;

            count += mergeSort(a, aux, left, mid);
            count += mergeSort(a, aux, mid + 1, right);
            count += merge(a, aux, left, mid + 1, right);
        }

        return count;
    }

    private static int merge(int[] a, int[] aux, int left, int mid, int right) {
        int count = 0;

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
    }

}
