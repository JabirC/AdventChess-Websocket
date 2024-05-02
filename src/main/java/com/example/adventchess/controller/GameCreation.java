package com.example.adventchess.model;

public class GameCreation {
    private String mode;
    private int time;

    public GameCreation(String mode, int time) {
        this.mode = mode;
        this.time = time;
    }

    // Getters and setters (not necessary for Gson serialization)
    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

}