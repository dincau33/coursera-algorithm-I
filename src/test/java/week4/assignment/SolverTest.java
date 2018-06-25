package week4.assignment;

import edu.princeton.cs.introcs.In;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

	private static final String FILE_PATH_FOLDER = "./src/test/resources/week4/assignment/8puzzle/";

	private static Board getBoardFromFile(String filePath) {
		In in = new In(filePath);
		int n = in.readInt();
		int[][] blocks = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				blocks[i][j] = in.readInt();
			}
		}
		return new Board(blocks);
	}

	@Test
	void failToSolveNullBoard() {
		assertThrows(IllegalArgumentException.class, () -> new Solver(null));
	}

	@Test
	void puzzle3x3_00IsAlreadySolved() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-00.txt");
		Solver solver = new Solver(board);

		assertThat(solver.isSolvable()).isTrue();
		assertThat(solver.moves()).isEqualTo(0);
		assertThat(solver.solution()).isEmpty();
	}

	@Test
	void puzzle3x3_01CanBeSolvedIn1Move() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-01.txt");
		Solver solver = new Solver(board);

		assertThat(solver.isSolvable()).isTrue();
		assertThat(solver.moves()).isEqualTo(1);
		assertThat(solver.solution()).hasSize(1);
		assertThat(solver.solution()).contains(board);
	}

	@Test
	void puzzle3x3_02CanBeSolvedIn2Moves() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-02.txt");
		Solver solver = new Solver(board);

		assertThat(solver.isSolvable()).isTrue();
		assertThat(solver.moves()).isEqualTo(2);
		assertThat(solver.solution()).hasSize(2);
		assertThat(solver.solution()).contains(board);

		for (Board b : solver.solution()) {
			System.out.println(b);
		}
	}

	@Test
	void puzzle3x3_03CanBeSolvedIn3Moves() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-03.txt");
		Solver solver = new Solver(board);

		assertThat(solver.isSolvable()).isTrue();
		assertThat(solver.moves()).isEqualTo(3);
		assertThat(solver.solution()).hasSize(3);
		assertThat(solver.solution()).contains(board);
	}

	@Test
	void puzzle3x3_05CanBeSolvedIn5Moves() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-05.txt");
		Solver solver = new Solver(board);

		assertThat(solver.isSolvable()).isTrue();
		assertThat(solver.moves()).isEqualTo(5);
		assertThat(solver.solution()).hasSize(5);
		assertThat(solver.solution()).contains(board);
	}

	@Test
	void puzzle3x3_09CanBeSolvedIn9Moves() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-09.txt");
		Solver solver = new Solver(board);

		assertThat(solver.isSolvable()).isTrue();
		assertThat(solver.moves()).isEqualTo(9);
		assertThat(solver.solution()).hasSize(9);
		assertThat(solver.solution()).contains(board);
	}

	@Test
	void puzzle3x3_10CanBeSolvedIn10Moves() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-10.txt");
		Solver solver = new Solver(board);

		assertThat(solver.isSolvable()).isTrue();
		assertThat(solver.moves()).isEqualTo(10);
		assertThat(solver.solution()).hasSize(10);
		assertThat(solver.solution()).contains(board);
	}

	@Test
	void puzzle3x3_11CanBeSolvedIn11Moves() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-11.txt");
		Solver solver = new Solver(board);

		assertThat(solver.isSolvable()).isTrue();
		assertThat(solver.moves()).isEqualTo(11);
		assertThat(solver.solution()).hasSize(11);
		assertThat(solver.solution()).contains(board);

		for (Board b : solver.solution()) {
			System.out.println(b);
		}
	}

	@Test
	void puzzle3x3_15CanBeSolvedIn15Moves() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-15.txt");
		Solver solver = new Solver(board);

		assertThat(solver.isSolvable()).isTrue();
		assertThat(solver.moves()).isEqualTo(15);
		assertThat(solver.solution()).hasSize(15);
		assertThat(solver.solution()).contains(board);

		for (Board b : solver.solution()) {
			System.out.println(b);
		}
	}

	@Test
	void puzzle3x3_20CanBeSolvedIn20Moves() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-20.txt");
		Solver solver = new Solver(board);

		assertThat(solver.isSolvable()).isTrue();
		assertThat(solver.moves()).isEqualTo(20);
		assertThat(solver.solution()).hasSize(20);
		assertThat(solver.solution()).contains(board);

		for (Board b : solver.solution()) {
			System.out.println(b);
		}
	}

	@Test
	void puzzle4x4_00IsAlreadySolved() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle4x4-00.txt");
		Solver solver = new Solver(board);

		assertThat(solver.isSolvable()).isTrue();
		assertThat(solver.moves()).isEqualTo(0);
		assertThat(solver.solution()).isEmpty();
	}

	@Test
	void puzzle4x4_02CanBeSolvedIn2Moves() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle4x4-02.txt");
		Solver solver = new Solver(board);

		assertThat(solver.isSolvable()).isTrue();
		assertThat(solver.moves()).isEqualTo(2);
		assertThat(solver.solution()).hasSize(2);
		assertThat(solver.solution()).contains(board);
	}

//	@Test
//	void puzzle4x4_20CanBeSolvedIn20Moves() {
//		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle4x4-20.txt");
//		Solver solver = new Solver(board);
//
//		assertThat(solver.isSolvable()).isTrue();
//		assertThat(solver.moves()).isEqualTo(20);
//		assertThat(solver.solution()).hasSize(20);
//		assertThat(solver.solution()).contains(board);
//	}
}