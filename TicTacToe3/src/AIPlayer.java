
public class AIPlayer extends Player {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7852858202130380789L;

	public AIPlayer() {
		super();
	}

	public AIPlayer(String username, String familyname, String givenname) {
		this();
		this.username = username;
		this.familyname = familyname;
		this.givenname = givenname;
	}

	@Override
	public Move makeMove(char[][] gameBoard) {
		// TODO Auto-generated method stub
		int row = -1;
		int column = -1;
		for (int i = 0; i <= 2; i++)
			for (int j = 0; j <= 2; j++) {
				if (gameBoard[i][j] == ' ') {
					row = i;
					column = j;
					return new Move(row, column);
				}
			}
		return new Move(row,column);
	}

}
