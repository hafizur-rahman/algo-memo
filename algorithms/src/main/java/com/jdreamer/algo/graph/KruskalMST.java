package com.jdreamer.algo.graph;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

import java.util.PriorityQueue;

/**
 * http://www.geeksforgeeks.org/greedy-algorithms-set-2-kruskals-minimum-spanning-tree-mst/
 * http://algs4.cs.princeton.edu/lectures/43MinimumSpanningTrees-2x2.pdf
 */
public class KruskalMST {
    private Queue<Edge> mst = new Queue<Edge>();

    public KruskalMST(EdgeWeightedGraph G) {
        // Algorithm:
        // 1. Consider edges in ascending order of weight.
        // 2. Add next edge to tree T unless doing so would create a cycle.
        //
        // Challenge. Find the min weight edge with exactly one endpoint in T.
        // Solution: Use UnionFind data structure.

        // Sort edges by weight
        PriorityQueue<Edge> minPQ = new PriorityQueue<Edge>();
        for (Edge e: G.edges()) {
            minPQ.add(e);
        }

        UF uf = new UF(G.V());
        while (!minPQ.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = minPQ.poll();
            int v = e.either();
            int w = e.other(v);

            // Avoid cycle
            if (!uf.connected(v, w)) {
                uf.union(v, w);
                mst.enqueue(e);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }
}
