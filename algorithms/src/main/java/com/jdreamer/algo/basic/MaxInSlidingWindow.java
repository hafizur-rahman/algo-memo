package com.jdreamer.algo.basic;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * http://www.geeksforgeeks.org/maximum-of-all-subarrays-of-size-k/
 *
 * Given an array of numbers and a sliding window size, how do you get the maximum numbers in all sliding windows?
 */
public class MaxInSlidingWindow {
    private static void printKMax(int[] items, int k) {
        Deque<Integer> que = new ArrayDeque<Integer>(k);

        for (int i = 0; i < k; i++) {
            // Remove index of items smaller than current item in the array
            while (!que.isEmpty() && items[i] >= items[que.peekLast()]) {
                que.pollLast();
            }

            que.addLast(i);
        }

        for (int i = k; i < items.length; i++) {
            System.out.println(items[que.getFirst()]);

            // Remove elements that are out of the sliding window
            while (!que.isEmpty() && que.peekFirst() <= (i - k)) {
                que.pollFirst();
            }

            // Remove index of items smaller than current item in the array
            while (!que.isEmpty() && items[i] >= items[que.peekLast()]) {
                que.pollLast();
            }

            que.addLast(i);
        }

        System.out.println(items[que.getFirst()]);
    }

    public static void main(String[] args) {
        int[] items = {12, 1, 78, 90, 57, 89, 56};

        printKMax(items, 3);
    }
}
