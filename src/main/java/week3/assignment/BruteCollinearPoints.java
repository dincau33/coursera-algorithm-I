package week3.assignment;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private ArrayList<LineSegment> segments = new ArrayList<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
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

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int l = k + 1; l < n; l++) {
                        double S0 = pointsCopy[i].slopeTo(pointsCopy[j]);
                        double S1 = pointsCopy[i].slopeTo(pointsCopy[k]);
                        double S2 = pointsCopy[i].slopeTo(pointsCopy[l]);
                        if (S0 == S1 && S0 == S2) {
                            segments.add(new LineSegment(pointsCopy[i], pointsCopy[l]));
                        }
                    }
                }
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
