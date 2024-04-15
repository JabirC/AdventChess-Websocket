package adventchess.chessgame.logic;
import java.util.ArrayList;
import java.util.List;
import adventchess.chessgame.model.*;
import java.util.Scanner;
import java.util.Random;

public class ChessGame {
    private ChessBoard board;
    private Player playerWhite;
    private Player playerBlack;
    private Player currentPlayer;
    private String gameId;

    public ChessGame(String gameId, String playerWhiteName, String playerBlackName) {
        this.board = new ChessBoard();
        this.gameId = gameId;
        playerWhite = new Player(playerWhiteName, "White");
        playerBlack = new Player(playerBlackName, "Black");
        currentPlayer = playerWhite;
    }

    public ChessGame(String gameId, String playerWhiteName, String playerBlackName, String[][] initialState) {
        this.board = new ChessBoard(initialState);
        this.gameId = gameId;
        playerWhite = new Player(playerWhiteName, "White");
        playerBlack = new Player(playerBlackName, "Black");
        currentPlayer = playerWhite;
    }

    public boolean makeMove(String color, int fromRow, int fromCol, int toRow, int toCol) {
        // There is a piece at the square and move is valid
        if (MoveValidator.isValidMove(board, fromRow, fromCol, toRow, toCol)) {
            ChessPiece piece = board.getPieceAt(fromRow, fromCol);

            // Piece is of the same color as the player making the move
            if(piece.getColor().equals(color)){
                ChessPiece deleted = board.movePiece(piece, new int[]{toRow, toCol});

                if(isChecked(color)){
                    board.reverseMove(deleted, new int[]{toRow, toCol}, new int[]{fromRow, fromCol});
                    return false;
                }
                // A piece was eaten as a result of this move
                if(deleted != null){
                    Player player = color.equals("White")? playerWhite : playerBlack;
                    player.getPiecesEaten().add(deleted);
                }

                return true;
            }
        }
        return false;
    }

    public boolean isChecked(String color){
        ChessPiece king = board.getKing(color);
        int[] kingPosition = king.getCurrentPosition();
        String opponentColor = color.equals("White") ? "Black" : "White";

        // Check if any opponent piece can attack the king's position
        for(ChessPiece opponentPiece: board.getPieces(opponentColor)){
            if(opponentPiece.isValidMove(board, kingPosition[0], kingPosition[1])){
                return true;
            }
        }
        return false;
    }

