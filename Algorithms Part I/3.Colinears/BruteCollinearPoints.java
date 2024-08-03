import java.util.Arrays;

public class BruteCollinearPoints {

    private int numberOfSegments;
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

        if (points == null)
            throw new IllegalArgumentException("points == null");

        for (int i = 0; i < points.length; i++)
            if (points[i] == null)
                throw new IllegalArgumentException("point " + i + " is null");

        Arrays.sort(points);

        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i - 1]) == 0)
                throw new IllegalArgumentException("point " + i + " repeated");
        }

        segments = new LineSegment[points.length];
        numberOfSegments = 0;

        double pq, pr, ps;
        boolean skip = false;

        for (int p = 0; p < points.length; p++) {
            for (int q = p + 1; q < points.length; q++) {
                for (int r = q + 1; r < points.length; r++) {
                    pq = points[p].slopeTo(points[q]);
                    pr = points[p].slopeTo(points[r]);
                    if (pq != pr)
                        continue;
                    for (int s = r + 1; s < points.length; s++) {
                        if (numberOfSegments == points.length)
                            break;
                        ps = points[p].slopeTo(points[s]);
                        if (pq != ps)
                            continue;
                        segments[numberOfSegments++] = new LineSegment(points[p], points[s]);
                        skip = true;
                        break;
                    }
                    if (skip) break;
                }
                if (skip) break;
            }
            skip = false;
        }

    }

    // the number of line segments
    public int numberOfSegments() {
        return numberOfSegments;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] s = new LineSegment[numberOfSegments];
        for (int i = 0; i < numberOfSegments; i++)
            s[i] = segments[i];
        return s;
    }
}
