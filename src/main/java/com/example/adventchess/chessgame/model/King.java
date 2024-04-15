package adventchess.chessgame.model;
import java.util.ArrayList;
import java.util.List;
// King
public class King extends ChessPiece{
    public King(String name, String color, int homeRow, int homeColumn) {
        super(name, color, homeRow, homeColumn);
    }

    public List<int[]> possibleMoves(ChessBoard board) {
        int[] position = getCurrentPosition();
        int currentRow = position[0];
        int currentColumn = position[1];

        List<int[]> moves = new ArrayList<>();


        // Check moves horizontally, vertically, and diagonally
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // Exclude the position of the king itself
                if (i != 0 || j != 0) {
                    if(isValidPosition(currentRow + i, currentColumn + j)){
                        ChessPiece square = board.getPieceAt(currentRow + i, currentColumn + j);
                        // Square is occupied
                        if(square != null){
                            // Enemy piece
                            if(!square.getColor().equals(getColor())){
                                addMove(moves, currentRow + i, currentColumn + j);
                            }
                        }
                        // Square is unoccupied
                        else if(square == null){
                            addMove(moves, currentRow + i, currentColumn + j);
                        }
                    }
                }
            }
        }
        return moves;
    }
}