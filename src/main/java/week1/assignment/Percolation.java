package week1.assignment;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.algorithms.WeightedQuickUnionUF;

public class Percolation {

    private int numberOfOpenSites = 0;
    private int n;

    // uf uses Union Find data structure to represents all the connected path of the grid
    // uf.parents is a flatten grid in one array
    // uf.parent[0] represents the virtual top site
    // uf.parent[n * n + 1] represents the virtual bottom site
    // uf.parent[(r - 1) * n +c] represents the site located on column c and row r
    private WeightedQuickUnionUF uf;
    private int virtualTopSiteIndex = 0;
    private int virtualBottomSiteIndex;

    // return site(row, col) index from uf data structure
    public int getUFSiteIndex(int row, int col) {
        validate(row, col);
        return (row - 1) * n + col;
    }

    // grid is an array of array
    // grid does not contain top and bottom virtual site
    // Upper left site of the grid is column col = 1 and row row = 1
    private boolean[][] grid;

    // Convert column or row to the right grid index
    // For example site (1, 1) has index (0, 0) in the grid
    public static int getGridSiteIndex(int i) {
        return i - 1;
    }

    // Validate if site (row, col) is in range or not
    public void validate(int row, int col) {
        if (row < 1 || row > n) throw new IllegalArgumentException("row " + row + " is not between 1 and " + n);
        if (col < 1 || col > n) throw new IllegalArgumentException("col " + col + " is not between 1 and " + n);
    }

    // create n-by-n grid, with all sites blocked
    public Percolation(int N) {
        if (N < 1) throw new IllegalArgumentException("n " + N + " should be greater than 0");
        n = N;
        virtualBottomSiteIndex = n * n + 1;
        uf = new WeightedQuickUnionUF(n * n + 2);
        grid = new boolean[n][n];
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[getGridSiteIndex(row)][getGridSiteIndex(col)] = true;
            numberOfOpenSites++;
            if (row == 1) {
                uf.union(virtualTopSiteIndex, getUFSiteIndex(row, col));
            }
            if (row > 1 && isOpen(row - 1, col)) {
                uf.union(getUFSiteIndex(row, col), getUFSiteIndex(row - 1, col));
            }
            if (row < n && isOpen(row + 1, col)) {
                uf.union(getUFSiteIndex(row, col), getUFSiteIndex(row + 1, col));
            }
            if (row == n) {
                uf.union(virtualBottomSiteIndex, getUFSiteIndex(row, col));
            }
            if (col > 1 && isOpen(row, col - 1)) {
                uf.union(getUFSiteIndex(row, col), getUFSiteIndex(row, col - 1));
            }
            if (col < n && isOpen(row, col + 1)) {
                uf.union(getUFSiteIndex(row, col), getUFSiteIndex(row, col + 1));
            }
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[getGridSiteIndex(row)][getGridSiteIndex(col)];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf.connected(virtualTopSiteIndex, getUFSiteIndex(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(virtualTopSiteIndex, virtualBottomSiteIndex);
    }

    public String toString() {
        String output = "";
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if (!isOpen(row, col)) {
                    output += "x ";
                } else {
                    output += "o ";
                }
            }
            output += "\r\n";
        }
        return output;
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation p = new Percolation(n);
        while (!p.percolates()) {
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);
            p.open(row, col);
        }
        StdOut.println("Visual representation of the percolated system at the end of the simulation:");
        StdOut.println(p.toString());
        double x = (double) p.numberOfOpenSites() / (n * n);
        StdOut.println("The percolation threshold is " + p.numberOfOpenSites() + "/" + n * n + " = " + x);
    }
}