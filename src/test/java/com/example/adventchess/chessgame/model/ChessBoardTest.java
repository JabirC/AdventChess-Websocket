import adventchess.chessgame.model.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChessBoardTest {

    @Test
    public void testChessBoardInitialization() {
        ChessBoard chessBoard = new ChessBoard();
        ChessPiece[][] board = chessBoard.getBoard();

        // Check that the white pieces are in their starting positions
        assertTrue(board[0][0] instanceof Rook);
        assertTrue(board[0][1] instanceof Knight);
        assertTrue(board[0][2] instanceof Bishop);
        assertTrue(board[0][3] instanceof Queen);
        assertTrue(board[0][4] instanceof King);
        assertTrue(board[0][5] instanceof Bishop);
        assertTrue(board[0][6] instanceof Knight);
        assertTrue(board[0][7] instanceof Rook);

        for (int i = 0; i < 8; i++) {
            assertTrue(board[1][i] instanceof Pawn);
        }

        // Check that the black pieces are in their starting positions
        assertTrue(board[7][0] instanceof Rook);
        assertTrue(board[7][1] instanceof Knight);
        assertTrue(board[7][2] instanceof Bishop);
        assertTrue(board[7][3] instanceof Queen);
        assertTrue(board[7][4] instanceof King);
        assertTrue(board[7][5] instanceof Bishop);
        assertTrue(board[7][6] instanceof Knight);
        assertTrue(board[7][7] instanceof Rook);

        for (int i = 0; i < 8; i++) {
            assertTrue(board[6][i] instanceof Pawn);
        }
    }

    @Test
    public void testChessBoardSpecificInitializationDefault() {
        String[][] initialState = {
            {"BR", "BN", "BB", "BQ", "BK", "BB", "BN", "BR"},
            {"BP", "BP", "BP", "BP", "BP", "BP", "BP", "BP"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "WP", "WP", "WP", "WP", "WP"},
            {"WR", "WN", "WB", "WQ", "WK", "WB", "WN", "WR"}
        };

        ChessBoard chessBoard = new ChessBoard(initialState);
        ChessPiece[][] board = chessBoard.getBoard();

        // Check that the white pieces are in their starting positions
        assertTrue(board[0][0] instanceof Rook);
        assertTrue(board[0][1] instanceof Knight);
        assertTrue(board[0][2] instanceof Bishop);
        assertTrue(board[0][3] instanceof Queen);
        assertTrue(board[0][4] instanceof King);
        assertTrue(board[0][5] instanceof Bishop);
        assertTrue(board[0][6] instanceof Knight);
        assertTrue(board[0][7] instanceof Rook);

        for (int i = 0; i < 8; i++) {
            assertTrue(board[1][i] instanceof Pawn);
        }

        // Check that the black pieces are in their starting positions
        assertTrue(board[7][0] instanceof Rook);
        assertTrue(board[7][1] instanceof Knight);
        assertTrue(board[7][2] instanceof Bishop);
        assertTrue(board[7][3] instanceof Queen);
        assertTrue(board[7][4] instanceof King);
        assertTrue(board[7][5] instanceof Bishop);
        assertTrue(board[7][6] instanceof Knight);
        assertTrue(board[7][7] instanceof Rook);

        for (int i = 0; i < 8; i++) {
            assertTrue(board[6][i] instanceof Pawn);
        }
    }

    @Test
    public void testChessBoardSpecificInitializationCustom() {
        // Non-default board state
        String[][] initialState = {
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "BR", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"BP", "BP", "BP", "BP", "BP", "BP", "BP", "BP"},
            {"WR", "WN", "WB", "WQ", "WK", "WB", "WN", "WR"}
        };

        ChessBoard chessBoard = new ChessBoard(initialState);
        ChessPiece[][] board = chessBoard.getBoard();
        

        // Check if all pieces were correctly initialized
        assertTrue(board[5][2] instanceof Rook);

        for (int i = 0; i < 8; i++) {
            assertTrue(board[1][i] instanceof Pawn);
            assertTrue((board[1][i]).getColor().equals("Black"));
        }

        assertTrue(board[0][0] instanceof Rook);
        assertTrue(board[0][1] instanceof Knight);
        assertTrue(board[0][2] instanceof Bishop);
        assertTrue(board[0][3] instanceof Queen);
        assertTrue(board[0][4] instanceof King);
        assertTrue(board[0][5] instanceof Bishop);
        assertTrue(board[0][6] instanceof Knight);
        assertTrue(board[0][7] instanceof Rook);

        assertTrue((board[0][0]).getColor().equals("White"));
        assertTrue((board[0][1]).getColor().equals("White"));
        assertTrue((board[0][2]).getColor().equals("White"));
        assertTrue((board[0][3]).getColor().equals("White"));
        assertTrue((board[0][4]).getColor().equals("White"));
        assertTrue((board[0][5]).getColor().equals("White"));
        assertTrue((board[0][6]).getColor().equals("White"));
        assertTrue((board[0][7]).getColor().equals("White"));
    }

    @Test
    public void testIsValidPosition() {
        // public static boolean isValidPosition(int row, int column)

        // Test valid positions
        assertTrue(ChessBoard.isValidPosition(0, 0));  // Top-left corner
        assertTrue(ChessBoard.isValidPosition(0, 0));  // Top-left corner
        assertTrue(ChessBoard.isValidPosition(7, 7));  // Bottom-right corner
        assertTrue(ChessBoard.isValidPosition(3, 5));  // Middle of the board

        // Test invalid positions
        assertFalse(ChessBoard.isValidPosition(-1, 2));  // Row below the board
        assertFalse(ChessBoard.isValidPosition(8, 4));   // Row above the board
        assertFalse(ChessBoard.isValidPosition(2, -3));  // Column left of the board
        assertFalse(ChessBoard.isValidPosition(6, 8));   // Column right of the board

    }

    @Test
    public void testGetPieceAt() {
        // Create a chessboard
        ChessBoard chessBoard = new ChessBoard();

        // Set up pieces on the board
        ChessPiece rook = new Rook("WR", "White", 5, 4);
        ChessPiece bishop = new Bishop("WB", "White", 2, 3);
        ChessPiece knight = new Knight("BN", "Black", 5,7);

        chessBoard.placePiece(rook, 5, 4);     // Place rook at (0, 0)
        chessBoard.placePiece(bishop, 2, 3);   // Place bishop at (2, 3)
        chessBoard.placePiece(knight, 5, 3);   // Place knight at (7, 7)

        // Test getPieceAt method
        assertEquals(rook, chessBoard.getPieceAt(5, 4));   // Rook at (0, 0)
        assertEquals(bishop, chessBoard.getPieceAt(2, 3)); // Bishop at (2, 3)
        assertEquals(knight, chessBoard.getPieceAt(5, 3)); // Knight at (7, 7)

        // Test for an empty square
        assertNull(chessBoard.getPieceAt(4, 4));           // Empty square at (4, 4)
    }

    @Test
    public void testPlacePieceValidPosition() {
        ChessBoard chessBoard = new ChessBoard();
        ChessPiece piece = chessBoard.getPieceAt(0,0);

        // Test placing a piece at a valid position
        chessBoard.placePiece(piece, 2, 2);

        // Assert that the piece is placed at the correct position
        assertEquals(piece, chessBoard.getPieceAt(2, 2));
    }

    @Test
    public void testPlacePieceInvalidPosition() {
        ChessBoard chessBoard = new ChessBoard();
        ChessPiece piece = chessBoard.getPieceAt(0,0);

        // Test placing a piece at an invalid position
        assertThrows(IllegalArgumentException.class, () -> chessBoard.placePiece(piece, -1, 5));
    }

    @Test
    public void testPlacePieceOccupiedSquare() {
        ChessBoard chessBoard = new ChessBoard();
        ChessPiece piece = chessBoard.getPieceAt(0,0);
        ChessPiece piece2 = chessBoard.getPieceAt(0,2);

        // Place the first piece
        chessBoard.placePiece(piece, 3, 3);

        // Test placing another piece on an occupied square
        assertThrows(IllegalStateException.class, () -> chessBoard.placePiece(piece2, 3, 3));

        // Assert that the second piece is not placed on an occupied square
        assertEquals(piece, chessBoard.getPieceAt(3, 3));
    }

    @Test
    public void testDeletePieceValidPositionOccupiedSquare() {
        ChessBoard chessBoard = new ChessBoard();
        ChessPiece piece = chessBoard.getPieceAt(0,0);
        chessBoard.placePiece(piece, 2, 2);

        // Test deleting a piece at a valid position (occupied square)
        ChessPiece deletedPiece = chessBoard.deletePiece(2, 2);

        // Assert that the correct piece is deleted
        assertEquals(piece, deletedPiece);
        // Assert that the square is now empty
        assertNull(chessBoard.getPieceAt(2, 2));
    }

    @Test
    public void testDeletePieceValidPositionUnoccupiedSquare() {
        ChessBoard chessBoard = new ChessBoard();

        // Test deleting a piece at a valid position (unoccupied square)
        assertThrows(IllegalStateException.class, () -> chessBoard.deletePiece(3, 3));
    }

    @Test
    public void testDeletePieceInvalidPosition() {
        ChessBoard chessBoard = new ChessBoard();

        // Test deleting a piece at an invalid position
        assertThrows(IllegalArgumentException.class, () -> chessBoard.deletePiece(-1, 5));
    }

    @Test
    public void testGetPieces() {
        // Testing default chess state
        ChessBoard chessBoard = new ChessBoard();
        ArrayList<ChessPiece> whitePieces = chessBoard.getPieces("White");
        ArrayList<ChessPiece> blackPieces = chessBoard.getPieces("Black");

        assertTrue(whitePieces.size() == 16);
        assertTrue(blackPieces.size() == 16);

        ChessPiece whiteKing = chessBoard.getKing("White");
        ChessPiece blackKing = chessBoard.getKing("Black");

        assertTrue(whitePieces.contains(whiteKing));
        assertTrue(blackPieces.contains(blackKing));

        assertFalse(whitePieces.contains(blackKing));
        assertFalse(blackPieces.contains(whiteKing));

        assertTrue(whiteKing.getName().equals("WK"));
        assertTrue(whiteKing.getColor().equals("White"));

        assertTrue(blackKing.getName().equals("BK"));
        assertTrue(blackKing.getColor().equals("Black"));
    }

    @Test
    public void testMovePiece() {
        ChessBoard board = new ChessBoard();

        // Test moving a pawn to an unoccupied square
        ChessPiece pawn = board.getPieceAt(1,0);
        ChessPiece deleted = board.movePiece(pawn, new int[]{3, 0});

        assertTrue(deleted == null);

        ChessPiece pawn1 = board.getPieceAt(3,0);

        assertTrue(pawn == pawn1);
        assertTrue(pawn1.getHome()[0] == 1);
        assertTrue(pawn1.getHome()[1] == 0);
        assertTrue(pawn1.getCurrentPosition()[0] == 3);
        assertTrue(pawn1.getCurrentPosition()[1] == 0);

        ChessPiece emptySquare = board.getPieceAt(1,0);

        assertTrue(emptySquare == null);
    }

    @Test
    public void testMovePieceWithDeletion() {
        ChessBoard board = new ChessBoard();
        ChessPiece blackKnight = board.getPieceAt(7,1);
        ChessPiece whiteQueen = board.getPieceAt(0,3);

        // Test moving a piece to an occupied square
        ChessPiece deleted = board.movePiece(blackKnight, new int[]{0, 3});
        String deletedColor = deleted.getColor();

        assertTrue(whiteQueen == deleted);
        assertTrue(board.getPieceAt(7,1) == null);
        assertTrue(blackKnight == board.getPieceAt(0,3));
        assertTrue(deleted.getCurrentPosition()[0] == 0);
        assertTrue(deleted.getCurrentPosition()[1] == 3);
        assertTrue(board.getPieces(deletedColor).size() == 15);
    }

    @Test
    public void testReverseMoveNoDeletion() {
        ChessBoard board = new ChessBoard();

        ChessPiece pawn = board.getPieceAt(1,0);
        ChessPiece deleted = board.movePiece(pawn, new int[]{3, 0});

        assertTrue(deleted == null);

        ChessPiece pawn1 = board.getPieceAt(3,0);

        assertTrue(pawn == pawn1);
        assertTrue(pawn1.getHome()[0] == 1);
        assertTrue(pawn1.getHome()[1] == 0);
        assertTrue(pawn1.getCurrentPosition()[0] == 3);
        assertTrue(pawn1.getCurrentPosition()[1] == 0);

        ChessPiece emptySquare = board.getPieceAt(1,0);

        assertTrue(emptySquare == null);

        // Test reversing a move that did not result in a deleted piece
        board.reverseMove(deleted, new int[]{3, 0}, new int[]{1, 0});

        assertTrue(pawn1.getHome()[0] == 1);
        assertTrue(pawn1.getHome()[1] == 0);
        assertTrue(pawn1.getCurrentPosition()[0] == 1);
        assertTrue(pawn1.getCurrentPosition()[1] == 0);

        ChessPiece pawn2 = board.getPieceAt(3,0);

        assertTrue(pawn2 == null);
    }


    @Test
    public void testReverseMoveWithDeletion() {
        ChessBoard board = new ChessBoard();
        ChessPiece blackKnight = board.getPieceAt(7,1);
        ChessPiece whiteQueen = board.getPieceAt(0,3);

        ChessPiece deleted = board.movePiece(blackKnight, new int[]{0, 3});
        String deletedColor = deleted.getColor();

        assertTrue(whiteQueen == deleted);
        assertTrue(board.getPieceAt(7,1) == null);
        assertTrue(blackKnight == board.getPieceAt(0,3));
        assertTrue(deleted.getCurrentPosition()[0] == 0);
        assertTrue(deleted.getCurrentPosition()[1] == 3);
        assertTrue(board.getPieces(deletedColor).size() == 15);

        // Test reversing a move that resulted in a deleted piece
        board.reverseMove(deleted, new int[]{0, 3}, new int[]{7, 1});

        assertTrue(whiteQueen == board.getPieceAt(0,3));
        assertTrue(blackKnight == board.getPieceAt(7,1));
        assertTrue(deleted.getCurrentPosition()[0] == 0);
        assertTrue(deleted.getCurrentPosition()[1] == 3);
        assertTrue(blackKnight.getCurrentPosition()[0] == 7);
        assertTrue(blackKnight.getCurrentPosition()[1] == 1);
        assertTrue(board.getPieces(deletedColor).size() == 16);
    }
}