
/**
 * This class is the implementation of the "TicTacToe" game
 * @author Yungang FENG 781130
 * @Time 2016-3-28 14:30:11
 * 
 */

import java.util.Scanner;

public class TicTacToe 
{

	// String to store players' names
	private String playerO;
	private String playerX;

	// String used to create the grid
	private final String GRID_LINE_1 = " | | ";
	private final String GRID_LINE_2 = "-----";

	// The height and the width of the grid
	private final int GRID_HEIGHT = 5;
	private final int GRID_WIDTH = 3;

	// The offset to transfer the position of the piece in array 'pieces' to the
	// array 'grid'
	private final int GRID_OFFSET = 2;

	// The maximum count of pieces in each row and line
	private final int PIECE_LINE_COUNT = 3;
	private final int PIECE_ROW_COUNT = 3;

	// The flag value of the turn of playerO and playerX
	private final int O_TURN = -1;
	private final int X_TURN = 1;

	// The indicator of the turn of which player, and playerO starts first
	private int playerTurnFlag = O_TURN;

	// The flag value of the game status, including 'game can go on', 'draw',
	// 'playerX wins' and 'playerO wins'
	private final int GAME_GO_ON = -1;
	private final int DRAW = 0;
	private final int O_WIN = 1;
	private final int X_WIN = 2;

	// The indicator of the status of the game, the default value is 'game can
	// go on'
	private int winnerFlag = GAME_GO_ON;

	// The counter of the steps
	private int stepCount = 0;

	// The maximum count of the steps
	private final int TOTAL_STEP_COUNT = PIECE_LINE_COUNT * PIECE_ROW_COUNT;

	// Scanner to read the input of keyboard
	private static Scanner keyboard = new Scanner(System.in);

	// The char array which stores the grid of certain height and width. This
	// array also stores the character of each piece, but it is only used to 
	// print the grid and pieces
	private char[][] grid = new char[GRID_HEIGHT][GRID_WIDTH];

	// The char array which stores the position of each piece, it is used to
	// check the status of the game
	private char[][] pieces = new char[PIECE_LINE_COUNT][PIECE_ROW_COUNT];

	/**
	 * The main method in this class
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		TicTacToe game = new TicTacToe();
		game.run();
	}

	/**
	 * The function to run the game process
	 */
	private void run() 
	{
		// initialize the grid
		initialize();

		// print the empty grid
		printGrid(grid);

		// while the status of the game is 'can go on', then continue the game
		while (winnerFlag == GAME_GO_ON) 
		{
			if (playerTurnFlag == O_TURN)
				System.out.println(getPlayerO() + "'s move:");
			if (playerTurnFlag == X_TURN)
				System.out.println(getPlayerX() + "'s move:");

			// read the input of the position of the piece
			updateChess(keyboard.nextInt(), keyboard.nextInt());
		}
	}

	/**
	 * The function to initialize the empty grid
	 */
	private void initializeGrid() 
	{
		for (int i = 0; i < GRID_HEIGHT; i += 2)
			grid[i] = GRID_LINE_1.toCharArray();
		for (int i = 1; i < GRID_HEIGHT; i += 2)
			grid[i] = GRID_LINE_2.toCharArray();
	}

	/**
	 * The function to print the grid
	 * 
	 * @param grid
	 *            the char array which stores the grid and the pieces
	 */
	private void printGrid(char[][] grid) 
	{
		for (char[] line : grid) 
		{
			for (char character : line)
				System.out.print(character);
			System.out.print('\n');
		}
	}

	/**
	 * update the grid with piece in the position which is indicated by the line
	 * and row and print the updated grid. If the game status is changed, it
	 * prints the new status
	 * 
	 * @param line
	 *            the line position of the piece
	 * @param row
	 *            the row position of the piece
	 */
	private void updateChess(int line, int row) 
	{
		if (playerTurnFlag == O_TURN)
			pieces[line][row] = 'O';
		if (playerTurnFlag == X_TURN)
			pieces[line][row] = 'X';

		// increase the count of steps by 1
		stepCount++;

		// update the grid with the position of the piece
		grid[line * GRID_OFFSET][row * GRID_OFFSET] = pieces[line][row];

		// print the updated grid
		printGrid(grid);

		// check the game state after one step, if the state is changed, print
		// the new state
		switch (getGameState(line, row)) 
		{
		case X_WIN:
			System.out.println("Game over. " + getPlayerX() + " won!");
			keyboard.close();
			return;

		case O_WIN:
			System.out.println("Game over. " + getPlayerO() + " won!");
			keyboard.close();
			return;

		case DRAW:
			System.out.println("Game over. It was a draw!");
			keyboard.close();
			return;
		default:
		}

		// change the turn of each player, since -1 indicates playerO's turn
		// and 1 indicates playerX's turn
		playerTurnFlag *= -1;
	}

