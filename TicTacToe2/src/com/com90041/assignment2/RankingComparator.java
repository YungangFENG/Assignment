package com.com90041.assignment2;

import java.util.Comparator;

public class RankingComparator implements Comparator<Player> {

	public RankingComparator(){
		
	}
	@Override
	public int compare(Player player1, Player player2) {
		double rate1;
		double rate2;
		double drawrate1;
		double drawrate2;
		if (player1.getUsername().equals(player2.getUsername()))
			return 0;
		if (player1.getGamePlayedCount() == 0) {
			rate1 = 0;
			drawrate1 = 0;
		}

		else {
			rate1 = ((double)player1.getGameWonCount()) / player1.getGamePlayedCount();
			drawrate1 = ((double) player1.getGameDrawnCount()) / player1.getGamePlayedCount();
		}

		if (player2.getGamePlayedCount() == 0) {
			rate2 = 0;
			drawrate2 = 0;
		}

		else {
			rate2 = ((double)player2.getGameWonCount()) / player2.getGamePlayedCount();
			drawrate2 = ((double)player2.getGameDrawnCount()) / player2.getGamePlayedCount();
		}

		if (rate1 == rate2){
			if (drawrate1 == drawrate2)
				return player1.getUsername().compareTo(player2.getUsername());
			else
				return (drawrate1 > drawrate2 ? -1: 1);
		}
		else
			return (rate1 > rate2 ? -1 : 1);
	}

}
