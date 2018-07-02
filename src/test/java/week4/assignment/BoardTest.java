package week4.assignment;

import edu.princeton.cs.introcs.In;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoardTest {

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
	void failToCreateBoardIfBlocksIsNull() {
		assertThrows(IllegalArgumentException.class, () -> new Board(null));
	}

	@Test
	void failToCreateBoardIfBlocksDimensionLowerThan2() {
		int[][] blocks = new int[1][1];
		assertThrows(IllegalArgumentException.class, () -> new Board(blocks));
	}

	@Test
	void failToCreateBoardIfBlocksDimensionHigherThan127() {
		int[][] blocks = new int[128][128];
		assertThrows(IllegalArgumentException.class, () -> new Board(blocks));
	}

	@Test
	void dimensionOfPuzzle3x3_00Is3() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-00.txt");
		assertThat(board.dimension()).isEqualTo(3);
	}

	@Test
	void hammingOfPuzzle3x3_00Is0() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-00.txt");
		assertThat(board.hamming()).isEqualTo(0);
	}

	@Test
	void hammingOfOfPuzzle3x3_01Is1() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-01.txt");
		assertThat(board.hamming()).isEqualTo(1);
	}

	@Test
	void hammingOfOfPuzzle3x3_10Is8() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-10.txt");
		assertThat(board.hamming()).isEqualTo(6);
	}

	@Test
	void manhattanOfPuzzle3x3_00Is0() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-00.txt");
		assertThat(board.manhattan()).isEqualTo(0);
	}

	@Test
	void manathanOfOfPuzzle3x3_01Is1() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-01.txt");
		assertThat(board.manhattan()).isEqualTo(1);
	}

	@Test
	void manathanOfOfPuzzle3x3_02Is2() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-02.txt");
		assertThat(board.manhattan()).isEqualTo(2);
	}

	@Test
	void manhattanOfOfPuzzle3x3_10Is10() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-10.txt");
		assertThat(board.manhattan()).isEqualTo(10);
	}

	@Test
	void manathanOfOfPuzzle3x3_20Is12() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-20.txt");
		assertThat(board.manhattan()).isEqualTo(12);
	}

	@Test
	void manathanOfOfPuzzle3x3_31Is21() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-31.txt");
		assertThat(board.manhattan()).isEqualTo(21);
	}

	@Test
	void Puzzle3x3_00IsGoal() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-00.txt");
		assertThat(board.isGoal()).isTrue();
	}

	@Test
	void Puzzle3x3_01IsNotGoal() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-01.txt");
		assertThat(board.isGoal()).isFalse();
	}

	@Test
	void Puzzle3x3_10IsNotGoal() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-10.txt");
		assertThat(board.isGoal()).isFalse();
	}

	@Test
	void nullIsNotTheSameAsThisBoard() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-00.txt");
		assertThat(board.equals(null)).isFalse();
	}

	@Test
	void ObjectInstanceIsNotTheSameAsThisBoard() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-00.txt");
		assertThat(board.equals(new Object())).isFalse();
	}

	@Test
	void BoardWithDifferentSizeIsNotTheSameAsThisBoard() {
		Board board1 = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-00.txt");
		Board board2 = getBoardFromFile(FILE_PATH_FOLDER + "puzzle4x4-00.txt");
		assertThat(board1.equals(board2)).isFalse();
	}

	@Test
	void Puzzle3x3_01BoardIsNotTheSameAsThisBoard() {
		Board board1 = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-00.txt");
		Board board2 = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-01.txt");
		assertThat(board1.equals(board2)).isFalse();
	}

	@Test
	void thisBoardIsEqualToItself() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-00.txt");
		assertThat(board.equals(board)).isTrue();
	}

	@Test
	void Puzzle3x3_00BoardIsNotTheSameAsThisBoard() {
		Board board1 = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-00.txt");
		Board board2 = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-00.txt");
		assertThat(board1.equals(board2)).isTrue();
	}

	@Test
	void hammingOfPuzzle3x3_00AfterTwinGenerationIs2(){
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-00.txt");
		assertThat(board.twin().hamming()).isEqualTo(2);
	}

	@Test
	void hammingOfPuzzle4x4_00AfterTwinGenerationIs2(){
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle4x4-00.txt");
		assertThat(board.twin().hamming()).isEqualTo(2);
	}

	@Test
	void Puzzle3x3_00Has2Neighbors() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-00.txt");
		assertThat(board.neighbors()).hasSize(2);
	}

	@Test
	void Puzzle3x3_01HasTheGoalBoardAsNeighbors() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-01.txt");
		Board goalBoard = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-00.txt");

		Iterable<Board> neighbors = board.neighbors();

		assertThat(neighbors).hasSize(3);

		boolean containsGoalBoard = false;
		for(Board b:neighbors) {
			if (b.equals(goalBoard)) {
				containsGoalBoard = true;
				break;
			}
		}

		assertThat(containsGoalBoard).isTrue();
	}

	@Test
	void Puzzle3x3_02Has4Neighbors() {
		Board board = getBoardFromFile(FILE_PATH_FOLDER + "puzzle3x3-02.txt");
		assertThat(board.neighbors()).hasSize(4);
	}
}