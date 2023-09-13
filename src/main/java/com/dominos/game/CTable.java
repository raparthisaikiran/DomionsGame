package com.dominos.game;

import java.util.ArrayList;
import java.util.List;

class CTable {
	  private List<CDomino> dominoesOnTable;

	    public CTable() {
	        dominoesOnTable = new ArrayList<>();
	    }

	    public void addToTableEnd(CDomino domino) {
	        dominoesOnTable.add(domino);
	    }
	    
	    public void addToTableStart(CDomino domino) {
	        dominoesOnTable.add(0, domino);
	    }

	    public void displayTable() {
	        System.out.println("Dominoes on the table:");
	        for (CDomino domino : dominoesOnTable) {
	            System.out.print(domino.toString() + " ");
	        }
	        System.out.println();
	    }

	    public int getHeadValue() {
	        if (!dominoesOnTable.isEmpty()) {
	            return dominoesOnTable.get(0).getSide1();
	        } else {
	            return -1;
	        }
	    }
	    public int size() {
	    	return dominoesOnTable.size();
	    }

	    public int getTailValue() {
	        if (!dominoesOnTable.isEmpty()) {
	            return dominoesOnTable.get(dominoesOnTable.size() - 1).getSide2();
	        } else {
	            return -1;
	        }
	    }
}

