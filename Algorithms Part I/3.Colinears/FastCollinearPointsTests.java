public class FastCollinearPointsTests {
    public static void main(String[] args) {

        FastCollinearPointsTests t = new FastCollinearPointsTests();

        t.throwsBySpecification();

        t.numberOfSegments();

        t.segments();

        StdOut.println("tests succefull complete");
    }

    private void throwsBySpecification() {

        boolean exceptionCatched;

        // 1
        exceptionCatched = false;
        try {
            new FastCollinearPoints(null);
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof IllegalArgumentException))
                throw new UnsupportedOperationException(
                        "throwsBySpecification() FastCollinearPoints(null) (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException(
                    "throwsBySpecification() FastCollinearPoints(null) (no exception) failed");

        // 2
        exceptionCatched = false;
        Point[] points = new Point[2];
        points[0] = new Point(0, 0);
        try {
            new FastCollinearPoints(points.clone());
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof IllegalArgumentException))
                throw new UnsupportedOperationException(
                        "throwsBySpecification() FastCollinearPoints(points(null)) (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException(
                    "throwsBySpecification() FastCollinearPoints(points(null)) (no exception) failed");

        // 3
        exceptionCatched = false;
        points[1] = new Point(0, 0);
        try {
            new FastCollinearPoints(points.clone());
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof IllegalArgumentException))
                throw new UnsupportedOperationException(
                        "throwsBySpecification() repeated points (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException(
                    "throwsBySpecification() repeated points (no exception) failed");

        // 4
        exceptionCatched = false;
        points[1] = new Point(0, 1);
        try {
            new FastCollinearPoints(points.clone());
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof IllegalArgumentException))
                throw new UnsupportedOperationException(
                        "throwsBySpecification() length points (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException(
                    "throwsBySpecification() length points (no exception) failed");

    }

    private void numberOfSegments() {

        Point[] points;
        FastCollinearPoints f;

        points = new Point[5];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(2, 0);
        points[3] = new Point(3, 0);
        points[4] = new Point(4, 5);

        f = new FastCollinearPoints(points.clone());
        if (f.numberOfSegments() != 1)
            throw new UnsupportedOperationException(
                    "numberOfSegments() 1 failed: returned " + f.numberOfSegments());

        points[3] = new Point(3, 4);
        f = new FastCollinearPoints(points.clone());
        if (f.numberOfSegments() != 0)
            throw new UnsupportedOperationException(
                    "numberOfSegments() 2 failed: returned " + f.numberOfSegments());

        points[2] = new Point(2, 3);
        points[1] = new Point(1, 2);
        f = new FastCollinearPoints(points.clone());
        if (f.numberOfSegments() != 1)
            throw new UnsupportedOperationException(
                    "numberOfSegments() 3 failed: returned " + f.numberOfSegments());

        points = new Point[8];
        points[0] = new Point(10, 30);
        points[1] = new Point(3, 9);
        points[2] = new Point(9, 27);
        points[3] = new Point(1, 3);
        points[4] = new Point(4, 4);
        points[5] = new Point(-1, -1);
        points[6] = new Point(-1000, -1000);
        points[7] = new Point(10000, 10000);

        f = new FastCollinearPoints(points.clone());
        if (f.numberOfSegments() != 2)
            throw new UnsupportedOperationException(
                    "numberOfSegments() 4 failed: returned " + f.numberOfSegments());
    }

    private void segments() {

        Point[] points;
        LineSegment[] segments;

        points = new Point[5];
        points[0] = new Point(-1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(4, 0);
        points[3] = new Point(2, 0);
        points[4] = new Point(4, 3);

        segments = (new FastCollinearPoints(points.clone())).segments();
        if (segments.length != 1)
            throw new UnsupportedOperationException(
                    "segments() 1 failed: returned " + segments.length);
        if (!(segments[0].toString().equals("(-1, 0) -> (4, 0)")))
            throw new UnsupportedOperationException(
                    "segments() 2 failed: returned " + segments[0].toString());

        points[3] = new Point(3, 2);
        segments = (new FastCollinearPoints(points.clone())).segments();
        if (segments.length != 0)
            throw new UnsupportedOperationException(
                    "segments() 3 failed: returned " + segments.length);

        points[2] = new Point(2, 1);
        points[1] = new Point(1, 0);
        segments = (new FastCollinearPoints(points.clone())).segments();
        if (segments.length != 1)
            throw new UnsupportedOperationException(
                    "segments() 4 failed: returned " + segments.length);

        points = new Point[8];
        points[0] = new Point(10, 30);
        points[1] = new Point(3, 9);
        points[2] = new Point(9, 27);
        points[3] = new Point(1, 3);
        points[4] = new Point(4, 4);
        points[5] = new Point(-1, -1);
        points[6] = new Point(-1000, -1000);
        points[7] = new Point(10000, 10000);
        segments = (new FastCollinearPoints(points.clone())).segments();
        if (segments.length != 2)
            throw new UnsupportedOperationException(
                    "segments() 5 failed: returned " + segments.length);
        if (!(segments[0].toString().equals("(-1000, -1000) -> (10000, 10000)")))
            throw new UnsupportedOperationException(
                    "segments() 6 failed: returned " + segments[0].toString());
        if (!(segments[1].toString().equals("(1, 3) -> (10, 30)")))
            throw new UnsupportedOperationException(
                    "segments() 7 failed: returned " + segments[1].toString());
    }
}
