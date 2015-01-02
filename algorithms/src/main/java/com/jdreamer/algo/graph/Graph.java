package com.jdreamer.algo.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	private final int V;
	private int E;
	private List<Integer>[] adj;

	public Graph(int V) {
		this.V = V;
		adj = new List[V];

		for (int v = 0; v < V; v++) {
			adj[v] = new ArrayList<Integer>();
		}
	}

	public void addEdge(int v, int w) {
		if (v < 0 || v >= V)
			throw new IndexOutOfBoundsException();
		if (w < 0 || w >= V)
			throw new IndexOutOfBoundsException();

		E++;
		adj[v].add(w);
		adj[w].add(v);
	}

	public List<Integer> adj(int v) {
		if (v < 0 || v >= V)
			throw new IndexOutOfBoundsException();
		
		return adj[v];
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
}
