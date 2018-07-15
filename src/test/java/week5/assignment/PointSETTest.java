package week5.assignment;

import edu.princeton.cs.algorithms.Point2D;
import edu.princeton.cs.introcs.In;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PointSETTest {

	private static final String FILE_PATH_FOLDER = "./src/test/resources/week5/assignment/kdtree/";

	private static PointSET getSetFromFile(String filePath) {
		PointSET set = new PointSET();
		In in = new In(filePath);
		while(in.hasNextLine()) {
			double x = in.readDouble();
			double y = in.readDouble();
			set.insert(new Point2D(x, y));
		}
		return set;
	}

	@Test
	void newlyCreatedPointSETIsEmtpty() {
		PointSET set = new PointSET();
		assertThat(set.isEmpty()).isTrue();
	}

	@Test
	void circle4PointSETHas4Points() {
		PointSET set = getSetFromFile(FILE_PATH_FOLDER + "circle4.txt");
		assertThat(set.size()).isEqualTo(4);
	}

	@Test
	void failToInsertPointIfPointIsNull() {
		PointSET set = new PointSET();
		assertThrows(IllegalArgumentException.class, () -> set.insert(null));
	}

	@Test
	void circle4PointSETContainsX00Y05Point() {
		PointSET set = getSetFromFile(FILE_PATH_FOLDER + "circle4.txt");

		Point2D p1 = new Point2D(0.0, 0.5);
		assertThat(set.contains(p1)).isTrue();

		Point2D p2 = new Point2D(0.5, 1.0);
		assertThat(set.contains(p2)).isTrue();

		Point2D p3 = new Point2D(0.5, 0.0);
		assertThat(set.contains(p3)).isTrue();

		Point2D p4 = new Point2D(1.0, 0.5);
		assertThat(set.contains(p4)).isTrue();

		Point2D p5 = new Point2D(0.1, 0.3);
		assertThat(set.contains(p5)).isFalse();
	}

	@Test
	void queryRectangleContains2PointsOfCicle4PointSET() {
		PointSET set = getSetFromFile(FILE_PATH_FOLDER + "circle4.txt");
		RectHV rec = new RectHV(0.2, 0.2, 1.0, 1.0);
		Iterable<Point2D> pts = set.range(rec);
		assertThat(pts).contains(new Point2D(0.5, 1.0));
		assertThat(pts).contains(new Point2D(1.0, 0.5));
		assertThat(pts).doesNotContain(new Point2D(0.0, 0.5));
		assertThat(pts).doesNotContain(new Point2D(0.5, 0.0));
	}

	@Test
	void queryRectangleContains4PointsOfCicle4PointSET() {
		PointSET set = getSetFromFile(FILE_PATH_FOLDER + "circle4.txt");
		RectHV rec = new RectHV(0.0, 0.0, 1.0, 1.0);
		Iterable<Point2D> pts = set.range(rec);
		assertThat(pts).contains(new Point2D(0.5, 1.0));
		assertThat(pts).contains(new Point2D(1.0, 0.5));
		assertThat(pts).contains(new Point2D(0.0, 0.5));
		assertThat(pts).contains(new Point2D(0.5, 0.0));
	}

	@Test
	void queryRectangleContainsNoPointsOfCicle4PointSET() {
		PointSET set = getSetFromFile(FILE_PATH_FOLDER + "circle4.txt");
		RectHV rec = new RectHV(0.2, 0.2, 0.3, 0.3);
		Iterable<Point2D> pts = set.range(rec);
		assertThat(pts).doesNotContain(new Point2D(0.5, 1.0));
		assertThat(pts).doesNotContain(new Point2D(1.0, 0.5));
		assertThat(pts).doesNotContain(new Point2D(0.0, 0.5));
		assertThat(pts).doesNotContain(new Point2D(0.5, 0.0));
	}

	@Test
	void failToFindPointsIfQueryRectangleIsNull() {
		PointSET set = new PointSET();
		assertThrows(IllegalArgumentException.class, () -> set.range(null));
	}

	@Test
	void failToFindNearestPointOfNullPoint() {
		PointSET set = new PointSET();
		assertThrows(IllegalArgumentException.class, () -> set.nearest(null));
	}

}