package com.jdreamer.algo;

import java.util.PriorityQueue;

/**
 * http://www.geeksforgeeks.org/merge-k-sorted-arrays/
 * http://www.geeksforgeeks.org/print-elements-sorted-order-row-column-wise-sorted-matrix/
 */
public class MergeKSortedArray {
    static class Node implements Comparable<Node> {
        int val;
        int i, j;

        public Node(int x, int i, int j) {
            this.val = x;
            this.i = i;
            this.j = j;
        }

        @Override
        public int compareTo(Node o) {
            if (o == null || this.val > o.val) {
                return 1;
            } else if (this.val < o.val) {
                return -1;
            }

            return 0;
        }
    }

    private static void mergeSortedArrays(int[][] a) {
        if (a == null || a.length == 0) {
            return;
        }

        PriorityQueue<Node> q = new PriorityQueue<Node>();
        for (int r = 0; r < a.length; r++) {
            q.add(new Node(a[r][0], r, 0));
        }

        // Time complexity: O(nk Log k)
        final int n = a.length;
        final int k = a[0].length;
        for (int count = 0; count < n * k; count++) {
            Node min = q.poll();
            System.out.println(min.val);

            if (min.j < a[min.i].length - 1) {
                min.val = a[min.i][++min.j];
                q.add(min);
            }
        }
    }

    public static void main(String[] args) {
        int a[][] = {{2, 6, 12, 34},
                {1, 2, 3, 7},
                {8, 10, 30, 35}};
        mergeSortedArrays(a);
    }
}
