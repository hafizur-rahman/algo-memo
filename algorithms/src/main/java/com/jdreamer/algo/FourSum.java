package com.jdreamer.algo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 4-SUM. Given an array a[] of N integers, the 4-SUM problem is to determine if
 * there exist distinct indices i, j, k, and l such that a[i]+a[j]=a[k]+a[l].
 * Design an algorithm for the 4-SUM problem that takes time proportional to N2
 * (under suitable technical assumptions).
 * 
 * @author bibagimon
 */
public class FourSum {
	static class Pair {
		int i, j;

		public Pair(int i, int j) {
			this.i = Math.min(i, j);
			this.j = Math.max(i, j);
		}

		public int hashCode() {
			return 31 * i + 47 * j;
		}

		public boolean equals(Object o) {
			if (!(o instanceof Pair))
				return false;

			Pair other = (Pair) o;
			return this.i == other.i && this.j == other.j;
		}

		public String toString() {
			return "Pair<" + i + ", " + j + ">";
		}
	}

	public static void main(String[] args) {
		Map<Integer, Set<Pair>> sums = new HashMap<Integer, Set<Pair>>();
		int[] input = { 2, 3, 1, 0, -4, -1 };
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input.length; j++) {
				if (i == j)
					continue;
				
				int sum = input[i] + input[j];
				if (!sums.containsKey(sum)) {
					sums.put(sum, new HashSet<Pair>());
				}
				sums.get(sum).add(new Pair(input[i], input[j]));
			}
		}

		for (int sum : sums.keySet()) {
			Set<Pair> pairs = sums.get(sum);
			if (pairs.size() > 1) {
				System.out.println(pairs);
				for (Pair pair : pairs) {
					System.out.println("sum: " + sum + ", " + pair.i + ", "
							+ pair.j);
				}
			}
		}
	}
}
