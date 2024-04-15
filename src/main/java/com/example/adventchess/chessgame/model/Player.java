package adventchess.chessgame.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    private String name;
    private String color;
    private boolean turn;
    private ArrayList<ChessPiece> piecesEaten;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
        this.piecesEaten = new ArrayList<>();
        if(color.equals("White")){
            this.turn = true;
        }
        else {
            this.turn = false;
        }
    }

    // Getter methods

    public String getName(){
        return name;
    }

    public String getColor(){
        return color;
    }

    public ArrayList<ChessPiece> getPiecesEaten(){
        return piecesEaten;
    }

    public boolean isTurn(){
        return turn;
    }

}