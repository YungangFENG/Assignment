import java.util.Scanner;

public class HumanPlayer extends Player {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7941515569372837908L;

	public HumanPlayer(){
		super();
	}
	
	public HumanPlayer(String username, String familyname, String givenname){
		this();
		this.username=username;
		this.familyname=familyname;
		this.givenname=givenname;
	}
	
	
	@Override
	public Move makeMove(char[][] gameBoard) {
		// TODO Auto-generated method stub
		Scanner keyboard=TicTacToe.keyboard;
		int row=keyboard.nextInt();
		int column=keyboard.nextInt();
		keyboard.nextLine();
		return new Move(row,column);
	}

}
