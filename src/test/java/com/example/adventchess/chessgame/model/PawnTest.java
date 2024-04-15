import adventchess.chessgame.model.*;
import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {

    @Test
    void testPossibleMovesWhitePawn() {
        // Set up
        ChessBoard board = new ChessBoard();
        ChessPiece whitePawn = board.getPieceAt(1,1);

        List<int[]> moves = whitePawn.possibleMoves(board);

        // Forward one square, forward two sqaures
        assertEquals(2, moves.size());
        assertTrue(ChessBoard.containsMove(moves, (new int[]{2, 1})));
        assertTrue(ChessBoard.containsMove(moves, (new int[]{3, 1})));
    }


    @Test
    void testPossibleMovesBlackPawn() {
        // Set up
        ChessBoard board = new ChessBoard();
        ChessPiece blackPawn = board.getPieceAt(6,1);

        List<int[]> moves = blackPawn.possibleMoves(board);

        // Forward one square, forward two sqaures
        assertEquals(2, moves.size());
        assertTrue(ChessBoard.containsMove(moves, (new int[]{5, 1})));
        assertTrue(ChessBoard.containsMove(moves, (new int[]{4, 1})));
    }

    @Test
    void testPossibleMovesWhitePawnBlocked() {
        // Set up
        ChessBoard board = new ChessBoard();
        ChessPiece whitePawn = board.getPieceAt(1,1);
        ChessPiece blockingPawn = board.getPieceAt(6,1);
        board.placePiece(blockingPawn,2,1);

        // White pawn is blocked, no possible moves
        List<int[]> whitePawnMoves = whitePawn.possibleMoves(board);
        assertEquals(0, whitePawnMoves.size());
        
        // Black Pawn can capture two pawns diagonally
        List<int[]> blockingPawnMoves = blockingPawn.possibleMoves(board);
        assertEquals(2, blockingPawnMoves.size());
        assertTrue(ChessBoard.containsMove(blockingPawnMoves, (new int[]{1, 0})));
        assertTrue(ChessBoard.containsMove(blockingPawnMoves, (new int[]{1, 2})));

        // Testing moves for the white pawn left of the original white pawn
        ChessPiece WhitePawnLeft = board.getPieceAt(1,0);
        List<int[]> WhitePawnLeftMoves = WhitePawnLeft.possibleMoves(board);
        // 3 possible moves: forward 1 square, forward 2 squares, eat the blocking pawn right diagonal
        assertEquals(3, WhitePawnLeftMoves.size());
        assertTrue(ChessBoard.containsMove(WhitePawnLeftMoves, (new int[]{2, 0})));
        assertTrue(ChessBoard.containsMove(WhitePawnLeftMoves, (new int[]{3, 0})));
        assertTrue(ChessBoard.containsMove(WhitePawnLeftMoves, (new int[]{2, 1})));

        // Testing moves for the white pawn right of the original white pawn
        ChessPiece WhitePawnRight = board.getPieceAt(1,2);
        List<int[]> WhitePawnRightMoves = WhitePawnRight.possibleMoves(board);
        // 3 possible moves: forward 1 square, forward 2 squares, eat the blocking pawn left diagonal
        assertEquals(3, WhitePawnRightMoves.size());
        assertTrue(ChessBoard.containsMove(WhitePawnRightMoves, (new int[]{2, 2})));
        assertTrue(ChessBoard.containsMove(WhitePawnRightMoves, (new int[]{3, 2})));
        assertTrue(ChessBoard.containsMove(WhitePawnRightMoves, (new int[]{2, 1})));
    }


    @Test
    void testPossibleMovesBlackPawnBlocked() {
        // Set up
        ChessBoard board = new ChessBoard();
        ChessPiece blackPawn = board.getPieceAt(6, 1);
        ChessPiece blockingPawn = board.getPieceAt(1, 1);
        board.placePiece(blockingPawn, 5, 1);

        // Black pawn is blocked, no possible moves
        List<int[]> blackPawnMoves = blackPawn.possibleMoves(board);
        assertEquals(0, blackPawnMoves.size());

        // White Pawn can capture two pawns diagonally
        List<int[]> blockingPawnMoves = blockingPawn.possibleMoves(board);
        assertEquals(2, blockingPawnMoves.size());
        assertTrue(ChessBoard.containsMove(blockingPawnMoves, new int[]{6, 0}));
        assertTrue(ChessBoard.containsMove(blockingPawnMoves, new int[]{6, 2}));

        // Testing moves for the black pawn left of the original black pawn
        ChessPiece blackPawnLeft = board.getPieceAt(6, 0);
        List<int[]> blackPawnLeftMoves = blackPawnLeft.possibleMoves(board);
        // 3 possible moves: forward 1 square, forward 2 squares, eat the blocking pawn right diagonal
        assertEquals(3, blackPawnLeftMoves.size());
        assertTrue(ChessBoard.containsMove(blackPawnLeftMoves, new int[]{5, 0}));
        assertTrue(ChessBoard.containsMove(blackPawnLeftMoves, new int[]{4, 0}));
        assertTrue(ChessBoard.containsMove(blackPawnLeftMoves, new int[]{5, 1}));

        // Testing moves for the black pawn right of the original black pawn
        ChessPiece blackPawnRight = board.getPieceAt(6, 2);
        List<int[]> blackPawnRightMoves = blackPawnRight.possibleMoves(board);
        // 3 possible moves: forward 1 square, forward 2 squares, eat the blocking pawn left diagonal
        assertEquals(3, blackPawnRightMoves.size());
        assertTrue(ChessBoard.containsMove(blackPawnRightMoves, new int[]{5, 2}));
        assertTrue(ChessBoard.containsMove(blackPawnRightMoves, new int[]{4, 2}));
        assertTrue(ChessBoard.containsMove(blackPawnRightMoves, new int[]{5, 1}));
    }

    @Test
    void testPossibleMovesWhitePawnCapture() {
        // Set up
        ChessBoard board = new ChessBoard();
        ChessPiece whitePawn = board.getPieceAt(1, 1);
        ChessPiece capturingPawn1 = board.getPieceAt(6, 0);
        ChessPiece capturingPawn2 = board.getPieceAt(6, 2);
        board.placePiece(capturingPawn1,2,0);
        board.placePiece(capturingPawn2,2,2);

        // White pawn can capture two pawns diagonally, or move forward one or two squares
        List<int[]> whitePawnMoves = whitePawn.possibleMoves(board);
        assertEquals(4, whitePawnMoves.size());
        // Diagonals
        assertTrue(ChessBoard.containsMove(whitePawnMoves, new int[]{2, 0}));
        assertTrue(ChessBoard.containsMove(whitePawnMoves, new int[]{2, 2}));
        // Forward
        assertTrue(ChessBoard.containsMove(whitePawnMoves, new int[]{2, 1}));
        assertTrue(ChessBoard.containsMove(whitePawnMoves, new int[]{3, 1}));

        // Capturing pawn1 can capture a white pawn
        List<int[]> capturingPawn1Moves = capturingPawn1.possibleMoves(board);
        assertEquals(1, capturingPawn1Moves.size());
        assertTrue(ChessBoard.containsMove(capturingPawn1Moves, new int[]{1, 1}));

        // Capturing pawn1 can capture two white pawns
        List<int[]> capturingPawn2Moves = capturingPawn2.possibleMoves(board);
        assertEquals(2, capturingPawn2Moves.size());
        assertTrue(ChessBoard.containsMove(capturingPawn2Moves, new int[]{1, 1}));
        assertTrue(ChessBoard.containsMove(capturingPawn2Moves, new int[]{1, 3}));
    }

    @Test
    void testPossibleMovesBlackPawnCapture() {
        // Set up
        ChessBoard board = new ChessBoard();
        ChessPiece blackPawn = board.getPieceAt(6, 1);
        ChessPiece capturingPawn1 = board.getPieceAt(1, 0);
        ChessPiece capturingPawn2 = board.getPieceAt(1, 2);
        board.placePiece(capturingPawn1, 5, 0);
        board.placePiece(capturingPawn2, 5, 2);

        // Black pawn can capture two pawns diagonally, or move forward one or two squares
        List<int[]> blackPawnMoves = blackPawn.possibleMoves(board);
        assertEquals(4, blackPawnMoves.size());
        // Diagonals
        assertTrue(ChessBoard.containsMove(blackPawnMoves, new int[]{5, 0}));
        assertTrue(ChessBoard.containsMove(blackPawnMoves, new int[]{5, 2}));
        // Forward
        assertTrue(ChessBoard.containsMove(blackPawnMoves, new int[]{5, 1}));
        assertTrue(ChessBoard.containsMove(blackPawnMoves, new int[]{4, 1}));

        // Capturing pawn1 can capture a black pawn
        List<int[]> capturingPawn1Moves = capturingPawn1.possibleMoves(board);
        assertEquals(1, capturingPawn1Moves.size());
        assertTrue(ChessBoard.containsMove(capturingPawn1Moves, new int[]{6, 1}));

        // Capturing pawn2 can capture two black pawns
        List<int[]> capturingPawn2Moves = capturingPawn2.possibleMoves(board);
        assertEquals(2, capturingPawn2Moves.size());
        assertTrue(ChessBoard.containsMove(capturingPawn2Moves, new int[]{6, 1}));
        assertTrue(ChessBoard.containsMove(capturingPawn2Moves, new int[]{6, 3}));
    }


    @Test
    void testPossibleMovesOutOfBounds() {
        ChessBoard board = new ChessBoard();
        ChessPiece whitePawn = board.getPieceAt(1, 1);
        ChessPiece blackPawn = board.getPieceAt(6, 1);
       
        // Placing white pawn at the edge
        board.deletePiece(7,1);
        board.placePiece(whitePawn,7,1);

        // The pawn is at the edge of the board, so it should have no possible moves
        assertEquals(0, whitePawn.possibleMoves(board).size());

        // Placing black Pawn at the edge
        board.deletePiece(0,1);
        board.placePiece(blackPawn,0,1);

        // The pawn is at the edge of the board, so it should have no possible moves
        assertEquals(0, blackPawn.possibleMoves(board).size());
    }

    @Test
    public void testValidMoveWhitePawn() {
        ChessBoard board = new ChessBoard();
        ChessPiece[][] chessBoard = board.getBoard();
    }


}