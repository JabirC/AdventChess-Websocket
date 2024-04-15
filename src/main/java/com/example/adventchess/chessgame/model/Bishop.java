package adventchess.chessgame.model;
import java.util.ArrayList;
import java.util.List;

// Bishop
public class Bishop extends ChessPiece{
    public Bishop(String name, String color, int homeRow, int homeColumn) {
        super(name, color, homeRow, homeColumn);
    }

    public List<int[]> possibleMoves(ChessBoard board) {
        int[] position = getCurrentPosition();
        int currentRow = position[0];
        int currentColumn = position[1];


        List<int[]> moves = new ArrayList<>();

        // Checking Top Right Diagonal
        for(int i = 1; currentRow + i < 8 && currentColumn + i < 8; i++){
            if(isValidPosition(currentRow + i, currentColumn + i)){
                ChessPiece piece = board.getPieceAt(currentRow + i, currentColumn + i);
                // Occupied square
                if(piece != null){
                    // Enemy piece
                    if(!piece.getColor().equals(getColor())){
                        addMove(moves, currentRow + i, currentColumn + i );
                        break;
                    }
                    // Ally piece
                    else{
                        break;
                    }
                }
                // Unoccupied square
                else if(piece == null){
                    addMove(moves, currentRow + i, currentColumn + i );
                }
            }
        }

        // Checking Top left Diagonal
        for(int i = 1; currentRow + i < 8 && currentColumn - i >= 0; i++){
            if(isValidPosition(currentRow + i, currentColumn - i)){
                ChessPiece piece = board.getPieceAt(currentRow + i, currentColumn - i);
                // Occupied square
                if(piece != null){
                    // Enemy piece
                    if(!piece.getColor().equals(getColor())){
                        addMove(moves, currentRow + i, currentColumn - i );
                        break;
                    }
                    // Ally piece
                    else{
                        break;
                    }
                }
                // Unoccupied square
                else if(piece == null){
                    addMove(moves, currentRow + i, currentColumn - i );
                }
            }
        }

        // Checking Bottom Right Diagonal
        for(int i = 1; currentRow - i >= 0 && currentColumn + i < 8; i++){
            if(isValidPosition(currentRow - i, currentColumn + i)){
                ChessPiece piece = board.getPieceAt(currentRow - i, currentColumn + i);
                // Occupied square
                if(piece != null){
                    // Enemy piece
                    if(!piece.getColor().equals(getColor())){
                        addMove(moves, currentRow - i, currentColumn + i );
                        break;
                    }
                    // Ally piece
                    else{
                        break;
                    }
                }
                // Unoccupied square
                else if(piece == null){
                    addMove(moves, currentRow - i, currentColumn + i );
                }
            }
        }

        // Checking Bottom left Diagonal
        for(int i = 1; currentRow - i >= 0 && currentColumn - i >= 0; i++){
            if(isValidPosition(currentRow - i, currentColumn - i)){
                ChessPiece piece = board.getPieceAt(currentRow - i, currentColumn - i);
                // Occupied square
                if(piece != null){
                    // Enemy piece
                    if(!piece.getColor().equals(getColor())){
                        addMove(moves, currentRow - i, currentColumn - i );
                        break;
                    }
                    // Ally piece
                    else{
                        break;
                    }
                }
                // Unoccupied square
                else if(piece == null){
                    addMove(moves, currentRow - i, currentColumn - i );
                }
            }
        }



        return moves;
    }
}