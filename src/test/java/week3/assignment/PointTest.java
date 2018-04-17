package week3.assignment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void compareTo() {
        // (y1 > y0) and (x1 > x0)
        Point p0 = new Point(1 ,1);
        Point p1 = new Point(2,2);
        assertEquals(-1,p0.compareTo(p1));
        // (y1 > y0) and (x1 = x0)
        p0 = new Point(1 ,1);
        p1 = new Point(1,2);
        assertEquals(-1,p0.compareTo(p1));
        // (y1 > y0) and (x1 < x0)
        p0 = new Point(3 ,1);
        p1 = new Point(1,2);
        assertEquals(-1,p0.compareTo(p1));
        // (y1 = y0) and (x1 > x0)
        p0 = new Point(1 ,2);
        p1 = new Point(3,2);
        assertEquals(-1,p0.compareTo(p1));
        // (y1 = y0) and (x1 = x0)
        p0 = new Point(1 ,2);
        p1 = new Point(1,2);
        assertEquals(0,p0.compareTo(p1));
        // (y1 < y0) and (x1 = x0)
        p0 = new Point(1 ,2);
        p1 = new Point(1,1);
        assertEquals(1,p0.compareTo(p1));
        // (y1 < y0) and (x1 > x0)
        p0 = new Point(1 ,2);
        p1 = new Point(2,1);
        assertEquals(1,p0.compareTo(p1));
        // (y1 < y0) and (x1 < x0)
        p0 = new Point(1 ,2);
        p1 = new Point(0,1);
        assertEquals(1,p0.compareTo(p1));
        // (y1 = y0) and (x1 < x0)
        p0 = new Point(1 ,2);
        p1 = new Point(0,2);
        assertEquals(1,p0.compareTo(p1));
    }

    @Test
    void slopeTo() {
        // Degenerated line
        Point p0 = new Point(1 ,1);
        Point p1 = new Point(1,1);
        assertEquals(Double.NEGATIVE_INFINITY,p0.slopeTo(p1));
        // Horizontal line
        p0 = new Point(1 ,1);
        p1 = new Point(10,1);
        assertEquals(0.0d,p0.slopeTo(p1));
        // Vertical line
        p0 = new Point(1 ,1);
        p1 = new Point(1,10);
        assertEquals(Double.POSITIVE_INFINITY,p0.slopeTo(p1));
        // Regular line
        p0 = new Point(1 ,1);
        p1 = new Point(2,2);
        assertEquals(1.0d,p0.slopeTo(p1));
    }

    @Test
    void slopeOrder() {
        // Regular line - slope(p1,p0) is less than slope(p2,p0)
        Point p0 = new Point(1 ,1);
        Point p1 = new Point(2,2);
        Point p2 = new Point(2,4);
        assertEquals(-1,p0.slopeOrder().compare(p1,p2));
        // Regular line - slope(p1,p0) is equal to slope(p2,p0)
        p0 = new Point(1 ,1);
        p1 = new Point(2,2);
        p2 = new Point(2,2);
        assertEquals(0,p0.slopeOrder().compare(p1,p2));
        // Regular line - slope(p1,p0) is higher than slope(p2,p0)
        p0 = new Point(1 ,1);
        p1 = new Point(2,4);
        p2 = new Point(2,2);
        assertEquals(1,p0.slopeOrder().compare(p1,p2));
        // Horizontal line - slope(p1,p0) is less than slope(p2,p0)
        p0 = new Point(1 ,1);
        p1 = new Point(2,1);
        p2 = new Point(2,2);
        assertEquals(-1,p0.slopeOrder().compare(p1,p2));
        // Horizontal line - slope(p1,p0) is equal to slope(p2,p0)
        p0 = new Point(1 ,1);
        p1 = new Point(2,1);
        p2 = new Point(5,1);
        assertEquals(0,p0.slopeOrder().compare(p1,p2));
        // Horizontal line - slope(p1,p0) is higher than slope(p2,p0)
        p0 = new Point(1 ,1);
        p1 = new Point(2,2);
        p2 = new Point(2,1);
        assertEquals(1,p0.slopeOrder().compare(p1,p2));
        // Vertical line - slope(p1,p0) is less than slope(p2,p0)
        p0 = new Point(1 ,1);
        p1 = new Point(2,2);
        p2 = new Point(1,2);
        assertEquals(-1,p0.slopeOrder().compare(p1,p2));
        // Vertical line - slope(p1,p0) is equal to slope(p2,p0)
        p0 = new Point(1 ,1);
        p1 = new Point(1,4);
        p2 = new Point(1,2);
        assertEquals(0,p0.slopeOrder().compare(p1,p2));
        // Vertical line - slope(p1,p0) is higher than slope(p2,p0)
        p0 = new Point(1 ,1);
        p1 = new Point(1,2);
        p2 = new Point(2,2);
        assertEquals(1,p0.slopeOrder().compare(p1,p2));
        // Degenerated line - slope(p1,p0) is less than slope(p2,p0)
        p0 = new Point(1,1);
        p1 = new Point(1,1);
        p2 = new Point(2,2);
        assertEquals(-1,p0.slopeOrder().compare(p1,p2));
        // Degenerated line - slope(p1,p0) is equal to slope(p2,p0)
        p0 = new Point(1,1);
        p1 = new Point(1,1);
        p2 = new Point(1,1);
        assertEquals(0,p0.slopeOrder().compare(p1,p2));
        // Degenerated line - slope(p1,p0) is higer than slope(p2,p0)
        p0 = new Point(1,1);
        p1 = new Point(2,2);
        p2 = new Point(1,1);
        assertEquals(1,p0.slopeOrder().compare(p1,p2));
    }
}