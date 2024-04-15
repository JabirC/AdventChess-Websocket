import adventchess.chessgame.model.*;
import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QueenTest {

    @Test
    void testPossibleMovesQueenHorizontalVertical() {
        // Set up
        ChessBoard board = new ChessBoard();
        Queen queen = new Queen("WQ", "White", 3, 3);
        board.placePiece(queen,3,3);

        // Queen can move horizontally and vertically
        List<int[]> queenMoves = queen.possibleMoves(board);
        assertEquals(19, queenMoves.size());

        // Check horizontal moves
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{3, 0}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{3, 1}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{3, 2}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{3, 4}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{3, 5}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{3, 6}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{3, 7}));

        // Check vertical moves
        assertFalse(ChessBoard.containsMove(queenMoves, new int[]{1, 3}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{2, 3}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{4, 3}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{5, 3}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{6, 3}));

        // Check top left diagonal
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{4, 2}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{5, 1}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{6, 0}));

        // Check top right diagonal
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{4, 4}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{5, 5}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{6, 6}));

        // Check bottom right diagonal
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{2, 4}));

        // Check bottom left diagonal
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{2, 2}));
    }

    @Test
    void testPossibleMovesQueenBlocked() {
        // Set up
        ChessBoard board = new ChessBoard();
        ChessPiece queen = board.getPieceAt(0,3);
        board.placePiece(queen,3,4);

        ChessPiece whiteknight1 = board.getPieceAt(0,1);
        board.placePiece(whiteknight1,3,3);

        ChessPiece whiteknight2 = board.getPieceAt(0,6);
        board.placePiece(whiteknight2,2,4);

        ChessPiece blackknight1 = board.getPieceAt(7,1);
        board.placePiece(blackknight1,4,4);

        ChessPiece blackknight2 = board.getPieceAt(7,6);
        board.placePiece(blackknight2,3,5);

        // Queen can move horizontally and vertically
        List<int[]> queenMoves = queen.possibleMoves(board);
        assertEquals(10, queenMoves.size());

        // Can eat black knights
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{4, 4}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{3, 5}));

        // Top right diagonal
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{4, 5}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{5, 6}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{6, 7}));

        // Top left diagonal
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{4, 3}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{5, 2}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{6, 1}));

        // Bottom left diagonal
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{2, 3}));

        // Bottom right diagonal
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{2, 5}));
    }

    @Test
    void testPossibleMovesQueenCenterBoard() {
        // Test case 1: Queen at the center of the board
        ChessBoard board = new ChessBoard();
        ChessPiece queen = board.getPieceAt(0,3);
        board.placePiece(queen, 3, 3);

        List<int[]> queenMoves = queen.possibleMoves(board);
        assertEquals(19, queenMoves.size());

        // Diagonals
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{4, 4}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{5, 5}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{6, 6}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{4, 2}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{5, 1}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{6, 0}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{2, 2}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{2, 4}));

        // Vertical
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{2, 3}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{4, 3}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{5, 3}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{6, 3}));

        // Horizontal
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{3, 0}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{3, 1}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{3, 2}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{3, 4}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{3, 5}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{3, 6}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{3, 7}));
    }

    @Test
    void testPossibleMovesQueenCornerBoard() {
        ChessBoard board = new ChessBoard();
        ChessPiece queen = board.getPieceAt(7,3);
        
        List<int[]> queenMoves = queen.possibleMoves(board);
        assertEquals(0, queenMoves.size());
    }

    @Test
    void testPossibleMovesQueenSurrounded() {
        // Test case 2: Queen at the corner of the board
        // Set up
        ChessBoard board = new ChessBoard();
        ChessPiece queen = board.getPieceAt(0,3);
        board.placePiece(queen, 5, 3);

        ChessPiece knight = board.getPieceAt(7,1);
        board.placePiece(knight, 4, 2);
        
        ChessPiece knight2 = board.getPieceAt(7,6);
        board.placePiece(knight2, 4, 4);
        
        List<int[]> queenMoves = queen.possibleMoves(board);
        // Can eat
        assertEquals(15, queenMoves.size());
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{4, 4}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{4, 2}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{6, 2}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{6, 3}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{6, 4}));

        // Horizontal
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{5, 0}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{5, 1}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{5, 2}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{5, 4}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{5, 5}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{5, 6}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{5, 7}));

        // Vertical
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{4, 3}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{3, 3}));
        assertTrue(ChessBoard.containsMove(queenMoves, new int[]{2, 3}));

    }

}