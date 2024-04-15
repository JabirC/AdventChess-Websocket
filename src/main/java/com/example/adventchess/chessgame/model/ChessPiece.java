package adventchess.chessgame.model;
import java.util.ArrayList;
import java.util.List;

public abstract class ChessPiece {
    private String name;
    private String color;
    private int homeRow;
    private int homeColumn;
    private int curRow;
    private int curColumn;

    public ChessPiece(String name, String color, int homeRow, int homeColumn){
        this.name = name;
        this.color = color;
        this.homeRow = homeRow;
        this.homeColumn = homeColumn;
        this.curRow = homeRow;
        this.curColumn = homeColumn;
    }

    // Getter methods

    public String getName(){
        return name;
    }

    public String getColor(){
        return color;
    }

    public int[] getHome(){
        int[] home = {homeRow, homeColumn};
        return home;
    }

    public int[] getCurrentPosition(){
        int [] cur = {curRow, curColumn};
        return cur;
    }

    // Setter method
    public void setCurrentPosition(int row, int column){
        this.curRow = row;
        this.curColumn = column;
    }

    // Method to check if a position is within the board boundaries
    public static boolean isValidPosition(int row, int column) {
        return row >= 0 && row < 8 && column >= 0 && column < 8;
    }

    // Add a square to the list of possible moves
    public static void addMove(List<int[]> moves, int newRow, int newCol) {
        if (isValidPosition(newRow, newCol)) {
            moves.add(new int[]{newRow, newCol});
        }
    }

    // Abstract method for possible moves
    public abstract List<int[]> possibleMoves(ChessBoard board);

    // Method to check if valid move
    public boolean isValidMove(ChessBoard board, int toRow, int toCol){
        List<int[]> moves = this.possibleMoves(board);
        return ChessBoard.containsMove(moves, new int[]{toRow, toCol});
    }
}