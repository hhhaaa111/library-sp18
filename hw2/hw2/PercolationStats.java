package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

import static java.lang.Math.sqrt;

public class PercolationStats {
    private  int T;
    private double[] arr;
    private double a;
    private double b;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must be greater than 0");
        }
        this.T = T;
        arr = new double[T];
        for (int i = 0; i < T; ++i) {
            Percolation percolations = pf.make(N);
            while (!percolations.percolates()) {
                int row,col;
                do{
                row = StdRandom.uniform(0, N);
                col = StdRandom.uniform(0, N);
                } while( percolations.isOpen(row,col));
                percolations.open(row, col);
            }
            arr[i] = (double) percolations.numberOfOpenSites() / (N * N);
        }

        this.a = this.mean();
        this.b = this.stddev();
    }

    //sample mean of percolation threshold,样本的平均值
    public double mean(){
        return StdStats.mean(arr);
    }


    public double stddev(){
        return StdStats.stddev(arr);
    }

    //low endpoint of 95% confidence interval
    public double confidenceLow(){
        return a - (1.96 * b) / sqrt(T);
    }

    public double confidenceHigh(){
        return a + (1.96 * b) / sqrt(T);
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(20, 10, new PercolationFactory());
        System.out.println(ps.mean());
        System.out.println(ps.stddev());
    }
}
