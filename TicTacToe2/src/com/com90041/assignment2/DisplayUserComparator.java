package com.com90041.assignment2;

import java.util.Comparator;

public class DisplayUserComparator implements Comparator<Player> {

	public DisplayUserComparator(){
		
	}
	@Override
	public int compare(Player player1, Player player2) {
		return player1.getUsername().compareTo(player2.getUsername());
	}

}
