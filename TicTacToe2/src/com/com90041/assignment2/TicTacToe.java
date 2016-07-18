package com.com90041.assignment2;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * This the main class of TicTacToe
 * 
 * @author Yungang FENG 781130
 *
 */
public class TicTacToe {
	public static Scanner keyboard = new Scanner(System.in);

	/**
	 * The main method of TicTacToe
	 */
	
	public TicTacToe(){
		
	}
	
	public static void main(String[] args) {
		TicTacToe gameSystem = new TicTacToe();
		gameSystem.run();
	}

	/**
	 * the run method which maintains the game process
	 */
	private void run() {

		System.out.println("Welcome to Tic Tac Toe!");
		PlayerManager pm=new PlayerManager();

		while (true) {
			System.out.print("\n>");
			String command = new String();
			String argument = new String();
			StringTokenizer st1 = new StringTokenizer(keyboard.nextLine(), " ");
			if (st1.hasMoreTokens())
				command = st1.nextToken();
			if (st1.hasMoreTokens())
				argument = st1.nextToken();

			switch (command) {

			/**
			 * exit the program
			 */
			case "exit":
				System.out.print("\n");
				System.exit(0);
				break;

			/**
			 * Add one player, if the username already exists, display feedback
			 */
			case "addplayer":
				StringTokenizer st2 = new StringTokenizer(argument, ",");
				String username=st2.nextToken();
				String familyName=st2.nextToken();
				String givenName=st2.nextToken();
				if (!pm.addPlayer(username, familyName, givenName))
					System.out.println("The username has been used already.");
				break;

			/**
			 * Remove one player by username, if the username does not exist, display
			 * feedback. If no argument, remove all players
			 */
			case "removeplayer":
				if (argument.equals("")) {
					System.out.println("Are you sure you want to remove all players? (y/n)");
					String yesOrNo = keyboard.nextLine();
					if (yesOrNo.equals("y")) {
						pm.removeAllPlayer();
					} else
						break;
				} else if (!pm.removePlayer(argument))
					System.out.println("The player does not exist.");
				break;

			/**
			 * Edit one player, if the username does not exist, display feedback
			 */
			case "editplayer":
				StringTokenizer st3 = new StringTokenizer(argument, ",");
				if (!pm.editPlayer(st3.nextToken(), st3.nextToken(), st3.nextToken()))
					System.out.println("The player does not exist.");
				break;

			/**
			 * Reset all statistics or reset one player's statistics
			 */
			case "resetstats":
				if (argument.equals("")) {
					System.out.println("Are you sure you want to reset " + 
									  "all player statistics? (y/n)");
					String yesOrNo = keyboard.nextLine();
					if (yesOrNo.equals("y")) {
						pm.resetAllStats();
					} else
						break;
				}

				else if (!pm.resetStats(argument))
					System.out.println("The player does not exist.");
				break;

			/**
			 * Display one player, if the username does not exist, display
			 * feedback. If there is no argument, display all players.
			 */
			case "displayplayer":
				if (argument.equals("")) {
					pm.displayAll();
				} else if (!pm.display(argument))
					System.out.println("The player does not exist.");
				break;

			/**
			 * Display the rankings
			 */
			case "rankings":
				pm.displayRanking();
				break;

			/**
			 * Start a game between two players. If player does not exists,
			 * display feedback
			 */
			case "playgame":
				StringTokenizer st4 = new StringTokenizer(argument, ",");
				String player1 = st4.nextToken();
				String player2 = st4.nextToken();
				if (pm.searchPlayer(player1) < 0) {
					System.out.print("Player does not exist.\n");
					break;
				}

				if (pm.searchPlayer(player2) < 0) {
					System.out.print("Player does not exist.\n");
					break;
				}

				if (player1.equals(player2)) {
					System.out.print("Please input two different players!\n");
					break;
				}

				Player playerO = pm.getPlayer(player1);
				Player playerX = pm.getPlayer(player2);
				GameManager gm = new GameManager(playerO, playerX, keyboard);
				gm.playGame();
				
			/**
			* if there is no command or command is wrong, break
			*/
			default:
					break;
			}
		}
	}

}
