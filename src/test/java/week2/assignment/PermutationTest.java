package week2.assignment;

import org.junit.jupiter.api.Test;

import java.io.*;

class PermutationTest {

	private static final String FILE_PATH_FOLDER = "./src/test/resources/week2/assignment/queues";

	private void executeMain(int k, String filePath) throws FileNotFoundException {
		String[] args = new String[1];
		args[0] = String.valueOf(k);
		final InputStream original = System.in;
		final FileInputStream fips = new FileInputStream(new File(filePath));
		System.setIn(fips);
		Permutation.main(args);
		System.setIn(original);
	}

	@Test
	void zeroPermutationOfDistinctInput() throws IOException {
		System.out.println("Permutation k=0 input=distinct.txt");
		executeMain(0, FILE_PATH_FOLDER + "/distinct.txt");
		System.out.println();
	}

	@Test
	void ninePermutationOfTaleInput() throws IOException {
		System.out.println("Permutation k=9 input=tale.txt");
		executeMain(9, FILE_PATH_FOLDER + "/tale.txt");
		System.out.println();
	}
}
