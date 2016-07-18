package com.com90041.assignment2;

import java.util.ArrayList;

/**
 * 
 * This is the class which manage all players in the TicTacToe game
 * 
 * @author Yungang FENG 781130 
 * 
 */
public class PlayerManager {
	private static final int STATS_LIMIT = 10;
	private ArrayList<Player> playerList;

	/**
	 * this is the constructor of this class
	 */
	public PlayerManager() {
		playerList = new ArrayList<Player>();
	}

	/**
	 * The method to add player
	 * 
	 * @param username
	 *            the unique username of one user
	 * @param familyName
	 *            the family name of this user
	 * @param givenName
	 *            the given name of this user
	 * @return the state which represents whether the player is successfully
	 *         added
	 */
	public boolean addPlayer(String username, String familyName, String givenName) {
		if (searchPlayer(username) >= 0)
			return false;
		Player p = new Player(username, familyName, givenName);
		playerList.add(p);
		return true;
	}

	/**
	 * The method to remove all player
	 */
	public void removeAllPlayer() {
		playerList.removeAll(playerList);
	}

	/**
	 * The method to remove a player by username
	 * 
	 * @param username
	 *            the user name of the player which you want to remove
	 * @return the state which represents whether the player is successfully
	 *         removed
	 */
	public boolean removePlayer(String username) {
		int index = searchPlayer(username);
		if (index < 0)
			return false;
		else {
			playerList.remove(index);
			return true;
		}
	}

	/**
	 * The method to search a user by username
	 * 
	 * @param username
	 *            the user name of the player which you want to search
	 * @return the index of this player in the player list, the negative return
	 *         value means the player does not exist
	 */
	public int searchPlayer(String username) {
		int index = 0;
		if (playerList.isEmpty())
			return -1;
		for (Player player : playerList) {

			if (player.getUsername().equals(username))
				break;

			index++;

			if (index >= playerList.size())
				index = -1;
		}
		return index;
	}

	/**
	 * The method to get a player by username
	 * 
	 * @param username
	 *            the username of the player
	 * @return the Player instance of this player
	 */
	public Player getPlayer(String username) {
		return playerList.get(searchPlayer(username));
	}

	/**
	 * The method to edit a player
	 * 
	 * @param username
	 *            the username of the player
	 * @param familyName
	 *            the new family name of this player
	 * @param givenName
	 *            the new given name of this player
	 * @return the state which represents whether the user exists
	 */
	public boolean editPlayer(String username, String familyName, String givenName) {
		int index = searchPlayer(username);
		if (index < 0)
			return false;
		else {
			playerList.get(index).setFamilyName(familyName);
			playerList.get(index).setGivenName(givenName);
			return true;
		}
	}

	/**
	 * The method to reset one user's statistics
	 * 
	 * @param username
	 *            the username of this user
	 * @return the state which represents whether the user exists
	 */
	public boolean resetStats(String username) {
		if (playerList.isEmpty())
			return true;
		int index = searchPlayer(username);
		if (index < 0)
			return false;

		Player player = playerList.get(index);
		player.setGameDrawnCount(0);
		player.setGamePlayedCount(0);
		player.setGameWonCount(0);
		return true;
	}

	/**
	 * The method to reset all statistics
	 */
	public void resetAllStats() {
		if (playerList.isEmpty())
			return;
		for (Player p : playerList) {
			p.setGameDrawnCount(0);
			p.setGamePlayedCount(0);
			p.setGameWonCount(0);
		}
	}

	/**
	 * The method to display all players
	 */
	public void displayAll() {
		
		playerList.sort(new DisplayUserComparator());
		if (playerList.isEmpty())
			return;
		for (Player player : playerList)
			System.out.println(printStats(player));
	}

	/**
	 * The method to display one user by his username
	 * 
	 * @param username
	 *            the username of this user
	 * @return the state which represents whether this user exists
	 */
	public boolean display(String username) {
		int index = searchPlayer(username);
		if (index < 0)
			return false;
		Player p = playerList.get(index);
		System.out.println(printStats(p));
		return true;
	}

	/**
	 * The method to display the rankings
	 */
	public void displayRanking() {
		playerList.sort(new RankingComparator());
		/**
		 *  if there are no more than 10 players, display all players
		 */
		if (playerList.size() <= STATS_LIMIT) {
			System.out.print(" WIN  | DRAW | GAME | USERNAME\n");
			for (Player player : playerList) {
				int winRate = (int) (player.getGamePlayedCount() == 0 ? 0
						: (player.getGameWonCount() * 100 / player.getGamePlayedCount()));
				int drawnRate = (int) (player.getGamePlayedCount() == 0 ? 0
						: (player.getGameDrawnCount() * 100 / player.getGamePlayedCount()));
				System.out.printf(" %3d%% | %3d%% | %2d   | %s\n", 
						winRate, drawnRate, player.getGamePlayedCount(),
						player.getUsername());
			}
		}
		/**
		 *  if there are more than 10 players, display the first 10 players
		 */
		else {
			System.out.print(" WIN  | DRAW | GAME | USERNAME\n");
			for (int i = 0; i < STATS_LIMIT; i++) {
				int winRate;
				int drawnRate;
				Player p = playerList.get(i);
				if (playerList.get(i).getGamePlayedCount() != 0) {
					winRate = (int) (p.getGameWonCount() * 100 / p.getGamePlayedCount());
					drawnRate = (int) (p.getGameDrawnCount() * 100 / p.getGamePlayedCount());
				} else {
					winRate = 0;
					drawnRate = 0;
				}

				System.out.printf(" %3d%% | %3d%% | %2d   | %s\n", 
									winRate, drawnRate, p.getGamePlayedCount(),
						p.getUsername());
			}
		}
	}

	/**
	 * The private method to print the statistics
	 * 
	 * @param p
	 *            the player which you want to print
	 * @return the string of the statistics
	 */
	private String printStats(Player player) {
		StringBuffer sb = new StringBuffer();
		sb.append(player.getUsername() + ",");
		sb.append(player.getFamilyName() + ",");
		sb.append(player.getGivenName() + ",");
		sb.append(player.getGamePlayedCount() + " games,");
		sb.append(player.getGameWonCount() + " wins,");
		sb.append(player.getGameDrawnCount() + " draws");
		return sb.toString();
	}

}