    public boolean isCheckMated(String color){
        // Player is in check
        if(isChecked(color)){
            // King cannot make a move
            if(!hasLegalKingMoves(color)){
                // No piece can make a move
                if(!hasLegalPieceMoves(color)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isStaleMate(String color){
        // Check if the player is in check
        if(isChecked(color)){
            return false;
        }

        // Check if the player has no legal moves
        if(!hasLegalPieceMoves(color)){
            return true;
        }

        // If neither condition is met, it's not a stalemate
        return false;
    }

    public boolean hasLegalKingMoves(String color){
        ChessPiece king = board.getKing(color);
        int[] currentPosition = king.getCurrentPosition();
        List<int[]> possibleMoves = king.possibleMoves(board);

        // Check if move will lead to a check
        for(int[] move: possibleMoves){
            // Simulate the move
            ChessPiece deleted = board.movePiece(king, move);

            // Check if the king is not in check after the move
            if(!isChecked(color)){
                // Undo the move 
                board.reverseMove(deleted, move, currentPosition);
                return true;
            }

            // Undo the move as it didn't lead to a legal move
            board.reverseMove(deleted, move, currentPosition);
        }
        return false;
    }

    public boolean hasLegalPieceMoves(String color){
        ArrayList<ChessPiece> pieces = board.getPieces(color);
        
        for(ChessPiece piece: pieces){
            int[] currentPosition = piece.getCurrentPosition();
            for(int[] move: piece.possibleMoves(board)){
                // Simulate the move
                ChessPiece deleted = board.movePiece(piece, move);
    
                // Check if the king is not in check after the move
                if(!isChecked(color)){
                    // Undo the move 
                    board.reverseMove(deleted, move, currentPosition);
                    return true;
                }
    
                // Undo the move as it didn't lead to a legal move
                board.reverseMove(deleted, move, currentPosition);
            }
        }
        return false;
    }

    // Getters
    public ChessBoard getBoard(){
        return board;
    }

    public Player getPlayer(String color){
        Player player = color.equals("White")? playerWhite : playerBlack;
        return player;
    }

    public void switchTurns() {
        // Switch turns between players
        currentPlayer = (currentPlayer == playerWhite) ? playerBlack: playerWhite;
    }

    private void displayBoard() {
        ChessPiece[][] boardArray = board.getBoard();
        
        System.out.println(" +-------------------------");
        
        for (int i = 7; i >= 0; i--) {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = boardArray[i][j];
                String pieceString = getPieceString(piece);
                System.out.print(" " + pieceString);
            }
            System.out.println();
        }
        
        System.out.println(" +-------------------------");
        System.out.println("   a  b  c  d  e  f  g  h");
    }
    
    private String getPieceString(ChessPiece piece) {
        if (piece == null) {
            return "--";
        }
    
        switch (piece.getColor()) {
            case "White":
                switch (piece.getName()) {
                    case "King":
                        return "WK";
                    case "Queen":
                        return "WQ";
                    case "Rook":
                        return "WR";
                    case "Bishop":
                        return "WB";
                    case "Knight":
                        return "WN";
                    case "Pawn":
                        return "WP";
                }
                break;
            case "Black":
                switch (piece.getName()) {
                    case "King":
                        return "BK";
                    case "Queen":
                        return "BQ";
                    case "Rook":
                        return "BR";
                    case "Bishop":
                        return "BB";
                    case "Knight":
                        return "BN";
                    case "Pawn":
                        return "BP";
                }
                break;
        }
    
        return "--"; // Default if no match is found
    }

    private int[][] getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            try {
                System.out.print("Enter your move (e.g., 'e2 e4'): ");
                String input = scanner.nextLine().trim();
                // Split the input into two parts
                String[] parts = input.split(" ");
                
                if (parts.length == 2) {
                    // Parse the coordinates and create a PlayerMove object
                    int[] from = parseCoordinates(parts[0]);
                    int[] to = parseCoordinates(parts[1]);

                    int[][] ret = new int[2][];
                    ret[0] = from;
                    ret[1] = to;

                    System.out.print(ret[0][0]);
                    System.out.print(ret[0][1]);
                    System.out.print(ret[1][0]);
                    System.out.print(ret[1][1]);
                    return ret;
                } else {
                    System.out.println("Invalid input. Please enter two space-separated coordinates.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input format. Please try again.");
            }
        }
    }
    
    private int[] parseCoordinates(String input) {
        int[] coordinates = new int[2];
        coordinates[1] = input.charAt(0) - 'a'; // Convert column (file) to index
        coordinates[0] = input.charAt(1) - '1'; // Convert row (rank) to index
        return coordinates;
    }

    public void startGame() {
        // Game loop
        while (true) {
            // Display the current game state
            displayBoard();
            String color = currentPlayer.getColor();

            // Check for game over conditions
            if (isCheckMated(color)) {
                System.out.println(color + " has lost");
                break;
            }

            if(isStaleMate(color)){
                System.out.println("Tie due to StaleMate");
                break;
            }

            // Get player input
            int[][] move = getPlayerMove();

            if(!makeMove(color, move[0][0], move[0][1], move[1][0], move[1][1])){

                continue;
            }

            // Switch turns
            switchTurns();
        }
    }

    // public Boolean verifyMove(String playerName, )

    // Creates a random initial board state
    public static String[][] createRandom(){
        String[][] board = new String[8][8];
        Random random = new Random();

        String[] pieces = {"R", "N", "B", "Q"};


        for (int i = 0; i < 8; i++) {
            // Loop through each column in the current row
            for (int j = 0; j < 8; j++) {
                board[i][j] = "--";
            }
        }

        // Initialize Kings 
        board[0][4] = "BK";
        board[7][4] = "WK";

        // Initialize a random amount of pawns
        for(int i=0; i < 8; i++){
            if(random.nextBoolean()){
                board[1][i] = "BP";
                board[6][i] = "WP";
            }
        }

        // Initalize main pieces
        for(int i=0; i < 8; i++){
            if(i==4) continue;
            if(random.nextBoolean()){
                int pieceIndex = random.nextInt(4);
                String piece = pieces[pieceIndex];

                board[0][i] = "B" + piece;
                board[7][i] = "W" + piece;

                if(pieceIndex == 0 || pieceIndex == 3){
                    board[1][i] = "BP";
                    board[6][i] = "WP";
                }
            }
        }

        return board;

    }

    public String getOpponentName(String session){
        if(playerWhite.getName().equals(session)){
            return playerBlack.getName();
        }
        else{
            return playerWhite.getName();
        }
    }

    public boolean isCurrentPlayer(String session){
        return currentPlayer.getName().equals(session);
    }

    public String[][] getStringBoard(){
        return board.getStringBoard();
    }

    public String getPlayerColor(String session){
        if(playerWhite.getName().equals(session)){
            return "White";
        }
        else{
            return "Black";
        }
    }

    public String getOpponentColor(String session){
        if(playerWhite.getName().equals(session)){
            return "Black";
        }
        else{
            return "White";
        }
    }

    public String getGameId(){
        return gameId;
    }
    

}