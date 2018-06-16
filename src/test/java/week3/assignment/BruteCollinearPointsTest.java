package week3.assignment;

import org.junit.jupiter.api.Test;
import edu.princeton.cs.introcs.In;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BruteCollinearPointsTest {

	private static final String FILE_PATH_FOLDER = "./src/test/resources/week3/assignment/collinear/";

	private static Point[] getPointsFromFile(String filePath) {
		In in = new In(filePath);
		int N = in.readInt();
		Point[] points = new Point[N];
		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}
		return points;
	}

	@Test
	void failToFindCollinearPointsOfNullPointsList() {
		assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(null));
	}

	@Test
	void failToFindCollinearPointsOfEmptyPointsList() {
		final Point[] ps = new Point[1];
		assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(ps));
	}

	@Test
	void failToFindCollinearPointsOfPointsListContainingANullPoint() {
		final Point[] ps = new Point[2];
		ps[0] = new Point(0, 0);
		ps[1] = null;
		assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(ps));
	}

	@Test
	void failToFindCollinearPointsOfTwoPointsListContainingDuplicatePoints() {
		final Point[] ps = new Point[2];
		ps[0] = new Point(0, 0);
		ps[1] = new Point(0, 0);
		assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(ps));
	}

	@Test
	void failToFindCollinearPointsOfThreePointsListContainingDuplicatePoints() {
		final Point[] ps = new Point[3];
		ps[0] = new Point(0, 0);
		ps[1] = new Point(1, 0);
		ps[2] = new Point(0, 0);
		assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(ps));
	}

	@Test
	void equidistantContains4Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "equidistant.txt");
		BruteCollinearPoints bcp = new BruteCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(4);
	}

	@Test
	void input3ContainsZeroSegment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "input3.txt");
		BruteCollinearPoints bcp = new BruteCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(0);
	}

	@Test
	void input6Contains5Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "input6.txt");
		BruteCollinearPoints bcp = new BruteCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(5);
	}

	@Test
	void input8Contains2Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "input8.txt");
		BruteCollinearPoints bcp = new BruteCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(2);
	}

	@Test
	void input40Contains4Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "input40.txt");
		BruteCollinearPoints bcp = new BruteCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(4);
	}

	@Test
	void input48Contains6Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "input48.txt");
		BruteCollinearPoints bcp = new BruteCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(6);
	}

	@Test
	void horizontal5Contains5Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "horizontal5.txt");
		BruteCollinearPoints bcp = new BruteCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(5);
	}

	@Test
	void horizontal25Contains25Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "horizontal25.txt");
		BruteCollinearPoints bcp = new BruteCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(25);
	}

	@Test
	void vertical5Contains5Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "vertical5.txt");
		BruteCollinearPoints bcp = new BruteCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(5);
	}

	@Test
	void vertical25Contains25Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "vertical25.txt");
		BruteCollinearPoints bcp = new BruteCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(25);
	}

	@Test
	void random23ContainsNoSegment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "random23.txt");
		BruteCollinearPoints bcp = new BruteCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(0);
	}

	@Test
	void random38ContainsNoSegment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "random38.txt");
		BruteCollinearPoints bcp = new BruteCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(0);
	}

}