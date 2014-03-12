---
layout: docs
title: Quick Sort
prev_section: insertionsort
next_section: shufflesort
permalink: /docs/quicksort/
---

{% highlight java %}
public class Quick {
	public static void sort(Comparable[] a)	{
		StdRandom.shuffle(a); // Eliminate dependence on input.
		sort(a, 0, a.length - 1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo) return;
		int j = partition(a, lo, hi); // Partition (see page 291).
		sort(a, lo, j-1); // Sort left part a[lo .. j-1].
		sort(a, j+1, hi); // Sort right part a[j+1 .. hi].
	}
	private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }
}
{% endhighlight %}

The crux of the method is the partitioning process, which rearranges the array to
make the following three conditions hold:

- The entry a[j] is in its final place in the array, for some j.
- No entry in a[lo] through a[j-1] is greater than a[j].
- No entry in a[j+1] through a[hi] is less than a[j].

We achieve a complete sort by partitioning, then recursively applying the method.

{% highlight java %}	
	private static int partition(Comparable[] a, int lo, int hi) {
		// Partition into a[lo..i-1], a[i], a[i+1..hi].
		int i = lo, j = hi+1; // left and right scan indices
		Comparable v = a[lo]; // partitioning item
		
		while (true) {
			// Scan right, scan left, check for scan complete, and exchange.
			while (less(a[++i], v)) if (i == hi) break;
			while (less(v, a[--j])) if (j == lo) break;
			if (i >= j) break;
			exch(a, i, j);
		}
	
		exch(a, lo, j); // Put v = a[j] into position
		
		return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
	}	
{% endhighlight %}

This code partitions on the item v in a[lo]. The main loop exits when the scan indices i and j cross.
Within the loop, we increment i while a[i] is less than v and decrement j while a[j] is greater than
v, then do an exchange to maintain the invariant property that no entries to the left of i are greater
than v and no entries to the right of j are smaller than v. Once the indices meet, we complete the
partitioning by exchanging a[lo] with a[j] (thus leaving the partitioning value in a[j]).