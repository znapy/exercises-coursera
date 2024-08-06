/*
 * Board.java
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

import java.lang.Math; 
import java.util.Arrays;
//import java.util.Iterator;
import java.util.NoSuchElementException;
//import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Queue;

import edu.princeton.cs.algs4.StdOut;

public class Board {
	
	private byte boardSize;
	private int[] boardTiles;
	private int zeroPosition = 0;
	private byte[] zeroCoordinates;
	private int manhattan = -1;
	
	// create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
		
		if (tiles == null)
			throw new IllegalArgumentException("tiles shouldn't be null");
		
		if ((tiles.length < 2)
			|| (tiles.length > 127))
			throw new IllegalArgumentException("tiles size should be: 2 ≤ n < 128");
		
		boardSize = (byte) tiles.length;
		boardTiles = new int[boardSize * boardSize];
		
		int z = 1;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (z > boardTiles.length)
					throw new IllegalArgumentException("tiles row "+i+" should be "+boardSize+" size");
				
				if (z == boardTiles.length)
					boardTiles[0] = tiles[i][j];
				else
					boardTiles[z] = tiles[i][j];
				z++;
			}
		}
		
		if (z != (boardTiles.length+1))
			throw new IllegalArgumentException("tiles size should be X:X");
		
		int[] sorted = boardTiles.clone();
		Arrays.sort(sorted);
		for (int i = 0; i < sorted.length; i++)
			if (sorted[i] != i)
				throw new IllegalArgumentException("incorrect tile "+sorted[i]);
		
		for (int i = 0; i < boardTiles.length; i++) {
			if (boardTiles[i] == 0) {
				zeroPosition = i; // to find zero position
				break;
			}
		}
		
		zeroCoordinates = coordinates(zeroPosition);
	}
                                           
    // string representation of this board
    public String toString() {
		String result = "" + boardSize;
		
		for (int i = 1; i < boardTiles.length; i++) {
			if ((i - 1 + boardSize) % boardSize == 0)
				result = result + "\n ";
			else
				result = result + " ";
			
			if (boardTiles[i] < 10)
				result = result + " ";
			result = result + + boardTiles[i];
		}
		if (boardTiles[0] < 10)
			result = result + " ";
		return result + " " + boardTiles[0];
	}

    // board dimension n
    public int dimension() {
		return (int) boardSize;
	}

    // number of tiles out of place
    public int hamming() {
		
		int hamming = 0;
		for (int i = 0; i < boardTiles.length; i++) {
			if (boardTiles[i] == 0)
				continue; // "0" is not a tile
			if (boardTiles[i] != i)
				hamming = hamming + 1;
		}
		
		return hamming;
	}

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
		
		if (manhattan == -1) {
			manhattan = 0;
			for (int i = 0; i < boardTiles.length; i++) {
				if (boardTiles[i] == 0)
					continue; // "0" is not a tile
				manhattan = manhattan + distanceToGoalPosition(boardTiles[i], i);
			}
		}
		
		return manhattan;
	}

	private int distanceToGoalPosition(int tile, int curPosition) {
		byte[] coordCur  = coordinates(curPosition);
		byte[] coordGoal = coordinates(tile);
		
		return Math.abs(coordCur[0] - coordGoal[0])
			 + Math.abs(coordCur[1] - coordGoal[1]);
	}

	private byte[] coordinates(int position) {
		
		int tile = position;
		if (position == 0) // zero like the last tile
			tile = boardSize * boardSize;
		
		byte col = (byte) ((tile + boardSize) % boardSize);
		if (col == 0) // the last column
			col = boardSize;
		
		byte row = (byte) (((tile - col) / boardSize) + 1);
		
		byte[] result = new byte[2];
		result[0] = row;
		result[1] = col;
		return result;
	}

	private int position(byte[] coordinates) {
		
		int result = (coordinates[0]*boardSize) - boardSize;  // rows
		result = result + coordinates[1]; // columns
		
		if (result == (boardSize * boardSize))
			return 0; // zero like the last tile
			
		return result;
	}

    // is this board the goal board?
    public boolean isGoal() {
		return hamming() == 0;
	}

    // does this board equal y?
    public boolean equals(Object other) {
		
		if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false; 
		
		Board that = (Board) other;
		
		return this.dimension() == that.dimension()
			&& Arrays.equals(this.boardTiles, that.boardTiles);
	}

    // all neighboring boards
    public Iterable<Board> neighbors() {
		
		Queue<Board> result = new Queue<Board>();
		
		byte[] possibleDirections = possibleDirections();
		for (byte direction : possibleDirections){
			
			Board neighbor = replace(direction);
			neighbor.manhattan = manhattanForNeighbor(direction, neighbor);
			
			result.enqueue(neighbor);
		}
		
		return (Iterable<Board>) result;
	}

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
		
		int[] newBoardTiles = boardTiles.clone();
		
		int t1 = newBoardTiles.length - 1;
		if (newBoardTiles[t1] == 0)
			t1--;
		int t2 = t1 - 1;
		if (newBoardTiles[t2] == 0)
			t2--;
		
		exch(newBoardTiles, t1, t2);
		 
		return boardFromBoardTiles(newBoardTiles);
	}
	
	private Board replace(byte direction){
		
		if (direction == -1)
			throw new NoSuchElementException("unavailable change direction "+direction);
		
		int[] newBoardTiles = this.boardTiles.clone();
		int position = position(zeroCoordinates);
		
		byte[] newCoordinates = zeroCoordinates.clone();
		
		if (direction == 3)
			newCoordinates[1]++;
		if (direction == 6) 
			newCoordinates[0]++;
		if (direction == 9)
			newCoordinates[1]--;
		if (direction == 12)
			newCoordinates[0]--;
		
		exch(newBoardTiles, position, position(newCoordinates));
		
		return boardFromBoardTiles(newBoardTiles);
		
	}

	private void exch(int[] boardTiles, int i, int j) {
		int swap = boardTiles[i];
		boardTiles[i] = boardTiles[j];
		boardTiles[j] = swap;
	}

	private byte[] possibleDirections() {
		
		boolean hasRight = false;
		boolean hasBottom = false;
		boolean hasLeft = false;
		boolean hasTop = false;
		
		if (zeroCoordinates[1] != boardSize) hasRight = true;
		if (zeroCoordinates[0] != boardSize) hasBottom = true;
		if (zeroCoordinates[1] != 1) hasLeft = true;
		if (zeroCoordinates[0] != 1) hasTop = true;
		
		int n = (hasRight?1:0)
			   + (hasBottom?1:0)
			   + (hasLeft?1:0)
			   + (hasTop?1:0);
		
		byte[] possibleDirections = new byte[n];
		byte i = 0;
		if (hasRight) possibleDirections[i++] = 3;
		if (hasBottom) possibleDirections[i++] = 6;
		if (hasLeft) possibleDirections[i++] = 9;
		if (hasTop) possibleDirections[i++] = 12;
		
		return possibleDirections;
	}
	
	private Board boardFromBoardTiles(int[] boardTiles) {
		
		int[][] boardArray = new int[boardSize][boardSize];
		for (int i = 0; i < boardTiles.length; i++) {
			byte[] coord = coordinates(i);
			boardArray[coord[0]-1][coord[1]-1] = boardTiles[i];
		}
		
		return new Board(boardArray);
	}

	private int manhattanForNeighbor(byte direction, Board neighbor) {
		
		// find tile1 for move, calc distanceToGoal for them
		// calc distanceToGoal from new position
		// new manhatten = prev - distance1 + distance 2
		
		int curDistance = this.    distanceToGoalPosition(this.    boardTiles[neighbor.zeroPosition], neighbor.zeroPosition);
		int newDistance = neighbor.distanceToGoalPosition(neighbor.boardTiles[this.    zeroPosition], this.    zeroPosition);
		
		return this.manhattan() - curDistance + newDistance;
		
	}

    // unit testing (not graded)
    public static void main(String[] args) {
	}

}
