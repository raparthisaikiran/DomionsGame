package com.dominos.game;

import java.util.List;

class CDomino {
    private int side1;
    private int side2;

    public CDomino(int side1, int side2) {
        this.side1 = side1;
        this.side2 = side2;
    }

    public int getSide1() {
        return side1;
    }

    public int getSide2() {
        return side2;
    }

    public boolean matches(int value) {
        return side1 == value || side2 == value;
    }
    
    public void swapValues() {
        int temp = side1;
        side1 = side2;
        side2 = temp;
    }

    @Override
    public String toString() {
        return "[" + side1 + "|" + side2 + "]";
    }
}

