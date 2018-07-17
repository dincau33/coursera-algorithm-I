package week5.assignment;

import edu.princeton.cs.algorithms.Point2D;
import edu.princeton.cs.algorithms.Stack;
import week3.assignment.Point;

public class KdTree {

	private static final boolean VERTICAL_LINE = true;
	private static final boolean HORIZONTAL_LINE = false;

	private int size = 0;
	private KdNode root = null;

	// construct an empty set of points
	public KdTree() {
	}

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
		root = insert(root, p, VERTICAL_LINE);
	}

	private KdNode insert(KdNode node, Point2D p, boolean line) {
		if (node == null) {
			size++;
			return new KdNode(p);
		}

		if (node.p.equals(p)) return node;

		if (line == VERTICAL_LINE) {
			if (p.x() < node.p.x()) {
				node.left = insert(node.left, p, HORIZONTAL_LINE);
			} else {
				node.right = insert(node.right, p, HORIZONTAL_LINE);
			}
		}

		if (line == HORIZONTAL_LINE) {
			if (p.y() < node.p.y()) {
				node.left = insert(node.left, p, VERTICAL_LINE);
			} else {
				node.right = insert(node.right, p, VERTICAL_LINE);
			}
		}

		return node;
	}

	// does the set contain point p?
	public boolean contains(Point2D p) {
		if (p == null) throw new IllegalArgumentException();
		return contains(root, p, VERTICAL_LINE);
	}

	private boolean contains(KdNode node, Point2D p, boolean line) {
		if (node != null) {
			if (node.p.equals(p)) return true;

			if (line == VERTICAL_LINE) {
				if (p.x() < node.p.x()) {
					return contains(node.left, p, HORIZONTAL_LINE);
				} else {
					return contains(node.right, p, HORIZONTAL_LINE);
				}
			}

			if (line == HORIZONTAL_LINE) {
				if (p.y() < node.p.y()) {
					return contains(node.left, p, VERTICAL_LINE);
				} else {
					return contains(node.right, p, VERTICAL_LINE);
				}
			}
		}
		return false;
	}

	// draw all points to standard draw
	public void draw() {
	}

	// all points that are inside the rectangle (or on the boundary)
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) throw new IllegalArgumentException();
		Stack<Point2D> pts = new Stack<>();
		range(root, rect, VERTICAL_LINE, pts);
		return pts;
	}

	private void range(KdNode node, RectHV rect, boolean line, Stack<Point2D> pts) {
		if (node != null) {
			if (rect.contains(node.p)) pts.push(node.p);

			if (line == VERTICAL_LINE) {
				if (rect.xmin() < node.p.x()) {
					range(node.left, rect, HORIZONTAL_LINE, pts);
				}
				if (rect.xmax() >= node.p.x()) {
					range(node.right, rect, HORIZONTAL_LINE, pts);
				}
			}

			if (line == HORIZONTAL_LINE) {
				if (rect.ymin() < node.p.y()) {
					range(node.left, rect, VERTICAL_LINE, pts);
				}
				if (rect.ymax() >= node.p.y()) {
					range(node.right, rect, VERTICAL_LINE, pts);
				}
			}
		}
	}

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		if (p == null) throw new IllegalArgumentException();
		return nearest(root, p, VERTICAL_LINE);
	}

	private Point2D nearest(KdNode node, Point2D p, boolean line) {
//		if (node != null) {
//			if (node.p.equals(p)) return node.p;
//
//			if (line == VERTICAL_LINE) {
//				if (p.x() < node.p.x()) {
//					Point2D nearest = nearest(node.left, p, HORIZONTAL_LINE);
//					if (p.distanceSquaredTo(node.p) < p.distanceTo(nearest)) {
//						return node.p;
//					} else {
//						return nearest;
//					}
//				} else {
//					Point2D nearest = nearest(node.right, p, HORIZONTAL_LINE);
//					if (p.distanceSquaredTo(node.p) < p.distanceTo(nearest)) {
//						return node.p;
//					} else {
//						return nearest;
//					}
//				}
//			}
//
//			if (line == HORIZONTAL_LINE) {
//				if (p.y() < node.p.y()) {
//					Point2D nearest = nearest(node.left, p, HORIZONTAL_LINE);
//					if (p.distanceSquaredTo(node.p) < p.distanceTo(nearest)) {
//						return node.p;
//					} else {
//						return nearest;
//					}
//				} else {
//					Point2D nearest = nearest(node.right, p, HORIZONTAL_LINE);
//					if (p.distanceSquaredTo(node.p) < p.distanceTo(nearest)) {
//						return node.p;
//					} else {
//						return nearest;
//					}
//				}
//			}
//		}

		return null;
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
