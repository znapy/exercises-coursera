
import edu.princeton.cs.algs4.StdOut;
import java.lang.String;

public class BoardTests {
	
    public static void main(String[] args) {
        BoardTests t = new BoardTests();

		t.throwsBySpecification();

        t._toString();

        t.dimension();

        t.hamming();
        
        t.manhattan();
        
        t.isGoal();
        
        t.equals();
        
        t.neighbors();
        
        t.twin();

        StdOut.println("tests succefull complete");
    }
    
    private void throwsBySpecification() {

        boolean exceptionCatched;

        // 1
        exceptionCatched = false;
        try {
            new Board(null);
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof IllegalArgumentException))
                throw new UnsupportedOperationException(
                        "throwsBySpecification() Board(null) (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException(
                    "throwsBySpecification() Board(null) (no exception) failed");
                    
        // 2
        exceptionCatched = false;
        try {
			int[][] a = {{}};
			new Board(a);
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof IllegalArgumentException))
                throw new UnsupportedOperationException(
                        "throwsBySpecification() Board empty (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException(
                    "throwsBySpecification() Board empty (no exception) failed");
        
        // 3
        exceptionCatched = false;
        try {
			int[][] a = {{0}};
			new Board(a);
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof IllegalArgumentException))
                throw new UnsupportedOperationException(
                        "throwsBySpecification() Board size=1 (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException(
                    "throwsBySpecification() Board size=1 (no exception) failed");
                    
        // 4
        exceptionCatched = false;
        try {
			int[][] a = {{0},{1}};
			new Board(a);
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof IllegalArgumentException))
                throw new UnsupportedOperationException(
                        "throwsBySpecification() Board 4 (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException(
                    "throwsBySpecification() Board 4 (no exception) failed");
                    
        // 5
        exceptionCatched = false;
        try {
			int[][] a = {{0,1}};
			new Board(a);
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof IllegalArgumentException))
                throw new UnsupportedOperationException(
                        "throwsBySpecification() Board 5 (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException(
                    "throwsBySpecification() Board 5 (no exception) failed");
                    
        // 6
        exceptionCatched = false;
        try {
			new Board(new int[1000][1000]);
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof IllegalArgumentException))
                throw new UnsupportedOperationException(
                        "throwsBySpecification() Board 6 (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException(
                    "throwsBySpecification() Board 6 (no exception) failed");
	}
    
    private void _toString() {
		
		int[][] a = {{0,1,2},{3,4,5},{6,7,8}};
		Board b = new Board(a);
		
		String goal = "3\n  0  1  2\n  3  4  5\n  6  7  8";
		
		if (!b.toString().equals(goal))
			throw new UnsupportedOperationException(
                    "toString() 1 failed, catched:\n"+b.toString());
		
	}
    
    private void dimension() {
		
		int[][] a = {{0,1,2},{3,4,5},{6,7,8}};
		Board b = new Board(a);
		
		if (b.dimension() != 3)
			throw new UnsupportedOperationException(
                    "dimension() 1 failed");
		
	}
    
    private void hamming() {
		
		int[][] a = {{1,2,3},{4,5,6},{7,8,0}};
		Board b = new Board(a);
		if (b.hamming() != 0)
			throw new UnsupportedOperationException(
						"hamming() 1 failed");
						
		a[0][0]=2;
		a[0][1]=1;
		b = new Board(a);
		if (b.hamming() != 2)
			throw new UnsupportedOperationException(
						"hamming() 2 failed");
						
		a[0][2]=6;
		a[1][2]=3;
		b = new Board(a);
		if (b.hamming() != 4)
			throw new UnsupportedOperationException(
						"hamming() 3 failed");
						
		a[1][1]=0;
		a[2][2]=5;
		b = new Board(a);
		if (b.hamming() != 5)
			throw new UnsupportedOperationException(
						"hamming() 4 failed, getted " + b.hamming());
						
		int[][] a5 = {{1,2,3},{4,5,6},{0,7,8}};
		b = new Board(a5);
		if (b.hamming() != 2)
			throw new UnsupportedOperationException(
						"hamming() 5 failed");
	}
    
    private void manhattan() {
		
		int[][] a1 = {{1,2,3},{4,5,6},{7,8,0}};
		Board b = new Board(a1);
		if (b.manhattan() != 0)
			throw new UnsupportedOperationException(
						"manhattan() 1 failed");
						
		int[][] a2 = {{2,1,3},{4,5,6},{7,8,0}};
		b = new Board(a2);
		if (b.manhattan() != 2)
			throw new UnsupportedOperationException(
						"manhattan() 2 failed");
						
		int[][] a3 = {{7,2,3},{1,5,6},{4,8,0}};
		b = new Board(a3);
		if (b.manhattan() != 4)
			throw new UnsupportedOperationException(
						"manhattan() 3 failed, getted "+b.manhattan());
						
		int[][] a4 = {{0,2,3},{4,5,6},{7,8,1}};
		b = new Board(a4);
		if (b.manhattan() != 4)
			throw new UnsupportedOperationException(
						"manhattan() 4 failed, getted "+b.manhattan());
						
		int[][] a5 = {{0,2,7},{4,5,6},{3,8,1}};
		b = new Board(a5);
		if (b.manhattan() != 12)
			throw new UnsupportedOperationException(
						"manhattan() 5 failed, getted "+b.manhattan());
						
		
	}
    
    private void isGoal() {
		
		int[][] a1 = {{1,2,3},{4,5,6},{7,8,0}};
		Board b = new Board(a1);
		if (!b.isGoal())
			throw new UnsupportedOperationException(
						"isGoal() 1 failed");
						
		int[][] a2 = {{1,2,3},{4,5,6},{7,0,8}};
		b = new Board(a2);
		if (b.isGoal())
			throw new UnsupportedOperationException(
						"isGoal() 2 failed");
						
		int[][] a3 = {{1,2},{3,0}};
		b = new Board(a3);
		if (!b.isGoal())
			throw new UnsupportedOperationException(
						"isGoal() 3 failed");
		
	}
    
    private void equals() {
		
		int[][] a1 = {{1,2,3},{4,5,6},{7,8,0}};
		int[][] a2 = {{1,2,3},{4,5,6},{7,0,8}};
		int[][] a3 = {{1,2,3},{4,5,6},{7,0,8}};
		Board b = new Board(a2);
		if (b.equals(new Board(a1)))
			throw new UnsupportedOperationException(
						"equals() 1 failed");
						
		if (!b.equals(new Board(a3)))
			throw new UnsupportedOperationException(
						"equals() 2 failed");
						
		b = new Board(a1);
		int[][] a4 = {{1,2},{3,0}}; // Is goal too, but another dimension
		Board b2 = new Board(a4);
		if (b.equals(b2))
			throw new UnsupportedOperationException(
						"equals() 3 failed");
		
	}
    
    private void neighbors() {
		
		// 1
		
		int[][] a1 = {{1,2},{3,0}};
		Board b = new Board(a1);
		int i = 0;
		for(Board n: b.neighbors()){
			i++;
			int[][] a11 = {{1,2},{0,3}};
			if (i == 1){
				if (!n.equals(new Board(a11)))
					throw new UnsupportedOperationException(
							"neighbors() 1-1 failed");
				if (n.manhattan() != 1)
					throw new UnsupportedOperationException(
							"neighbors() 1-1-manhattan failed");
			}
			
			int[][] a12 = {{1,0},{3,2}};
			if (i == 2) {
				if (!n.equals(new Board(a12)))
					throw new UnsupportedOperationException(
							"neighbors() 1-2 failed");
				if (n.manhattan() != 1)
					throw new UnsupportedOperationException(
							"neighbors() 1-2-manhattan failed");
			}
			
			if (i == 3)
				throw new UnsupportedOperationException(
						"neighbors() 1-3 failed");
		}
		
		// 2
		
		int[][] a2 = {{0,2,3},{4,5,6},{7,8,1}};
		b = new Board(a2);
		i = 0;
		for(Board n: b.neighbors()){
			i++;
			int[][] a21 = {{2,0,3},{4,5,6},{7,8,1}};
			if (i == 1) {
				if (!n.equals(new Board(a21)))
					throw new UnsupportedOperationException(
							"neighbors() 2-1 failed");
				if (n.manhattan() != 5)
					throw new UnsupportedOperationException(
							"neighbors() 2-1-manhattan failed");
			}
			
			int[][] a22 = {{4,2,3},{0,5,6},{7,8,1}};
			if (i == 2) {
				if (!n.equals(new Board(a22)))
					throw new UnsupportedOperationException(
							"neighbors() 2-2 failed");
				if (n.manhattan() != 5)
					throw new UnsupportedOperationException(
							"neighbors() 2-2-manhattan failed");
			}
			
			if (i == 3)
				throw new UnsupportedOperationException(
						"neighbors() 2-3 failed");
		}
		
		// 3
		
		int[][] a3 = {{1,0,3},{4,5,6},{7,8,2}};
		b = new Board(a3);
		i = 0;
		for(Board n: b.neighbors()){
			i++;
			int[][] a31 = {{1,3,0},{4,5,6},{7,8,2}};
			if (i == 1) {
				if (!n.equals(new Board(a31)))
					throw new UnsupportedOperationException(
							"neighbors() 3-1 failed");
				if (n.manhattan() != 4)
					throw new UnsupportedOperationException(
							"neighbors() 3-1-manhattan failed");
			}
			
			int[][] a32 = {{1,5,3},{4,0,6},{7,8,2}};
			if (i == 2) {
				if (!n.equals(new Board(a32)))
					throw new UnsupportedOperationException(
							"neighbors() 3-2 failed");
				if (n.manhattan() != 4)
					throw new UnsupportedOperationException(
							"neighbors() 3-2-manhattan failed");
			}
						
			int[][] a33 = {{0,1,3},{4,5,6},{7,8,2}};
			if (i == 3) {
				if (!n.equals(new Board(a33)))
					throw new UnsupportedOperationException(
							"neighbors() 3-3 failed");
				if (n.manhattan() != 4)
					throw new UnsupportedOperationException(
							"neighbors() 3-3-manhattan failed");
			}
			
			if (i == 4)
				throw new UnsupportedOperationException(
						"neighbors() 3-4 failed");
		}
		
		// 4
		
		int[][] a4 = {{1,2,3},{4,0,6},{7,8,5}};
		b = new Board(a4);
		i = 0;
		for(Board n: b.neighbors()){
			i++;
			int[][] a41 = {{1,2,3},{4,6,0},{7,8,5}};
			if (i == 1) {
				if (!n.equals(new Board(a41)))
					throw new UnsupportedOperationException(
							"neighbors() 4-1 failed");
				if (n.manhattan() != 3)
					throw new UnsupportedOperationException(
							"neighbors() 4-1-manhattan failed");
			}
			
			int[][] a42 = {{1,2,3},{4,8,6},{7,0,5}};
			if (i == 2) {
				if (!n.equals(new Board(a42)))
					throw new UnsupportedOperationException(
							"neighbors() 4-2 failed");
				if (n.manhattan() != 3)
					throw new UnsupportedOperationException(
							"neighbors() 4-2-manhattan failed");
			}
						
			int[][] a43 = {{1,2,3},{0,4,6},{7,8,5}};
			if (i == 3) {
				if (!n.equals(new Board(a43)))
					throw new UnsupportedOperationException(
							"neighbors() 4-3 failed");
				if (n.manhattan() != 3)
					throw new UnsupportedOperationException(
							"neighbors() 4-3-manhattan failed");
			}
						
			int[][] a44 = {{1,0,3},{4,2,6},{7,8,5}};
			if (i == 4) {
				if (!n.equals(new Board(a44)))
					throw new UnsupportedOperationException(
							"neighbors() 4-4 failed");
				if (n.manhattan() != 3)
					throw new UnsupportedOperationException(
							"neighbors() 4-4-manhattan failed");
			}
			
			if (i == 5)
				throw new UnsupportedOperationException(
						"neighbors() 4-5 failed");
		}
		
	}
    
    private void twin() {
		int[][] a1 = {{1,2,3},{4,5,6},{7,8,0}};
		int[][] a2 = {{1,2,3},{4,5,6},{8,7,0}};
		Board b = new Board(a1);
		if (!b.twin().equals(new Board(a2)))
			throw new UnsupportedOperationException("twin() 1 failed");
			
		int[][] a3 = {{1,2},{3,0}};
		int[][] a4 = {{1,3},{2,0}};
		b = new Board(a3);
		if (!b.twin().equals(new Board(a4)))
			throw new UnsupportedOperationException("twin() 2 failed");
			
		int[][] a5 = {{1,2},{0,3}};
		int[][] a6 = {{2,1},{0,3}};
		b = new Board(a5);
		if (!b.twin().equals(new Board(a6)))
			throw new UnsupportedOperationException("twin() 3 failed, returned "+b.twin());
	}
}
