/*
 * Board.java: Creates a game board. Boards can be created based on a
 * two-dimensional array or the length and width dimensions. We define
 * a "cell" to be a particular spot on the board with a unique row and
 * column number, and is thus associated with an ordered pair of indices
 * in the form (rowIndex, columnIndex).
 * 
 * This board may be used for a variety of games, under the condition that
 * the gameplay of said game is based on a two-dimensional grid, and that
 * each spot on the board has a finite amount of states (less than 2^32).
 * 
 * The "pieces" of the board are represented by number; each piece has a 
 * unique number. The number 0, however, signifies an empty cell (one that
 * does not contain a piece).
 * 
 * Utility methods include checking if the board contains a certain cell, 
 * checking if a cell is occupied, setting a cell to a certain number,
 * and clearing the board.
 * 
 * Author: Joshua Xiong
 * Version: 1.0
 * Date: 2013-11-24
 * Period: 6
 */

public class Board {

	private int[][] grid;
	private int numCols;
	private int numRows;
	
	/* Constructors */
	
	/*
	 * Constructor: Board(int, int)
	 * Parameters: row = Number of rows of the Board
	 * 			   col = Number of columns of the Board
	 * Description: Creates an empty Board object with the specified number 
	 * 		of rows and columns. 
	 */
	public Board(int row, int col){
		this.grid = new int[row][col];
		this.numRows = row;
		this.numCols = col;
	}
	
	/*
	 * Constructor: Board(int[][])
	 * Parameters: grid[][] = Two-dimensional array to be turned into a Board
	 * Description: Creates a Board object based on a two-dimensional array.
	 */
	public Board(int[][] grid){
		this.grid = grid;
		this.numCols = grid[0].length;
		this.numRows = grid.length;
	}
	
	/* Methods */
	
	/*
	 * Method: isValidCell(int, int)
	 * Parameters: row = Row index of cell
	 * 			   col = Column index of cell
	 * Returns: Whether or not the cell is contained within the board, as a boolean.
	 * Description: Checks to see if a cell is contained within the board
	 * 		based on the indices.
	 */
	public boolean isValidCell(int row, int col){
		return (row < this.numRows && row > -1 &&
				col < this.numCols && col > -1);
	}
	
	/*
	 * Method: isOccupied(int, int)
	 * Parameters: row = Row index of cell
	 * 			   col = Column index of cell
	 * Returns: Whether or not the cell contains a piece, as a boolean.
	 * Description: Checks to see if a cell contains a piece. If the cell
	 * 		is not contained within the board, false is returned.
	 */
	public boolean isOccupied(int row, int col){
		return (this.isValidCell(row, col)) ? (grid[row][col] != 0) : false;
	}
	
	/*
	 * Method: clear()
	 * Parameters: None
	 * Returns: Sets all of the cells in the Board to 0 (i.e. no pieces).
	 * Description: Sets all of the cells on the Board to 0, effectively 
	 * 		clearing the Board.
	 */
	public void clear(){this.grid = new int[this.numRows][this.numCols];}
	
	/*
	 * Method: getCell(int, int)
	 * Parameters: row = Row index of cell
	 * 			   col = Column index of cell
	 * Returns: The number that is located within a cell.
	 * Description: Returns the number that is located within a cell. If
	 * 		the cell is not contained within the Board, then 0 is returned.
	 */
	public int getCell(int row, int col){
		return (this.isValidCell(row, col)) ? grid[row][col] : 0;
	}
	
	/*
	 * Method: setCell(int, int, int)
	 * Parameters: row = Row index of cell
	 * 			   col = Column index of cell
	 * 			   newValue = Number to set the cell equal to
	 * Returns: The old value of the cell.
	 * Description: Changes a cell of the Board to a specified value. If the
	 * 		cell is not contained within the Board, nothing happens, and 0 
	 * 		is returned.
	 */
	public int setCell(int row, int col, int newValue){
		if (this.isValidCell(row, col)){
			int oldValue = getCell(row,col);
			grid[row][col] = newValue;
			return oldValue;
		}
		return 0;
	}
	
	/*
	 * Method: toString()
	 * Parameters: None
	 * Returns: A String representation of the Board
	 * Description: The Board is printed as an indexed "table" with numbers
	 * 		in their corresponding positions on the Board.
	 */
	public String toString(){
		String array = "     0  ";
		for (int i = 1; i < numCols; i++){
			array += i;
			for (int j = 0; j < 2 - ((int) Math.log10(i)); j++)
				array += " ";
		}
		array += "\n" + "   ——";
		for (int i = 0; i < numCols; i++)
			array += "———";
		array += "\n";
		for (int col = 0; col < numRows; col++){
			array += col;
			if (col == 0)
				array += "  ";
			for (int j = 1; j < 3-((int) Math.log10(col)); j++)
				array += " ";
			array+= "| ";
			for (int row = 0; row < numCols; row++)
				array += grid[col][row] + ", ";
			array += "\n";
		}
		return array;
	}
	
	/* Accessors */
	
	/*
	 * Accessor: getNumRows()
	 * Description: Returns the number of rows of the Board.
	 */
	public int getNumRows(){return this.numRows;}
	
	/*
	 * Accessor: getNumCols()
	 * Description: Returns the number of columns of the Board.
	 */
	public int getNumCols(){return this.numCols;}
	
}
