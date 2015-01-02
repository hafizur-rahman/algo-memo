package com.jdreamer.algo;

import java.util.PriorityQueue;

/**
 * http://www.geeksforgeeks.org/connect-n-ropes-minimum-cost/
 */
public class ConnectRopesWithMinCost {
    /*
    There are given n ropes of different lengths, we need to connect these ropes into one rope. The cost to connect two
    ropes is equal to sum of their lengths. We need to connect the ropes with minimum cost.

     For example if we are given 4 ropes of lengths 4, 3, 2 and 6. We can connect the ropes in following ways.
     1) First connect ropes of lengths 2 and 3. Now we have three ropes of lengths 4, 6 and 5.
     2) Now connect ropes of lengths 4 and 5. Now we have two ropes of lengths 6 and 9.
     3) Finally connect the two ropes and all ropes have connected.

     Total cost for connecting all ropes is 5 + 9 + 15 = 29. This is the optimized cost for connecting ropes.
     Other ways of connecting ropes would always have same or more cost. For example, if we connect 4 and 6 first (we
     get three strings of 3, 2 and 10), then connect 10 and 3 (we get two strings of 13 and 2). Finally we connect 13 and 2. Total cost in this way is 10 + 13 + 15 = 38.
    */
    private static int findMinCost(int[] len) {
        // If we observe the above problem closely, we can notice that the lengths of the ropes which are picked first
        // are included more than once in total cost. Therefore, the idea is to connect smallest two ropes first and
        // recur for remaining ropes. This approach is similar to Huffman Coding. We put smallest ropes down the tree
        // so that they can be repeated multiple times rather than the longer ropes.
        PriorityQueue<Integer> minPQ = new PriorityQueue<Integer>(len.length);
        for (int l : len) {
            minPQ.add(l);
        }

        int cost = 0;
        while (minPQ.size() > 1) {
            int first = minPQ.poll();
            int second = minPQ.poll();

            cost += (first + second);
            minPQ.add(first + second);
        }

        return cost;
    }

    public static void main(String[] args) {
        int[] len = {4, 3, 2, 6};
        int cost = findMinCost(len);

        System.out.printf("Total cost for connecting ropes is %d", cost);
    }
}
