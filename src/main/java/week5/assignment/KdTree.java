package week5.assignment;

import edu.princeton.cs.algorithms.Point2D;
import edu.princeton.cs.algorithms.Stack;

public class KdTree {

	private static final boolean VERTICAL = true;
	private static final boolean HORIZONTAL = false;

	private int size = 0;
	private KdNode root = null;

	// construct an empty set of points
	public KdTree() {
	}

	// is the set empty?
	public boolean isEmpty() { return root == null; }

	// number of points in the set
	public int size() {
		return size;
	}

	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (p == null) throw new IllegalArgumentException();
		root = insert(root, p, VERTICAL);
	}

	private KdNode insert(KdNode node, Point2D p, boolean direction) {
		if (node == null) {
			size++;
			return new KdNode(p);
		}

		if (node.p.equals(p)) return node;

		if (direction == VERTICAL) {
			if (p.x() < node.p.x()) {
				node.left = insert(node.left, p, HORIZONTAL);
			} else {
				node.right = insert(node.right, p, HORIZONTAL);
			}
		}

		if (direction == HORIZONTAL) {
			if (p.y() < node.p.y()) {
				node.left = insert(node.left, p, VERTICAL);
			} else {
				node.right = insert(node.right, p, VERTICAL);
			}
		}

		return node;
	}

	// does the set contain point p?
	public boolean contains(Point2D p) {
		if (p == null) throw new IllegalArgumentException();
		return contains(root, p, VERTICAL);
	}

	private boolean contains(KdNode node, Point2D p, boolean direction) {
		if (node != null) {
			if (node.p.equals(p)) return true;

			if (direction == VERTICAL) {
				if (p.x() < node.p.x()) {
					return contains(node.left, p, HORIZONTAL);
				} else {
					return contains(node.right, p, HORIZONTAL);
				}
			}

			if (direction == HORIZONTAL) {
				if (p.y() < node.p.y()) {
					return contains(node.left, p, VERTICAL);
				} else {
					return contains(node.right, p, VERTICAL);
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
		range(root, rect, VERTICAL, pts);
		return pts;
	}

	private void range(KdNode node, RectHV rect, boolean direction, Stack<Point2D> pts) {
		if (node == null) return;

		if (rect.contains(node.p)) pts.push(node.p);

		if (direction == VERTICAL) {
			if (rect.xmin() < node.p.x()) {
				range(node.left, rect, HORIZONTAL, pts);
			}
			if (rect.xmax() >= node.p.x()) {
				range(node.right, rect, HORIZONTAL, pts);
			}
		}

		if (direction == HORIZONTAL) {
			if (rect.ymin() < node.p.y()) {
				range(node.left, rect, VERTICAL, pts);
			}
			if (rect.ymax() >= node.p.y()) {
				range(node.right, rect, VERTICAL, pts);
			}
		}
	}

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		if (p == null) throw new IllegalArgumentException();
		return nearest(root, p, VERTICAL, root);
	}

	private Point2D nearest(KdNode node, Point2D p, boolean direction, KdNode current) {
		if (node != null) {
			if (node.p.equals(p)) return node.p;

			if (direction == VERTICAL) {
				if (p.x() < node.p.x()) {
					if (node.left == null) return current.p;
					if (p.distanceSquaredTo(node.left.p) < p.distanceSquaredTo(current.p)) {
						return nearest(node.left, p, HORIZONTAL, node.left);
					} else {
						return nearest(node.right, p, HORIZONTAL, current);
					}
				} else {
					if (node.right == null) return current.p;
					if (p.distanceSquaredTo(node.right.p) < p.distanceSquaredTo(current.p)) {
						return nearest(node.right, p, HORIZONTAL, node.right);
					} else {
						return nearest(node.left, p, HORIZONTAL, current);
					}
				}
			}

			if (direction == HORIZONTAL) {
				if (p.y() < node.p.y()) {
					if (node.left == null) return current.p;
					if (p.distanceSquaredTo(node.left.p) < p.distanceSquaredTo(current.p)) {
						return nearest(node.left, p, VERTICAL, node.left);
					} else {
						return nearest(node.right, p, VERTICAL, current);
					}
				} else {
					if (node.right == null) return current.p;
					if (p.distanceSquaredTo(node.right.p) < p.distanceSquaredTo(current.p)) {
						return nearest(node.right, p, VERTICAL, node.right);
					} else {
						return nearest(node.left, p, VERTICAL, current);
					}
				}
			}
		}

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
