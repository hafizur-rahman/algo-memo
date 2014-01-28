public class SlashMaze {
	private static final int[] DX = { -1, 1, 0, 0 };
	private static final int[] DY = { 0, 0, -1, 1 };

	private int W, H;
	private int[][] nodes;
	private boolean[][] visited;
	private boolean[][] hash;
	private int grids;
	private int cycles;

	public SlashMaze(int w, int h) {
		this.W = 3 * w;
		this.H = 3 * h;

		nodes = new int[H][W];
		visited = new boolean[H][W];
		hash = new boolean[H][W];

		for (int i = 0; i < H; i++) {
			nodes[i] = new int[W];
			visited[i] = new boolean[W];
			hash[i] = new boolean[W];
		}
	}

	private void addEdges(int i, int j, char c) {
		if (c == '\\') {
			nodes[i][j] = nodes[i - 1][j - 1] = nodes[i + 1][j + 1] = 1;
		} else {
			nodes[i][j] = nodes[i - 1][j + 1] = nodes[i + 1][j - 1] = 1;
		}
	}

	private void colorBoundary() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (isBoundary(i, j) && !visited[i][j] && nodes[i][j] == 0) {
					floodFill(i, j, 2);
				}
			}
		}
	}

	private void floodFill(int x, int y, int color) {
		visited[x][y] = true;
		nodes[x][y] = color;

		for (int i = 0; i < 4; i++) {
			int nx = x + DX[i];
			int ny = y + DY[i];

			if (!isOut(nx, ny) && !visited[nx][ny] && nodes[nx][ny] == 0) {
				floodFill(nx, ny, color);
			}
		}

	}

	private void solve() {
		int maxGrids = -1;

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (nodes[i][j] == 0 && !hash[i][j]) {
					cycles++;
					grids = 0;

					dfs(i, j);

					maxGrids = Math.max(maxGrids, grids);
				}
			}
		}

		grids = maxGrids;
	}

	private void dfs(int x, int y) {
		grids++;

		hash[x][y] = true;

		for (int i = 0; i < 4; ++i) {
			int nx = x + DX[i];
			int ny = y + DY[i];

			if (!isOut(nx, ny) && nodes[nx][ny] == 0 && !hash[nx][ny]) {
				dfs(nx, ny);
			}
		}

	}

	private boolean isOut(int x, int y) {
		return (x < 0 || y < 0 || x >= H || y >= W);
	}

	private boolean isBoundary(int x, int y) {
		return (x == 0 || y == 0 || x == H - 1 || y == W - 1);
	}

	public static void main(String[] args) {
		In in = new In(args[0]);

		int caseNo = 0;
		while (true) {
			caseNo++;

			int w = in.readInt();
			int h = in.readInt();

			if (w == 0 || h == 0)
				break;

			SlashMaze maze = new SlashMaze(w, h);

			for (int i = 1; i < 3 * h; i += 3) {
				in.readChar(); // new line

				for (int j = 1; j < 3 * w; j += 3) {
					char c = in.readChar();
					maze.addEdges(i, j, c);
				}
			}

			maze.colorBoundary();
			maze.solve();

			System.out.println("Maze #" + caseNo + ":");
			if (maze.cycles > 0) {
				System.out.println(maze.cycles
						+ " Cycles; the longest has length " + maze.grids / 3);
			} else {
				System.out.println("There are no cycles.");
			}

			System.out.println();
		}
	}
}
