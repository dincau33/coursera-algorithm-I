package week5.assignment;

import edu.princeton.cs.algorithms.Point2D;
import java.util.TreeSet;

public class PointSET {

	private TreeSet<Point2D> set;

	// construct an empty set of points
	public PointSET() {
		set = new TreeSet<>();
	}

	// is the set empty?
	public boolean isEmpty() {
		return set.isEmpty();
	}

	// number of points in the set
	public int size() {
		return set.size();
	}

	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (p == null) throw new IllegalArgumentException();
		set.add(p);
	}

	// does the set contain point p?
	public boolean contains(Point2D p) {
		return set.contains(p);
	}

	// draw all points to standard draw
	public void draw() {
		for(Point2D p : set){
			p.draw();
		}
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
