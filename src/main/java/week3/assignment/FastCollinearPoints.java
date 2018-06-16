package week3.assignment;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

	private final ArrayList<LineSegment> segments = new ArrayList<>();

	// finds all line segments containing 4 or more points
	public FastCollinearPoints(Point[] points) {
		if (points == null) throw new IllegalArgumentException();

		if (hasNull(points)) {
			throw new IllegalArgumentException();
		}

		int n = points.length;
		Point[] slopeOrderPoints = Arrays.copyOf(points, n);
		Point[] sortedPoints = Arrays.copyOf(points, n);
		Arrays.sort(sortedPoints);

		if (hasDuplicate(sortedPoints)) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < n; i++) {
			Point p = sortedPoints[i];
			Arrays.sort(slopeOrderPoints);
			Arrays.sort(slopeOrderPoints, p.slopeOrder());
			for (int first = 1, last = 2; last < n; last++) {
				// find last collinear to p point
				while (last < n
						&& Double.compare(p.slopeTo(slopeOrderPoints[first]), p.slopeTo(slopeOrderPoints[last])) == 0) {
					last++;
				}
				// if found at least 3 elements
				// if point is lower than second point of the segment. if not it means the segment has been already added
				if (last - first >= 3 && p.compareTo(slopeOrderPoints[first]) < 0) {
					segments.add(new LineSegment(p, slopeOrderPoints[last - 1]));
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
