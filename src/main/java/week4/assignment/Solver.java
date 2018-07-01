package week4.assignment;

import edu.princeton.cs.algorithms.MinPQ;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

import java.util.Stack;

public class Solver {

	private final MinPQ<SearchNode> priorityQueue = new MinPQ<>();
	private SearchNode current;

	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {

		if (initial == null) throw new IllegalArgumentException();

		current = new SearchNode(initial, null, 0);

		while (!current.board.isGoal()) {
			for (Board neighbor : current.board.neighbors()) {
				if (current.previous == null || !neighbor.equals(current.previous.board)) {
					SearchNode next = new SearchNode(neighbor, current, current.moves + 1);
					priorityQueue.insert(next);
				}
			}

			current = priorityQueue.delMin();
		}

	}

	// is the initial board solvable?
	public boolean isSolvable() {
		return true;
	}

	// min number of moves to solve initial board; -1 if unsolvable
	public int moves() {
		if (!isSolvable()) return -1;
		else return current.moves;
	}

	// sequence of boards in a shortest solution; null if unsolvable
	public Iterable<Board> solution() {
		if (!isSolvable()) return null;

		Stack<Board> stack = new Stack<>();
		SearchNode node = current;
		while (node != null) {
			stack.push(node.board);
			node = node.previous;
		}

		Stack<Board> solution = new Stack<>();
		while(!stack.empty()) {
			solution.push(stack.pop());
		}

		return solution;
	}

	// solve a slider puzzle (given below)
	public static void main(String[] args) {

		// create initial board from file
		In in = new In(args[0]);
		int n = in.readInt();
		int[][] blocks = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				blocks[i][j] = in.readInt();
		Board initial = new Board(blocks);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
	}

	private class SearchNode implements Comparable<SearchNode> {

		private final Board board;
		private final SearchNode previous;
		private final int moves;
		private final int priority;

		private SearchNode(Board board, SearchNode previous, int moves) {
			if (board == null) throw new IllegalArgumentException();

			this.board = board;
			this.previous = previous;
			this.moves = moves;
			this.priority = this.board.manhattan() + this.moves;
		}

		public int compareTo(SearchNode that) {
			return this.priority - that.priority;
		}
	}
}
