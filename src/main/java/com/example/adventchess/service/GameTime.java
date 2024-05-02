package com.example.adventchess.model;

import java.util.HashMap;
import java.util.Map;


public class GameTime {
    private int originalGameTime;
    private String player1;
    private String player2;
    private Map<String, Integer> timeLeft = new HashMap<>();
    private long lastMove;


    public GameTime(String player1, String player2, int time) {
        this.originalGameTime = time;
        this.player1 = player1;
        this.player2 = player2;
        this.lastMove = System.currentTimeMillis() / 1000;
        this.timeLeft.put(player1, time * 60);
        this.timeLeft.put(player2, time * 60);
    }

    public int getTime(String session){
        return this.timeLeft.get(session);
    }

    public void updateSession(String session){
        int timeDiff = (int)((System.currentTimeMillis() / 1000) - lastMove);
        if(timeLeft.get(session) - timeDiff <= 0){
            this.timeLeft.put(session, 0);
        }
        else{
            this.timeLeft.put(session, timeLeft.get(session) - timeDiff);
        }
        this.lastMove = System.currentTimeMillis() / 1000;
    }

    public void reset(){
        this.lastMove = System.currentTimeMillis() / 1000;
        this.timeLeft.put(this.player1, this.originalGameTime * 60);
        this.timeLeft.put(this.player2, this.originalGameTime * 60);
    }

}