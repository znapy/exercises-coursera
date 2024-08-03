import java.util.Arrays;

public class FastCollinearPoints {

    private int numberOfSegments;
    private LineSegment[] segments;

    public FastCollinearPoints(
            Point[] pointsOriginal) {
        // finds all line segments containing 4 or more points

        if (pointsOriginal == null)
            throw new IllegalArgumentException("points == null");

        Point[] points = pointsOriginal.clone();

        for (int i = 0; i < points.length; i++)
            if (points[i] == null)
                throw new IllegalArgumentException("point " + i + " is null");

        if (points.length < 4)
            throw new IllegalArgumentException("points.length < 4");

        numberOfSegments = 0;
        segments = new LineSegment[points.length];

        Point[] pointsInSO;
        double slope;
        Arrays.sort(points);
        for (int i = 0; i < points.length; i++) {

            if (i > 0
                    && points[i].compareTo(points[i - 1]) == 0)
                throw new IllegalArgumentException("point " + i + " repeated");

            pointsInSO = points.clone();
            Arrays.sort(pointsInSO,
                        points[i].slopeOrder()); // stable sort - maximum end point is real

            for (int j = 1; j < pointsInSO.length; j++) {

                if (j == (points.length - 2))
                    break;

                if (points[i].compareTo(pointsInSO[j]) > 0) continue;

                slope = points[i].slopeTo(pointsInSO[j]);

                if (points[i].slopeTo(pointsInSO[j + 1]) == slope
                        && points[i].slopeTo(pointsInSO[j + 2]) == slope) {
                    segments[numberOfSegments++] = new LineSegment(points[i],
                                                                   pointsInSO[j + 2]);
                    break;
                }
            }

        }
    }

    public int numberOfSegments() {
        // the number of line segments
        return numberOfSegments;

    }

    public LineSegment[] segments() {
        // the line segments
        LineSegment[] s = new LineSegment[numberOfSegments];
        for (int i = 0; i < numberOfSegments; i++)
            s[i] = segments[i];
        return s;
    }
}
