package week1.assignment;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.algorithms.WeightedQuickUnionUF;

public class Percolation {

    private int numberOfOpenSites = 0;
    private final int n;

    // uf uses Union Find data structure to represents all the connected path of the grid
    // uf.parents is a flatten grid in one array
    // uf.parent[0] represents the virtual top site
    // uf.parent[n * n + 1] represents the virtual bottom site
    // uf.parent[(r - 1) * n +c] represents the site located on column c and row r
    private final WeightedQuickUnionUF uf;
    private final int virtualTopSiteIndex = 0;
    private final int virtualBottomSiteIndex;

    // grid is an array of array
    // grid does not contain top and bottom virtual site
    // Upper left site of the grid is column col = 1 and row row = 1
    private boolean[][] grid;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n < 1) throw new IllegalArgumentException("n " + n + " should be greater than 0");
        this.n = n;
        virtualBottomSiteIndex = n * n + 1;
        uf = new WeightedQuickUnionUF(n * n + 2);
        grid = new boolean[n][n];
    }

    // return site(row, col) index from uf data structure
    private int ufSiteIndex(int row, int col) {
        validate(row, col);
        return (row - 1) * n + col;
    }

    // convert column or row to the right grid index
    // for example site (1, 1) has index (0, 0) in the grid
    private static int gridSiteIndex(int i) {
        return i - 1;
    }

    // validate if site (row, col) is in range or not
    private void validate(int row, int col) {
        if (row < 1 || row > n) throw new IllegalArgumentException("row " + row + " is not between 1 and " + n);
        if (col < 1 || col > n) throw new IllegalArgumentException("col " + col + " is not between 1 and " + n);
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[gridSiteIndex(row)][gridSiteIndex(col)] = true;
            numberOfOpenSites++;
            if (row == 1) {
                uf.union(virtualTopSiteIndex, ufSiteIndex(row, col));
            }
            if (row > 1 && isOpen(row - 1, col)) {
                uf.union(ufSiteIndex(row, col), ufSiteIndex(row - 1, col));
            }
            if (row < n && isOpen(row + 1, col)) {
                uf.union(ufSiteIndex(row, col), ufSiteIndex(row + 1, col));
            }
            if (row == n) {
                uf.union(virtualBottomSiteIndex, ufSiteIndex(row, col));
            }
            if (col > 1 && isOpen(row, col - 1)) {
                uf.union(ufSiteIndex(row, col), ufSiteIndex(row, col - 1));
            }
            if (col < n && isOpen(row, col + 1)) {
                uf.union(ufSiteIndex(row, col), ufSiteIndex(row, col + 1));
            }
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[gridSiteIndex(row)][gridSiteIndex(col)];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf.connected(virtualTopSiteIndex, ufSiteIndex(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(virtualTopSiteIndex, virtualBottomSiteIndex);
    }

    // calculate the percolation threshold
    // !!! this method is private because it is not part of the assignment API and should not be exposed to PercolationStats !!!
    private double percolationThreshold() {
        return (double) numberOfOpenSites() / (n * n);
    }

    // run experiment
    // open site as long as the system does not percolate
    // !!! this method is private because it is not part of the assignment API and should not be exposed to PercolationStats !!!
    private void runExperiment() {
        while (!percolates()) {
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);
            open(row, col);
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation p = new Percolation(n);
        p.runExperiment();
        StdOut.println("The percolation threshold is " + p.numberOfOpenSites() + "/" + n * n + " = " + p.percolationThreshold());
    }
}