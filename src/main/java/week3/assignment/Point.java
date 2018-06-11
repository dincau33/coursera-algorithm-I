package week3.assignment;

import java.util.Comparator;

import edu.princeton.cs.introcs.StdDraw;

public class Point implements Comparable<Point> {

	private final int x;     // x-coordinate of this point
	private final int y;     // y-coordinate of this point

	// constructs the point (x, y)
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// draws this point
	public void draw() {
		/* DO NOT MODIFY */
		StdDraw.point(x, y);
	}

	// draws the line segment from this point to that point
	public void drawTo(Point that) {
		/* DO NOT MODIFY */
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	// string representation
	public String toString() {
		/* DO NOT MODIFY */
		return "(" + x + ", " + y + ")";
	}

	// compare two points by y-coordinates, breaking ties by x-coordinates
	public int compareTo(Point that) {
		if (that.y > this.y) return -1;
		if (that.y == this.y && that.x > this.x) return -1;
		if (that.y == this.y && that.x == this.x) return 0;
		return 1;
	}

	// the slope between this point and that point
	public double slopeTo(Point that) {
		// degenerated line
		if (this.compareTo(that) == 0) return Double.NEGATIVE_INFINITY;
		// horizontal line
		if (that.y == this.y) return +0;
		// vertical line
		if (that.x == this.x) return Double.POSITIVE_INFINITY;
		// other cases
		return (that.y - this.y) / (double) (that.x - this.x);
	}

	// compare two points by slopes they make with this point
	public Comparator<Point> slopeOrder() {
		return new SlopeComparator();
	}

	private class SlopeComparator implements Comparator<Point> {
		public int compare(Point p1, Point p2) {
			double s1 = slopeTo(p1);
			double s2 = slopeTo(p2);
			return Double.compare(s1, s2);
		}

	}

}
