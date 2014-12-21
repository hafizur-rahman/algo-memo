package com.jdreamer.algo;

public class BinarySearch {
    public static int search(Comparable[] a, Comparable key) {
        int l = 0;
        int r = a.length-1;

        while (l <= r) {
            int m = l + (r - l)/2;

            int result = key.compareTo(a[m]);
            if (result == 0) {
                return m;
            } else if (result < 0) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }

    /*
      Suppose a sorted array is rotated at some pivot unknown to you beforehand.
     (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
     Find the minimum element.

     You may assume no duplicate exists in the array.
     */
    public static Comparable findMin(Comparable[] a) {
        int l = 0;
        int r = a.length-1;

        while (l <= r) {
            if (a[l].compareTo(a[r]) <= 0)
                return a[l];

            int m = l + (r - l)/2;
            if (a[m].compareTo(a[l]) >= 0) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        return a[l];
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{1, 2, 3, 4, 5, 6, 7, 19, 99, 128, 190};

//        for (int i : a)
//            System.out.println(search(a, i));

        Integer[] b = new Integer[]{19, 99, 128, 190, 1, 2, 3, 4, 5, 6, 7};
        System.out.println(findMin(b));
    }
}
