package week1.assignment;

import java.io.*;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.*;

class PercolationTest {

	private static final String FILE_PATH_FOLDER = "./src/test/resources/week1/assignment/percolation/";

	@Test
	void failToCreatePercolationGridIfNLowerThan1() {
		assertThrows(IllegalArgumentException.class, () -> new Percolation(0));
	}

	@Test
	void failToOpenOutOfBoundSite() {
		Percolation p = new Percolation(3);

		assertThrows(IllegalArgumentException.class, () -> p.open(0, 1));
		assertThrows(IllegalArgumentException.class, () -> p.open(4, 1));
		assertThrows(IllegalArgumentException.class, () -> p.open(1, 0));
		assertThrows(IllegalArgumentException.class, () -> p.open(1, 4));
	}

	@Test
	void assessIfOutOfBoundSiteIsFullThrowsException() {
		Percolation p = new Percolation(2);

		assertThrows(IllegalArgumentException.class, () -> p.isFull(0, 1));
	}

	@Test
	void siteIsFullIfOpenAndConnectedToTopOpenSite() {
		Percolation p = new Percolation(2);

		assertThat(p.isFull(1, 1)).isFalse();
		assertThat(p.isFull(2, 1)).isFalse();


		p.open(1, 1);

		assertThat(p.isFull(1, 1)).isTrue();
		assertThat(p.isFull(2, 1)).isFalse();


		p.open(2, 1);

		assertThat(p.isFull(1, 1)).isTrue();
		assertThat(p.isFull(2, 1)).isTrue();
	}

	private Percolation createGridFromFile(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		int n = sc.nextInt();

		Percolation p = new Percolation(n);

		while (sc.hasNextLine()) {
			int row = sc.nextInt();
			int col = sc.nextInt();
			p.open(row, col);
		}

		return p;
	}

	@Test
	void gridBasedOnFileInput1Percolates() throws FileNotFoundException {
		File input1 = new File(FILE_PATH_FOLDER + "/input1.txt");
		Percolation p = createGridFromFile(input1);

		assertThat(p.percolates()).isTrue();
	}

	@Test
	void gridBasedOnFileInput1NoDoesNotPercolates() throws FileNotFoundException {
		File input1No = new File(FILE_PATH_FOLDER + "/input1-no.txt");
		Percolation p = createGridFromFile(input1No);

		assertThat(p.percolates()).isFalse();
	}

	@Test
	void gridBasedOnFileInput2Percolates() throws FileNotFoundException {
		File input2 = new File(FILE_PATH_FOLDER + "/input2.txt");
		Percolation p = createGridFromFile(input2);

		assertThat(p.percolates()).isTrue();
	}

	@Test
	void gridBasedOnFileInput2NoDoesNotPercolates() throws FileNotFoundException {
		File input2No = new File(FILE_PATH_FOLDER + "/input2-no.txt");
		Percolation p = createGridFromFile(input2No);

		assertThat(p.percolates()).isFalse();
	}

	@Test
	void gridBasedOnFileInput3Percolates() throws FileNotFoundException {
		File input3 = new File(FILE_PATH_FOLDER + "/input3.txt");
		Percolation p = createGridFromFile(input3);

		assertThat(p.percolates()).isTrue();
	}

	@Test
	void gridBasedOnFileInput4Percolates() throws FileNotFoundException {
		File input4 = new File(FILE_PATH_FOLDER + "/input4.txt");
		Percolation p = createGridFromFile(input4);

		assertThat(p.percolates()).isTrue();
	}

	@Test
	void gridBasedOnFileInput5Percolates() throws FileNotFoundException {
		File input5 = new File(FILE_PATH_FOLDER + "/input5.txt");
		Percolation p = createGridFromFile(input5);

		assertThat(p.percolates()).isTrue();
	}

