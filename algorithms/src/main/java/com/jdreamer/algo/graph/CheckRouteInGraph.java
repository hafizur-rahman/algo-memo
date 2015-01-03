package com.jdreamer.algo.graph;

import edu.princeton.cs.algs4.Digraph;

import java.util.LinkedList;

/**
 * http://www.geeksforgeeks.org/find-if-there-is-a-path-between-two-vertices-in-a-given-graph/
 *
 * Given a directed graph, design an algorithm to find out whether there is a route be- tween two nodes
 */
public class CheckRouteInGraph {
    public static boolean search(Digraph G, int startNode, int endNode) {
        boolean[] marked = new boolean[G.V()];

        LinkedList<Integer> q = new LinkedList<Integer>();
        q.add(startNode);

        while(!q.isEmpty()) {
            int v = q.removeFirst(); // pop

            marked[v] = true;
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    if (w == endNode) {
                        return true;
                    } else {
                        marked[w] = true;
                        q.add(w);
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(4);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(1, 2);
        G.addEdge(2, 0);
        G.addEdge(2, 3);
        G.addEdge(3, 3);

        for (int[] a : new int[][]{ new int[]{1, 3}, new int[]{3, 1}}) {
            if (search(G, a[0], a[1])) {
                System.out.printf("There is path from %d to %d.\n", a[0], a[1]);
            } else {
                System.out.printf("There is no path from %d to %d.\n", a[0], a[1]);
            }
        }
    }
}
