package week1.assignment;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdRandom;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PercolationTest {

    @Test
    void testPercolationConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Percolation(0));
        assertThrows(IllegalArgumentException.class, () -> new Percolation(-1));
        Percolation p = new Percolation(1);
    }

    @Test
    void testOpen() {
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
    void testIsOpen() {
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
    void testIsFull() {
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
    void testNumberOfOpenSites() {
        Percolation p = new Percolation(2);
        assertEquals(0, p.numberOfOpenSites());
        p.open(1, 1);
        assertEquals(1, p.numberOfOpenSites());
    }

    void runPercolationExperiment(int n) {
        Percolation p = new Percolation(n);
        while (!p.percolates()) {
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);
            p.open(row, col);
        }
    }

    @Test
    void testPercolates() {
        runPercolationExperiment(3);
        runPercolationExperiment(5);
        runPercolationExperiment(10);
        runPercolationExperiment(10);
        runPercolationExperiment(20);
        runPercolationExperiment(20);
        runPercolationExperiment(50);
        runPercolationExperiment(50);
    }

    private Percolation createGridFromfile(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        int n = sc.nextInt();
        Percolation p = new Percolation(n);
        while (sc.hasNextLine()) {
            int row = sc.nextInt();
            int col = sc.nextInt();
            p.open(row, col);
        }
        return p;
    }

    @Test
    void testResource() throws FileNotFoundException {
        File input1 = new File("./src/test/resources/week1/assignment/percolation/input1.txt");
        assertTrue(createGridFromfile(input1).percolates());
        File input1No = new File("./src/test/resources/week1/assignment/percolation/input1-no.txt");
        assertFalse(createGridFromfile(input1No).percolates());
        File input2 = new File("./src/test/resources/week1/assignment/percolation/input2.txt");
        assertTrue(createGridFromfile(input2).percolates());
        File input2No = new File("./src/test/resources/week1/assignment/percolation/input2-no.txt");
        assertFalse(createGridFromfile(input2No).percolates());
        File input3 = new File("./src/test/resources/week1/assignment/percolation/input3.txt");
        assertTrue(createGridFromfile(input3).percolates());
        File input4 = new File("./src/test/resources/week1/assignment/percolation/input4.txt");
        assertTrue(createGridFromfile(input4).percolates());
        File input5 = new File("./src/test/resources/week1/assignment/percolation/input5.txt");
        assertTrue(createGridFromfile(input5).percolates());
        File input6 = new File("./src/test/resources/week1/assignment/percolation/input6.txt");
        assertTrue(createGridFromfile(input6).percolates());
        File input7 = new File("./src/test/resources/week1/assignment/percolation/input7.txt");
        assertTrue(createGridFromfile(input7).percolates());
        File input8 = new File("./src/test/resources/week1/assignment/percolation/input8.txt");
        assertTrue(createGridFromfile(input8).percolates());
        File input8Dups = new File("./src/test/resources/week1/assignment/percolation/input8-dups.txt");
        assertTrue(createGridFromfile(input8Dups).percolates());
        File input8No = new File("./src/test/resources/week1/assignment/percolation/input8-no.txt");
        assertFalse(createGridFromfile(input8No).percolates());
        File input10 = new File("./src/test/resources/week1/assignment/percolation/input10.txt");
        assertTrue(createGridFromfile(input10).percolates());
        File input10No = new File("./src/test/resources/week1/assignment/percolation/input10-no.txt");
        assertFalse(createGridFromfile(input10No).percolates());
        File input20 = new File("./src/test/resources/week1/assignment/percolation/input20.txt");
        assertTrue(createGridFromfile(input20).percolates());
        File input50 = new File("./src/test/resources/week1/assignment/percolation/input50.txt");
        assertTrue(createGridFromfile(input50).percolates());
        File jerry47 = new File("./src/test/resources/week1/assignment/percolation/jerry47.txt");
        assertTrue(createGridFromfile(jerry47).percolates());
        File sedgewick60 = new File("./src/test/resources/week1/assignment/percolation/sedgewick60.txt");
        assertTrue(createGridFromfile(sedgewick60).percolates());
        File snake13 = new File("./src/test/resources/week1/assignment/percolation/snake13.txt");
        assertTrue(createGridFromfile(snake13).percolates());
        File snake101 = new File("./src/test/resources/week1/assignment/percolation/snake101.txt");
        assertTrue(createGridFromfile(snake101).percolates());
        File wayne98 = new File("./src/test/resources/week1/assignment/percolation/wayne98.txt");
        assertTrue(createGridFromfile(wayne98).percolates());
    }

}
