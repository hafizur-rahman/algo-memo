---
layout: docs
title: Merge Sort
permalink: /docs/mergesort/
---

{% highlight java %}
public class Merge {
	private static Comparable[] aux; // auxiliary array for merges
	
	public static void sort(Comparable[] a) {
		aux = new Comparable[a.length]; // Allocate space just once.
		sort(a, 0, a.length - 1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
		// Sort a[lo..hi].
		if (hi <= lo) return;
		int mid = lo + (hi - lo)/2;
		
		sort(a, lo, mid); // Sort left half.
		sort(a, mid+1, hi); // Sort right half.
		merge(a, lo, mid, hi); // Merge results (code on page 271).
	}

	public static void merge(Comparable[] a, final int lo, int mid, int hi) {
		// Merge a[lo..mid] with a[mid+1..hi].
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++) // Copy a[lo..hi] to aux[lo..hi].
			aux[k] = a[k];
	
		for (int k = lo; k <= hi; k++) // Merge back to a[lo..hi].
			if (i > mid) a[k] = aux[j++];
			else if (j > hi ) a[k] = aux[i++];
			else if (less(aux[j], aux[i])) a[k] = aux[j++];
			else a[k] = aux[i++];
	}
}
{% endhighlight %}

- This method merges by first copying into the auxiliary array aux[] then merging back to a[].
- In the merge (the second for loop), there are four conditions:
* left half exhausted (take from the right)
* right half exhausted (take from the left)
* current key on right less than current key on left (take from the right)
* current key on right greater than or equal to current key on left (take from the left).