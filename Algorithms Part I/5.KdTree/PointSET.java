/*
 * PointSET.java
 * 
 * Copyright 2020 znapy
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */
 
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {

	private SET<Point2D> points;
	
	public         PointSET() {                             // construct an empty set of points 
		points = new SET<Point2D>();
	}
	
	public           boolean isEmpty() {                    // is the set empty? 
		return size() == 0;
	}
	
	public               int size() {                       // number of points in the set 
		return points.size();
	}
	
	public              void insert(Point2D p) {            // add the point to the set (if it is not already in the set)
		if (p == null)
			throw new IllegalArgumentException("point is null");
			
		points.add(p);
	}
	
	public           boolean contains(Point2D p) {          // does the set contain point p? 
		if (p == null)
			throw new IllegalArgumentException("point is null");
			
		return points.contains(p);
	}
	
	public              void draw() {                       // draw all points to standard draw 
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.01);
		for (Point2D p : points)
			StdDraw.point(p.x(), p.y());
		
		// splitting lines
		//StdDraw.setPenColor(StdDraw.RED);
		//StdDraw.setPenColor(StdDraw.BLUE);
		//StdDraw.setPenRadius();
	}
	
	public Iterable<Point2D> range(RectHV rect) {           // all points that are inside the rectangle (or on the boundary) 
		if (rect == null)
			throw new IllegalArgumentException("rectangle is null");
		
		Queue<Point2D> result = new Queue<Point2D>();
		
		for (Point2D p : points)
			if (rect.contains(p))
				result.enqueue(p);
		
		return (Iterable<Point2D>) result;
	}
	
	public           Point2D nearest(Point2D p) {           // a nearest neighbor in the set to point p; null if the set is empty 
		if (p == null)
			throw new IllegalArgumentException("point is null");
			
		if (isEmpty())
			return null;
		
		Point2D smallestPoint = null;
		double smallestDistance = 0;
		double distance;
		for (Point2D cur : points){
			distance = cur.distanceTo(p);
			if (smallestPoint == null){
				smallestPoint = cur;
				smallestDistance = distance;
				continue;
			}
			
			if (distance < smallestDistance){
				smallestDistance = distance;
				smallestPoint = cur;
			}
		}
		
		return smallestPoint;
	}

	public static void main(String[] args) {                // unit testing of the methods (optional) 
	}
}
