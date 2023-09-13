package com.dominos.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DominoGame {

	public static void main(String[] args) {
		CRandom randomizer = new CRandom();
		CTable table = new CTable();
		List<CDomino> availablePieces = new ArrayList<>();
		List<CDomino> allDominoes = new ArrayList<>();

		// Initialize all 28 domino pieces
		for (int i = 0; i <= 6; i++) {
			for (int j = i; j <= 6; j++) {
				CDomino domino = new CDomino(i, j);
				allDominoes.add(domino);
			}
		}

		// Shuffle the domino pieces randomly
		Collections.shuffle(allDominoes);

		// Distribute 10 pieces to each player
		CPlayer player1 = new CPlayer();
		CPlayer player2 = new CPlayer();
		for (int i = 0; i < 10; i++) {
			player1.addToHand(allDominoes.remove(0));
			player2.addToHand(allDominoes.remove(0));
		}
		System.out.println(player1.toString());
		System.out.println(player2.toString());

		System.out.println(allDominoes.toString());

		// Put the remaining 8 pieces in available pieces
		availablePieces.addAll(allDominoes);
		List<CDomino> availablePiecesForEveryTurn = new ArrayList<>();
		availablePiecesForEveryTurn.addAll(allDominoes);

		// Determine the starting player randomly
		CPlayer currentPlayer = randomizer.getRandomInt(0, 1) == 0 ? player1 : player2;
		int maxPickCounter = 0;
		// ...
		while (true) {
			int currentHeadValue = table.getHeadValue();
			int currentTailValue = table.getTailValue();
			String playerName = (currentPlayer == player1) ? "Player 1" : "Player 2"; // Switch players

			System.out.println("Current " + playerName + " hand: " + currentPlayer.getHand());

			if (table.size() == 0 || currentPlayer.hasMatchingDomino(currentHeadValue)
					|| currentPlayer.hasMatchingDomino(currentTailValue)) {
				CDomino playedDomino = null;

				if (currentPlayer.hasMatchingDomino(currentHeadValue)) {
					playedDomino = currentPlayer.playDomino(currentHeadValue);
				} else if (currentPlayer.hasMatchingDomino(currentTailValue)) {
					playedDomino = currentPlayer.playDomino(currentTailValue);
				} else {
					playedDomino = currentPlayer.playFirstDomino();
				}

				if (playedDomino != null) {
					if (allDominoes.contains(playedDomino)) {
						allDominoes.remove(allDominoes.indexOf(playedDomino));
					}
					// Determine whether to add the domino to the head or tail of the table
					if (playedDomino.getSide1() == table.getHeadValue()) {
						playedDomino.swapValues(); // Swap if necessary to match the tail
						table.addToTableStart(playedDomino);
					} else if (playedDomino.getSide2() == table.getTailValue()) {
						playedDomino.swapValues(); // Swap if necessary to match the tail
						table.addToTableEnd(playedDomino);
					} else if (table.getHeadValue() == playedDomino.getSide2()) {
						table.addToTableStart(playedDomino);
					} else {
						table.addToTableEnd(playedDomino);
					}

					System.out.println("Player played: " + playedDomino);
					table.displayTable();

					// Update the current head and tail values on the table
					currentHeadValue = table.getHeadValue();
					currentTailValue = table.getTailValue();
					currentPlayer = (currentPlayer == player1) ? player2 : player1; // Switch players
				}
			} else {
				if (!availablePiecesForEveryTurn.isEmpty()) {
					maxPickCounter += 1;
					CDomino drawnDomino = availablePiecesForEveryTurn
							.remove(randomizer.getRandomInt(0, availablePiecesForEveryTurn.size() - 1));
					currentPlayer.addToHand(drawnDomino);
					System.out.println(playerName + " drew: " + drawnDomino);
				} else {
					availablePiecesForEveryTurn.addAll(allDominoes);
					System.out.println(playerName + " has no more available moves.");
					currentPlayer = (currentPlayer == player1) ? player2 : player1; // Switch players
				}
			}

			if (currentPlayer.getHandSize() == 0) {
				// Player has placed all their pieces, they win
				break;
			}
			if (maxPickCounter==100 || allDominoes.isEmpty()) {
				break;
			}
		}
		table.displayTable();

		if (player1.getHandSize() == 0) {
			System.out.println("Player 1 wins!");
			System.out.println("Player 2's remaining pieces: " + player2.getHand());
		} else if (player2.getHandSize() == 0) {
			System.out.println("Player 2 wins!");
			System.out.println("Player 1's remaining pieces: " + player1.getHand());
		} else {
			System.out.println("Match Tie, max picks exceeded");
		}
	}
}
