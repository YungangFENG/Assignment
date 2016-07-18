package com.com90041.assignment2;

/**
 * 
 * This is the player class for the TicTacToe game
 * 
 * @author Yungang FENG 781130
 *
 */
public class Player {
	private String username;
	private String familyName;
	private String givenName;
	private int gamePlayedCount = 0;
	private int gameWonCount = 0;
	private int gameDrawnCount = 0;

	/**
	 * the constructor of the Player class
	 * 
	 * @param username
	 *            the unique username of the player
	 * @param familyName
	 *            the family name of this player
	 * @param givenName
	 *            the given name of this player
	 */
	public Player(String username, String familyName, String givenName) {
		this.username = username;
		this.familyName = familyName;
		this.givenName = givenName;
	}


	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the family name
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * @param familyName
	 *            the family name to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * @return the given name
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @param givenName
	 *            the given name to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * @return the gamePlayedCount
	 */
	public int getGamePlayedCount() {
		return gamePlayedCount;
	}

	/**
	 * @param gamePlayedCount
	 *            the gamePlayedCount to set
	 */
	public void setGamePlayedCount(int gamePlayedCount) {
		this.gamePlayedCount = gamePlayedCount;
	}

	/**
	 * @return the gameWonCount
	 */
	public int getGameWonCount() {
		return gameWonCount;
	}

	/**
	 * @param gameWonCount
	 *            the gameWonCount to set
	 */
	public void setGameWonCount(int gameWonCount) {
		this.gameWonCount = gameWonCount;
	}

	/**
	 * @return the gameDrawnCount
	 */
	public int getGameDrawnCount() {
		return gameDrawnCount;
	}

	/**
	 * @param gameDrawnCount
	 *            the gameDrawnCount to set
	 */
	public void setGameDrawnCount(int gameDrawnCount) {
		this.gameDrawnCount = gameDrawnCount;
	}

}
