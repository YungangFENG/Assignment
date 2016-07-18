
public class AdvancedAIPlayer extends Player {

	public AdvancedAIPlayer() {

	}

	public AdvancedAIPlayer(String username, String familyname, String givenname) {
		super();
		super.username = username;
		super.familyname = familyname;
		super.givenname = givenname;
	}

	@Override
	public Move makeMove(char[][] gameBoard) {
		// TODO Auto-generated method stub
		int[] step = countStep(gameBoard);
		char currentPiece;
		char otherPiece;
		if (step[0] % 2 == 0) {
			currentPiece = 'O';
			otherPiece = 'X';
		} else {
			currentPiece = 'X';
			otherPiece = 'O';
		}

		switch (step[0]) {
		case 0:
			return new Move(0, 0);
		case 1:
			if (gameBoard[1][1] == ' ')
				return new Move(1, 1);
			else
				return new Move(0, 0);
		case 2:
			if(gameBoard[0][2]!=' ')
				return new Move(2,0);
			if(gameBoard[2][0]!=' ')
				return new Move(0,2);
			if(gameBoard[2][2]!=' ')
				return new Move(2,0);
			if (gameBoard[1][1] == ' ')
				return new Move(1, 1);
			else
				return new Move(2, 2);

		default:
			Move potentialMove;
			potentialMove = checkCanWin(currentPiece, gameBoard);
			if (potentialMove.getRow() != -1)
				return potentialMove;
			potentialMove = checkCanWin(otherPiece, gameBoard);
			if (potentialMove.getRow() != -1)
				return potentialMove;
			potentialMove = checkBuild22(currentPiece, gameBoard);
			if (potentialMove.getRow() != -1)
				return potentialMove;
			potentialMove = checkBuild22(otherPiece, gameBoard);
			if (potentialMove.getRow() != -1)
				return potentialMove;
			potentialMove = checkBuild2(currentPiece, gameBoard);
			if (potentialMove.getRow() != -1)
				return potentialMove;
			potentialMove = checkBuild2(otherPiece, gameBoard);
			if (potentialMove.getRow() != -1)
				return potentialMove;
			return randomMove(gameBoard);
		}

	}

