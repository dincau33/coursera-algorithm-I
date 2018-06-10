package week3.assignment;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PointTest {

	@Test
	void point0LowerThanPoint1IfY1HigherThanY0AndX1HigherThanX0() {
		// (y1 > y0) and (x1 > x0)
		Point p0 = new Point(1, 1);
		Point p1 = new Point(2, 2);
		assertThat(p0.compareTo(p1)).isEqualTo(-1);
	}

	@Test
	void point0LowerThanPoint1IfY1HigherThanY0AndX1EqualToX0() {
		// (y1 > y0) and (x1 = x0)
		Point p0 = new Point(1, 1);
		Point p1 = new Point(1, 2);
		assertThat(p0.compareTo(p1)).isEqualTo(-1);
	}

	@Test
	void point0LowerThanPoint1IfY1HigherThanY0AndX1LowerThanX0() {
		// (y1 > y0) and (x1 < x0)
		Point p0 = new Point(3, 1);
		Point p1 = new Point(1, 2);
		assertThat(p0.compareTo(p1)).isEqualTo(-1);
	}

	@Test
	void point0LowerThanPoint1IfY1EqualToY0AndX1HigherThanX0() {
		// (y1 = y0) and (x1 > x0)
		Point p0 = new Point(1, 2);
		Point p1 = new Point(3, 2);
		assertThat(p0.compareTo(p1)).isEqualTo(-1);
	}

	@Test
	void point0EqualToPoint1IfY1EqualToY0AndX1EqualToX0() {
		// (y1 = y0) and (x1 = x0)
		Point p0 = new Point(1, 2);
		Point p1 = new Point(1, 2);
		assertThat(p0.compareTo(p1)).isEqualTo(0);
	}

	@Test
	void point0HigherThanPoint1IfY1LowerThanY0AndX1EqualToX0() {
		// (y1 < y0) and (x1 = x0)
		Point p0 = new Point(1, 2);
		Point p1 = new Point(1, 1);
		assertThat(p0.compareTo(p1)).isEqualTo(1);
	}

	@Test
	void point0HigherThanPoint1IfY1LowerThanY0AndX1HigherThanX0() {
		// (y1 < y0) and (x1 > x0)
		Point p0 = new Point(1, 2);
		Point p1 = new Point(2, 1);
		assertThat(p0.compareTo(p1)).isEqualTo(1);
	}

	@Test
	void point0HigherThanPoint1IfY1LowerThanY0AndX1LowerThanX0() {
		// (y1 < y0) and (x1 < x0)
		Point p0 = new Point(1, 2);
		Point p1 = new Point(0, 1);
		assertThat(p0.compareTo(p1)).isEqualTo(1);
	}

	@Test
	void point0HigherThanPoint1IfY1EqualToY0AndX1LowerThanX0() {
		// (y1 = y0) and (x1 < x0)
		Point p0 = new Point(1, 2);
		Point p1 = new Point(0, 2);
		assertThat(p0.compareTo(p1)).isEqualTo(1);
	}

	@Test
	void slopeOfDegeneratedLineIsNegativeInfinity() {
		Point p0 = new Point(1, 1);
		Point p1 = new Point(1, 1);
		assertThat(p0.slopeTo(p1)).isEqualTo(Double.NEGATIVE_INFINITY);
	}

	@Test
	void slopeOfHorizontalLineIsZero() {
		Point p0 = new Point(1, 1);
		Point p1 = new Point(10, 1);
		assertThat(p0.slopeTo(p1)).isEqualTo(0.0d);
	}

	@Test
	void slopeOfVerticalLineIsPositiveInfinity() {
		Point p0 = new Point(1, 1);
		Point p1 = new Point(1, 10);
		assertThat(p0.slopeTo(p1)).isEqualTo(Double.POSITIVE_INFINITY);
	}

	@Test
	void slopeOfRegularLine() {
		Point p0 = new Point(1, 1);
		Point p1 = new Point(2, 2);
		assertThat(p0.slopeTo(p1)).isEqualTo(1.0d);
	}

	@Test
	void regularLineSlopeP1P0LessThanRegularLineSlopeP2P0() {
		// Regular line - slope(p1,p0) is less than slope(p2,p0)
		Point p0 = new Point(1, 1);
		Point p1 = new Point(2, 2);
		Point p2 = new Point(2, 4);
		assertThat(p0.slopeOrder().compare(p1, p2)).isEqualTo(-1);
	}

	@Test
	void regularLineSlopeP1P0EqualToRegularLineSlopeP2P0() {
		// Regular line - slope(p1,p0) is equal to slope(p2,p0)
		Point p0 = new Point(1, 1);
		Point p1 = new Point(2, 2);
		Point p2 = new Point(2, 2);
		assertThat(p0.slopeOrder().compare(p1, p2)).isEqualTo(0);
	}

	@Test
	void regularLineSlopeP1P0HigherThanRegularLineSlopeP2P0() {
		// Regular line - slope(p1,p0) is equal to slope(p2,p0)
		Point p0 = new Point(1, 1);
		Point p1 = new Point(2, 4);
		Point p2 = new Point(2, 2);
		assertThat(p0.slopeOrder().compare(p1, p2)).isEqualTo(1);
	}

	@Test
	void horizontalLineSlopeP1P0LessThanRegularLineSlopeP2P0() {
		// Horizontal line - slope(p1,p0) is less than slope(p2,p0)
		Point p0 = new Point(1, 1);
		Point p1 = new Point(2, 1);
		Point p2 = new Point(2, 2);
		assertThat(p0.slopeOrder().compare(p1, p2)).isEqualTo(-1);
	}

	@Test
	void horizontalLineSlopeP1P0EqualToHorizontalLineSlopeP2P0() {
		// Horizontal line - slope(p1,p0) is equal to slope(p2,p0)
		Point p0 = new Point(1, 1);
		Point p1 = new Point(2, 1);
		Point p2 = new Point(5, 1);
		assertThat(p0.slopeOrder().compare(p1, p2)).isEqualTo(0);
	}

	@Test
	void horizontalLineSlopeP1P0HigherThanRegularLineSlopeP2P0() {
		// Horizontal line - slope(p1,p0) is higher than slope(p2,p0)
		Point p0 = new Point(1, 1);
		Point p1 = new Point(2, 1);
		Point p2 = new Point(2, -1);
		assertThat(p0.slopeOrder().compare(p1, p2)).isEqualTo(1);
	}

	@Test
	void regularLineSlopeP1P0LessThanVerticalLineSlopeP2P0() {
		// Regular line - slope(p1,p0) is less than slope(p2,p0)
		Point p0 = new Point(1, 1);
		Point p1 = new Point(2, 2);
		Point p2 = new Point(1, 2);
		assertThat(p0.slopeOrder().compare(p1, p2)).isEqualTo(-1);
	}

	@Test
	void verticalLineSlopeP1P0EqualToVerticalLineSlopeP2P0() {
		// Vertical line - slope(p1,p0) is equal to slope(p2,p0)
		Point p0 = new Point(1, 1);
		Point p1 = new Point(1, 4);
		Point p2 = new Point(1, 2);
		assertThat(p0.slopeOrder().compare(p1, p2)).isEqualTo(0);
	}

	@Test
	void verticalLineSlopeP1P0HigherThanRegularLineSlopeP2P0() {
		// Vertical line - slope(p1,p0) is higher than slope(p2,p0)
		Point p0 = new Point(1, 1);
		Point p1 = new Point(1, 2);
		Point p2 = new Point(2, 2);
		assertThat(p0.slopeOrder().compare(p1, p2)).isEqualTo(1);
	}

	@Test
	void degeneratedLineSlopeP1P0LessThanRegularLineSlopeP2P0() {
		// Degenerated line - slope(p1,p0) is less than slope(p2,p0)
		Point p0 = new Point(1, 1);
		Point p1 = new Point(1, 1);
		Point p2 = new Point(2, 2);
		assertThat(p0.slopeOrder().compare(p1, p2)).isEqualTo(-1);
	}

	@Test
	void degeneratedLineSlopeP1P0EqualToDegeneratedLineSlopeP2P0() {
		// Degenerated line - slope(p1,p0) is equal to slope(p2,p0)
		Point p0 = new Point(1, 1);
		Point p1 = new Point(1, 1);
		Point p2 = new Point(1, 1);
		assertThat(p0.slopeOrder().compare(p1, p2)).isEqualTo(0);
	}

	@Test
	void regularLineSlopeP1P0HigherThanDegeneratedLineSlopeP2P0() {
		// Regular line - slope(p1,p0) is higer than slope(p2,p0)
		Point p0 = new Point(1, 1);
		Point p1 = new Point(2, 2);
		Point p2 = new Point(1, 1);
		assertThat(p0.slopeOrder().compare(p1, p2)).isEqualTo(1);
	}
}