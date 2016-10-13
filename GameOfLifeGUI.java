/*
 * GameOfLifeGUI.java: A class that creates a graphical user interface 
 * for the Game of Life based on a GameOfLife object.
 * 
 * Features of the GUI include 
 * 
 * Author: Joshua Xiong
 * Version: 1.0
 * Date: 2013-11-24
 * Period: 6
 */

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class GameOfLifeGUI {

	private GameOfLife game = new GameOfLife(150, 200);
	private int numCols = game.getNumCols();
	private int numRows = game.getNumRows();

	private JPanel buttonPanel = new JPanel();

	/* Main */
	public static void main(String[] args) {
		new GameOfLifeGUI();
	}

	/* Constructor */
	
	/*
	 * Constructor: GameOfLifeGUI()
	 * Parameters: None
	 * Description: Creates a GUI for the GameOfLife.
	 */
	public GameOfLifeGUI() {
		JFrame frame = new JFrame("Game of Life");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Grid grid = new Grid();
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(grid, BorderLayout.NORTH);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		frame.add(panel);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}

	/* 
	 * This grid class is the graphical implementation of the game.
	 * All changes to the game state occur here.
	 */
	@SuppressWarnings("serial")
	public class Grid extends JPanel {

		private final int CELL_DIMENSION = 4;
		private int height = numRows * CELL_DIMENSION;
		private int width = numCols * CELL_DIMENSION;
		private ArrayList<Point> cells = new ArrayList<Point>();
		private int timerDelay = 100;
		private Timer nextGenTimer = new Timer(timerDelay, new TimerListener());
		private boolean clickState = false;
		private int speed = 3;

		private JLabel generationCounter = new JLabel("Generation: "
				+ game.getGeneration());

		/* Constructor */
		
		/*
		 * Constructor: Grid()
		 * Parameters: None
		 * Description: Creates a Grid object on which to show the states of all
		 * 		of the cells.
		 */
		public Grid() {
			/* MouseListeners */
			MouseListener listener = new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent event) {
					changeCellState(event.getX(), event.getY());
					repaint();
				}
			};
			addMouseListener(listener);

			MouseMotionListener dragListener = new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent event) {
					changeCellStateDragged(event.getX(), event.getY());
					repaint();
				}
			};
			addMouseMotionListener(dragListener);

			/* Buttons */
			JButton nextGenButton = new JButton("Next Generation");
			ActionListener nextGenListener = new NextGenerationListener();
			nextGenButton.addActionListener(nextGenListener);

			JButton clearButton = new JButton("Clear");
			ActionListener clearListener = new ClearListener();
			clearButton.addActionListener(clearListener);

			JButton startButton = new JButton("Start");
			ActionListener startListener = new StartListener();
			startButton.addActionListener(startListener);

			JButton stopButton = new JButton("Stop");
			ActionListener stopListener = new StopListener();
			stopButton.addActionListener(stopListener);

			JButton setSpeedButton = new JButton("Set Speed");
			ActionListener setSpeedListener = new SetSpeedListener();
			setSpeedButton.addActionListener(setSpeedListener);

			JButton randomGameButton = new JButton("Random Game");
			ActionListener randomGameListener = new RandomGameListener();
			randomGameButton.addActionListener(randomGameListener);

			buttonPanel.add(nextGenButton);
			buttonPanel.add(clearButton);
			buttonPanel.add(startButton);
			buttonPanel.add(stopButton);
			buttonPanel.add(randomGameButton);
			buttonPanel.add(setSpeedButton);
			buttonPanel.add(generationCounter);
		}

		/*
		 * This class is used for the class Timer. Each generation is
		 * computed repeatedly until the Timer is stopped.
		 */
		class TimerListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				game.nextGeneration();
				refreshGame();
				repaint();
			}
		}

		/*
		 * This class is used for the Start button that appears in the GUI.
		 * When clicked, each generation will be shown without any additional 
		 * prompt from the user.
		 */
		class StartListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				nextGenTimer.start();
			}
		}

		/*
		 * This class is used for the Stop button that appears in the GUI.
		 * When clicked, the Timer will stop, and further generations will not be
		 * computed until any one of the buttons that trigger the start of the Timer
		 * is clicked.
		 */
		class StopListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				nextGenTimer.stop();
			}
		}

		/*
		 * This class is used for the NextGeneration button that appears in the GUI.
		 * When clicked, the Timer will stop, and one more generation will be computed.
		 */
		class NextGenerationListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				nextGenTimer.stop();
				game.nextGeneration();
				refreshGame();
				repaint();
			}
		}

		/*
		 * This class is used for the Clear button that appears in the GUI.
		 * When clicked, the Timer will stop, and the GameOfLife will be cleared.
		 */
		class ClearListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				nextGenTimer.stop();
				game.clear();
				refreshGame();
				repaint();
			}
		}

		/*
		 * This class is used for the RandomGame button that appears in the GUI.
		 * When clicked, the Timer will stop, and the GameOfLife will be replaced
		 * with a random game.
		 */
		class RandomGameListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				nextGenTimer.stop();
				game.getRandomBoard();
				refreshGame();
				repaint();
			}
		}

		/*
		 * This class is used for the SetSpeed button that appears in the GUI.
		 * When clicked, the Timer will stop, and the user will be prompted for a
		 * new speed between 1 and 5. If the input is not valid, there will be no
		 * speed change, and the Timer will remained stopped. If the input is valid,
		 * the speed will be changed accordingly, and the Timer will start.
		 */
		class SetSpeedListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				nextGenTimer.stop();
				int newSpeed = 0;
				String input = JOptionPane
						.showInputDialog("The speed is currently " + speed
								+ ".\nNew (integer) speed 1 (slow) - 5 (fast):");
				if (input != null) {
					try {
						newSpeed = Integer.parseInt(input);
					} catch (NumberFormatException e) {
					}
					if (newSpeed > 0 && newSpeed < 6) {
						speed = newSpeed;
						timerDelay = (int) (1000 / (2.5 * Math
								.pow(2, speed - 1)));
						nextGenTimer = new Timer(timerDelay,
								new TimerListener());
						nextGenTimer.start();
					}
				}
			}
		}

		/*
		 * Method: paintComponent(Graphics)
		 * Parameters: g = A Graphics object
		 * Returns: Void
		 * Description: Creates a graphical representation of the grid. Cells
		 * 		that are alive are colored blue, and cells that are dead are
		 * 		colored white. Boundaries between cells are separated by gray lines.
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLUE);
			for (Point cell : cells)
				g.fillRect(cell.x, cell.y, CELL_DIMENSION, CELL_DIMENSION);
			g.setColor(new Color(160, 160, 160));
			for (int i = 0; i <= width; i += CELL_DIMENSION)
				g.drawLine(i, 0, i, height);
			for (int i = 0; i <= height; i += CELL_DIMENSION)
				g.drawLine(0, i, width, i);
		}

		/*
		 * Method: refreshGame()
		 * Parameters: None
		 * Returns: Void
		 * Description: Updates the ArrayList of alive cells and updates the
		 * 		generation label.
		 */
		public void refreshGame() {
			ArrayList<Point> nextGen = new ArrayList<Point>();
			for (int i = 0; i < numRows; i++)
				for (int j = 0; j < numCols; j++)
					if (game.getCell(i, j) == 1)
						nextGen.add(new Point(j * CELL_DIMENSION, i * CELL_DIMENSION));
			cells = nextGen;
			generationCounter.setText("Generation: " + game.getGeneration());
		}

		/*
		 * Method: changeCellStateDragged(int x, int y)
		 * Parameters: x = x-coordinate of click
		 * 			   y = y-coordinate of click
		 * Returns: Void
		 * Description: Updates the ArrayList of alive cells. Clicking and dragging
		 * 		the mouse will change the state of all cells on which it passes to
		 * 		the state that is opposite of the cell that is initially clicked on.
		 */
		public void changeCellStateDragged(int x, int y) {
			int xIndex = x / CELL_DIMENSION;
			int yIndex = y / CELL_DIMENSION;
			if (x < width && y < height) {
				Point p = new Point(xIndex * CELL_DIMENSION, yIndex * CELL_DIMENSION);
				if (cells.contains(p) == clickState) {
					if (cells.contains(p)) {
						cells.remove(p);
						game.setCell(yIndex, xIndex, 0);
					} 
					else {
						cells.add(p);
						game.setCell(yIndex, xIndex, 1);
					}
				}
			}
		}

		/*
		 * Method: changeCellState(int x, int y)
		 * Parameters: x = x-coordinate of click
		 * 			   y = y-coordinate of click
		 * Returns: Void
		 * Description: Updates the ArrayList of alive cells. Clicking the mouse
		 * 		will change the state of the cell on which it is clicked.
		 */
		public void changeCellState(int x, int y) {
			int xIndex = x / CELL_DIMENSION;
			int yIndex = y / CELL_DIMENSION;
			if (x < width && y < height) {
				Point p = new Point(xIndex * CELL_DIMENSION, yIndex
						* CELL_DIMENSION);
				clickState = cells.contains(p);
				if (cells.contains(p)) {
					cells.remove(p);
					game.setCell(yIndex, xIndex, 0);
				} else {
					cells.add(p);
					game.setCell(yIndex, xIndex, 1);
				}
			}
		}

		/*
		 * Method: getPreferredSize()
		 * Parameters: None
		 * Returns: The preferred Dimension of the JPanel
		 * Description: Sets the size of the JPanel so that all features are visible. 
		 */
		@Override
		public Dimension getPreferredSize() {return new Dimension(width, height + 1);}

	}

}