	private Move checkCanWin(char piece, char[][] gameBoard) {

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				if (gameBoard[i][j] == ' '){
					if(gameBoard[i][(j+1)%3]==piece&&gameBoard[i][(j+2)%3]==piece)
						return new Move(i, j);
					if(gameBoard[(i+1)%3][j]==piece&&gameBoard[(i+2)%3][j]==piece)
						return new Move(i, j);
					if(i==j){
						if (gameBoard[(i + 1) % 3][(j + 1) % 3] == piece 
								&& gameBoard[(i + 2) % 3][(j + 2) % 3] == piece)
								return new Move(i, j);
					}
					else if (i == j+2 || i+2 == j) {
						if (gameBoard[(i + 1) % 3][(j + 2) % 3] == piece 
							&& gameBoard[(i + 2) % 3][(j + 1) % 3] == piece)
							return new Move(i, j);
					}
				}
			}
		return new Move(-1, -1);
	}

	private Move checkBuild22(char piece, char[][] gameBoard) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				if (gameBoard[i][j] == ' '){
					int count1 = 0;
					int countO1 = 0;
					int count2 = 0;
					int countO2 = 0;
					int count3 = 0;
					int countO3 = 0;
					for (int k = 0; k < 3; k++) {
						if (j != k) {
							if (gameBoard[i][k] != ' ') {
								if (gameBoard[i][k] == piece)
									count1++;
								else
									countO1++;
							}
						}
					}
					for (int k = 0; k < 3; k++) {
						if (i != k) {
							if (gameBoard[k][j] != ' ') {
								if (gameBoard[i][k] == piece)
									count2++;
								else
									countO2++;
							}
						}
					}
					if (i == j) {
						if (gameBoard[(i + 1) % 3][(j + 1) % 3] == piece)
							count3++;
						else if (gameBoard[(i + 1) % 3][(j + 1) % 3] != ' ')
							countO3++;
						if (gameBoard[(i + 2) % 3][(j + 2) % 3] == piece)
							count3++;
						else if (gameBoard[(i + 2) % 3][(j + 2) % 3] != ' ')
							countO3++;
					}
					if(i==j+2 || i+2==j){
						if (gameBoard[(i + 1) % 3][(j + 2) % 3] == piece)
							count3++;
						else if (gameBoard[(i + 1) % 3][(j + 2) % 3] != ' ')
							countO3++;
						if (gameBoard[(i + 2) % 3][(j + 1) % 3] == piece)
							count3++;
						else if (gameBoard[(i + 2) % 3][(j + 1) % 3] != ' ')
							countO3++;
					}
					if (check(count1, countO1, count2, countO2, count3, countO3))
						return new Move(i, j);
				}
			}
		return new Move(-1, -1);
	}

	private boolean check(int a1, int a2, int b1, int b2, int c1, int c2) {
		if (a1 == 1 && b1 == 1 && a2 == 0 && b2 == 0)
			return true;
		if (a1 == 1 && c1 == 1 && a2 == 0 && c2 == 0)
			return true;
		if (b1 == 1 && c1 == 1 && b2 == 0 && c2 == 0)
			return true;
		return false;
	}

	private Move checkBuild2(char piece, char[][] gameBoard) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				if (gameBoard[i][j] == ' '){
					int countForPiece1 = 0;
					int countForOtherPiece1 = 0;
					for (int k = 0; k < 3; k++) {
						if (j != k) {
							if (gameBoard[i][k] != ' ') {
								if (gameBoard[i][k] == piece)
									countForPiece1++;
								else
									countForOtherPiece1++;
							}
						}
					}
					if (countForPiece1 == 1 && countForOtherPiece1 == 0)
						return new Move(i, j);

					int countForPiece2 = 0;
					int countForOtherPiece2 = 0;
					for (int k = 0; k < 3; k++) {
						if (i != k) {
							if (gameBoard[k][j] != ' ') {
								if (gameBoard[i][k] == piece)
									countForPiece2++;
								else
									countForOtherPiece2++;
							}
						}
					}
					if (countForPiece2 == 1 && countForOtherPiece2 == 0)
						return new Move(i, j);
					if (i == j) {
						if (gameBoard[(i + 1) % 3][(j + 1) % 3] == piece 
							&& gameBoard[(i + 2) % 3][(j + 2) % 3] == ' ')
							return new Move(i, j);
						if (gameBoard[(i + 2) % 3][(j + 2) % 3] == piece 
							&& gameBoard[(i + 1) % 3][(j + 1) % 3] == ' ')
							return new Move(i, j);
					}
					if(i==j+2 || i+2==j){
						if (gameBoard[(i + 1) % 2][(j + 3) % 2] == piece 
								&& gameBoard[(i + 2) % 2][(j - 2) % 2] == ' ')
								return new Move(i, j);
						if (gameBoard[(i + 2) % 2][(j + 4) % 2] == piece 
								&& gameBoard[(i + 1) % 2][(j - 1) % 2] == ' ')
								return new Move(i, j);
					}
				}
			}
		return new Move(-1, -1);
	}

	private Move randomMove(char[][] gameBoard) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				if (gameBoard[i][j] == ' ')
					return new Move(i, j);
			}
		return new Move(-1, -1);
	}

	private int[] countStep(char[][] gameBoard) {
		int count[] = new int[3];
		for (int i = 0; i < 3; i++)
			count[i] = 0;
		for (char[] c1 : gameBoard)
			for (char c : c1) {
				switch (c) {
				case 'O':
					count[0]++;
					count[1]++;
					break;
				case 'X':
					count[0]++;
					count[2]++;
					break;
				}
			}
		return count;
	}

}
