package com.mygdx.game.graphics;

public class ScoreManager {
    public static ScoreManager instance;
    private float score;

    public ScoreManager() {
        score = 0; // Ensure proper initialization
    }

    // Correctly implement incrementScore
    public void incrementScore(float amount) {
        score += amount;
    }

    public float getScore() {
        return score;
    }

    public void resetScore() {
        score = 0;
    }
}


