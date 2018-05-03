package week1.assignment;

import java.io.*;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;

class PercolationTest {

    @Test
    void failToCreatePercolationGridIfNLowerThan1() {
        assertThrows(IllegalArgumentException.class, () -> new Percolation(0));
    }

    @Test
    void failToOpenOutOfBoundSite() {
        Percolation p = new Percolation(3);

        assertThrows(IllegalArgumentException.class, () -> p.open(0, 1));
        assertThrows(IllegalArgumentException.class, () -> p.open(4, 1));
        assertThrows(IllegalArgumentException.class, () -> p.open(1, 0));
        assertThrows(IllegalArgumentException.class, () -> p.open(1, 4));
    }

    @Test
    void assessIfOutOfBoundSiteIsFullThrowsException() {
        Percolation p = new Percolation(2);

        assertThrows(IllegalArgumentException.class, () -> p.isFull(0, 1));
    }

    @Test
    void siteIsFullIfOpenAndConnectedToTopOpenSite() {
        Percolation p = new Percolation(2);

        assertThat(p.isFull(1, 1), is(false));
        assertThat(p.isFull(2, 1), is(false));


        p.open(1, 1);

        assertThat(p.isFull(1, 1), is(true));
        assertThat(p.isFull(2, 1), is(false));


        p.open(2, 1);

        assertThat(p.isFull(1, 1), is(true));
        assertThat(p.isFull(2, 1), is(true));
    }

    private Percolation createGridFromFile(File file) throws FileNotFoundException {
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
        Percolation p = createGridFromFile(input1);

        assertThat(p.percolates(), is(true));
    }

    @Test
    void gridBasedOnFileInput1NoDoesNotPercolates() throws FileNotFoundException {
        File input1No = new File("./src/test/resources/week1/assignment/percolation/input1-no.txt");
        Percolation p = createGridFromFile(input1No);

        assertThat(p.percolates(), is(false));
    }

    @Test
    void gridBasedOnFileInput2Percolates() throws FileNotFoundException {
        File input2 = new File("./src/test/resources/week1/assignment/percolation/input2.txt");
        Percolation p = createGridFromFile(input2);

        assertThat(p.percolates(), is(true));
    }

    @Test
    void gridBasedOnFileInput2NoDoesNotPercolates() throws FileNotFoundException {
        File input2No = new File("./src/test/resources/week1/assignment/percolation/input2-no.txt");
        Percolation p = createGridFromFile(input2No);

        assertThat(p.percolates(), is(false));
    }

    @Test
    void gridBasedOnFileInput3Percolates() throws FileNotFoundException {
        File input3 = new File("./src/test/resources/week1/assignment/percolation/input3.txt");
        Percolation p = createGridFromFile(input3);

        assertThat(p.percolates(), is(true));
    }

    @Test
    void gridBasedOnFileInput4Percolates() throws FileNotFoundException {
        File input4 = new File("./src/test/resources/week1/assignment/percolation/input4.txt");
        Percolation p = createGridFromFile(input4);

        assertThat(p.percolates(), is(true));
    }

    @Test
    void gridBasedOnFileInput5Percolates() throws FileNotFoundException {
        File input5 = new File("./src/test/resources/week1/assignment/percolation/input5.txt");
        Percolation p = createGridFromFile(input5);

        assertThat(p.percolates(), is(true));
    }

    @Test
    void gridBasedOnFileInput6Percolates() throws FileNotFoundException {
        File input6 = new File("./src/test/resources/week1/assignment/percolation/input6.txt");
        Percolation p = createGridFromFile(input6);

        assertThat(p.percolates(), is(true));
    }

    @Test
    void gridBasedOnFileInput7Percolates() throws FileNotFoundException {
        File input7 = new File("./src/test/resources/week1/assignment/percolation/input7.txt");
        Percolation p = createGridFromFile(input7);

        assertThat(p.percolates(), is(true));
    }

    @Test
    void gridBasedOnFileInput8Percolates() throws FileNotFoundException {
        File input8 = new File("./src/test/resources/week1/assignment/percolation/input8.txt");
        Percolation p = createGridFromFile(input8);

        assertThat(p.percolates(), is(true));
    }

    @Test
    void gridBasedOnFileInput8DupsPercolates() throws FileNotFoundException {
        File input8Dups = new File("./src/test/resources/week1/assignment/percolation/input8-dups.txt");
        Percolation p = createGridFromFile(input8Dups);

        assertThat(p.percolates(), is(true));
    }

    @Test
    void gridBasedOnFileInput8NoDoesNotPercolate() throws FileNotFoundException {
        File input8No = new File("./src/test/resources/week1/assignment/percolation/input8-no.txt");
        Percolation p = createGridFromFile(input8No);

        assertThat(p.percolates(), is(false));
    }

    @Test
    void gridBasedOnFileInput10Percolates() throws FileNotFoundException {
        File input10 = new File("./src/test/resources/week1/assignment/percolation/input10.txt");
        Percolation p = createGridFromFile(input10);

        assertThat(p.percolates(), is(true));
    }

    @Test
    void gridBasedOnFileInput10NoDoesNotPercolate() throws FileNotFoundException {
        File input10No = new File("./src/test/resources/week1/assignment/percolation/input10-no.txt");
        Percolation p = createGridFromFile(input10No);

        assertThat(p.percolates(), is(false));
    }

    @Test
    void gridBasedOnFileInput20Percolates() throws FileNotFoundException {
        File input20 = new File("./src/test/resources/week1/assignment/percolation/input20.txt");
        Percolation p = createGridFromFile(input20);

        assertThat(p.percolates(), is(true));
    }

    @Test
    void gridBasedOnFileInput50Percolates() throws FileNotFoundException {
        File input50 = new File("./src/test/resources/week1/assignment/percolation/input50.txt");
        Percolation p = createGridFromFile(input50);

        assertThat(p.percolates(), is(true));
    }

    @Test
    void gridBasedOnFileJerry47Percolates() throws FileNotFoundException {
        File jerry47 = new File("./src/test/resources/week1/assignment/percolation/jerry47.txt");
        Percolation p = createGridFromFile(jerry47);

        assertThat(p.percolates(), is(true));
    }

    @Test
    void gridBasedOnFileSedgewick60Percolates() throws FileNotFoundException {
        File sedgewick60 = new File("./src/test/resources/week1/assignment/percolation/sedgewick60.txt");
        Percolation p = createGridFromFile(sedgewick60);

        assertThat(p.percolates(), is(true));
    }

    @Test
    void gridBasedOnFileSnake13Percolates() throws FileNotFoundException {
        File snake13 = new File("./src/test/resources/week1/assignment/percolation/snake13.txt");
        Percolation p = createGridFromFile(snake13);

        assertThat(p.percolates(), is(true));
    }

    @Test
    void gridBasedOnFileSnake101Percolates() throws FileNotFoundException {
        File snake101 = new File("./src/test/resources/week1/assignment/percolation/snake101.txt");
        Percolation p = createGridFromFile(snake101);

        assertThat(p.percolates(), is(true));
    }

    @Test
    void gridBasedOnFileWayne98Percolates() throws FileNotFoundException {
        File wayne98 = new File("./src/test/resources/week1/assignment/percolation/wayne98.txt");
        Percolation p = createGridFromFile(wayne98);

        assertThat(p.percolates(), is(true));
    }

}
