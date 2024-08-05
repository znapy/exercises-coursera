import java.util.Arrays;

public class PointTests {
    public static void main(String[] args) {
        PointTests t = new PointTests();

        t.compareTo();

        t.slopeTo();

        t.slopeOrder();

        StdOut.println("tests succefull complete");
    }

    private void compareTo() {
        Point point00 = new Point(0, 0);
        Point point10 = new Point(1, 0);
        Point point01 = new Point(0, 1);
        Point point11 = new Point(1, 1);

        // to x=0, y=0
        if (point00.compareTo(point00) != 0)
            throw new UnsupportedOperationException(
                    "compareTo() 1 failed");

        StdOut.println();
        if (!(point00.compareTo(point10) < 0))
            throw new UnsupportedOperationException(
                    "compareTo() 2 failed");

        if (!(point00.compareTo(point01) < 0))
            throw new UnsupportedOperationException(
                    "compareTo() 3 failed");

        if (!(point00.compareTo(point11) < 0))
            throw new UnsupportedOperationException(
                    "compareTo() 4 failed");

        // to x=1, y=0
        if (!(point10.compareTo(point00) > 0))
            throw new UnsupportedOperationException(
                    "compareTo() 2-1 failed");

        if (point10.compareTo(point10) != 0)
            throw new UnsupportedOperationException(
                    "compareTo() 2-2 failed");

        if (!(point10.compareTo(point01) < 0))
            throw new UnsupportedOperationException(
                    "compareTo() 2-3 failed");

        if (!(point10.compareTo(point11) < 0))
            throw new UnsupportedOperationException(
                    "compareTo() 2-4 failed");

        // to x=0, y=1
        if (!(point01.compareTo(point00) > 0))
            throw new UnsupportedOperationException(
                    "compareTo() 3-1 failed");

        if (!(point01.compareTo(point10) > 0))
            throw new UnsupportedOperationException(
                    "compareTo() 3-2 failed");

        if (point01.compareTo(point01) != 0)
            throw new UnsupportedOperationException(
                    "compareTo() 3-3 failed");

        if (!(point01.compareTo(point11) < 0))
            throw new UnsupportedOperationException(
                    "compareTo() 3-4 failed");

        // to x=1, y=1
        if (!(point11.compareTo(point00) > 0))
            throw new UnsupportedOperationException(
                    "compareTo() 4-1 failed");

        if (!(point11.compareTo(point10) > 0))
            throw new UnsupportedOperationException(
                    "compareTo() 4-2 failed");

        if (!(point11.compareTo(point01) > 0))
            throw new UnsupportedOperationException(
                    "compareTo() 4-3 failed");

        if (point11.compareTo(point11) != 0)
            throw new UnsupportedOperationException(
                    "compareTo() 4-4 failed");

        // other
        Point point21 = new Point(2, 1);
        if (!(point00.compareTo(point21) < 0))
            throw new UnsupportedOperationException(
                    "compareTo() 5 failed");

        if (!(point21.compareTo(point00) > 0))
            throw new UnsupportedOperationException(
                    "compareTo() 6 failed");

        Point point12 = new Point(1, 2);
        if (!(point00.compareTo(point12) < 0))
            throw new UnsupportedOperationException(
                    "compareTo() 7 failed");

        if (!(point12.compareTo(point00) > 0))
            throw new UnsupportedOperationException(
                    "compareTo() 8 failed"); //*/
    }

