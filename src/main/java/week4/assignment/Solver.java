package week4.assignment;

import edu.princeton.cs.algorithms.MinPQ;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

import java.util.ArrayList;
import java.util.Collections;

public class Solver {

	private SearchNode current;
	private SearchNode twinCurrent;

	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {

		if (initial == null) throw new IllegalArgumentException();

		current = new SearchNode(initial, null, 0);
		twinCurrent = new SearchNode(initial.twin(), null, 0);

		MinPQ<SearchNode> priorityQueue = new MinPQ<>();
		MinPQ<SearchNode> twinPriorityQueue = new MinPQ<>();

		while (!current.board.isGoal() && !twinCurrent.board.isGoal()) {
			for (Board neighbor : current.board.neighbors()) {
				if (current.previous == null || !neighbor.equals(current.previous.board)) {
					SearchNode next = new SearchNode(neighbor, current, current.moves + 1);
					priorityQueue.insert(next);
				}
			}

			for (Board neighbor : twinCurrent.board.neighbors()) {
				if (twinCurrent.previous == null || !neighbor.equals(twinCurrent.previous.board)) {
					SearchNode next = new SearchNode(neighbor, twinCurrent, twinCurrent.moves + 1);
					twinPriorityQueue.insert(next);
				}
			}

			current = priorityQueue.delMin();
			twinCurrent = twinPriorityQueue.delMin();
		}

	}

	// is the initial board solvable?
	public boolean isSolvable() {
		if (current.board.isGoal()) {
			return true;
		}
		if (twinCurrent.board.isGoal()) {
			return false;
		}

		return false;
	}

	// min number of moves to solve initial board; -1 if unsolvable
	public int moves() {
		if (!isSolvable()) return -1;
		else return current.moves;
	}

	// sequence of boards in a shortest solution; null if unsolvable
	public Iterable<Board> solution() {
		if (!isSolvable()) return null;

		ArrayList<Board> solution = new ArrayList<>();
		SearchNode node = current;
		while (node != null) {
			solution.add(node.board);
			node = node.previous;
		}
		Collections.reverse(solution);

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
