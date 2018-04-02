package week1.assignment;

import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PercolationTest {

    @Test
    public void testPercolationConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Percolation(0));
        assertThrows(IllegalArgumentException.class, () -> new Percolation(-1));
        Percolation p = new Percolation(1);
    }

    @Test
    public void testOpen() {
        Percolation p = new Percolation(3);
        assertThrows(IllegalArgumentException.class, () -> p.open(0, 1));
        p.open(2, 2);
        assertEquals(1, p.numberOfOpenSites());
        assertTrue(p.isOpen(2, 2));
        assertFalse(p.isOpen(1, 1));
        assertFalse(p.isOpen(1, 2));
        assertFalse(p.isOpen(1, 3));
        assertFalse(p.isOpen(2, 1));
        assertFalse(p.isOpen(2, 3));
        assertFalse(p.isOpen(3, 1));
        assertFalse(p.isOpen(3, 2));
        assertFalse(p.isOpen(3, 3));
        p.open(2, 1);
        p.open(2, 3);
        p.open(1, 1);
        p.open(3, 3);
        assertTrue(p.isFull(2, 2));
        assertTrue(p.isOpen(1, 1));
        assertFalse(p.isOpen(1, 2));
        assertFalse(p.isOpen(1, 3));
        assertTrue(p.isOpen(2, 1));
        assertTrue(p.isOpen(2, 3));
        assertFalse(p.isOpen(3, 1));
        assertFalse(p.isOpen(3, 2));
        assertTrue(p.isOpen(3, 3));
        p.open(2, 2);
        assertTrue(p.isFull(2, 2));
        assertTrue(p.isOpen(1, 1));
        assertFalse(p.isOpen(1, 2));
        assertFalse(p.isOpen(1, 3));
        assertTrue(p.isOpen(2, 1));
        assertTrue(p.isOpen(2, 3));
        assertFalse(p.isOpen(3, 1));
        assertFalse(p.isOpen(3, 2));
        assertTrue(p.isOpen(3, 3));
    }

    @Test
    public void testIsOpen() {
        Percolation p = new Percolation(2);
        assertThrows(IllegalArgumentException.class, () -> p.isOpen(1, 3));
        assertThrows(IllegalArgumentException.class, () -> p.isOpen(1, 0));
        assertThrows(IllegalArgumentException.class, () -> p.isOpen(0, 1));
        assertThrows(IllegalArgumentException.class, () -> p.isOpen(3, 1));
        assertThrows(IllegalArgumentException.class, () -> p.isOpen(3, 3));
        assertFalse(p.isOpen(1, 1));
        assertFalse(p.isOpen(1, 2));
        assertFalse(p.isOpen(2, 1));
        assertFalse(p.isOpen(2, 2));
        p.open(1, 1);
        assertTrue(p.isOpen(1, 1));
        assertFalse(p.isOpen(1, 2));
        assertFalse(p.isOpen(2, 1));
        assertFalse(p.isOpen(2, 2));
    }

    @Test
    public void testIsFull() {
        Percolation p = new Percolation(2);
        assertThrows(IllegalArgumentException.class, () -> p.isFull(0, 1));
        assertFalse(p.isFull(1, 1));
        assertFalse(p.isFull(2, 1));
        p.open(1, 1);
        assertTrue(p.isFull(1, 1));
        assertFalse(p.isFull(2, 1));
        p.open(2, 1);
        assertTrue(p.isFull(1, 1));
        assertTrue(p.isFull(2, 1));
    }

    @Test
    public void testNumberOfOpenSites() {
        Percolation p = new Percolation(2);
        assertEquals(0, p.numberOfOpenSites());
        p.open(1, 1);
        assertEquals(1, p.numberOfOpenSites());
    }

    private void runPercolationExperiment(int n) {
        Percolation p = new Percolation(n);
        while (!p.percolates()) {
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);
            p.open(row, col);
        }
    }

    @Test
    public void testPercolates() {
        runPercolationExperiment(3);
        runPercolationExperiment(5);
        runPercolationExperiment(10);
        runPercolationExperiment(10);
        runPercolationExperiment(20);
        runPercolationExperiment(20);
        runPercolationExperiment(50);
        runPercolationExperiment(50);
    }
}
