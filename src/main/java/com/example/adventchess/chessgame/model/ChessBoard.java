package adventchess.chessgame.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessBoard {
    private ChessPiece[][] board;
    private ArrayList<ChessPiece> whitePieces;
    private ArrayList<ChessPiece> blackPieces;
    private ChessPiece whiteKing;
    private ChessPiece blackKing;

    // Initialize to new game state
    public ChessBoard() {
        // Initialize the chess board
        this.board = new ChessPiece[8][8];
        this.whitePieces = new ArrayList<>();
        this.blackPieces = new ArrayList<>();
        initializeBoard();
    }

    // Initialize to specific game state
    public ChessBoard(String[][] initialState){
        // Initialize the chess board
        this.board = new ChessPiece[8][8];
        this.whitePieces = new ArrayList<>();
        this.blackPieces = new ArrayList<>();  
        initializeBoard(initialState);
    }

    private void initializeBoard() {
        // Place white pieces
        placePiece(new Rook("WR", "White", 0, 0), 0, 0);
        placePiece(new Knight("WN", "White", 0, 1), 0, 1);
        placePiece(new Bishop("WB", "White", 0, 2), 0, 2);
        placePiece(new Queen("WQ", "White", 0, 3), 0, 3);
        placePiece(new Bishop("WB", "White", 0, 5), 0, 5);
        placePiece(new Knight("WN", "White", 0, 6), 0, 6);
        placePiece(new Rook("WR", "White", 0, 7), 0, 7);
        ChessPiece whiteKing = new King("WK", "White", 0, 4);
        placePiece(whiteKing, 0, 4);
        this.whiteKing = whiteKing;

        for (int i = 0; i < 8; i++) {
            placePiece(new Pawn("WP", "White", 1, i), 1, i);
        }

        for (int i = 0; i < 2; i++) {
            for(int j = 0; j < 8; j++){
                ChessPiece piece = getPieceAt(i,j);
                whitePieces.add(piece);
            }
        }

        // Place black pieces
        placePiece(new Rook("BR", "Black", 7, 0), 7, 0);
        placePiece(new Knight("BN", "Black", 7, 1), 7, 1);
        placePiece(new Bishop("BB", "Black", 7, 2), 7, 2);
        placePiece(new Queen("BQ", "Black", 7, 3), 7, 3);
        placePiece(new Bishop("BB", "Black", 7, 5), 7, 5);
        placePiece(new Knight("BN", "Black", 7, 6), 7, 6);
        placePiece(new Rook("BR", "Black", 7, 7), 7, 7);
        ChessPiece blackKing = new King("BK", "Black", 7, 4);
        placePiece(blackKing, 7, 4);
        this.blackKing = blackKing;

        for (int i = 0; i < 8; i++) {
            placePiece(new Pawn("BP","Black", 6, i), 6, i);
        }

        for (int i = 7; i > 5; i--) {
            for(int j = 0; j < 8; j++){
                ChessPiece piece = getPieceAt(i,j);
                blackPieces.add(piece);
            }
        }



    }

    private void initializeBoard(String[][] initialState) {
        if (initialState.length != 8 || initialState[0].length != 8) {
            throw new IllegalArgumentException("Invalid board state dimensions");
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String pieceSymbol = initialState[i][j];
    
                switch (pieceSymbol) {
                    case "WR":
                        Rook whiteRook = new Rook("WR", "White", 7 - i, j);
                        placePiece(whiteRook, 7 - i, j);
                        whitePieces.add(whiteRook);
                        break;
                    case "WN":
                        Knight whiteKnight = new Knight("WN", "White", 7 - i, j);
                        placePiece(whiteKnight, 7 - i, j);
                        whitePieces.add(whiteKnight);
                        break;
                    case "WB":
                        Bishop whiteBishop = new Bishop("WB", "White", 7 - i, j);
                        placePiece(whiteBishop, 7 - i, j);
                        whitePieces.add(whiteBishop);
                        break;
                    case "WQ":
                        Queen whiteQueen = new Queen("WQ", "White", 7 - i, j);
                        placePiece(whiteQueen, 7 - i, j);
                        whitePieces.add(whiteQueen);
                        break;
                    case "WK":
                        ChessPiece whiteKing = new King("WK", "White", 7 - i, j);
                        placePiece(whiteKing, 7 - i, j);
                        this.whiteKing = whiteKing;
                        whitePieces.add(whiteKing);
                        break;
                    case "WP":
                        Pawn whitePawn = new Pawn("WP", "White", 7 - i, j);
                        placePiece(whitePawn, 7 - i, j);
                        whitePieces.add(whitePawn);
                        break;
                    case "BR":
                        Rook blackRook = new Rook("BR", "Black", 7 - i, j);
                        placePiece(blackRook, 7 - i, j);
                        blackPieces.add(blackRook);
                        break;
                    case "BN":
                        Knight blackKnight = new Knight("BN", "Black", 7 - i, j);
                        placePiece(blackKnight, 7 - i, j);
                        blackPieces.add(blackKnight);
                        break;
                    case "BB":
                        Bishop blackBishop = new Bishop("BB", "Black", 7 - i, j);
                        placePiece(blackBishop, 7 - i, j);
                        blackPieces.add(blackBishop);
                        break;
                    case "BQ":
                        Queen blackQueen = new Queen("BQ", "Black", 7 - i, j);
                        placePiece(blackQueen, 7 - i, j);
                        blackPieces.add(blackQueen);
                        break;
                    case "BK":
                        ChessPiece blackKing = new King("BK", "Black", 7 - i, j);
                        placePiece(blackKing, 7 - i, j);
                        this.blackKing = blackKing;
                        blackPieces.add(blackKing);
                        break;
                    case "BP":
                        Pawn blackPawn = new Pawn("BP", "Black", 7 - i, j);
                        placePiece(blackPawn, 7 - i, j);
                        blackPieces.add(blackPawn);
                        break;
                    // Handle other piece symbols or empty squares as needed
                    default:
                        // Handle unknown piece symbols or empty squares
                        break;
                }
            }
        }
    }

    // Method to place a chess piece on the board
    public void placePiece(ChessPiece piece, int row, int column) {
        // Check if the position is within the board boundaries
        if (isValidPosition(row, column)) {
            // Check if the square is empty before placing the piece
            if (board[row][column] == null) {
                // Remove piece from old position
                int[] curPosition = piece.getCurrentPosition();
                board[curPosition[0]][curPosition[1]] = null;
                // Update the piece's current position
                board[row][column] = piece;
                piece.setCurrentPosition(row, column);
            } else {
                // throw an exception (square is occupied)
                throw new IllegalStateException("Square is already occupied: Cannot place the piece");
            }
        } else {
            // throw an exception (invalid position)
            throw new IllegalArgumentException("Invalid position: Position is outside the board boundaries");
        }
    }

    // Method to remove a chess piece on the board
    public ChessPiece deletePiece(int row, int col){
        // Check if the position is within the board boundaries
        if (isValidPosition(row, col)) {
            ChessPiece piece = getPieceAt(row, col);
            // Check if a piece is at the position
            if(piece != null){
                String color = piece.getColor();
                if(color.equals("White")){
                    whitePieces.remove(piece);
                }
                else{
                    blackPieces.remove(piece);
                }
                board[row][col] = null;
                return piece;
            } else{
                // throw an exception (No pieces at position)
                throw new IllegalStateException("Square is not occupied: Cannot delete piece at unoccupied square");
            }
        } else {
            // throw an exception (invalid position)
            throw new IllegalArgumentException("Invalid position: Position is outside the board boundaries");
        }
    }

    public ChessPiece movePiece(ChessPiece piece, int[] to){
        ChessPiece pieceAtDestination = getPieceAt(to[0], to[1]);

        // If no pieces at destination
        if(pieceAtDestination == null){
            placePiece(piece, to[0], to[1]);
        }
        // If destination square is occupied
        else{
            deletePiece(to[0], to[1]);
            placePiece(piece, to[0], to[1]);
        }
        // Return the deleted piece or null if square was unoccupied
        return pieceAtDestination;
    }

    // Reverse a move made using the movePiece method
    public void reverseMove(ChessPiece deletedPiece, int[] to, int[] from){
        ChessPiece movedPiece = getPieceAt(to[0], to[1]);
        // Place moved piece back at its original square
        placePiece(movedPiece, from[0], from[1]);

        // If a piece was deleted by the move, reinstate it back to the board
        if(deletedPiece != null){
            placePiece(deletedPiece, to[0], to[1]);
            String color = deletedPiece.getColor();
            if(color.equals("White")){
                whitePieces.add(deletedPiece);
            }
            else{
                blackPieces.add(deletedPiece);
            }
        }
    }

    // Method to check if a position is within the board boundaries
    public static boolean isValidPosition(int row, int column) {
        return row >= 0 && row < 8 && column >= 0 && column < 8;
    }

    // Helper method to check if a specific move is present in the list
    public static boolean containsMove(List<int[]> moves, int[] targetMove) {
        for (int[] move : moves) {
            if (Arrays.equals(move, targetMove)) {
                return true;
            }
        }
        return false;
    }


    // Getter method to retrieve the current state of the board
    public ChessPiece[][] getBoard() {
        return board;
    }

    // Get all the pieces of a certain color
    public ArrayList<ChessPiece> getPieces(String color){
        if(color.equals("White")){
            return whitePieces;
        }
        else {
            return blackPieces;
        }
    }

    // Get the King of a certain color
    public ChessPiece getKing(String color){
        if(color.equals("White")){
            return whiteKing;
        }
        else {
            return blackKing;
        }
    }

    // Get piece at a certain square
    public ChessPiece getPieceAt(int fromRow, int fromCol){
        if(board[fromRow][fromCol] == null){
            return null;
        }
        else{
            return board[fromRow][fromCol];
        }
    }

    public String[][] getStringBoard(){
        String[][] stringBoard = new String[8][8];
        for (int i = 0; i < stringBoard.length; i++) {
            for (int j = 0; j < stringBoard[i].length; j++) {
                if(board[i][j] == null){
                    stringBoard[i][j] = "--";
                }
                else{
                    stringBoard[i][j] = board[i][j].getName();
                }
            }
            System.out.println();
        }
        return stringBoard;
    }
}