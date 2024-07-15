import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

    private double[] stat;

    private int experiments;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){

        if (n <= 0) throw new IllegalArgumentException("n <= 0");
        if (trials <= 0) throw new IllegalArgumentException("trials <= 0");

        experiments = trials;

        int row, col, step;
            int expNumber = 0;
            double[] statCur = new double[trials];
            while ( expNumber < trials ) {
                    Percolation perc = new Percolation(n);
                    row = StdRandom.uniform(n) + 1;
                    col = StdRandom.uniform(n) + 1;
                    while (!perc.percolates()) {
                            while (perc.isOpen(row, col)) {
                                    row = StdRandom.uniform(n) + 1;
                                    col = StdRandom.uniform(n) + 1;
                            }
                            //StdOut.println("" + expNumber + " (" + row + ", " + col + ")");
                            perc.open(row, col);
                    }
                    statCur[expNumber] = perc.numberOfOpenSites() / (double) (n*n);
                    //StdOut.println("   percolated in " + stat[expNumber]);
                    expNumber++;
            }

            stat = new double[expNumber];
            for (int i = 0; i < expNumber; i++) {
                    stat[i] = statCur[i];
            }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(stat);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(stat);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(experiments);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(experiments);
    }

   // test client (see below)
   public static void main(String[] args) {

            int n = Integer.parseInt(args[0]);
            int trials = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(n, trials);

            StdOut.println("mean: " + ps.mean() );

        StdOut.println("stddev: " + ps.stddev() );
    
        StdOut.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]" );    

   }

}

