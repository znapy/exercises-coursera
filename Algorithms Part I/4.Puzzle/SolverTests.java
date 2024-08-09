
import edu.princeton.cs.algs4.StdOut;
import java.lang.String;
import java.util.Iterator;

public class SolverTests {
	
    public static void main(String[] args) {
        SolverTests t = new SolverTests();

		t.throwsBySpecification();

        t.isSolvable();
        t.moves();
        t.solution();
        
        StdOut.println("tests succefull complete");
    }
    static private void f(int a){
			a = 0;
		}
    private void throwsBySpecification() {

        boolean exceptionCatched;

        // 1
        exceptionCatched = false;
        try {
            new Solver(null);
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof IllegalArgumentException))
                throw new UnsupportedOperationException(
                        "throwsBySpecification() Solver(null) (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException(
                    "throwsBySpecification() Solver(null) (no exception) failed");
                    
        
	}
    
    private void isSolvable() {
		
		int[][] a = {{1,2,3},{4,5,6},{8,7,0}};
		Solver s = new Solver(new Board(a));
		if (s.isSolvable())
			throw new UnsupportedOperationException(
						"isSolvable() 1 failed");
						
		int[][] a2 = {{1,2,3},{4,5,6},{7,8,0}};
		s = new Solver(new Board(a2));
		if (!s.isSolvable())
			throw new UnsupportedOperationException(
						"isSolvable() 2 failed");
						
		int[][] a3 = {{1,2,3},{4,5,6},{7,0,8}};
		s = new Solver(new Board(a3));
		if (!s.isSolvable())
			throw new UnsupportedOperationException(
						"isSolvable() 3 failed");
		
	}
    
    private void moves() {
		
		int[][] a = {{1,2,3},{4,5,6},{8,7,0}};
		Solver s = new Solver(new Board(a));
		if (s.moves() != -1)
			throw new UnsupportedOperationException(
						"moves() 1 failed, returned "+s.moves());
						
		int[][] a2 = {{1,2,3},{4,5,6},{7,8,0}};
		s = new Solver(new Board(a2));
		if (s.moves() != 0)
			throw new UnsupportedOperationException(
						"moves() 2 failed");
						
		int[][] a3 = {{1,2,3},{4,5,6},{7,0,8}};
		s = new Solver(new Board(a3));
		if (s.moves() != 1)
			throw new UnsupportedOperationException(
						"moves() 3 failed");
						
		int[][] a4 = {{1,2,0},{4,5,3},{7,8,6}};
		s = new Solver(new Board(a4));
		if (s.moves() != 2)
			throw new UnsupportedOperationException(
						"moves() 4 failed");
		
	}
    
    private void solution() {
		
		int[][] a0 = {{1,2,3},{4,5,6},{8,7,0}};
		Solver s = new Solver(new Board(a0));
		if (s.solution() != null)
			throw new UnsupportedOperationException(
						"solution() 0 failed, returned "+s.solution());
		
		int[][] a1 = {{1,2,3},{4,5,6},{7,8,0}};
		Iterator<Board> res = (new Solver(new Board(a1))).solution().iterator();
		if (!res.hasNext())
			throw new UnsupportedOperationException(
						"solution() 1-1 failed");
		if (!res.next().equals( new Board(a1) ))
			throw new UnsupportedOperationException(
						"solution() 1-2 failed");
		if (res.hasNext())
			throw new UnsupportedOperationException(
						"solution() 1-3 failed");

		int[][] a2 = {{1,2,3},{4,5,6},{7,0,8}};
		res = (new Solver(new Board(a2))).solution().iterator();
		if (!res.hasNext())
			throw new UnsupportedOperationException(
						"solution() 2-1 failed");
		if (!res.next().equals( new Board(a2) ))
			throw new UnsupportedOperationException(
						"solution() 2-2 failed");
		if (!res.hasNext())
			throw new UnsupportedOperationException(
						"solution() 2-3 failed");
		if (!res.next().equals( new Board(a1) ))
			throw new UnsupportedOperationException(
						"solution() 2-4 failed");
		if (res.hasNext())
			throw new UnsupportedOperationException(
						"solution() 2-5 failed");
						
		int[][] a3 = {{1,2,3},{4,5,6},{0,7,8}};
		Iterable<Board> solution = (new Solver(new Board(a3))).solution();
		res = solution.iterator();
		if (!res.hasNext())
			throw new UnsupportedOperationException(
						"solution() 3-1 failed");
		if (!res.next().equals( new Board(a3) ))
			throw new UnsupportedOperationException(
						"solution() 3-2 failed");
		if (!res.hasNext())
			throw new UnsupportedOperationException(
						"solution() 3-3 failed");
		if (!res.next().equals( new Board(a2) ))
			throw new UnsupportedOperationException(
						"solution() 3-4 failed");
		if (!res.hasNext())
			throw new UnsupportedOperationException(
						"solution() 3-5 failed");
		if (!res.next().equals( new Board(a1) ))
			throw new UnsupportedOperationException(
						"solution() 3-6 failed");
		if (res.hasNext())
			throw new UnsupportedOperationException(
						"solution() 3-7 failed");
	}
}
