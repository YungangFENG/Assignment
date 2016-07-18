import java.io.Serializable;

public abstract class Player implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3407194772713576419L;

	protected String username;
	protected String familyname;
	protected String givenname;
	protected int gamePlayedCount;
	protected int gameDrawnCount;
	protected int gameWonCount;
	public Player(){
		gamePlayedCount=0;
		gameWonCount=0;
		gameDrawnCount=0;
	}
	public abstract Move makeMove(char[][] gameBoard);
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the familyname
	 */
	public String getFamilyname() {
		return familyname;
	}
	/**
	 * @param familiname the familyname to set
	 */
	public void setFamilyname(String familiname) {
		this.familyname = familiname;
	}
	/**
	 * @return the givenname
	 */
	public String getGivenname() {
		return givenname;
	}
	/**
	 * @param givenname the givenname to set
	 */
	public void setGivenname(String givenname) {
		this.givenname = givenname;
	}
	/**
	 * @return the gamePlayedCount
	 */
	public int getGamePlayedCount() {
		return gamePlayedCount;
	}
	/**
	 * @param gamePlayedCount the gamePlayedCount to set
	 */
	public void setGamePlayedCount(int gamePlayedCount) {
		this.gamePlayedCount = gamePlayedCount;
	}
	/**
	 * @return the gameDrawnCount
	 */
	public int getGameDrawnCount() {
		return gameDrawnCount;
	}
	/**
	 * @param gameDrawnCount the gameDrawnCount to set
	 */
	public void setGameDrawnCount(int gameDrawnCount) {
		this.gameDrawnCount = gameDrawnCount;
	}
	/**
	 * @return the gameWonCount
	 */
	public int getGameWonCount() {
		return gameWonCount;
	}
	/**
	 * @param gameWonCount the gameWonCount to set
	 */
	public void setGameWonCount(int gameWonCount) {
		this.gameWonCount = gameWonCount;
	}
	public String toString(){
		return(username+","+familyname+","+username+","+
			   gamePlayedCount+","+gameWonCount+","+gameDrawnCount+"\n");
	}
}
