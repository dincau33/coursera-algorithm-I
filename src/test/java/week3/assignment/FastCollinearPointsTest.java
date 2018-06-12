package week3.assignment;

import edu.princeton.cs.introcs.In;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FastCollinearPointsTest {

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
		assertThrows(IllegalArgumentException.class, () -> new FastCollinearPoints(null));
	}

	@Test
	void failToFindCollinearPointsOfEmptyPointsList() {
		final Point[] ps = new Point[1];
		assertThrows(IllegalArgumentException.class, () -> new FastCollinearPoints(ps));
	}

	@Test
	void failToFindCollinearPointsOfPointsListContainingANullPoint() {
		final Point[] ps = new Point[2];
		ps[0] = new Point(0, 0);
		ps[1] = null;
		assertThrows(IllegalArgumentException.class, () -> new FastCollinearPoints(ps));
	}

	@Test
	void failToFindCollinearPointsOfTwoPointsListContainingDuplicatePoints() {
		final Point[] ps = new Point[2];
		ps[0] = new Point(0, 0);
		ps[1] = new Point(0, 0);
		assertThrows(IllegalArgumentException.class, () -> new FastCollinearPoints(ps));
	}

	@Test
	void failToFindCollinearPointsOfThreePointsListContainingDuplicatePoints() {
		final Point[] ps = new Point[3];
		ps[0] = new Point(0, 0);
		ps[1] = new Point(1, 0);
		ps[2] = new Point(0, 0);
		assertThrows(IllegalArgumentException.class, () -> new FastCollinearPoints(ps));
	}

	@Test
	void input3ContainsZeroSegment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "input3.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(0);
	}

	@Test
	void input6Contains5Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "input6.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(1);
	}

	@Test
	void input8Contains2Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "input8.txt");
		BruteCollinearPoints bcp = new BruteCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(2);
	}
}