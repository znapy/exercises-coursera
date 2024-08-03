public class BruteCollinearPointsTests {
    public static void main(String[] args) {
        BruteCollinearPointsTests t = new BruteCollinearPointsTests();

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
            new BruteCollinearPoints(null);
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof IllegalArgumentException))
                throw new UnsupportedOperationException(
                        "throwsBySpecification() BruteCollinearPoints(null) (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException(
                    "throwsBySpecification() BruteCollinearPoints(null) (no exception) failed");

        // 2
        exceptionCatched = false;
        Point[] points = new Point[4];
        try {
            new BruteCollinearPoints(points);
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof IllegalArgumentException))
                throw new UnsupportedOperationException(
                        "throwsBySpecification() BruteCollinearPoints(points(null)) (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException(
                    "throwsBySpecification() BruteCollinearPoints(points(null)) (no exception) failed");

        // 3
        exceptionCatched = false;
        points[0] = new Point(1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, 1);
        points[3] = new Point(0, 0);
        try {
            new BruteCollinearPoints(points);
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
    }

    private void numberOfSegments() {

        Point[] points = new Point[4];
        points[0] = new Point(1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, 1);
        points[3] = new Point(1, 1);

        BruteCollinearPoints b = new BruteCollinearPoints(points);
        if (b.numberOfSegments() != 0)
            throw new UnsupportedOperationException(
                    "numberOfSegments() 1 failed: returned " + b.numberOfSegments());

        points[0] = new Point(1, 0);
        points[1] = new Point(2, 0);
        points[2] = new Point(3, 0);
        points[3] = new Point(4, 0);
        b = new BruteCollinearPoints(points.clone());
        if (b.numberOfSegments() != 1)
            throw new UnsupportedOperationException(
                    "numberOfSegments() 2 failed: " + b.numberOfSegments());

        points = new Point[8];
        points[0] = new Point(0, 4);
        points[1] = new Point(0, 3);
        points[2] = new Point(0, 2);
        points[3] = new Point(0, 1);
        points[4] = new Point(1, 0);
        points[5] = new Point(1, -1);
        points[6] = new Point(1, -2);
        points[7] = new Point(1, -3);
        b = new BruteCollinearPoints(points.clone());
        if (b.numberOfSegments() != 2)
            throw new UnsupportedOperationException(
                    "numberOfSegments() 3 failed: " + b.numberOfSegments());

    }

    private void segments() {
        Point[] points;
        LineSegment[] s;

        points = new Point[4];
        points[0] = new Point(1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, 1);
        points[3] = new Point(1, 1);

        s = (new BruteCollinearPoints(points))
                .segments();
        if (s.length != 0)
            throw new UnsupportedOperationException(
                    "segments() 1 failed: returned " + s.length);

        points = new Point[4];
        points[0] = new Point(1, 2);
        points[1] = new Point(3, 6);
        points[2] = new Point(1001, 2002);
        points[3] = new Point(10, 20);
        s = (new BruteCollinearPoints(points))
                .segments();
        if (s.length != 1)
            throw new UnsupportedOperationException(
                    "segments() 2-1 failed: returned " + s.length);
        if (!(s[0].toString().equals("(1, 2) -> (1001, 2002)")))
            throw new UnsupportedOperationException(
                    "segments() 2-2 failed: returned " + s[0].toString());

        points = new Point[8];
        points[0] = new Point(3, 1);
        points[1] = new Point(9, 3);
        points[2] = new Point(3003, 1001);
        points[3] = new Point(15, 5);
        points[4] = new Point(1, 3);
        points[5] = new Point(3, 9);
        points[6] = new Point(1001, 3003);
        points[7] = new Point(5, 15);
        s = (new BruteCollinearPoints(points))
                .segments();
        if (s.length != 2)
            throw new UnsupportedOperationException(
                    "segments() 3-1 failed: returned " + s.length);
        if (!(s[0].toString().equals("(3, 1) -> (3003, 1001)")))
            throw new UnsupportedOperationException(
                    "segments() 3-2 failed: returned " + s[0].toString());
        if (!(s[1].toString().equals("(1, 3) -> (1001, 3003)")))
            throw new UnsupportedOperationException(
                    "segments() 3-3 failed: returned " + s[1].toString());

        points = new Point[8];
        points[0] = new Point(10000, 0);
        points[1] = new Point(0, 10000);
        points[2] = new Point(3000, 7000);
        points[3] = new Point(7000, 3000);
        points[4] = new Point(20000, 21000);
        points[5] = new Point(3000, 4000);
        points[6] = new Point(14000, 15000);
        points[7] = new Point(6000, 7000);
        s = (new BruteCollinearPoints(points))
                .segments();
        if (s.length != 2)
            throw new UnsupportedOperationException(
                    "segments() 4-1 failed: returned " + s.length);
        if (!(s[0].toString().equals("(10000, 0) -> (0, 10000)")))
            throw new UnsupportedOperationException(
                    "segments() 4-2 failed: returned " + s[0].toString());
        if (!(s[1].toString().equals("(3000, 4000) -> (20000, 21000)")))
            throw new UnsupportedOperationException(
                    "segments() 4-3 failed: returned " + s[1].toString());

    }
}
