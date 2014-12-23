package com.jdreamer.algo;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * http://www.geeksforgeeks.org/find-k-closest-elements-given-value/
 */
public class KClosestElements {
    private static int findCrossOverIndex(int[] items, int key, int start, int end) {
        if (key >= items[end]) {
            return end;
        } else if (key < items[start]) {
            return start;
        }

        int mid = start + (end - start) / 2;
        if (key == items[mid]) {
            return mid;
        } else if (key > items[mid]) {
            return findCrossOverIndex(items, key, mid + 1, end);
        } else {
            return findCrossOverIndex(items, key, start, mid - 1);
        }
    }

    private static Set<Integer> kClosestElements(int[] items, int key, final int k) {
        final int crossOverIndex = findCrossOverIndex(items, key, 0, items.length - 1);
        System.out.println("Crossover index: " + crossOverIndex);

        int left = crossOverIndex;
        int right = crossOverIndex + 1;
        if (key == items[left]) {
            left--;
        }

        final Set<Integer> kItems = new LinkedHashSet<Integer>(k);
        while (kItems.size() < k && left >= 0 && right < items.length) {
            if (key - items[left] < items[right] - key) {
                kItems.add(items[left--]);
            } else {
                kItems.add(items[right++]);
            }
        }

        while (kItems.size() < k && left >= 0) {
            kItems.add(items[left--]);
        }

        while (kItems.size() < k && right < items.length) {
            kItems.add(items[right++]);
        }

        return kItems;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 19, 20, 128, 190};

        Set<Integer> kItems = kClosestElements(a, 19, 4);
        for (int i : kItems) {
            System.out.println(i);
        }
    }
}
