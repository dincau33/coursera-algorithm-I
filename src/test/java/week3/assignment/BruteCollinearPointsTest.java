package week3.assignment;

import org.junit.jupiter.api.Test;
import edu.princeton.cs.introcs.In;

import static org.junit.jupiter.api.Assertions.*;

class BruteCollinearPointsTest {

	@Test
	void segments() {
		assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(null));
		final Point[] ps1 = new Point[1];
		assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(ps1));
		Point[] ps = getPointsFromTestFile("input3.txt");
		BruteCollinearPoints bcp = new BruteCollinearPoints(ps);
		assertEquals(0, bcp.numberOfSegments());
		ps = getPointsFromTestFile("input6.txt");
		bcp = new BruteCollinearPoints(ps);
		assertEquals(5, bcp.numberOfSegments());
		ps = getPointsFromTestFile("input8.txt");
		bcp = new BruteCollinearPoints(ps);
		assertEquals(2, bcp.numberOfSegments());
	}

	static Point[] getPointsFromTestFile(String fileName) {
		In in = new In("src/test/resources/week3/assignment/collinear/" + fileName);
		int N = in.readInt();
		Point[] points = new Point[N];
		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}
		return points;
	}
}