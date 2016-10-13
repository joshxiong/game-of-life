/*
 * TestBoard.java: A test driver class for testing all of the utility
 * methods in the Board class.
 * 
 * Author: Joshua Xiong
 * Version: 1.0
 * Date: 2013-11-24
 * Period: 6
 */

public class TestBoard {
	
	public static void main(String[] args){
		int[][] array1 = {{1,2,3},{2,3,4},{3,4,5},{4,5,6}};
		Board b1 = new Board(3,2);
		Board b2 = new Board(array1);
		Board b3 = new Board(1,1);
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
		System.out.println(b2.isValidCell(2, 4));
		System.out.println(b3.isValidCell(0, 1));
		b2.setCell(2, 2, 999);
		System.out.println(b2);
		b2.clear();
		System.out.println(b2);
	}
}
