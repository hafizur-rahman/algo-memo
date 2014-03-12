---
layout: docs
title: Heap Sort
permalink: /docs/heapsort/
---
In a heap, the parent of the node in position k is in position ⎣k /2⎦ and, conversely,
the two children of the node in position k are in positions 2k and 2k + 1. Instead of using
explicit links (as in the binary tree structures), we can travel up and down by doing 
simple arithmetic on array indices: to move up the tree from a[k] we set k
to k/2; to move down the tree we set k to 2*k or 2*k+1.

The heapsort algorithm can be divided into two parts.

In the first step, a heap is built out of the data. The heap is often placed in an array with the layout of a complete binary tree. The complete binary tree maps the binary tree structure into the array indices; each array index represents a node; the index of the node's parent, left child branch, or right child branch are simple expressions.
  
In the second step, a sorted array is created by repeatedly removing the largest element from the heap (the root of the heap), and inserting it into the array. The heap is updated after each removal to maintain the heap. Once all objects have been removed from the heap, the result is a sorted array.

{% highlight java %}
public class Heap {
	/**
     * Rearranges the array in ascending order, using the natural order.
     * @param pq the array to be sorted
     */
    public static void sort(Comparable[] pq) {
        int N = pq.length;
        for (int k = N/2; k >= 1; k--)
            sink(pq, k, N);
        while (N > 1) {
            exch(pq, 1, N--);
            sink(pq, 1, N);
        }
    }

   /*
    * Helper functions to restore the heap invariant.
    */
    private static void sink(Comparable[] pq, int k, int N) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

   /*
    * Helper functions for comparisons and swaps.
    * Indices are "off-by-one" to support 1-based indexing.
    */
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }
}
{% endhighlight %}