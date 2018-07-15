package week5.assignment;

import edu.princeton.cs.algorithms.Point2D;
import edu.princeton.cs.algorithms.Stack;

public class KdTree {

	private int size = 0;
	private KdNode root = null;

	// construct an empty set of points
	public KdTree() {}

	// is the set empty?
	public boolean isEmpty() {
		return true;
	}

	// number of points in the set
	public int size() {
		return size;
	}

	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (p == null) throw new IllegalArgumentException();
	}

	// does the set contain point p?
	public boolean contains(Point2D p) {
		if (p == null) throw new IllegalArgumentException();
		return false;
	}

	// draw all points to standard draw
	public void draw() {
	}

	// all points that are inside the rectangle (or on the boundary)
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) throw new IllegalArgumentException();
		Stack<Point2D> pts = new Stack<>();
		return pts;
	}

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		if (p == null) throw new IllegalArgumentException();
		Point2D nearest = null;
		return nearest;
	}

	private class KdNode {
		Point2D p;
		KdNode left = null;
		KdNode right = null;

		public KdNode(Point2D p) {
			this.p = p;
		}
	}

}
