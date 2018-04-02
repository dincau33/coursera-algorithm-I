package week1.assignment;

import edu.princeton.cs.introcs.StdOut;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class PercolationStatsTest {

    @Test
    public void testPercolationStatsConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new PercolationStats(-1, 1));
        assertThrows(IllegalArgumentException.class, () -> new PercolationStats(0, 1));
        assertThrows(IllegalArgumentException.class, () -> new PercolationStats(1, 0));
        assertThrows(IllegalArgumentException.class, () -> new PercolationStats(1, -1));
        assertThrows(IllegalArgumentException.class, () -> new PercolationStats(-1, -1));
        PercolationStats ps = new PercolationStats(1, 1);
    }

    @Test
    void testMean() {
        PercolationStats ps1 = new PercolationStats(1, 1);
        assertEquals(1.0, ps1.mean());
        PercolationStats ps2 = new PercolationStats(2, 10);
        assertEquals(0.625, ps2.mean(), 0.125);
    }

    @Test
    void testStddev() {
        PercolationStats ps1 = new PercolationStats(1, 1);
        assertTrue(Double.isNaN(ps1.stddev()));
        PercolationStats ps2 = new PercolationStats(2, 10);
        assertEquals(0.1, ps2.stddev(), 0.05);
        assertEquals(0.1, ps2.stddev(), 0.05);
    }

    @Test
    void testConfidenceLo() {
        PercolationStats ps1 = new PercolationStats(1, 1);
        assertTrue(Double.isNaN(ps1.confidenceLo()));
        PercolationStats ps2 = new PercolationStats(2, 10);
        assertEquals(0.60, ps2.confidenceLo(), 0.1);
        assertEquals(0.60, ps2.confidenceLo(), 0.1);
    }

    @Test
    void testConfidenceHi() {
        PercolationStats ps1 = new PercolationStats(1, 1);
        assertTrue(Double.isNaN(ps1.confidenceHi()));
        PercolationStats ps2 = new PercolationStats(2, 10);
        assertEquals(0.70, ps2.confidenceHi(), 0.1);
    }
}