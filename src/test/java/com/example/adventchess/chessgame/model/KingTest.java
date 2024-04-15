import adventchess.chessgame.model.*;
import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class KingTest {

    @Test
    void testPossibleMovesKing() {
        // Test case 1: King at the center of the board
        ChessBoard board1 = new ChessBoard();
        ChessPiece king1 = board1.getPieceAt(0,4);
        board1.placePiece(king1, 3, 3);

        List<int[]> king1Moves = king1.possibleMoves(board1);
        assertEquals(8, king1Moves.size());
        assertTrue(ChessBoard.containsMove(king1Moves, new int[]{2, 2}));
        assertTrue(ChessBoard.containsMove(king1Moves, new int[]{2, 3}));
        assertTrue(ChessBoard.containsMove(king1Moves, new int[]{2, 4}));
        assertTrue(ChessBoard.containsMove(king1Moves, new int[]{3, 2}));
        assertTrue(ChessBoard.containsMove(king1Moves, new int[]{3, 4}));
        assertTrue(ChessBoard.containsMove(king1Moves, new int[]{4, 2}));
        assertTrue(ChessBoard.containsMove(king1Moves, new int[]{4, 3}));
        assertTrue(ChessBoard.containsMove(king1Moves, new int[]{4, 4}));
    }

    @Test
    void testPossibleMovesKingCorner() {
        // Test case 2: King at the corner of the board
        ChessBoard board = new ChessBoard();
        ChessPiece king = board.getPieceAt(7,4);
        board.deletePiece(0,0);
        board.placePiece(king, 0, 0);

        List<int[]> kingMoves = king.possibleMoves(board);
        assertEquals(3, kingMoves.size());
        assertTrue(ChessBoard.containsMove(kingMoves, new int[]{0, 1}));
        assertTrue(ChessBoard.containsMove(kingMoves, new int[]{1, 0}));
        assertTrue(ChessBoard.containsMove(kingMoves, new int[]{1, 1}));
    }

    @Test
    void testPossibleMovesKingOwnCorner() {
        ChessBoard board = new ChessBoard();
        ChessPiece king = board.getPieceAt(7,4);

        List<int[]> kingMoves = king.possibleMoves(board);
        assertEquals(0, kingMoves.size());
    }
}