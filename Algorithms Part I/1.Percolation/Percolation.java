import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {

    private WeightedQuickUnionUF uf;
    private int size, headInd;
    private boolean[] opened;
    private int numberOfOpened;
   
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {

        if (n <= 0) throw new IllegalArgumentException("n <= 0");

        size = n;
        headInd = n * n + 1;
        uf = new WeightedQuickUnionUF(headInd + 1);
        opened = new boolean[n*n];
//        for (int i = 0; i < n; i++) {
//            uf.union(headInd - 1, i);
            //StdOut.println("union " + (headInd - 1) + ", " + i);
//            uf.union(headInd, headInd - 2 - i);
            //StdOut.println("union " + (headInd) + ", " + (headInd - 2 - i));
//        }
    }

    private int index(int row, int col) {

        if (row <= 0) throw new IllegalArgumentException("row <= 0");
            if (col <= 0) throw new IllegalArgumentException("col <= 0");

        if (row > size) throw new IllegalArgumentException("row > size");
            if (col > size) throw new IllegalArgumentException("col > size");

        return size * (row - 1) + (col - 1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        if ( isOpen(row, col) ) return;

        int neighbourInd, neighbourRow, neighbourCol;

        int ind = index(row, col);

        opened[ind] = true;

        numberOfOpened = numberOfOpened + 1;

        if ( row == 1 )
            uf.union(headInd - 1, ind);

        if ( row == size )
            uf.union(headInd, ind);

        if ( row > 1 ) { // top neighbour
            neighbourRow = row - 1;
            neighbourCol = col;
            neighbourInd = index(neighbourRow, neighbourCol);
            if ( isOpen(neighbourRow, neighbourCol) ) uf.union(neighbourInd, ind);
            //StdOut.println("union top: " + neighbourRow + ", " + neighbourCol + " | " + neighbourInd + ", " + ind);}

        }

        if ( row < size ) { // down neighbour
            neighbourRow = row + 1;
                    neighbourCol = col;
            neighbourInd = index(neighbourRow, neighbourCol);
            if ( isOpen(neighbourRow, neighbourCol) ) uf.union(neighbourInd, ind);
                //StdOut.println("union down: " + neighbourRow + ", " + neighbourCol + " | " + neighbourInd + ", " + ind);}
        }

        if ( col > 1 ) { // left neighbour
            neighbourRow = row;
                    neighbourCol = col - 1;
                    neighbourInd = index(neighbourRow, neighbourCol);
                    if ( isOpen(neighbourRow, neighbourCol) ) uf.union(neighbourInd, ind);
                //StdOut.println("union left: " + neighbourRow + ", " + neighbourCol + " | " + neighbourInd + ", " + ind);}

            }

        if ( col < size ) { // right neighbour
            neighbourRow = row;
                    neighbourCol = col + 1;
                    neighbourInd = index(neighbourRow, neighbourCol);
                    if ( isOpen(neighbourRow, neighbourCol) ) uf.union(neighbourInd, ind);
                //StdOut.println("union top: " + neighbourRow + ", " + neighbourCol + " | " + neighbourInd + ", " + ind);}

            }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return opened[index(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isOpen(row, col)) {
            return false;
        }
        return uf.connected(headInd - 1, index(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpened;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(headInd - 1, headInd);
    }

    // test client (optional)
    public static void main(String[] args) {

    } 
}
