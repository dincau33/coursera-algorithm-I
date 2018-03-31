package week1.assignment;

import edu.princeton.cs.introcs.StdOut;
//import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.algorithms.WeightedQuickUnionUF;

public class Percolation {

    // uf uses Union Find data structure to represents all the connected path of the grid
    // uf.parents is a flatten grid in one array
    // uf.parent[0] represents the virtual top site
    // uf.parent[n * n + 1] represents the virtual bottom site
    // uf.parent[(r - 1) * n +c] represents the site located on column c and row r
    private WeightedQuickUnionUF uf;

    private int virtualTopSiteIndex() {
        return 0;
    }

    private int virtualBottomSiteIndex() {
        return this.n * this.n + 1;
    }

    private int getSiteIndexFromUF(int row, int col) {
        return (row - 1) * this.n + col;
    }

    // grid is a array of array
    // grid[r-1][c-1] returns true if site located on column c and row r is opened
    // grid[r-1][c-1] returns false if site located on column c and row r is full
    // grid does not contain top and bottom virtual site
    // Upper left site of the grid is column c = 1 and row r = 1
    private boolean[][] grid;

    private int getSiteIndexFromGrid(int i) {
        return i - 1;
    }

    private void validate(int row, int col) {
        if (row < 1 || row > n) throw new IllegalArgumentException("row " + row + " is not between 1 and " + n);
        if (col < 1 || col > n) throw new IllegalArgumentException("col " + col + " is not between 1 and " + n);
    }

    private int numberOfOpenSites = 0;
    private int n;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n < 1) throw new IllegalArgumentException("n " + n + " is not absolutely positive");
        this.n = n;
        uf = new WeightedQuickUnionUF(n * n + 2);
        grid = new boolean[n][n];
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                grid[getSiteIndexFromGrid(row)][getSiteIndexFromGrid(col)] = false;
            }
        }
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (isFull(row, col)) {
            grid[getSiteIndexFromGrid(row)][getSiteIndexFromGrid(col)] = true;
            numberOfOpenSites++;
            if (row == 1) {
                uf.union(virtualTopSiteIndex(), getSiteIndexFromUF(row, col));
            }
            if (row > 1 && isOpen(row - 1, col)) {
                uf.union(getSiteIndexFromUF(row, col), getSiteIndexFromUF(row - 1, col));
            }
            if (row < n && isOpen(row + 1, col)) {
                uf.union(getSiteIndexFromUF(row, col), getSiteIndexFromUF(row + 1, col));
            }
            if (row == n) {
                uf.union(virtualBottomSiteIndex(), getSiteIndexFromUF(row, col));
            }
            if (col > 1 && isOpen(row, col - 1)) {
                uf.union(getSiteIndexFromUF(row, col), getSiteIndexFromUF(row, col - 1));
            }
            if (col < n && isOpen(row, col + 1)) {
                uf.union(getSiteIndexFromUF(row, col), getSiteIndexFromUF(row, col + 1));
            }
        } else {
            StdOut.println("Site (" + row + "," + col + ") is already opened");
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        return this.grid[this.getSiteIndexFromGrid(row)][this.getSiteIndexFromGrid(col)];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        return !this.grid[this.getSiteIndexFromGrid(row)][this.getSiteIndexFromGrid(col)];
    }

    // number of open sites
    public int numberOfOpenSites() {
        return this.numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(this.virtualTopSiteIndex(), this.virtualBottomSiteIndex());
    }

    public String toString() {
        String output = "";
        for (int row = 1; row <= this.n; row++) {
            for (int col = 1; col <= this.n; col++) {
                if (isFull(row, col)) {
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
        Percolation p = new Percolation(2);
        StdOut.println("Visual representation of the current system at the beginning of the simulation:");
        StdOut.println(p.toString());
        StdOut.println("Number of open sites: " + p.numberOfOpenSites());
        p.open(1, 1);
        p.open(1, 2);
        p.open(2, 2);
        StdOut.println("Visual representation of the current system at the end of the simulation:");
        StdOut.println(p.toString());
        StdOut.println("Number of open sites: " + p.numberOfOpenSites());
        if (p.percolates()) {
            StdOut.println("The system percolate");
        }
    }
}