    private void slopeTo() {
        Point point00 = new Point(0, 0);
        Point point10 = new Point(1, 0);
        Point point01 = new Point(0, 1);
        Point point11 = new Point(1, 1);

        // to x=0, y=0
        if (point00.slopeTo(point00) != Double.NEGATIVE_INFINITY)
            throw new UnsupportedOperationException(
                    "slopeTo() 1 failed");

        if (point00.slopeTo(point10) != 0.0)
            throw new UnsupportedOperationException(
                    "slopeTo() 2 failed");

        if (point00.slopeTo(point01) != Double.POSITIVE_INFINITY)
            throw new UnsupportedOperationException(
                    "slopeTo() 3 failed");

        if (point00.slopeTo(point11) != 1.0)
            throw new UnsupportedOperationException(
                    "slopeTo() 4 failed");

        // to x=1, y=0
        if (point10.slopeTo(point00) != 0.0)
            throw new UnsupportedOperationException(
                    "slopeTo() 2-1 failed");

        if (point10.slopeTo(point10) != Double.NEGATIVE_INFINITY)
            throw new UnsupportedOperationException(
                    "slopeTo() 2-2 failed");

        if (point10.slopeTo(point01) != -1.0)
            throw new UnsupportedOperationException(
                    "slopeTo() 2-3 failed");

        if (point10.slopeTo(point11) != Double.POSITIVE_INFINITY)
            throw new UnsupportedOperationException(
                    "slopeTo() 2-4 failed");

        // to x=0, y=1
        if (point01.slopeTo(point00) != Double.POSITIVE_INFINITY)
            throw new UnsupportedOperationException(
                    "slopeTo() 3-1 failed");

        if (point01.slopeTo(point10) != -1)
            throw new UnsupportedOperationException(
                    "slopeTo() 3-2 failed");

        if (point01.slopeTo(point01) != Double.NEGATIVE_INFINITY)
            throw new UnsupportedOperationException(
                    "slopeTo() 3-3 failed");

        if (point01.slopeTo(point11) != 0.0)
            throw new UnsupportedOperationException(
                    "slopeTo() 3-4 failed");

        // to x=1, y=1
        if (point11.slopeTo(point00) != 1)
            throw new UnsupportedOperationException(
                    "slopeTo() 4-1 failed");

        if (point11.slopeTo(point10) != Double.POSITIVE_INFINITY)
            throw new UnsupportedOperationException(
                    "slopeTo() 4-2 failed");

        if (point11.slopeTo(point01) != 0.0)
            throw new UnsupportedOperationException(
                    "slopeTo() 4-3 failed");

        if (point11.slopeTo(point11) != Double.NEGATIVE_INFINITY)
            throw new UnsupportedOperationException(
                    "slopeTo() 4-4 failed");

        // other
        Point point21 = new Point(2, 1);
        if (point00.slopeTo(point21) != 0.5)
            throw new UnsupportedOperationException(
                    "slopeTo() 5 failed");

        if (point21.slopeTo(point00) != 0.5)
            throw new UnsupportedOperationException(
                    "slopeTo() 6 failed");

        Point point12 = new Point(1, 2);
        if (point00.slopeTo(point12) != 2.0)
            throw new UnsupportedOperationException(
                    "slopeTo() 7 failed");

        if (point12.slopeTo(point00) != 2.0)
            throw new UnsupportedOperationException(
                    "slopeTo() 8 failed");
    }

    private void slopeOrder() {

        Point[] points = new Point[7];
        Point point00 = new Point(0, 0);
        points[0] = point00;
        points[1] = new Point(1, 0);
        points[2] = new Point(0, 1);
        points[3] = new Point(1, 1);
        points[4] = new Point(-1, 0);
        points[5] = new Point(0, -1);
        points[6] = new Point(-1, -1);

        Arrays.sort(points, point00.slopeOrder());

        if (!("(0, 0)".equals(points[0].toString())))
            throw new UnsupportedOperationException(
                    "slopeOrder() 1 failed");

        if (!("(1, 0)".equals(points[1].toString())
                || "(-1, 0)".equals(points[1].toString())))
            throw new UnsupportedOperationException(
                    "slopeOrder() 2 failed");

        if (!("(1, 0)".equals(points[2].toString())
                || "(-1, 0)".equals(points[2].toString())))
            throw new UnsupportedOperationException(
                    "slopeOrder() 3 failed");

        if (!("(1, 1)".equals(points[3].toString())
                || "(-1, -1)".equals(points[3].toString())))
            throw new UnsupportedOperationException(
                    "slopeOrder() 4 failed");

        if (!("(1, 1)".equals(points[4].toString())
                || "(-1, -1)".equals(points[4].toString())))
            throw new UnsupportedOperationException(
                    "slopeOrder() 5 failed");

        if (!("(0, 1)".equals(points[5].toString())
                || "(0, -1)".equals(points[5].toString())))
            throw new UnsupportedOperationException(
                    "slopeOrder() 5 failed");

        if (!("(0, 1)".equals(points[6].toString())
                || "(0, -1)".equals(points[6].toString())))
            throw new UnsupportedOperationException(
                    "slopeOrder() 5 failed");
    }
}
