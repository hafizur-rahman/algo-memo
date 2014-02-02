import java.util.Arrays;

public class BiColoring {

	public static void main(String[] args) {
		In in = new In(args[0]);

		while (true) {
			int V = in.readInt();

			if (V == 0)
				break;

			int E = in.readInt();

			Digraph G = new Digraph(V);
			for (int i = 0; i < E; i++) {
				int v = in.readInt();
				int w = in.readInt();

				G.addEdge(v, w);
			}

			int[] color = new int[V];
			Arrays.fill(color, 1);

			// Check whether the graph is bipatrite
			Queue<Integer> q = new Queue<Integer>();
			q.enqueue(0);
			color[0] = 0;

			boolean isBipartite = true;
			while (!q.isEmpty() && isBipartite) {
				int v = q.dequeue();

				for (int w : G.adj(v)) {
					if (color[w] == 1) {
						color[w] = 1 - color[v];

						q.enqueue(w);
					} else if (color[w] == color[v]) {
						isBipartite = false;
						break;
					}
				}
			}

			if (isBipartite) {
				System.out.println("BICOLORABLE.");
			} else {
				System.out.println("NOT BICOLORABLE.");
			}
		}
	}
}
