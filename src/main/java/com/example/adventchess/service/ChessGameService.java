package com.example.adventchess.service;

import adventchess.chessgame.logic.ChessGame;
import com.example.adventchess.model.GameStateMessage;
import com.example.adventchess.model.MoveMessage;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Random;

import com.google.gson.Gson;


@Service
public class ChessGameService {

    private final Map<String, ChessGame> userGameMap = new HashMap<>();
    private final Map<String, String> rematchMap = new HashMap<>();

    @Autowired
    private final SimpMessageSendingOperations messagingTemplate;

    public ChessGameService(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Creates a new game session between two players.
     * 
     * This method creates a new game session between two players by generating a unique
     * game identifier (UUID) and initializing a new ChessGame instance with the specified
     * players and game mode. The initial chess board setup is either classic or randomly
     * generated based on the game mode.
     * After creating the game instance, it stores it in the userGameMap for both players.
     * It then sends a message to each player to notify them of the start of the game,
     * including the initial game state.
     * 
     * @param session1 The session identifier of the first player.
     * @param session2 The session identifier of the second player.
     * @param mode The mode of the game (e.g., "classic" or "adventure").
     */
    public void createGameSession(String session1, String session2, String mode) {
        String gameId = UUID.randomUUID().toString();
        ChessGame chessGame;
        String[][] board = {
            {"BR", "BN", "BB", "BQ", "BK", "BB", "BN", "BR"},
            {"BP", "BP", "BP", "BP", "BP", "BP", "BP", "BP"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "WP", "WP", "WP", "WP", "WP"},
            {"WR", "WN", "WB", "WQ", "WK", "WB", "WN", "WR"}
        };

        // Randomize white/black player
        Random random = new Random();
        Boolean isFirstSessionWhite = random.nextBoolean();

        if(mode.equals("classic")){
            if(isFirstSessionWhite){
                chessGame = new ChessGame(gameId, session1, session2);
            }
            else{
                chessGame = new ChessGame(gameId, session2, session1);
            }
        }
        else {
            // Randomized intial board state
            board = ChessGame.createRandom();
            if(isFirstSessionWhite){
                chessGame = new ChessGame(gameId, session1, session2, board);
            }
            else{
                chessGame = new ChessGame(gameId, session2, session1, board);
            }
        }

        // Store the ChessGame instance associated with each user's session ID
        userGameMap.put(session1, chessGame);
        userGameMap.put(session2, chessGame);


        // Send a message to each user to notify the start of the game

        Gson gson = new Gson();
        String messageSession1 = gson.toJson(new GameStateMessage(gameId, chessGame.getStringBoard(), isFirstSessionWhite, isFirstSessionWhite, new MoveMessage(-1, -1, -1, -1)));
        String messageSession2 = gson.toJson(new GameStateMessage(gameId, chessGame.getStringBoard(), !isFirstSessionWhite, !isFirstSessionWhite, new MoveMessage(-1, -1, -1, -1)));

        messagingTemplate.convertAndSend("/topic/reply" + session1, messageSession1);
        messagingTemplate.convertAndSend("/topic/reply" + session2, messageSession2);
    }

    /**
     * Initiates a rematch for a chess game.
     * 
     * This method starts a rematch for the specified game by creating a new ChessGame instance
     * with the same players and either the classic chess board setup or a randomly generated board.
     * The color of the players is determined randomly, and the game mode (classic or adventure) 
     * influences the board setup.
     * After creating the new game instance, it stores it in the userGameMap for both players.
     * It then sends a message to each player to notify them of the start of the rematch,
     * including the initial game state.
     * 
     * @param gameId The unique identifier of the game to be rematched.
     * @param session1 The session identifier of one of the players.
     * @param mode The mode of the game (e.g., "classic" or "adventure").
     */
    public void startRematch(String gameId, String session1, String mode) {
        String session2 = rematchMap.get(gameId);
        rematchMap.remove(gameId);
        ChessGame chessGame;
        String[][] board = {
            {"BR", "BN", "BB", "BQ", "BK", "BB", "BN", "BR"},
            {"BP", "BP", "BP", "BP", "BP", "BP", "BP", "BP"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "WP", "WP", "WP", "WP", "WP"},
            {"WR", "WN", "WB", "WQ", "WK", "WB", "WN", "WR"}
        };
        Random random = new Random();
        Boolean isFirstSessionWhite = random.nextBoolean();

        if(mode.equals("\"classic\"")){
            if(isFirstSessionWhite){
                chessGame = new ChessGame(gameId, session1, session2);
            }
            else{
                chessGame = new ChessGame(gameId, session2, session1);
            }
        }
        else {
            board = ChessGame.createRandom();
            if(isFirstSessionWhite){
                chessGame = new ChessGame(gameId, session1, session2, board);
            }
            else{
                chessGame = new ChessGame(gameId, session2, session1, board);
            }
        }

        // Store the ChessGame instance associated with each user's session ID
        userGameMap.put(session1, chessGame);
        userGameMap.put(session2, chessGame);

        // Send a message to each user to notify the start of the game

        Gson gson = new Gson();
        String messageSession1 = gson.toJson(new GameStateMessage(gameId, chessGame.getStringBoard(), isFirstSessionWhite, isFirstSessionWhite, new MoveMessage(-1, -1, -1, -1)));
        String messageSession2 = gson.toJson(new GameStateMessage(gameId, chessGame.getStringBoard(), !isFirstSessionWhite, !isFirstSessionWhite, new MoveMessage(-1, -1, -1, -1)));
        // String message = String.format("{\"gameId\" : \"%s\"}", gameId);

        messagingTemplate.convertAndSend("/topic/reply" + session1, messageSession1);
        messagingTemplate.convertAndSend("/topic/reply" + session2, messageSession2);
    }


    /**
     * Verifies and processes a move made by a player in the chess game.
     * 
     * This method is called when a player attempts to make a move in the game.
     * It verifies if it's the player's turn to make a move, then attempts to make the move.
     * If the move is valid, it switches the turn to the opponent.
     * It then sends the result of the move attempt to both players.
     * If the move results in a game ending state (checkmate or stalemate), it sends
     * a terminal message to both players indicating the result of the game.
     * 
     * @param session The session identifier of the player making the move.
     * @param gameId The unique identifier of the game.
     * @param move The move made by the player (contains source and destination coordinates).
     */
    public void verifyMove(String session, String gameId, MoveMessage move){
        ChessGame game = userGameMap.get(session);
        Gson gson = new Gson();

        // Check if it's this user's turn to make a move
        if(game.isCurrentPlayer(session)){
            // Attempt to make the move
            boolean isValidMove = game.makeMove(game.getPlayerColor(session), move.getFromRow(), move.getFromCol(), move.getToRow(), move.getToCol());
            if(isValidMove){
                game.switchTurns();
            }
            else{
                move = new MoveMessage(-1, -1, -1, -1);
            }

            // Send back the result of the move attempt to both users
            String messageSession1 = gson.toJson(new GameStateMessage(gameId, game.getStringBoard(), false, !isValidMove, move));
            String messageSession2 = gson.toJson(new GameStateMessage(gameId, game.getStringBoard(), false, isValidMove, move));
            messagingTemplate.convertAndSend("/topic/state" + gameId + session, messageSession1);
            messagingTemplate.convertAndSend("/topic/state" + gameId + game.getOpponentName(session), messageSession2);


            if(isValidMove){
                // Check if move resulted in a game ending state

                String opponentColor = game.getOpponentColor(session);

                if (game.isCheckMated(opponentColor)) {
                    String terminalMessage = String.format("{\"result\" : \"%s Wins!\",\"condition\" : \"Checkmate\" }", game.getPlayerColor(session));
                    messagingTemplate.convertAndSend("/topic/state" + gameId, terminalMessage);
                }

                if(game.isStaleMate(opponentColor)){
                    String terminalMessage = String.format("{\"result\" : \"Stalemate\",\"condition\" : \"%s\" }", game.getPlayerColor(session));
                    messagingTemplate.convertAndSend("/topic/state" + gameId, terminalMessage);
                }
            }
        }
        else{
            // Not the user's turn to make a move, send back old game state
            String messageSession1 = gson.toJson(new GameStateMessage(gameId, game.getStringBoard(), false, false, new MoveMessage(-1,-1,-1,-1)));
            messagingTemplate.convertAndSend("/topic/state" + gameId + session, messageSession1);
        }
    }


    /**
     * Handles disconnection of a player from the game.
     * 
     * This method is called when a player disconnects from the game.
     * It retrieves the ChessGame object associated with the player's session from the userGameMap.
     * If a game is found, it checks if a rematch is ongoing for the game and removes it from the rematchMap if present.
     * It then constructs a terminal message indicating the result of the game (e.g., winner, reason for termination)
     * and sends it to the appropriate topic using messagingTemplate.
     * If the reason for disconnection is not "Resignation", it removes the game from the userGameMap.
     * 
     * @param session The session identifier of the player who disconnected.
     * @param reason The reason for disconnection (e.g., "Resignation", "Disconnection").
     */
    public void handleDisconnect(String session, String reason){
        ChessGame game = userGameMap.get(session);
        if(game != null){
            if(rematchMap.containsKey(game.getGameId())){
                rematchMap.remove(game.getGameId());
            }
            String opponent = game.getOpponentName(session);
            String terminalMessage = String.format("{\"result\" : \"%s Wins!\",\"condition\" : \"%s\" }", game.getOpponentColor(session), reason);
            messagingTemplate.convertAndSend("/topic/state" + game.getGameId(), terminalMessage);
            if(!reason.equals("Resignation")){
                removeGame(session);
            }
        }
    }



    /**
     * Handles a rematch request for a game.
     * 
     * This method is called when a player requests a rematch for a specific game.
     * It checks if the player's session has an existing game session in the userGameMap.
     * If the session is associated with an existing game, it further checks if a rematch
     * has already been initiated for the specified gameId. If a rematch request exists for
     * the game, it starts the rematch process. Otherwise, it adds the gameId to the
     * rematchMap to indicate that a rematch request has been made.
     * 
     * @param gameId The unique identifier of the game for which a rematch is requested.
     * @param session The session identifier of the player requesting the rematch.
     * @param mode The mode of the game (e.g., classic, adventure).
     */
    public void handleRematch(String gameId, String session, String mode){
        if(userGameMap.containsKey(session)){
            if(rematchMap.containsKey(gameId)){
                startRematch(gameId, session, mode);
            }
            else{
                rematchMap.put(gameId, session);
            }
        }
    }


    /**
     * Handles the removal of a game after its completion or a player disconnection
     *
     * @param session The session identifier of the player that has disconnected
     */
    public void removeGame(String session){
        ChessGame game = userGameMap.get(session);
        if(game != null){
            String opponent = game.getOpponentName(session);
            userGameMap.remove(session);
            userGameMap.remove(opponent);
        }
    }

}