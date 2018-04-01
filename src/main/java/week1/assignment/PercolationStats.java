package week1.assignment;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private double[] percolationStatsResult;

    private static void validate(int n, int trials) {
        if (n < 1) throw new IllegalArgumentException("n " + n + " is not higher than 1");
        if (trials < 1) throw new IllegalArgumentException("trials " + trials + " is not higher than 1");
    }

    private static double runExperiment(int n) {
        Percolation p = new Percolation(n);
        while (!p.percolates()) {
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);
            p.open(row, col);
        }
        return (double) p.numberOfOpenSites() / (n * n);
    }

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        validate(n, trials);
        percolationStatsResult = new double[trials];
        for (int i = 0; i < trials; i++) {
            percolationStatsResult[i] = runExperiment(n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(percolationStatsResult);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(percolationStatsResult);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(percolationStatsResult.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(percolationStatsResult.length));
    }

    // test client (described below)
    public static void main(String[] args) {
        int n = StdIn.readInt();
        int trials = StdIn.readInt();
        StdOut.println("Percolation experiment result with n = " + n + " and trials = " + trials);
        PercolationStats ps = new PercolationStats(n, trials);
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}