	@Test
	void gridBasedOnFileInput6Percolates() throws FileNotFoundException {
		File input6 = new File(FILE_PATH_FOLDER + "/input6.txt");
		Percolation p = createGridFromFile(input6);

		assertThat(p.percolates()).isTrue();
	}

	@Test
	void gridBasedOnFileInput7Percolates() throws FileNotFoundException {
		File input7 = new File(FILE_PATH_FOLDER + "/input7.txt");
		Percolation p = createGridFromFile(input7);

		assertThat(p.percolates()).isTrue();
	}

	@Test
	void gridBasedOnFileInput8Percolates() throws FileNotFoundException {
		File input8 = new File(FILE_PATH_FOLDER + "/input8.txt");
		Percolation p = createGridFromFile(input8);

		assertThat(p.percolates()).isTrue();
	}

	@Test
	void gridBasedOnFileInput8DupsPercolates() throws FileNotFoundException {
		File input8Dups = new File(FILE_PATH_FOLDER + "/input8-dups.txt");
		Percolation p = createGridFromFile(input8Dups);

		assertThat(p.percolates()).isTrue();
	}

	@Test
	void gridBasedOnFileInput8NoDoesNotPercolate() throws FileNotFoundException {
		File input8No = new File(FILE_PATH_FOLDER + "/input8-no.txt");
		Percolation p = createGridFromFile(input8No);

		assertThat(p.percolates()).isFalse();
	}

	@Test
	void gridBasedOnFileInput10Percolates() throws FileNotFoundException {
		File input10 = new File(FILE_PATH_FOLDER + "/input10.txt");
		Percolation p = createGridFromFile(input10);

		assertThat(p.percolates()).isTrue();
	}

	@Test
	void gridBasedOnFileInput10NoDoesNotPercolate() throws FileNotFoundException {
		File input10No = new File(FILE_PATH_FOLDER + "/input10-no.txt");
		Percolation p = createGridFromFile(input10No);

		assertThat(p.percolates()).isFalse();
	}

	@Test
	void gridBasedOnFileInput20Percolates() throws FileNotFoundException {
		File input20 = new File(FILE_PATH_FOLDER + "/input20.txt");
		Percolation p = createGridFromFile(input20);

		assertThat(p.percolates()).isTrue();
	}

	@Test
	void gridBasedOnFileInput50Percolates() throws FileNotFoundException {
		File input50 = new File(FILE_PATH_FOLDER + "/input50.txt");
		Percolation p = createGridFromFile(input50);

		assertThat(p.percolates()).isTrue();
	}

	@Test
	void gridBasedOnFileJerry47Percolates() throws FileNotFoundException {
		File jerry47 = new File(FILE_PATH_FOLDER + "/jerry47.txt");
		Percolation p = createGridFromFile(jerry47);

		assertThat(p.percolates()).isTrue();
	}

	@Test
	void gridBasedOnFileSedgewick60Percolates() throws FileNotFoundException {
		File sedgewick60 = new File(FILE_PATH_FOLDER + "/sedgewick60.txt");
		Percolation p = createGridFromFile(sedgewick60);

		assertThat(p.percolates()).isTrue();
	}

	@Test
	void gridBasedOnFileSnake13Percolates() throws FileNotFoundException {
		File snake13 = new File(FILE_PATH_FOLDER + "/snake13.txt");
		Percolation p = createGridFromFile(snake13);

		assertThat(p.percolates()).isTrue();
	}

	@Test
	void gridBasedOnFileSnake101Percolates() throws FileNotFoundException {
		File snake101 = new File(FILE_PATH_FOLDER + "/snake101.txt");
		Percolation p = createGridFromFile(snake101);

		assertThat(p.percolates()).isTrue();
	}

	@Test
	void gridBasedOnFileWayne98Percolates() throws FileNotFoundException {
		File wayne98 = new File(FILE_PATH_FOLDER + "/wayne98.txt");
		Percolation p = createGridFromFile(wayne98);

		assertThat(p.percolates()).isTrue();
	}

}
