package adventchess.chessgame.logic;
import java.util.ArrayList;
import java.util.List;
import adventchess.chessgame.model.*;


public class MoveValidator {
    public static boolean isValidMove(ChessBoard board, int fromRow, int fromCol, int toRow, int toCol) {
        // Check if the coordinates are within the bounds of the chessboard
        if (!ChessBoard.isValidPosition(fromRow, fromCol) || !ChessBoard.isValidPosition(toRow, toCol)) {
            return false;
        }

        // Check if there is a piece at the source position
        if (board.getPieceAt(fromRow, fromCol) == null) {
            return false;
        }

        // // Get the piece at the source position
        ChessPiece piece = board.getPieceAt(fromRow, fromCol);
        return piece.isValidMove(board, toRow, toCol);
    }
}