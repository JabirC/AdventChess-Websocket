import adventchess.chessgame.model.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChessPieceTest {

    @Test
    public void testChessPieceInitialization() {
        // Test initialization for King
        King king = new King("WK", "White", 1, 5);
        assertEquals("WK", king.getName());
        assertEquals("White", king.getColor());
        assertArrayEquals(new int[]{1, 5}, king.getHome());
        assertArrayEquals(new int[]{1, 5}, king.getCurrentPosition());

        // Test initialization for Queen
        Queen queen = new Queen("BQ", "Black", 8, 4);
        assertEquals("BQ", queen.getName());
        assertEquals("Black", queen.getColor());
        assertArrayEquals(new int[]{8, 4}, queen.getHome());
        assertArrayEquals(new int[]{8, 4}, queen.getCurrentPosition());

        // Test initialization for Rook
        Rook rook = new Rook("WR", "White", 1, 1);
        assertEquals("WR", rook.getName());
        assertEquals("White", rook.getColor());
        assertArrayEquals(new int[]{1, 1}, rook.getHome());
        assertArrayEquals(new int[]{1, 1}, rook.getCurrentPosition());

        // Test initialization for Bishop
        Bishop bishop = new Bishop("BB", "Black", 8, 3);
        assertEquals("BB", bishop.getName());
        assertEquals("Black", bishop.getColor());
        assertArrayEquals(new int[]{8, 3}, bishop.getHome());
        assertArrayEquals(new int[]{8, 3}, bishop.getCurrentPosition());

        // Test initialization for Knight
        Knight knight = new Knight("WN", "White", 1, 2);
        assertEquals("WN", knight.getName());
        assertEquals("White", knight.getColor());
        assertArrayEquals(new int[]{1, 2}, knight.getHome());
        assertArrayEquals(new int[]{1, 2}, knight.getCurrentPosition());

        // Test initialization for Pawn
        Pawn pawn = new Pawn("BP", "Black", 7, 4);
        assertEquals("BP", pawn.getName());
        assertEquals("Black", pawn.getColor());
        assertArrayEquals(new int[]{7, 4}, pawn.getHome());
        assertArrayEquals(new int[]{7, 4}, pawn.getCurrentPosition());
    }
    

    @Test
    public void testSetCurrentPosition() {
        // Test setting current position for King
        King king = new King("WK", "White", 1, 5);
        king.setCurrentPosition(2, 6);
        assertArrayEquals(new int[]{2, 6}, king.getCurrentPosition());

        // Test setting current position for Queen
        Queen queen = new Queen("BQ", "Black", 8, 4);
        queen.setCurrentPosition(6, 3);
        assertArrayEquals(new int[]{6, 3}, queen.getCurrentPosition());

        // Test setting current position for Rook
        Rook rook = new Rook("WR", "White", 1, 1);
        rook.setCurrentPosition(3, 1);
        assertArrayEquals(new int[]{3, 1}, rook.getCurrentPosition());

        // Test setting current position for Bishop
        Bishop bishop = new Bishop("BB", "Black", 8, 3);
        bishop.setCurrentPosition(7, 5);
        assertArrayEquals(new int[]{7, 5}, bishop.getCurrentPosition());

        // Test setting current position for Knight
        Knight knight = new Knight("WN", "White", 1, 2);
        knight.setCurrentPosition(3, 1);
        assertArrayEquals(new int[]{3, 1}, knight.getCurrentPosition());

        // Test setting current position for Pawn
        Pawn pawn = new Pawn("BP", "Black", 7, 4);
        pawn.setCurrentPosition(6, 4);
        assertArrayEquals(new int[]{6, 4}, pawn.getCurrentPosition());
    }

    @Test
    public void testIsValidMove() {
        ChessBoard board = new ChessBoard();
        ChessPiece wKnight = board.getPieceAt(0,1);
        assertTrue(wKnight.isValidMove(board, 2, 0));
        assertTrue(wKnight.isValidMove(board, 2, 2));
        assertFalse(wKnight.isValidMove(board, 1, 3));
    }
}