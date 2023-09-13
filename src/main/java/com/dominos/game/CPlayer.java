package com.dominos.game;
import java.util.ArrayList;
import java.util.List;

class CPlayer {
    private List<CDomino> hand;

    public CPlayer() {
        hand = new ArrayList<>();
    }

    public void addToHand(CDomino domino) {
        hand.add(domino);
    }

    public boolean hasMatchingDomino(int value) {
        for (CDomino domino : hand) {
            if (domino.matches(value)) {
                return true;
            }
        }
        return false;
    }

    public CDomino playDomino(int value) {
        for (CDomino domino : hand) {
            if (domino.matches(value)) {
                hand.remove(domino);
                return domino;
            }
        }
        return null; // Player couldn't match any domino
    }
    
    public CDomino playFirstDomino() {
        for (CDomino domino : hand) {
        	hand.remove(domino);
            return domino;
        }
       return null;
    }

    public int getHandSize() {
        return hand.size();
    }

    public List<CDomino> getHand() {
        return hand;
    }
    
    @Override
    public String toString() {
        StringBuilder handString = new StringBuilder();
        handString.append("Player's hand: [");

        for (int i = 0; i < hand.size(); i++) {
            CDomino domino = hand.get(i);
            handString.append(domino.toString());

            if (i < hand.size() - 1) {
                handString.append(", ");
            }
        }

        handString.append("]");
        return handString.toString();
    }
}

