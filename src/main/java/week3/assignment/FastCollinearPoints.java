package week3.assignment;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private ArrayList<LineSegment> segments = new ArrayList<>();

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();

        if (hasNull(points)) {
            throw new IllegalArgumentException();
        }

        int n = points.length;
        Point[] pointsCopy = Arrays.copyOf(points, n);
        Arrays.sort(pointsCopy);

        if (hasDuplicate(pointsCopy)) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < n; i++) {
            Point p = pointsCopy[i];
            Arrays.sort(pointsCopy, p.slopeOrder());

            for (int first = 1, last = 2; last < n; last++) {
                // find last collinear to p point
                while (last < n
                        && Double.compare(p.slopeTo(pointsCopy[first]), p.slopeTo(pointsCopy[last])) == 0) {
                    last++;
                }
                // if found at least 3 elements, make segment if it's unique
                if (last - first >= 3 && p.compareTo(pointsCopy[first]) < 0) {
                    segments.add(new LineSegment(p, pointsCopy[last - 1]));
                }
                // Try to find next
                first = last;
            }

        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[numberOfSegments()]);
    }

    // test the whole array fo duplicate points
    private boolean hasDuplicate(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean hasNull(Point[] points) {
        return Arrays.asList(points).contains(null);
    }
}
