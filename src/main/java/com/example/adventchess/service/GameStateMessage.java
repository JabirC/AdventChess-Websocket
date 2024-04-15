package com.example.adventchess.model;

public class GameStateMessage {
    private String gameId;
    private String[][] gameState;
    private boolean isWhite;
    private boolean turn;

    private int fromRow;
    private int fromCol;
    private int toRow;
    private int toCol;

    public GameStateMessage(String gameId, String[][] gameState, boolean isWhite, boolean turn, MoveMessage move) {
        this.gameId = gameId;
        this.gameState = gameState;
        this.isWhite = isWhite;
        this.turn = turn;

        this.fromRow = move.getFromRow();
        this.fromCol = move.getFromCol();
        this.toRow = move.getToRow();
        this.toCol = move.getToCol();
    }

    // Getters and setters (not necessary for Gson serialization)
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String[][] getGameState() {
        return gameState;
    }

    public void setGameState(String[][] gameState) {
        this.gameState = gameState;
    }

    public boolean getIsWhite(){
        return isWhite;
    }

    public void setIsWhite(boolean isWhite){
        this.isWhite = isWhite;
    }

    public boolean getTurn(){
        return turn;
    }

    public void setTurn(boolean turn){
        this.turn = turn;
    }


    public int getFromRow(){
        return fromRow;
    }

    public void setFromRow(int row){
        this.fromRow = row;
    }

    public int getFromCol(){
        return fromCol;
    }

    public void setFromCol(int col){
        this.fromCol = col;
    }


    public int getToRow(){
        return toRow;
    }

    public void setToRow(int row){
        this.toRow = row;
    }

    public int getToCol(){
        return toCol;
    }

    public void setToCol(int col){
        this.toCol = col;
    }
}