	/**
	 * get the state of the game
	 * 
	 * @param line
	 *            the line position of the piece
	 * @param row
	 *            the row position of the piece
	 * @return the flag value of the game state
	 */
	private int getGameState(int line, int row) 
	{

		// check if there are 3 same pieces in this line
		checkLine(line);

		// check if there are 3 same pieces in this row
		checkRow(row);

		// check if there are 3 same pieces in this diagonal
		checkDiagonal(line, row);

		// check if the count of steps reaches the maximum number
		checkTotalCount();

		return winnerFlag;
	}

	private String getPlayerO() 
	{
		return playerO;
	}

	private void setPlayerO(String playerO) 
	{
		this.playerO = playerO;
	}

	private String getPlayerX() 
	{
		return playerX;
	}

	private void setPlayerX(String playerX) 
	{
		this.playerX = playerX;
	}
	
	/**
	 * check if there are 3 same pieces in this line
	 * 
	 * @param line
	 *            the line of the position of the new piece
	 */
	private void checkLine(int line) 
	{
		char check = 0;
		if (playerTurnFlag == O_TURN)
			check = 'O';
		if (playerTurnFlag == X_TURN)
			check = 'X';
		for (int i = 0; i < PIECE_LINE_COUNT; i++) 
		{
			if (pieces[line][i] != check)
				return;
		}
		if (check == 'O')
			winnerFlag = O_WIN;
		if (check == 'X')
			winnerFlag = X_WIN;
	}

	/**
	 * check if there are 3 same pieces in this row
	 * 
	 * @param line
	 *            the row of the position of the new piece
	 */
	private void checkRow(int row) 
	{
		char check = 0;
		if (playerTurnFlag == O_TURN)
			check = 'O';
		if (playerTurnFlag == X_TURN)
			check = 'X';
		for (int i = 0; i < PIECE_ROW_COUNT; i++) 
		{
			if (pieces[i][row] != check)
				return;
		}
		if (check == 'O')
			winnerFlag = O_WIN;
		if (check == 'X')
			winnerFlag = X_WIN;
	}

	/**
	 * check if there are 3 same pieces in the diagonal or the anti-diagonal
	 * 
	 * @param line
	 *            the line of the position of the new piece
	 * @param row
	 *            the row of the position of the new piece
	 */
	private void checkDiagonal(int line, int row) 
	{

		char check = 0;
		if (playerTurnFlag == O_TURN)
			check = 'O';
		if (playerTurnFlag == X_TURN)
			check = 'X';

		// check if the new piece is on the diagonal or the anti-diagonal, if
		// not, return
		if (line != row && (line + row) != PIECE_ROW_COUNT - 1)
			return;

		// if the new piece is on the diagonal, check the diagonal
		if (line == row) 
		{
			for (int i = 0; i < PIECE_ROW_COUNT; i++) 
			{
				if (pieces[i][i] != check)
					return;
			}
		}
		// if the new piece is on the anti-diagonal, check the anti-diagonal
		else 
		{
			for (int i = 0; i < PIECE_ROW_COUNT; i++) 
			{
				if (pieces[i][PIECE_ROW_COUNT - i - 1] != check)
					return;
			}
		}

		if (check == 'O')
			winnerFlag = O_WIN;
		if (check == 'X')
			winnerFlag = X_WIN;
	}

	/**
	 * check the count of the steps, if it reaches the maximum and no winner,
	 * then set the state to draw
	 */
	private void checkTotalCount() 
	{
		if (stepCount == TOTAL_STEP_COUNT)
			winnerFlag = DRAW;
	}

	/**
	 * initialize the game
	 */
	private void initialize() 
	{
		System.out.print("Welcome to Tic Tac Toe!\n\n");

		System.out.print("Enter Player O's name:\n");
		setPlayerO(keyboard.nextLine());

		System.out.print("Enter Player X's name:\n");
		setPlayerX(keyboard.nextLine());

		initializeGrid();
	}

}
