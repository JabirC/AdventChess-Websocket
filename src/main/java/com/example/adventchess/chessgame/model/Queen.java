package adventchess.chessgame.model;
import java.util.ArrayList;
import java.util.List;
// Queen
public class Queen extends ChessPiece{
    public Queen(String name, String color, int homeRow, int homeColumn) {
        super(name, color, homeRow, homeColumn);
    }

    public List<int[]> possibleMoves(ChessBoard board) {
        int[] position = getCurrentPosition();
        int currentRow = position[0];
        int currentColumn = position[1];

        List<int[]> moves = new ArrayList<>();


        // Check moves along the right columns (horizontal)
        for (int i = currentColumn; i < 8; i++) {
            if (i != currentColumn) {
                ChessPiece square = board.getPieceAt(currentRow,i);
                // Square is occupied
                if(square != null){
                    // Square contains enemy piece
                    if(!square.getColor().equals(getColor())){
                        addMove(moves, currentRow, i);
                        break;
                    }
                    // Square contains ally piece
                    else{
                        break;
                    }
                }
                // Square is empty
                else if(square == null){
                    addMove(moves, currentRow, i);
                }
            }
        }

        // Check moves along the left columns (horizontal)
        for (int i = currentColumn; i >= 0; i--) {
            if (i != currentColumn) {
                ChessPiece square = board.getPieceAt(currentRow,i);
                // Square is occupied
                if(square != null){
                    // Square contains enemy piece
                    if(!square.getColor().equals(getColor())){
                        addMove(moves, currentRow, i);
                        break;
                    }
                    // Square contains ally piece
                    else{
                        break;
                    }
                }
                // Square is empty
                else if(square == null){
                    addMove(moves, currentRow, i);
                }
            }
        }

        // Check moves along the upper rows (horizontal)
        for (int i = currentRow; i < 8; i++) {
            if (i != currentRow) {
                ChessPiece square = board.getPieceAt(i,currentColumn);
                // Square is occupied
                if(square != null){
                    // Square contains enemy piece
                    if(!square.getColor().equals(getColor())){
                        addMove(moves, i, currentColumn);
                        break;
                    }
                    // Square contains ally piece
                    else{
                        break;
                    }
                }
                // Square is empty
                else if(square == null){
                    addMove(moves, i, currentColumn);
                }
            }
        }

        // Check moves along the bottom rows (horizontal)
        for (int i = currentRow; i >= 0; i--) {
            if (i != currentRow) {
                ChessPiece square = board.getPieceAt(i,currentColumn);
                // Square is occupied
                if(square != null){
                    // Square contains enemy piece
                    if(!square.getColor().equals(getColor())){
                        addMove(moves, i, currentColumn);
                        break;
                    }
                    // Square contains ally piece
                    else{
                        break;
                    }
                }
                // Square is empty
                else if(square == null){
                    addMove(moves, i, currentColumn);
                }
            }
        }


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