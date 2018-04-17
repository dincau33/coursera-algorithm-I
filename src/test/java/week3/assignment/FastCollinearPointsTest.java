package week3.assignment;

import edu.princeton.cs.introcs.In;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FastCollinearPointsTest {

    @Test
    void numberOfSegments() {
        assertThrows(IllegalArgumentException.class, () -> new FastCollinearPoints(null));
        Point[] ps = getPointsFromTestFile("input3.txt");
        FastCollinearPoints fcp = new FastCollinearPoints(ps);
        assertEquals(0,fcp.numberOfSegments());
        ps = getPointsFromTestFile("input6.txt");
        fcp = new FastCollinearPoints(ps);
        assertEquals(1,fcp.numberOfSegments());
        ps = getPointsFromTestFile("input8.txt");
        fcp = new FastCollinearPoints(ps);
        assertEquals(2,fcp.numberOfSegments());
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