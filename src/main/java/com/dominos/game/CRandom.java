package com.dominos.game;
import java.util.Random;

class CRandom {
    private Random random;

    public CRandom() {
        random = new Random();
    }

    public int getRandomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}

