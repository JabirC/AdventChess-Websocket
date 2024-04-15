import adventchess.chessgame.model.*;
import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RookTest {

    @Test
    void testPossibleMovesRookHorizontalVertical() {
        // Set up
        ChessBoard board = new ChessBoard();
        Rook rook = new Rook("WR", "White", 3, 3);
        board.placePiece(rook,3,3);

        // Rook can move horizontally and vertically
        List<int[]> rookMoves = rook.possibleMoves(board);
        assertEquals(11, rookMoves.size());

        // Check horizontal moves
        assertTrue(ChessBoard.containsMove(rookMoves, new int[]{3, 0}));
        assertTrue(ChessBoard.containsMove(rookMoves, new int[]{3, 1}));
        assertTrue(ChessBoard.containsMove(rookMoves, new int[]{3, 2}));
        assertTrue(ChessBoard.containsMove(rookMoves, new int[]{3, 4}));
        assertTrue(ChessBoard.containsMove(rookMoves, new int[]{3, 5}));
        assertTrue(ChessBoard.containsMove(rookMoves, new int[]{3, 6}));
        assertTrue(ChessBoard.containsMove(rookMoves, new int[]{3, 7}));

        // Check vertical moves
        assertFalse(ChessBoard.containsMove(rookMoves, new int[]{1, 3}));
        assertTrue(ChessBoard.containsMove(rookMoves, new int[]{2, 3}));
        assertTrue(ChessBoard.containsMove(rookMoves, new int[]{4, 3}));
        assertTrue(ChessBoard.containsMove(rookMoves, new int[]{5, 3}));
        assertTrue(ChessBoard.containsMove(rookMoves, new int[]{6, 3}));
    }


    @Test
    void testPossibleMovesRookBlocked() {
        // Set up
        ChessBoard board = new ChessBoard();
        ChessPiece rook = board.getPieceAt(0,0);
        board.placePiece(rook,3,4);

        ChessPiece whiteknight1 = board.getPieceAt(0,1);
        board.placePiece(whiteknight1,3,3);

        ChessPiece whiteknight2 = board.getPieceAt(0,6);
        board.placePiece(whiteknight2,2,4);

        ChessPiece blackknight1 = board.getPieceAt(7,1);
        board.placePiece(blackknight1,4,4);

        ChessPiece blackknight2 = board.getPieceAt(7,6);
        board.placePiece(blackknight2,3,5);

        // Rook can move horizontally and vertically
        List<int[]> rookMoves = rook.possibleMoves(board);
        assertEquals(2, rookMoves.size());

        assertTrue(ChessBoard.containsMove(rookMoves, new int[]{4, 4}));
        assertTrue(ChessBoard.containsMove(rookMoves, new int[]{3, 5}));
    }

}