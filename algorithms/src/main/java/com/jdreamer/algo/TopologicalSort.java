package com.jdreamer.algo;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * http://algs4.cs.princeton.edu/lectures/42DirectedGraphs-2x2.pdf
 */
public class TopologicalSort {
    private boolean[] marked;
    private boolean[] onStack;
    private Stack<Integer> reversePostOrder;
    private boolean hasCycle = false;


    public TopologicalSort(Digraph G) {
        reversePostOrder = new Stack<Integer>();
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        onStack[v] = true;

        for (int w : G.adj(v)) {
            if (hasCycle)
                return;

            if (!marked[w]) {
                dfs(G, w);
            } else if (onStack[w]) {
                hasCycle = true;
            }
        }

        reversePostOrder.push(v);
        onStack[v] = false;
    }

    public Iterable<Integer> reversePostOrder() {
        return hasCycle ? null : reversePostOrder;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    private static void example1() {
        String[] dependencies = {"Algorithms/Theoretical CS/Databases/Scientific Computing",
                "Introduction to CS/Advanced Programming/Algorithms",
                "Advanced Programming/Scientific Computing",
                "Scientific Computing/Computational Biology",
                "Theoretical CS/Computational Biology/Artificial Intelligence",
                "Linear Algebra/Theoretical CS",
                "Calculus/Linear Algebra",
                "Artificial Intelligence/Neural Networks/Robotics/Machine Learning",
                "Machine Learning/Neural Networks"};

        List<String> courseList = new ArrayList<String>();
        for (String s : dependencies) {
            String[] courses = s.split("/");

            for (int i = 0; i < courses.length; i++) {
                if (!courseList.contains(courses[i])) {
                    courseList.add(courses[i]);
                }
            }
        }

        Digraph G = new Digraph(courseList.size());
        for (String s : dependencies) {
            String[] courses = s.split("/");
            int v = courseList.indexOf(courses[0]);
            for (int i = 1; i < courses.length; i++) {

                int w = courseList.indexOf(courses[i]);
                G.addEdge(v, w);
            }
        }

        TopologicalSort sort = new TopologicalSort(G);
        if (sort.hasCycle()) {
            System.out.println("Graph has cycle!");
        } else {
            for (int courseId : sort.reversePostOrder()) {
                System.out.println(courseList.get(courseId));
            }
        }
    }

    public static void main(String[] args) {
        example1();
    }
}
