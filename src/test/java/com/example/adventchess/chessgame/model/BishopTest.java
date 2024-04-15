import adventchess.chessgame.model.*;
import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BishopTest {

    @Test
    void testPossibleMovesBishopCenterBoard() {
        // Test case 1: Bishop at the center of the board
        ChessBoard board = new ChessBoard();
        ChessPiece bishop = board.getPieceAt(0,2);
        board.placePiece(bishop, 3, 3);

        List<int[]> bishopMoves = bishop.possibleMoves(board);
        assertEquals(8, bishopMoves.size());
        assertTrue(ChessBoard.containsMove(bishopMoves, new int[]{4, 4}));
        assertTrue(ChessBoard.containsMove(bishopMoves, new int[]{5, 5}));
        assertTrue(ChessBoard.containsMove(bishopMoves, new int[]{6, 6}));
        assertTrue(ChessBoard.containsMove(bishopMoves, new int[]{4, 2}));
        assertTrue(ChessBoard.containsMove(bishopMoves, new int[]{5, 1}));
        assertTrue(ChessBoard.containsMove(bishopMoves, new int[]{6, 0}));
        assertTrue(ChessBoard.containsMove(bishopMoves, new int[]{2, 2}));
        assertTrue(ChessBoard.containsMove(bishopMoves, new int[]{2, 4}));
    }

    @Test
    void testPossibleMovesBishopCornerBoard() {
        // Test case 2: Bishop at the corner of the board
        ChessBoard board = new ChessBoard();
        ChessPiece bishop = board.getPieceAt(7,2);
        
        List<int[]> bishopMoves = bishop.possibleMoves(board);
        assertEquals(0, bishopMoves.size());
    }

    @Test
    void testPossibleMovesBishopSurrounded() {
        // Test case 2: Bishop at the corner of the board
        // Set up
        ChessBoard board = new ChessBoard();
        ChessPiece bishop = board.getPieceAt(0,2);
        board.placePiece(bishop, 5, 3);

        ChessPiece knight = board.getPieceAt(7,1);
        board.placePiece(knight, 4, 2);
        
        ChessPiece queen = board.getPieceAt(7,3);
        board.placePiece(queen, 4, 4);
        
        List<int[]> bishopMoves = bishop.possibleMoves(board);
        assertEquals(4, bishopMoves.size());
        assertTrue(ChessBoard.containsMove(bishopMoves, new int[]{4, 4}));
        assertTrue(ChessBoard.containsMove(bishopMoves, new int[]{4, 2}));
        assertTrue(ChessBoard.containsMove(bishopMoves, new int[]{6, 2}));
        assertTrue(ChessBoard.containsMove(bishopMoves, new int[]{6, 4}));
    }


}