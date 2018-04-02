package week1.assignment;

import edu.princeton.cs.algorithms.WeightedQuickUnionUF;

public class Percolation {

    // vuf uses Union Find data structure to represents all the connected path of the grid
    // vuf is a flatten grid in one array
    // vuf[0] represents the virtual top site
    // vuf[n * n + 1] represents the virtual bottom site
    // vuf[(r - 1) * n + c] represents the site located on column c and row r
    private static final int VIRTUAL_TOP_SITE_INDEX = 0;
    private final int virtualBottomSiteIndex;
    private final WeightedQuickUnionUF vuf;

    // uf uses Union Find data structure to represents all the connected path of the grid
    // uf is a flatten grid in one array
    // uf[0] represents the virtual top site
    // uf[(r - 1) * n + c] represents the site located on column c and row r
    // The virtual bottom site is not added to the data structure in order to solve backwash problem
    // Read more at http://garajeando.blogspot.ca/2013/09/moocs-solved-backwash-problem-in.html
    private final WeightedQuickUnionUF uf;

    private int numberOfOpenSites = 0;
    private final int n;

    // grid is an array of array
    // grid does not contain top and bottom virtual site
    // Upper left site of the grid is column col = 1 and row row = 1
    private boolean[][] grid;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n < 1) throw new IllegalArgumentException("n " + n + " should be greater than 0");
        this.n = n;
        virtualBottomSiteIndex = n * n + 1;
        vuf = new WeightedQuickUnionUF(n * n + 2);
        uf = new WeightedQuickUnionUF(n * n + 1);
        grid = new boolean[n][n];
    }

    // return site(row, col) index from vuf data structure
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
                vuf.union(VIRTUAL_TOP_SITE_INDEX, ufSiteIndex(row, col));
                uf.union(VIRTUAL_TOP_SITE_INDEX, ufSiteIndex(row, col));
            }
            if (row > 1 && isOpen(row - 1, col)) {
                vuf.union(ufSiteIndex(row, col), ufSiteIndex(row - 1, col));
                uf.union(ufSiteIndex(row, col), ufSiteIndex(row - 1, col));
            }
            if (row < n && isOpen(row + 1, col)) {
                vuf.union(ufSiteIndex(row, col), ufSiteIndex(row + 1, col));
                uf.union(ufSiteIndex(row, col), ufSiteIndex(row + 1, col));
            }
            if (row == n) {
                vuf.union(virtualBottomSiteIndex, ufSiteIndex(row, col));
            }
            if (col > 1 && isOpen(row, col - 1)) {
                vuf.union(ufSiteIndex(row, col), ufSiteIndex(row, col - 1));
                uf.union(ufSiteIndex(row, col), ufSiteIndex(row, col - 1));
            }
            if (col < n && isOpen(row, col + 1)) {
                vuf.union(ufSiteIndex(row, col), ufSiteIndex(row, col + 1));
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
        return uf.connected(VIRTUAL_TOP_SITE_INDEX, ufSiteIndex(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return vuf.connected(VIRTUAL_TOP_SITE_INDEX, virtualBottomSiteIndex);
    }
}