/*
 * TestGameOfLife.java: A test driver class for testing all of the
 * methods in the GameOfLife class.
 * 
 * Author: Joshua Xiong
 * Version: 1.0
 * Date: 2013-11-24
 * Period: 6
 */

public class TestGameOfLife {

	public static void main(String[] args){
		GameOfLife game1 = new GameOfLife(3,4);
		int[][] array1 = {{1,0,0,0,0,1,1,0,1},
						  {1,1,0,1,0,1,0,0,1},
						  {1,0,1,0,1,1,1,0,0},
						  {1,0,0,1,0,0,0,1,0}};
		GameOfLife game2 = new GameOfLife(array1);
		int[][] array2 = {{0,0,0,0,0,0,0,0,0},
				  		  {0,0,0,0,1,1,0,0,0},
				  		  {0,0,0,0,1,1,0,0,0},
				  		  {0,0,0,0,0,0,0,0,0}};
		GameOfLife game3 = new GameOfLife(array2);
		int[][] array3 = {{0,1,0,0,0,0,0,0,0},
						  {0,0,1,0,0,0,0,0,0},
						  {1,1,1,0,0,0,0,0,0},
						  {0,0,0,0,0,0,0,0,0},
						  {0,0,0,0,0,0,0,0,0},
						  {0,0,0,0,0,0,0,0,0}};
		GameOfLife game4 = new GameOfLife(array3);
		System.out.println(game1);
		System.out.println();
		System.out.println(game2);
		System.out.println();
		System.out.println(game3);
		System.out.println();
		game3.nextGeneration();
		System.out.println(game3);
		System.out.println();
		System.out.println(game4);
		System.out.println();
		game4.nextGeneration();
		System.out.println(game4);
		System.out.println();
		game4.nextGeneration();
		System.out.println(game4);
		System.out.println();
		game4.nextGeneration();
		System.out.println(game4);
		System.out.println();
		game4.nextGeneration();
		System.out.println(game4);
		System.out.println();
		game4.nextGeneration();
		System.out.println(game4);
		System.out.println();
		game4.nextGeneration();
		System.out.println(game4);
		System.out.println();
		game4.nextGeneration();
		System.out.println(game4);
		System.out.println();
		game4.nextGeneration();
		System.out.println(game4);
		System.out.println();
		game4.nextGeneration();
		System.out.println(game4);
		System.out.println();
		game4.nextGeneration();
		System.out.println(game4);
		System.out.println();
		game4.nextGeneration();
		System.out.println(game4);
		System.out.println();
		game4.nextGeneration();
		System.out.println(game4);
		System.out.println();
		game4.nextGeneration();
		System.out.println(game4);
		System.out.println();
		game4.nextGeneration();
		System.out.println(game4);
		System.out.println();
		game4.nextGeneration();
		System.out.println(game4);
		System.out.println();
		game4.setCell(0, 0, 7);
		System.out.println(game4);
		GameOfLife game5 = new GameOfLife(99,99);
		game5.getRandomBoard();
		System.out.println(game5);
	}
		
}
