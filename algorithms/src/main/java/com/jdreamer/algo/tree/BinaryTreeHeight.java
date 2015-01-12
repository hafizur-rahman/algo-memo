package com.jdreamer.algo.tree;

/**
 * http://www.geeksforgeeks.org/find-height-binary-tree-represented-parent-array/
 */
public class BinaryTreeHeight {

    private static int calculateHeight(int[] parent) {
        int[] depth = new int[parent.length];

        for (int i = 0; i < parent.length; i++) {
            fillDepth(parent, i, depth);
        }

        int maxDepth = 0;
        for (int d : depth) {
            if (d > maxDepth) {
                maxDepth = d;
            }
        }
        return maxDepth;
    }

    private static void fillDepth(int[] parent, int index, int[] depth) {
        if (depth[index] > 0 || parent[index] == -1) return;

        if (depth[parent[index]] == 0) {
            fillDepth(parent, parent[index], depth);
        }

        depth[index] = 1 + depth[parent[index]];
    }

    public static void main(String[] args) {
        System.out.println(calculateHeight(new int[]{-1, 0, 0, 1, 1, 3, 5}));
        System.out.println(calculateHeight(new int[]{1, 5, 5, 2, 2, -1, 3}));
    }
}
