/*
 * GameOfLife.java: A class that implements the rules of Conway's
 * Game of Life onto a Board object. Note that this is not a "game"
 * in the traditional sense; rather, it is more like a cellular automaton.
 * 
 * The rules are as follows:
 * 1. The game is played on a 2-dimensional grid consisting of cells.
 * 2. A cell is considered to be either "alive" or "dead".
 * 3. All cells are set to one of the two states. By default, all cells
 * 		are dead.
 * 4. Based on these starting conditions, the next set of cell states will
 * 		be generated. Each set of states is called a "generation". The starting
 * 		generation is Generation 0.
 * 5. Define a "neighbor" of a cell to be a cell that has at least one point 
 * 		in common with the cell. Specifically, this includes cells that are 
 * 		horizontally and vertically adjacent to the cell, as well as diagonally 
 * 		adjacent cells.  Successive generations are computed as follows:
 * 	5.1. If a cell has 3 neighbors, it will become alive in the next generation, 
 * 		regardless of state.
 * 	5.2. If a cell has less than 2 neighbors or more than 3 neighbors, it will become 
 * 		dead in the next generation, regardless of state.
 * 	5.3 If a cell has 2 neighbors, its state does not change between generations.
 * 
 * With respect to the Board that the GameOfLife object is based upon, we will use 
 * the number 1 to denote a cell that is alive, and the number 0 to denote a cell 
 * that is dead. 
 * 
 * Author: Joshua Xiong
 * Version: 1.0
 * Date: 2013-11-24
 * Period: 6
 */

public class GameOfLife {

	private Board board;
	private int generation = 0;
	
	/*
	 * Constructor: GameOfLife(int, int)
	 * Parameters: row = Number of rows of the board
	 * 			   col = Number of columns of the board
	 * Description: Creates a GameOfLife object, setting the board to have
	 * 		the specified dimensions.
	 */
	public GameOfLife(int row, int col){this.board = new Board(row, col);}
	
	/*
	 * Constructor: GameOfLife(int[][])
	 * Parameters: grid[][] = Two-dimensional array to be used as a Board
	 * 								for the GameOfLife object
	 * Description: Creates a GameOfLife object based on a two-dimensional array.
	 */
	public GameOfLife(int[][] grid){this.board = new Board(grid);}
	
	
	/*
	 * Method: getOccupiedNeighbors(int, int)
	 * Parameters: row = Row index of cell
	 * 			   col = Column index of cell
	 * Returns: The number of neighbors of a cell.
	 * Description: Returns the number that is located within a cell. If
	 * 		the cell is not contained within the Board, then 0 is returned.
	 */
	public int getOccupiedNeighbors(int row, int col){
		int neighbors = 0;
		for (int i = row - 1; i < row + 2; i++) {
			for (int j = col - 1; j < col + 2; j++){
				if (board.isValidCell(i, j)){
					neighbors += board.getCell(i, j);
				}
			}
		}
		return neighbors - board.getCell(row, col);
	}
	
	/*
	 * Method: getCell(int, int)
	 * Parameters: row = Row index of cell
	 * 			   col = Column index of cell
	 * Returns: The number that is located within a cell.
	 * Description: Returns the number that is located within a cell. If the 
	 * 		cell is not contained within the GameOfLife board, then 0 is returned.
	 */
	public int getCell(int row, int col){return this.board.getCell(row, col);}
	
	/*
	 * Method: cellStateNextGeneration(int, int)
	 * Parameters: row = Row index of cell
	 * 			   col = Column index of cell
	 * Returns: The number that represents the cell's state in the next generation.
	 * Description: Returns the number that represents the cell's state in the next
	 * 		generation.
	 */
	public int cellStateNextGeneration(int row, int col){
		if (this.getOccupiedNeighbors(row, col) == 3)
			return 1;
		if (this.getOccupiedNeighbors(row, col) == 2)
			return board.getCell(row, col);
		return 0;
	}
	
	/*
	 * Method: clear()
	 * Parameters: None
	 * Returns: Void
	 * Description: Sets all of the cells in the Board to 0 (i.e. all 
	 * 		cells are dead), and resets the generation to 0.
	 */
	public void clear(){
		this.board.clear();
		this.generation = 0;
	}
	
	/*
	 * Method: nextGeneration()
	 * Parameters: None
	 * Returns: Void
	 * Description: Generates the next generation of the GameOfLife, and stores
	 * 		the Board with the updated states into the old Board data.
	 */
	public void nextGeneration(){
		int[][] nextGen = new int[this.getNumRows()][this.getNumCols()];
		for (int i = 0; i < this.getNumRows(); i++)
			for (int j = 0; j < this.getNumCols(); j++)
				nextGen[i][j] = this.cellStateNextGeneration(i, j);
		this.board = new Board(nextGen);
		this.generation++;
	}
	
	/*
	 * Method: setCell(int, int, int)
	 * Parameters: row = Row index of cell
	 * 			   col = Column index of cell
	 * 			   newValue = Number to set the cell equal to
	 * Returns: Void
	 * Description: Changes a cell of the GameOfLife Board to a specified value. 
	 * 		If the cell is not contained within the Board, nothing happens.
	 */
	public void setCell(int row, int col, int newValue){
		this.board.setCell(row, col, newValue);
	}
	
	/*
	 * Method: getRandomBoard()
	 * Parameters: None
	 * Returns: Void
	 * Description: Generates a Board with random initial state to be used
	 * 		for the GameOfLife. Each cell has a 15% chance of being alive in
	 * 		this method.
	 */
	public void getRandomBoard(){
		for (int i = 0; i < this.getNumRows(); i++)
			for (int j = 0; j < this.getNumCols(); j++)
				this.setCell(i, j, (int)(Math.random() + 0.15));
		this.generation = 0;
	}
	
	/*
	 * Method: toString()
	 * Parameters: None
	 * Returns: A String representation of the GameOfLIfe
	 * Description: The Board is printed as in the Board class. The only
	 * 		modification is that the generation number is shown below.
	 */
	public String toString(){
		return this.board.toString() + "\n" + "Generation: " + this.generation;
	}
	
	/* Accessors */
	
	/*
	 * Accessor: getNumRows()
	 * Description: Returns the number of rows of the GameOfLife Board.
	 */
	public int getNumRows(){return this.board.getNumRows();}
	
	/*
	 * Accessor: getNumCols()
	 * Description: Returns the number of columns of the GameOfLife Board.
	 */
	public int getNumCols(){return this.board.getNumCols();}
	
	/*
	 * Accessor: getNumCols()
	 * Description: Returns the current generation of the GameOfLife.
	 */
	public int getGeneration(){return this.generation;}
	

}
