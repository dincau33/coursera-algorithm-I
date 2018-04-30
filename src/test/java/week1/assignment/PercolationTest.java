package week1.assignment;

import edu.princeton.cs.introcs.StdRandom;

import java.io.*;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PercolationTest {

    @Test
    void failToCreatePercolationGridIfNLowerThan1() {
        assertThrows(IllegalArgumentException.class, () -> new Percolation(0));
    }

    @Test
    void failToOpenOutOfBoundSite() {
        Percolation p = new Percolation(3);
        assertThrows(IllegalArgumentException.class, () -> p.open(0, 1));
    }

    @Test
    void assessIfOutOfBoundSiteIsFullThrowsException() {
        Percolation p = new Percolation(2);
        assertThrows(IllegalArgumentException.class, () -> p.isFull(0, 1));
    }

    @Test
    void siteIsFullIfOpenAndConnectedToTopOpenSite() {
        Percolation p = new Percolation(2);
        assertFalse(p.isFull(1, 1));
        assertFalse(p.isFull(2, 1));

        p.open(1, 1);
        assertTrue(p.isFull(1, 1));
        assertFalse(p.isFull(2, 1));

        p.open(2, 1);
        assertTrue(p.isFull(1, 1));
        assertTrue(p.isFull(2, 1));
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
    void gridBasedOnFileInput1Percolates() throws FileNotFoundException {
        File input1 = new File("./src/test/resources/week1/assignment/percolation/input1.txt");
        assertTrue(createGridFromfile(input1).percolates());
    }

    @Test
    void gridBasedOnFileInput1NoDoesNotPercolates() throws FileNotFoundException {
        File input1No = new File("./src/test/resources/week1/assignment/percolation/input1-no.txt");
        assertFalse(createGridFromfile(input1No).percolates());
    }

    @Test
    void gridBasedOnFileInput2Percolates() throws FileNotFoundException {
        File input2 = new File("./src/test/resources/week1/assignment/percolation/input2.txt");
        assertTrue(createGridFromfile(input2).percolates());
    }

    @Test
    void gridBasedOnFileInput2NoDoesNotPercolates() throws FileNotFoundException {
        File input2No = new File("./src/test/resources/week1/assignment/percolation/input2-no.txt");
        assertFalse(createGridFromfile(input2No).percolates());
    }

    @Test
    void gridBasedOnFileInput3Percolates() throws FileNotFoundException {
        File input3 = new File("./src/test/resources/week1/assignment/percolation/input3.txt");
        assertTrue(createGridFromfile(input3).percolates());
    }

    @Test
    void gridBasedOnFileInput4Percolates() throws FileNotFoundException {
        File input4 = new File("./src/test/resources/week1/assignment/percolation/input4.txt");
        assertTrue(createGridFromfile(input4).percolates());
    }

    @Test
    void gridBasedOnFileInput5Percolates() throws FileNotFoundException {
        File input5 = new File("./src/test/resources/week1/assignment/percolation/input5.txt");
        assertTrue(createGridFromfile(input5).percolates());
    }

    @Test
    void gridBasedOnFileInput6Percolates() throws FileNotFoundException {
        File input6 = new File("./src/test/resources/week1/assignment/percolation/input6.txt");
        assertTrue(createGridFromfile(input6).percolates());
    }

    @Test
    void gridBasedOnFileInput7Percolates() throws FileNotFoundException {
        File input7 = new File("./src/test/resources/week1/assignment/percolation/input7.txt");
        assertTrue(createGridFromfile(input7).percolates());
    }

    @Test
    void gridBasedOnFileInput8Percolates() throws FileNotFoundException {
        File input8 = new File("./src/test/resources/week1/assignment/percolation/input8.txt");
        assertTrue(createGridFromfile(input8).percolates());
    }

    @Test
    void gridBasedOnFileInput8DupsPercolates() throws FileNotFoundException {
        File input8Dups = new File("./src/test/resources/week1/assignment/percolation/input8-dups.txt");
        assertTrue(createGridFromfile(input8Dups).percolates());
    }

    @Test
    void gridBasedOnFileInput8NoDoesNotPercolate() throws FileNotFoundException {
        File input8No = new File("./src/test/resources/week1/assignment/percolation/input8-no.txt");
        assertFalse(createGridFromfile(input8No).percolates());
    }

    @Test
    void gridBasedOnFileInput10Percolates() throws FileNotFoundException {
        File input10 = new File("./src/test/resources/week1/assignment/percolation/input10.txt");
        assertTrue(createGridFromfile(input10).percolates());
    }

    @Test
    void gridBasedOnFileInput10NoDoesNotPercolate() throws FileNotFoundException {
        File input10No = new File("./src/test/resources/week1/assignment/percolation/input10-no.txt");
        assertFalse(createGridFromfile(input10No).percolates());
    }

    @Test
    void gridBasedOnFileInput20Percolates() throws FileNotFoundException {
        File input20 = new File("./src/test/resources/week1/assignment/percolation/input20.txt");
        assertTrue(createGridFromfile(input20).percolates());
    }

    @Test
    void gridBasedOnFileInput50Percolates() throws FileNotFoundException {
        File input50 = new File("./src/test/resources/week1/assignment/percolation/input50.txt");
        assertTrue(createGridFromfile(input50).percolates());
    }

    @Test
    void gridBasedOnFileJerry47Percolates() throws FileNotFoundException {
        File jerry47 = new File("./src/test/resources/week1/assignment/percolation/jerry47.txt");
        assertTrue(createGridFromfile(jerry47).percolates());
    }

    @Test
    void gridBasedOnFileSedgewick60Percolates() throws FileNotFoundException {
        File sedgewick60 = new File("./src/test/resources/week1/assignment/percolation/sedgewick60.txt");
        assertTrue(createGridFromfile(sedgewick60).percolates());
    }

    @Test
    void gridBasedOnFileSnake13Percolates() throws FileNotFoundException {
        File snake13 = new File("./src/test/resources/week1/assignment/percolation/snake13.txt");
        assertTrue(createGridFromfile(snake13).percolates());
    }

    @Test
    void gridBasedOnFileSnake101Percolates() throws FileNotFoundException {
        File snake101 = new File("./src/test/resources/week1/assignment/percolation/snake101.txt");
        assertTrue(createGridFromfile(snake101).percolates());
    }

    @Test
    void gridBasedOnFileWayne98Percolates() throws FileNotFoundException {
        File wayne98 = new File("./src/test/resources/week1/assignment/percolation/wayne98.txt");
        assertTrue(createGridFromfile(wayne98).percolates());
    }

}
