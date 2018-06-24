package week4.assignment;

import edu.princeton.cs.introcs.StdRandom;

import java.util.Stack;

public class Board {

	private final int[][] blocks;
	private final int n;
	private int hamming = -1;
	private int manhattan = -1;
	private Board twin = null;

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
		// caching
		if (hamming != -1) {
			return hamming;
		}

		int tmp = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (blocks[i][j] != 0 && !matchGoalBlock(i, j)) tmp++;
			}
		}
		hamming = tmp;
		return hamming;
	}

	private int movesToTargetPosition(int block, int currentRow, int currentCol) {
		int targetRow = (block - 1) / n;
		int targetCol = (block - 1) % n;
		int horizontalMoves = Math.abs(targetRow - currentRow);
		int verticalMoves = Math.abs(targetCol - currentCol);
		return horizontalMoves + verticalMoves;
	}

	// sum of Manhattan distances between blocks and goal
	public int manhattan() {
		//caching
		if (manhattan != -1) {
			return manhattan;
		}

		int tmp = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (blocks[i][j] != 0 && !matchGoalBlock(i, j)) {
					tmp += movesToTargetPosition(blocks[i][j], i, j);
				}
			}
		}
		manhattan = tmp;
		return manhattan;
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

	private int[][] getBlocksCopy() {
		int[][] blocksCopy = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				blocksCopy[i][j] = blocks[i][j];
				}
			}

		return blocksCopy;
	}

	private static void exchBlock(int[][] block, int currentRow, int currentCol, int targetRow, int targetCol) {
		int swap = block[targetRow][targetCol];
		block[targetRow][targetCol] = block[currentRow][currentCol];
		block[currentRow][currentCol] = swap;
	}

	// a blocks that is obtained by exchanging any pair of blocks
	public Board twin() {
		if (twin != null) {
			return twin;
		}

		// Find source block to swap
		boolean sourceBlockSelected = false;
		int sourceRow = -1;
		int sourceCol = -1;
		while (!sourceBlockSelected) {
			sourceRow = StdRandom.uniform(n - 1);
			sourceCol = StdRandom.uniform(n - 1);
			if (blocks[sourceRow][sourceCol] != 0) sourceBlockSelected = true;
		}

		// Find target block to swap
		boolean targetBlockSelected = false;
		int targetRow = -1;
		int targetCol = -1;
		while (!targetBlockSelected) {
			targetRow = StdRandom.uniform(n - 1);
			targetCol = StdRandom.uniform(n - 1);
			if (targetRow != sourceRow && targetCol != sourceCol) {
				if (blocks[targetRow][targetCol] != 0) targetBlockSelected = true;
			}
		}

		int[][] twinBlocks = getBlocksCopy();
		exchBlock(twinBlocks, sourceRow, sourceCol, targetRow, targetCol);

		return new Board(twinBlocks);
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
		Stack<Board> neighbors = new Stack<>();

		// Find empty block
		int emptyBlockRow = -1;
		int emptyBlockCol = -1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (blocks[i][j] == 0) {
					emptyBlockRow = i;
					emptyBlockCol = j;
					break;
				}
			}
		}

		// Create neighbor by swapping empty block left
		if (emptyBlockCol > 0) {
			int[][] blocksCopy = getBlocksCopy();
			exchBlock(blocksCopy, emptyBlockRow, emptyBlockCol, emptyBlockRow, emptyBlockCol - 1);
			neighbors.add(new Board(blocksCopy));
		}

		// Create neighbor by swapping empty block right
		if (emptyBlockCol < n - 1) {
			int[][] blocksCopy = getBlocksCopy();
			exchBlock(blocksCopy, emptyBlockRow, emptyBlockCol, emptyBlockRow, emptyBlockCol + 1);
			neighbors.add(new Board(blocksCopy));
		}

		// Create neighbor by swapping empty block up
		if (emptyBlockRow > 0) {
			int[][] blocksCopy = getBlocksCopy();
			exchBlock(blocksCopy, emptyBlockRow, emptyBlockCol, emptyBlockRow - 1, emptyBlockCol);
			neighbors.add(new Board(blocksCopy));
		}

		// Create neighbor by swapping empty block down
		if (emptyBlockRow < n -1) {
			int[][] blocksCopy = getBlocksCopy();
			exchBlock(blocksCopy, emptyBlockRow, emptyBlockCol, emptyBlockRow + 1, emptyBlockCol);
			neighbors.add(new Board(blocksCopy));
		}

		return neighbors;
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