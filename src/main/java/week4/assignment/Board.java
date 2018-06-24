package week4.assignment;

public class Board {

	private final int[][] blocks;
	private final int n;
	private int hamming = -1;
	private int manhattan = -1;

	private static void validateBlocks(int[][] blocks) {
		if (blocks == null) throw new IllegalArgumentException();
		if (blocks.length < 2) throw new IllegalArgumentException();
		if (blocks.length > 127) throw new IllegalArgumentException();
	}

	// construct a blocks from an n-by-n array of blocks
	// (where blocks[i][j] = block in row i, column j)
	public Board(int[][] blocks) {

		validateBlocks(blocks);

		this.blocks = blocks;
		n = blocks.length;
	}

	// blocks dimension n
	public int dimension() {
		return n;
	}

	private boolean matchGoalBlock(int row, int col) {
		if (row == n - 1 && col == n - 1) {
			return blocks[row][col] == 0;
		} else {
			return blocks[row][col] == row * n + col + 1;
		}
	}

	// number of blocks out of place
	public int hamming() {
		if (hamming != -1) {
			return hamming;
		} else {
			int tmp = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (blocks[i][j] != 0 && !matchGoalBlock(i, j)) tmp++;
				}
			}
			hamming = tmp;
			return hamming;
		}
	}

	private int movesToReachRightPosition(int block, int row, int col) {
		int targetRow = (block - 1) / n;
		int targetCol = (block - 1) % n;
		int horizontalMove = Math.abs(targetRow - row);
		int verticalMove = Math.abs(targetCol - col);
		return horizontalMove + verticalMove;
	}

	// sum of Manhattan distances between blocks and goal
	public int manhattan() {
		if (manhattan != -1) {
			return manhattan;
		} else {
			int tmp = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (blocks[i][j] != 0 && !matchGoalBlock(i, j)) {
						tmp += movesToReachRightPosition(blocks[i][j], i, j);
					}
				}
			}
			manhattan = tmp;
			return manhattan;
		}
	}

	// is this blocks the goal blocks?
	public boolean isGoal() {
		boolean isGoal = true;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!matchGoalBlock(i, j)) {
					isGoal = false;
					break;
				}
			}
		}
		return isGoal;
	}

	// a blocks that is obtained by exchanging any pair of blocks
	public Board twin() {
		return null;
	}

	// does this blocks equal y?
	public boolean equals(Object y) {
		if (y == null) return false;

		if (y.getClass() != this.getClass()) return false;

		if (y == this) return true;

		Board that = (Board) y;

		if (that.dimension() != this.dimension()) return false;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (that.blocks[i][j] != this.blocks[i][j]) {
					return false;
				}
			}
		}

		return true;
	}

	// all neighboring boards
	public Iterable<Board> neighbors() {
		return null;
	}

	// string representation of this blocks (in the output format specified below)
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(n).append("\n");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				s.append(String.format("%2d ", blocks[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}
}