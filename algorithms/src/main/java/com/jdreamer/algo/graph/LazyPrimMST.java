package com.jdreamer.algo.graph;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Queue;

import java.util.PriorityQueue;

/**
 * http://algs4.cs.princeton.edu/43mst/
 * http://algs4.cs.princeton.edu/43mst/LazyPrimMST.java.html
 */
public class LazyPrimMST {
    private double weight;
    private Queue<Edge> mst;
    private boolean[] marked;
    private PriorityQueue<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G) {
        mst = new Queue<Edge>();
        pq = new PriorityQueue<Edge>(G.E());
        marked = new boolean[G.V()];

        // Run Prim from all vertices to get a minimum spanning forest
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                prim(G, v);
            }
        }
    }

    private void prim(EdgeWeightedGraph G, int s) {
        dfs(G, s);

        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            int v = e.either();
            int w = e.other(v);

            if (marked[v] && marked[w])
                continue;

            mst.enqueue(e);
            weight += e.weight();

            if (!marked[v]) dfs(G, v);
            if (!marked[w]) dfs(G, w);
        }
    }

    private void dfs(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e: G.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.add(e);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double totalWeight() {
        return weight;
    }
}
