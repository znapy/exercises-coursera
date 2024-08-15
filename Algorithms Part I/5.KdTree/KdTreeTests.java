

import edu.princeton.cs.algs4.StdOut;
import java.lang.String;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTreeTests {
	
	public static void main(String[] args) {
        KdTreeTests t = new KdTreeTests();

		t.throwsBySpecification();
		
		t.isEmpty();
		t.size();
		t.contains();
		
		t.insert();
		t.range();
		t.nearest();

        StdOut.println("tests succefull complete");
    }
    
    private void throwsBySpecification() {

        boolean exceptionCatched;
        
        KdTree ps = new KdTree();

        // 1
        exceptionCatched = false;
        try {
            ps.insert(null);
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof IllegalArgumentException))
                throw new UnsupportedOperationException(
                        "throwsBySpecification() ps.insert(null) (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException(
                    "throwsBySpecification() ps.insert(null) (no exception) failed");
                    
        // 2
        exceptionCatched = false;
        try {
			ps.contains(null);
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof IllegalArgumentException))
                throw new UnsupportedOperationException(
                        "throwsBySpecification() ps.contains(null) (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException(
                    "throwsBySpecification() ps.contains(null) (no exception) failed");
        
        // 3
        exceptionCatched = false;
        try {
			ps.range(null);
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof IllegalArgumentException))
                throw new UnsupportedOperationException(
                        "throwsBySpecification() ps.range(null) (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException(
                    "throwsBySpecification() ps.range(null) (no exception) failed");
                    
        // 4
        exceptionCatched = false;
        try {
			ps.nearest(null);
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof IllegalArgumentException))
                throw new UnsupportedOperationException(
                        "throwsBySpecification() ps.nearest(null) (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException(
                    "throwsBySpecification() ps.nearest(null) (no exception) failed");
                    
	}
	
	private void isEmpty() {
		
		KdTree ps = new KdTree();
		
		if (!ps.isEmpty())
			throw new UnsupportedOperationException(
						"isEmpty() 1 failed");
						
		ps.insert(new Point2D(0,0));
		if (ps.isEmpty())
			throw new UnsupportedOperationException(
						"isEmpty() 2 failed");
	}
	
	private void size() {
		
		KdTree ps = new KdTree();
		
		if (ps.size() != 0)
			throw new UnsupportedOperationException(
						"size() 1 failed");
		
		ps.insert(new Point2D(0,0));
		if (ps.size() != 1)
			throw new UnsupportedOperationException(
						"size() 2 failed, returned "+ps.size());
		
		ps.insert(new Point2D(0,0)); // already in set
		if (ps.size() != 1)
			throw new UnsupportedOperationException(
						"size() 3 failed, returned "+ps.size());
	}
	
	private void contains() {
		
		KdTree ps = new KdTree();
		
		Point2D p0 = new Point2D(0,0);
		
		if (ps.contains(p0))
			throw new UnsupportedOperationException(
						"contains() 1 failed");
		
		ps.insert(new Point2D(0,0));
		if (!ps.contains(p0))
			throw new UnsupportedOperationException(
						"contains() 2 failed");
	}
	
	private void insert() {
		
		KdTree ps = new KdTree();
		
		Point2D p = new Point2D(0,0);
		ps.insert(p);
		
		if (!ps.contains(p))
			throw new UnsupportedOperationException(
						"insert() 1 failed");
		
	}
	
	private void range() {
		
		KdTree ps = new KdTree();
		int i;
		Iterable<Point2D> result;
		
		RectHV rectAll = new RectHV(0,0,1,1);
		
		Point2D p0 = new Point2D(0,0);
		ps.insert(p0);
		
		i = 0;
		result = ps.range(rectAll);
		if (!result.iterator().hasNext())
			throw new UnsupportedOperationException(
							"range() 1-1 failed");
		for (Point2D p : result) {
			i++;
			if (i == 1 && p != p0)
				throw new UnsupportedOperationException(
							"range() 1-2 failed");
		}
		
		Point2D p1 = new Point2D(1,1);
		ps.insert(p1);
		
		i = 0;
		result = ps.range(rectAll);
		if (!result.iterator().hasNext())
			throw new UnsupportedOperationException(
							"range() 2-1 failed");
		for (Point2D p : result) {
			i++;
			if (i == 1 && p != p0)
				throw new UnsupportedOperationException(
							"range() 2-2 failed");
			if (i == 2 && p != p1)
				throw new UnsupportedOperationException(
							"range() 2-3 failed");
		}
		
		RectHV rectHalf1 = new RectHV(0,0,0.5,0.5);
		i = 0;
		result = ps.range(rectHalf1);
		if (!result.iterator().hasNext())
			throw new UnsupportedOperationException(
							"range() 3-1 failed");
		for (Point2D p : result) {
			i++;
			if (i == 1 && p != p0)
				throw new UnsupportedOperationException(
							"range() 3-2 failed");
			if (i == 2)
				throw new UnsupportedOperationException(
							"range() 3-3 failed");
		}
		
		RectHV rectHalf2 = new RectHV(0.5, 0.5, 1, 1);
		result = ps.range(rectHalf2);
		if (result.iterator().next() != p1)
			throw new UnsupportedOperationException(
						"range() 4 failed");
	}

	private void nearest() {
		
		KdTree ps = new KdTree();
		Point2D result;
		Point2D p0 = new Point2D(0,0);
		Point2D p05 = new Point2D(0.5,0.5);
		Point2D p1 = new Point2D(1,1);
		
		if (ps.nearest(p0) != null)
			throw new UnsupportedOperationException(
						"nearest() 1 failed");
						
		ps.insert(p0);
		result = ps.nearest(new Point2D(0,0));
		if (result != p0)
			throw new UnsupportedOperationException(
						"nearest() 2 failed, returned "+result);
						
		result = ps.nearest(p1);
		if (result != p0)
			throw new UnsupportedOperationException(
						"nearest() 3 failed");
						
		ps.insert(p1);
		result = ps.nearest(p05);
		if (result != p0)
			throw new UnsupportedOperationException(
						"nearest() 4 failed");
						
		result = ps.nearest(new Point2D(0.6, 0.6));
		if (result != p1)
			throw new UnsupportedOperationException(
						"nearest() 5 failed, returned "+result);
						
		ps = new KdTree();
		ps.insert(p05);
		ps.insert(p1);
		result = ps.nearest(p0);
		if (result != p05)
			throw new UnsupportedOperationException(
						"nearest() 6 failed");
						
		ps = new KdTree();
		ps.insert(p05);
		ps.insert(p0);
		result = ps.nearest(p1);
		if (result != p05)
			throw new UnsupportedOperationException(
						"nearest() 7 failed");
	}
	
}
