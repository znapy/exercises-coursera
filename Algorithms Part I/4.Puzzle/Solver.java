/*
 * Solver.java
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
 
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import java.util.Comparator;
import java.lang.Math;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
 
public class Solver {
	
	private Node lastNode = null;
	private Node lastTwin = null;
	private MinPQ<Node> pqMain;
	
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
		
		if (initial == null)
			throw new IllegalArgumentException("initial shouldn't be null");
		
		GameOrder gameOrder = new GameOrder();
		pqMain = new MinPQ<Node>(gameOrder);
		
		lastNode = new Node(null, initial);
		lastTwin = new Node(null, initial.twin());
		lastTwin.isTwin = true;
		addNeighbors2PQ(lastNode);
		addNeighbors2PQ(lastTwin);
		
		while (!isOver())
			setNextNode();
		
		if (!lastNode.board.isGoal()) // out of memory as unsolvable too
			lastNode = null;
		
		pqMain = null;
	}

	private boolean isOver() {
		
		return lastNode.board.isGoal()
			|| lastTwin.board.isGoal();
		
	}

	private class Node {
		public Node parant;
		public Board board;
		public boolean isTwin = false;
		public byte moves = 0;
		public int priority = 0;
		public byte manhattan = 0;
		
		public Node(Node parant, Board board) {
			this.parant = parant;
			this.board = board;
			if (parant != null)
				this.moves = (byte) (parant.moves + 1);
			
			if (parant != null)
				isTwin = parant.isTwin;
			
			priority = board.manhattan() + moves;
			manhattan = (byte) board.manhattan();
		}

	}
	
	private void setNextNode() {
		
		Node minNode = pqMain.delMin();
		
		if (!minNode.isTwin)
			lastNode = minNode;
		if (minNode.isTwin)
			lastTwin = minNode;
		
		addNeighbors2PQ(minNode);
	}
	
	private void addNeighbors2PQ(Node minNode){
		
		for (Board neighbor: minNode.board.neighbors()){
			
			if (minNode.parant != null && neighbor.equals(minNode.parant.board))
				continue;
			
			pqMain.insert(new Node(minNode, neighbor));
		}
		
		//StdOut.println("\nStep " + lastNode.moves + ":");
		//printBoards(lastNode.board, lastTwin.board);
	}
	
	private class GameOrder implements Comparator<Node> {
        
        public int compare(Node n1, Node n2) {
			
			int res = n1.priority - n2.priority;
			if (res == 0)
				return n1.manhattan - n2.manhattan;
			
			return res;
        }
    }

	private void printBoards(Board board1, Board board2){
		
		String[] a1 = board1.toString().split("\n");
		String[] a2 = board2.toString().split("\n");
		
		String[] s;
		for (int i = 1; i < a1.length; i++){
			StdOut.print(a1[i] + "    " + a2[i] + "    |    ");
			for (Node node: pqMain){
				s = node.board.toString().split("\n");
				StdOut.print(s[i] + "    ");
			}
			StdOut.println();
		}
	}
	
	private void printCurrentChain(){
		StdOut.println("-\n");
		String[] s;
		for (int i = 0; i < lastNode.board.dimension()+1; i++){
			Node node = lastNode;
			while (node != null){
				if (i==0) {
					int p = node.priority;
					int h = node.board.hamming();
					int m = node.board.manhattan();
					StdOut.print(" p"+p+ " m"+m+" h"+h+" ");
					if (p < 10)
						StdOut.print(" ");
					if (h < 10)
						StdOut.print(" ");
					if (m < 10)
						StdOut.print(" ");
					node = node.parant;
					continue;
				}
				s = node.board.toString().split("\n");
				StdOut.print(s[i] + "    ");
				node = node.parant;
			}
			StdOut.println();
		}
	}
    
    // is the initial board solvable? (see below)
    public boolean isSolvable() {
		if (lastNode == null)
			return false;
		return true;
	}

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
		if (lastNode == null)
			return -1;
		return lastNode.moves;
	}

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
		
		if (lastNode == null)
			return null;
		
		Stack<Board> solution = new Stack<Board>();
		Node curNode = lastNode;
		while (curNode != null){
			solution.push(curNode.board);
			curNode = curNode.parant;
		}
		return (Iterable<Board>) solution;
	}

    // test client (see below) 
    public static void main(String[] args) {
		
		// create initial board from file
		In in = new In(args[0]);
		int n = in.readInt();
		int[][] tiles = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				tiles[i][j] = in.readInt();
		Board initial = new Board(tiles);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			int i=0;
			for (Board board : solver.solution()) {
				StdOut.println("Step "+ (++i) + " // size " + board);
			}
			
		}
		
	}

}
