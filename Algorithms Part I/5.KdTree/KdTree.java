/*
 * KdTree.java
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
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {

	private Node head;
	private int size;
	private static final boolean VERTICAL = false;
	private static final boolean GORIZONTAL = true;
	
	private static class Node {
		
		private Point2D p;      // the point
		private RectHV rect;    // the axis-aligned rectangle corresponding to this node
		private Node lb;        // the left/bottom subtree
		private Node rt;        // the right/top subtree
		public Node(Point2D point, RectHV rectangle){
			p = point;
			rect = rectangle;
			lb = null;
			rt = null;
		}
		public Node rt() {return rt;}
		public Node lb() {return lb;}
		public void setRt(Node n) {rt = n;}
		public void setLb(Node n) {lb = n;}
		public Point2D point() {return p;}
		public RectHV rectangle() {return rect;}
		public void print() {
			double x1 = rect.xmin();
			double y1 = rect.ymin();
			double x2 = rect.xmax();
			double y2 = rect.ymax();
			StdOut.printf("point %4.2f %4.2f, rectangle %4.2f %4.2f - %4.2f %4.2f", p.x(), p.y(), x1, y1, x2, y2);
		}
	}
	
	private static final double key(Point2D point, boolean level) {
		if (level == VERTICAL)
			return point.x();
		return point.y();
	}
	
	public         KdTree() {                             // construct an empty set of points 
		head = null;
		size = 0;
	}
	
	public           boolean isEmpty() {                    // is the set empty? 
		return size() == 0;
	}
	
	public               int size() {                       // number of points in the set 
		return size;
	}
	
	public              void insert(Point2D p) {            // add the point to the set (if it is not already in the set)
		if (p == null)
			throw new IllegalArgumentException("point is null");
			
		if (head == null) {
			RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);
			head = new Node(p, rect);
			size = size +1;
			return;
		}
		
		Node last = setLastNodeForPoint(p);
		
	}
	
	private Node setLastNodeForPoint(Point2D p) {
		
		Node current = head;
		boolean level = VERTICAL;
		boolean goRightTop = false;
		double rect_x1 = 0;
		double rect_y1 = 0;
		double rect_x2 = 1;
		double rect_y2 = 1;
		
		while (true) {
			
			if (p.equals(current.point())) break;
			
			goRightTop = goRightTop(current.point(), level, p);
			
			if (goRightTop){
				rect_x1 = (level == GORIZONTAL) ? rect_x1 : current.point().x();
				rect_y1 = (level == VERTICAL) ? rect_y1 : current.point().y();
			}
			if (!goRightTop){
				rect_x2 = (level == GORIZONTAL) ? rect_x2 : current.point().x();
				rect_y2 = (level == VERTICAL) ? rect_y2 : current.point().y();
			}
			
			if (goRightTop && current.rt() == null){
				
				RectHV rect = new RectHV(rect_x1, rect_y1, rect_x2, rect_y2);
				current.setRt(new Node(p, rect));
				size = size +1;
				
				current = current.rt();
				break;
			}
			if (!goRightTop && current.lb() == null) {
				RectHV rect = new RectHV(rect_x1, rect_y1, rect_x2, rect_y2);
				current.setLb(new Node(p, rect));
				size = size +1;
				
				current = current.lb();
				break;
			}
			
			if (goRightTop) current = current.rt();
			if (!goRightTop) current = current.lb();
			
			level = !level;
		}
		return current;
	}
	
	public           boolean contains(Point2D p) {          // does the set contain point p? 
		if (p == null)
			throw new IllegalArgumentException("point is null");
			
		Node current = head;
		boolean level = VERTICAL;
		boolean goRightTop = false;
		while (current != null) {
			if (p.equals(current.point())) break;
			goRightTop = goRightTop(current.point(), level, p);
			current = (goRightTop) ? current.rt() : current.lb();
			level = !level;
		}
		return current != null;
	}
	
	private boolean goRightTop(Point2D prevPoint, boolean levelPrevPoint, Point2D exPoint) {
		
		boolean goRightTop = true;
		if (key(exPoint, levelPrevPoint) < key(prevPoint, levelPrevPoint))
			goRightTop = false;
			
		return goRightTop;
	}
	
	public              void draw() {                       // draw all points to standard draw 
		drawSubTree(head, null, VERTICAL);
		
	}
	
	private void drawSubTree(Node curNode, Point2D prevPoint, boolean level) {
		
		if (curNode == null)
			return;
		
		Point2D curPoint = curNode.point();
		
		boolean theLast = (curNode.lb() == null) && (curNode.rt() == null);
		drawPoint(curPoint, prevPoint, theLast);
		drawLine(curPoint, prevPoint, level);
		
		drawSubTree(curNode.lb(), curPoint, !level);
		drawSubTree(curNode.rt(), curPoint, !level);
	}
	
	private void drawPoint(Point2D curPoint, Point2D prevPoint, boolean theLast){
		StdDraw.setPenRadius(0.01);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.point(curPoint.x(), curPoint.y());
		
		//~ if (prevPoint != null && theLast) {
			//~ StdDraw.setPenColor(StdDraw.GREEN);
			//~ StdDraw.point(prevPoint.x(), prevPoint.y());
		//~ }
	}
	
	private void drawLine(Point2D curPoint, Point2D prevPoint, boolean level){
		
		StdDraw.setPenRadius(0.001);
		StdDraw.setPenColor(StdDraw.BLUE);
		if (level == VERTICAL)
			StdDraw.setPenColor(StdDraw.RED);
		
		double x1=0, x2=0, y1=0, y2=0;
		
		boolean goRightTop = false;
		boolean goLeftBottom = false;
		if (prevPoint != null) {
			goRightTop = goRightTop(prevPoint, !level, curPoint); // level from parant
			goLeftBottom = !goRightTop;
		}
		
		if (level == VERTICAL) {
			
			x1 = curPoint.x();
			y1 = (goRightTop) ? prevPoint.y() : 0;
			
			x2 = curPoint.x();
			y2 = (goLeftBottom) ? prevPoint.y() : 1;
			
		}
		if (level == GORIZONTAL) {
			
			x1 = (goRightTop) ? prevPoint.x() : 0;
			y1 = curPoint.y();
			
			x2 = (goLeftBottom) ? prevPoint.x() : 1;
			y2 = curPoint.y();
		}
			
		StdDraw.line(x1, y1, x2, y2);
	}
	
	public Iterable<Point2D> range(RectHV rect) {           // all points that are inside the rectangle (or on the boundary) 
		if (rect == null)
			throw new IllegalArgumentException("rectangle is null");
		
		Queue<Point2D> result = new Queue<Point2D>();
		
		rangeInSubtree(head, rect, result);
		
		return (Iterable<Point2D>) result;
	}
	
	private void rangeInSubtree(Node current, RectHV rect, Queue<Point2D> result) {
		if (current == null)
			return;
		if (!current.rectangle().intersects(rect))
			return;
		
		if (rect.contains(current.point()))
			result.enqueue(current.point());
		
		rangeInSubtree(current.lb(), rect, result);
		rangeInSubtree(current.rt(), rect, result);
	}
	
	public           Point2D nearest(Point2D p) {           // a nearest neighbor in the set to point p; null if the set is empty 
		if (p == null)
			throw new IllegalArgumentException("point is null");
			
		if (isEmpty())
			return null;
		
		NearestResult result = new NearestResult(null, 2.1);
		nearestInSubtree(head, p, result);
		return result.closestNode.point();
	}
	
	private class NearestResult {
		public Node closestNode;
		public double smallestDistance;
		public NearestResult(Node closestNode, double smallestDistance) {
			this.closestNode = closestNode;
			this.smallestDistance = smallestDistance;
		}
	}
	
	private void nearestInSubtree(Node current, Point2D target, NearestResult result) {
		
		if (current == null)
			return;
		
		double rectDistance = current.rectangle().distanceSquaredTo(target);
		
		if (result.smallestDistance < rectDistance)
			return;
		
		double pointDistance = current.point().distanceSquaredTo(target);
		if (pointDistance < result.smallestDistance) {
			result.closestNode = current;
			result.smallestDistance = pointDistance;
		}
		
		nearestInSubtree(current.lb(), target, result);
		nearestInSubtree(current.rt(), target, result);
	}

	public static void main(String[] args) {                // unit testing of the methods (optional) 
		
		//~ // initialize the two data structures with point from file
        //~ String filename = args[0];
        //~ In in = new In(filename);
        //~ PointSET brute = new PointSET();
        //~ KdTree kdtree = new KdTree();
        //~ while (!in.isEmpty()) {
            //~ double x = in.readDouble();
            //~ double y = in.readDouble();
            //~ Point2D p = new Point2D(x, y);
            //~ kdtree.insert(p);
            //~ brute.insert(p);
        //~ }
		
		//~ // nearest
		//~ RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);
        //~ StdDraw.enableDoubleBuffering();
        //~ while (true) {
            //~ if (StdDraw.isMousePressed()) {
                //~ double x = StdDraw.mouseX();
                //~ double y = StdDraw.mouseY();
                //~ Point2D p = new Point2D(x, y);
                //~ Point2D p_nb = brute.nearest(p);
                //~ Point2D p_nk = kdtree.nearest(p);
                //~ if (rect.contains(p)) {
                    //~ StdOut.printf("%8.6f %8.6f, nearest: ", x, y);
                    
                    //~ StdOut.printf(" brute=%8.6f %8.6f", p_nb.x(), p_nb.y());
                    //~ StdOut.printf(" kdtree=%8.6f %8.6f ", p_nk.x(), p_nk.y());
                    //~ StdOut.printf(" is the same: %b\n", p_nb==p_nk);
                    
                    //~ StdDraw.clear();
                    //~ kdtree.draw();
                    //~ StdDraw.show();
                //~ }
            //~ }
            //~ StdDraw.pause(10);
        //~ }
		
	}
}
