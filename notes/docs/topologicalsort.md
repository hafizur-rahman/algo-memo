---
layout: docs
title: Topological Sort
permalink: /docs/topologicalsort/
---
Let's first consider a scenario.

## Precedence Scheduling
Goal. Given a set of tasks to be completed with precedence constraints, in which order should we schedule the tasks?

Digraph model. vertex = task; edge = precedence constraint.


Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge uv, vertex u comes before v in the ordering. Topological Sorting for a graph is not possible if the graph is not a DAG.

{% highlight java %}
public class DepthFirstOrder {
   private boolean[] marked;
   private Stack<Integer> reversePost;
   
   public DepthFirstOrder(Digraph G) {
      reversePost = new Stack<Integer>();
      marked = new boolean[G.V()];
      for (int v = 0; v < G.V(); v++)
         if (!marked[v]) dfs(G, v);
   }
   
   private void dfs(Digraph G, int v) {
      marked[v] = true;
      for (int w : G.adj(v))
         if (!marked[w]) dfs(G, w);
      reversePost.push(v);
   }

   public Iterable<Integer> reversePost() {
     return reversePost;
   }
}
{% endhighlight %}

## Time Complexity
The above algorithm is simply DFS with an extra stack. So time complexity is same as DFS which is O(V+E).

## Applications
- Scheduling jobs from the given dependencies among jobs
- Instruction scheduling
- Ordering of formula cell evaluation when recomputing formula values in spreadsheets
- Logic synthesis
- Determining the order of compilation tasks to perform in makefiles
- Data serialization
- Resolving symbol dependencies in linkers

## UVa Contest Problem Set


### Links
- [Princeton University Lecture Slides](http://algs4.cs.princeton.edu/lectures/42DirectedGraphs.pdf)
- [Washington University Lecture Slides](https://courses.cs.washington.edu/courses/cse326/03wi/lectures/RaoLect20.pdf)
- [geeksforgeeks](http://www.geeksforgeeks.org/topological-sorting/)
</ul>