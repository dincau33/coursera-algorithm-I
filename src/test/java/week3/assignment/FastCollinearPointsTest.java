package week3.assignment;

import edu.princeton.cs.introcs.In;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

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
	void equidistantContains4Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "equidistant.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(4);
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
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(2);
	}

	@Test
	void input9Contains2Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "input9.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(1);
	}

	@Test
	void input10Contains2Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "input10.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(2);
	}

	@Test
	void input20Contains5Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "input20.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(5);
	}

	@Test
	void input40Contains4Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "input40.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(4);
	}

	@Test
	void input48Contains6Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "input48.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(6);
	}

	@Test
	void input50Contains7Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "input50.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(7);
	}

	@Test
	void input80Contains31Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "input80.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(31);
	}

	@Test
	void input299Contains6Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "input299.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(6);
	}

	@Test
	void input300Contains6Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "input6.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(1);
	}

	@Test
	void horizontal5Contains5Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "horizontal5.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(5);
	}

	@Test
	void horizontal25Contains25Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "horizontal25.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(25);
	}

	@Test
	void horizontal50Contains50Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "horizontal50.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(50);
	}

	@Test
	void horizontal75Contains75Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "horizontal75.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(75);
	}

	@Test
	void horizontal100Contains100Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "horizontal100.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(100);
	}

	@Test
	void vertical5Contains5Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "vertical5.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(5);
	}

	@Test
	void vertical25Contains25Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "vertical25.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(25);
	}

	@Test
	void vertical50Contains50Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "vertical50.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(50);
	}

	@Test
	void vertical75Contains75Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "vertical75.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(75);
	}

	@Test
	void vertical100Contains100Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "vertical100.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(100);
	}

	@Test
	void random23ContainsNoSegment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "random23.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(0);
	}

	@Test
	void random38ContainsNoSegment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "random38.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(0);
	}

	@Test
	void random91ContainsNoSegment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "random91.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(0);
	}

	@Test
	void random152ContainsNoSegment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "random152.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(0);
	}

	@Test
	void inarowContains5Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "inarow.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(5);
	}

	@Test
	void kw1260Contains288Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "kw1260.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(288);
	}

	@Test
	void rs1423Contains443Segment() {
		Point[] ps = getPointsFromFile(FILE_PATH_FOLDER + "rs1423.txt");
		FastCollinearPoints bcp = new FastCollinearPoints(ps);
		assertThat(bcp.numberOfSegments()).isEqualTo(443);
	}
}