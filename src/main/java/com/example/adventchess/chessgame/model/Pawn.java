package adventchess.chessgame.model;
import java.util.ArrayList;
import java.util.List;

// Pawn
public class Pawn extends ChessPiece{
    public Pawn(String name, String color, int homeRow, int homeColumn) {
        super(name, color, homeRow, homeColumn);
    }

    public List<int[]> possibleMoves(ChessBoard board) {
        int[] position = getCurrentPosition();
        int currentRow = position[0];
        int currentColumn = position[1];

        // Depending on the pawn's color, it can move forward one square
        int forwardDirection = getColor().equals("White") ? 1 : -1;

        List<int[]> moves = new ArrayList<>();

        if (isValidPosition(currentRow + forwardDirection, currentColumn)) {
            // Check if square in the front is empty
            if(board.getPieceAt(currentRow + forwardDirection, currentColumn) == null){
                addMove(moves, currentRow + forwardDirection, currentColumn);

                // Optionally, a pawn can move two squares forward on its first move
                if (currentRow == getHome()[0]) {
                    if(isValidPosition(currentRow + 2 * forwardDirection, currentColumn) && board.getPieceAt(currentRow + 2 * forwardDirection, currentColumn) == null){
                        addMove(moves, currentRow + 2 * forwardDirection, currentColumn);
                    }
                }
            }
        }

        // Capture piece diagonally to the right
        if(isValidPosition(currentRow + forwardDirection, currentColumn + forwardDirection)){
            ChessPiece rightDiagSquare = board.getPieceAt(currentRow + forwardDirection, currentColumn + forwardDirection);
            // Right diagonal square is occupied by piece of opposite color
            if(rightDiagSquare != null && !rightDiagSquare.getColor().equals(getColor())){
                addMove(moves, currentRow + forwardDirection, currentColumn + forwardDirection);
            }
        }
        // Capture piece diagonally to the left
        if(isValidPosition(currentRow + forwardDirection, currentColumn - forwardDirection)){
            // Left diagonal square is occupied by piece of opposite color
            ChessPiece leftDiagSquare = board.getPieceAt(currentRow + forwardDirection, currentColumn - forwardDirection);
            if(leftDiagSquare != null && !leftDiagSquare.getColor().equals(getColor())){
                addMove(moves, currentRow + forwardDirection, currentColumn - forwardDirection);
            }
        }
        return moves;
    }
}