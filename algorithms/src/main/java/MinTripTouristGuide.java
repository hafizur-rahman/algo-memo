import java.util.Arrays;
import java.util.Comparator;

/**
 * http://www.zzzyk.com/show/8a43714450447eac.htm
 */
public class MinTripTouristGuide {
	private static final Comparator<DirectedEdge> ASC_ORDER = new Comparator<DirectedEdge>() {

		@Override
		public int compare(DirectedEdge e1, DirectedEdge e2) {
			if (e1.weight() == e2.weight()) {
				return 0;
			} else if (e1.weight() < e2.weight()) {
				return 1;
			}

			return -1;
		}
	};

	private static int getTripCount(DirectedEdge[] edges, int start, int end,
			int limit) {
		Arrays.sort(edges, ASC_ORDER);

		int capacity = Integer.MAX_VALUE;
		UF uf = new UF(edges.length);
		for (DirectedEdge edge : edges) {
			uf.union(edge.from(), edge.to());

			if (uf.connected(start, end)) {
				capacity = (int) edge.weight();
				break;
			}
		}

		capacity = capacity - 1;

		return (limit % capacity) == 0 ? (limit / capacity)
				: (limit / capacity + 1);
	}

	public static void main(String[] args) {
		In in = new In(args[0]);

		int V = in.readInt();
		int E = in.readInt();

		DirectedEdge[] edges = new DirectedEdge[E];
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			int weight = in.readInt();

			edges[i] = new DirectedEdge(v, w, weight);
		}

		int start = in.readInt();
		int end = in.readInt();
		int limit = in.readInt();

		int tripCount = getTripCount(edges, start, end, limit);
		System.out.println(tripCount);
	}

}
