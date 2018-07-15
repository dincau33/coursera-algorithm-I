package week5.assignment;

import edu.princeton.cs.algorithms.Point2D;
import java.util.TreeSet;

public class KdTree {

	private TreeSet<Point2D> set = null;

	// construct an empty set of points
	public KdTree() {

	}

	// is the set empty?
	public boolean isEmpty() {
		return true;
	}

	// number of points in the set
	public int size() {
		return set.size();
	}

	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {

	}

	// does the set contain point p?
	public boolean contains(Point2D p) {
		return false;
	}

	// draw all points to standard draw
	public void draw() {

	}

	// all points that are inside the rectangle (or on the boundary)
	public Iterable<Point2D> range(RectHV rect) {
		return null;
	}

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		return new Point2D(0, 0);
	}


}
