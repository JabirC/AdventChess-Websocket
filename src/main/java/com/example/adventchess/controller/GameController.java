package com.example.adventchess;

import java.util.UUID;
import java.security.Principal;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CompletableFuture;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.example.adventchess.service.ChessGameService;
import com.example.adventchess.model.MoveMessage;
import com.example.adventchess.model.GameCreation;


@Controller
public class GameController {
  private final BlockingQueue<String> sessionQueueClassic5 = new LinkedBlockingQueue<>();
  private final BlockingQueue<String> sessionQueueClassic10 = new LinkedBlockingQueue<>();
  private final BlockingQueue<String> sessionQueueClassic20 = new LinkedBlockingQueue<>();
  private final BlockingQueue<String> sessionQueueAdventure5 = new LinkedBlockingQueue<>();
  private final BlockingQueue<String> sessionQueueAdventure10 = new LinkedBlockingQueue<>();
  private final BlockingQueue<String> sessionQueueAdventure20 = new LinkedBlockingQueue<>();
  private final ExecutorService executorService = Executors.newCachedThreadPool();
  private final Set<String> pings = new HashSet<>();

  private final Map<String, Long> totalMatchMakingTimeClassic5 = new HashMap<>();
  private final Map<String, Long> totalMatchMakingTimeClassic10 = new HashMap<>();
  private final Map<String, Long> totalMatchMakingTimeClassic20 = new HashMap<>();
  private final Map<String, Long> totalMatchMakingTimeAdventure5 = new HashMap<>();
  private final Map<String, Long> totalMatchMakingTimeAdventure10 = new HashMap<>();
  private final Map<String, Long> totalMatchMakingTimeAdventure20 = new HashMap<>();

  private final Map<String, Integer> matchedPlayersClassic5 = new HashMap<>();
  private final Map<String, Integer> matchedPlayersClassic10 = new HashMap<>();
  private final Map<String, Integer> matchedPlayersClassic20 = new HashMap<>();
  private final Map<String, Integer> matchedPlayersAdventure5 = new HashMap<>();
  private final Map<String, Integer> matchedPlayersAdventure10 = new HashMap<>();
  private final Map<String, Integer> matchedPlayersAdventure20 = new HashMap<>();

  SimpMessagingTemplate simpMessagingTemplate;
  ChessGameService chessGameService;

  @Autowired
  public GameController(SimpMessagingTemplate simpMessagingTemplate, ChessGameService chessGameService){
    this.simpMessagingTemplate = simpMessagingTemplate;
    this.chessGameService = chessGameService;

    // Start asynchronous background thread to handle matchmaking
    startQueueProcessor(sessionQueueClassic5, "classic", 5);
    startQueueProcessor(sessionQueueClassic10, "classic", 10);
    startQueueProcessor(sessionQueueClassic20, "classic", 20);
    startQueueProcessor(sessionQueueAdventure5, "adventure", 5);
    startQueueProcessor(sessionQueueAdventure10, "adventure", 10);
    startQueueProcessor(sessionQueueAdventure20, "adventure", 20);
  }

 /**
  *  Place players in queue depending on game mode
  */
  @MessageMapping("/connect/game")
  public void handleConnect(Principal principal, SimpMessageHeaderAccessor headerAccessor, GameCreation params) {
      String session = principal.getName();
      if (session != null) {
          if (params.getMode().equals("classic")) {
              if (params.getTime() == 5) {
                  sessionQueueClassic5.add(session);
              } else if (params.getTime() == 10) {
                  sessionQueueClassic10.add(session);
              } else if (params.getTime() == 20) {
                  sessionQueueClassic20.add(session);
              } else {
                  System.out.println("Invalid Time");
              }
          } else if (params.getMode().equals("adventure")) {
              if (params.getTime() == 5) {
                  sessionQueueAdventure5.add(session);
              } else if (params.getTime() == 10) {
                  sessionQueueAdventure10.add(session);
              } else if (params.getTime() == 20) {
                  sessionQueueAdventure20.add(session); // Fixed typo
              } else {
                  System.out.println("Invalid Time");
              }
          } else {
              System.out.println("Invalid game mode");
          }
      }
  }

 /**
  *  Handle multiple types of player disconnections such as page reload, component destruction, and resignation
  */
  @MessageMapping("/disconnect")
  public void disconnect(Principal principal, String reason) {
      String session = principal.getName();
      chessGameService.handleDisconnect(session, reason);
  }

 /**
  *  Handles rematch functionality
  */
  @MessageMapping("/game/{gameId}/rematch")
  public void handleRematch(@DestinationVariable String gameId, Principal principal, String mode) {
      String session = principal.getName();
      chessGameService.handleRematch(gameId, session, mode);
  }

 /**
  *  Verification of player moves within a game
  */
  @MessageMapping("/game/{gameId}/move")
  public void handleMove(@DestinationVariable String gameId, Principal principal, MoveMessage moveMessage) {
        String session = principal.getName();
        chessGameService.verifyMove(session, gameId, moveMessage);
  }

 /**
  *  Player requested time check
  */
  @MessageMapping("/game/{gameId}/time")
  public void handleTimeCheck(@DestinationVariable String gameId, Principal principal) {
        String session = principal.getName();
        chessGameService.verifyTime(session, gameId);
  }
  
 /**
  *  Used to check for user availabity when matching user for a new game
  */
  @MessageMapping("/pong")
  public void handlePing(Principal principal, String message) {
        String session = principal.getName();
        pings.add(session);
  }

 /**
  *  Background asynchronous thread that handles matchmaking for a given game queue
  */
  private void startQueueProcessor(BlockingQueue<String> sessionQueue, String mode, int time) {
    Map<String, Long> totalMatchMakingTime;
    Map<String, Integer> matchedPlayers;

    // Select the appropriate maps based on the mode and time
    if (mode.equals("classic")) {
        if (time == 5) {
            totalMatchMakingTime = totalMatchMakingTimeClassic5;
            matchedPlayers = matchedPlayersClassic5;
        } else if (time == 10) {
            totalMatchMakingTime = totalMatchMakingTimeClassic10;
            matchedPlayers = matchedPlayersClassic10;
        } else {
            totalMatchMakingTime = totalMatchMakingTimeClassic20;
            matchedPlayers = matchedPlayersClassic20;
        }
    } else {
        if (time == 5) {
            totalMatchMakingTime = totalMatchMakingTimeAdventure5;
            matchedPlayers = matchedPlayersAdventure5;
        } else if (time == 10) {
            totalMatchMakingTime = totalMatchMakingTimeAdventure10;
            matchedPlayers = matchedPlayersAdventure10;
        } else {
            totalMatchMakingTime = totalMatchMakingTimeAdventure20;
            matchedPlayers = matchedPlayersAdventure20;
        }
    }

    executorService.execute(() -> {
        while (true) {
            try {
                List<String> sessions = new ArrayList<>();
                // Take a batch of players from the queue
                String firstSession = sessionQueue.take();
                long startTime = System.currentTimeMillis();
                sessions.add(firstSession);
                sessionQueue.drainTo(sessions, 9); // Drain up to 10 players

                // Perform heartbeat checks asynchronously for the batch
                List<CompletableFuture<Boolean>> heartbeatChecks = new ArrayList<>();
                for (String session : sessions) {
                    heartbeatChecks.add(checkHeartbeatAsync(session));
                }

                // Wait for all heartbeat checks to complete
                CompletableFuture.allOf(heartbeatChecks.toArray(new CompletableFuture[0])).join();

                // Process the results
                Iterator<String> sessionsIterator = sessions.iterator();
                for (CompletableFuture<Boolean> heartbeatCheck : heartbeatChecks) {
                    boolean result = heartbeatCheck.get();
                    String session = sessionsIterator.next();

                    if (result) {
                        // This player is connected, find a match
                        String otherSession = sessionQueue.take();
                        if (otherSession != null) {
                            long endTime = System.currentTimeMillis();
                            long matchMakingTime = endTime - startTime;

                            // Update total match-making time and matched players count
                            totalMatchMakingTime.put(session, totalMatchMakingTime.getOrDefault(session, 0L) + matchMakingTime);
                            matchedPlayers.put(session, matchedPlayers.getOrDefault(session, 0) + 1);

                            chessGameService.createGameSession(session, otherSession, mode, time);
                            System.out.println("Avg mm time (" + mode + ":" + time +"min) : " + getAverageMatchMakingTime(mode, time));
                        } else {
                            // No match found, put the player back into the queue
                            sessionQueue.add(session);
                        }
                    } else {
                        // This player is not connected, do nothing
                    }
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    });
}

public double getAverageMatchMakingTime(String mode, int time) {
    Map<String, Long> totalMatchMakingTime;
    Map<String, Integer> matchedPlayers;

    // Select the appropriate maps based on the mode and time
    if (mode.equals("classic")) {
        if (time == 5) {
            totalMatchMakingTime = totalMatchMakingTimeClassic5;
            matchedPlayers = matchedPlayersClassic5;
        } else if (time == 10) {
            totalMatchMakingTime = totalMatchMakingTimeClassic10;
            matchedPlayers = matchedPlayersClassic10;
        } else {
            totalMatchMakingTime = totalMatchMakingTimeClassic20;
            matchedPlayers = matchedPlayersClassic20;
        }
    } else {
        if (time == 5) {
            totalMatchMakingTime = totalMatchMakingTimeAdventure5;
            matchedPlayers = matchedPlayersAdventure5;
        } else if (time == 10) {
            totalMatchMakingTime = totalMatchMakingTimeAdventure10;
            matchedPlayers = matchedPlayersAdventure10;
        } else {
            totalMatchMakingTime = totalMatchMakingTimeAdventure20;
            matchedPlayers = matchedPlayersAdventure20;
        }
    }

    long totalTime = totalMatchMakingTime.values().stream().mapToLong(Long::longValue).sum();
    int totalMatchedPlayers = matchedPlayers.values().stream().mapToInt(Integer::intValue).sum();

    if (totalMatchedPlayers > 0) {
        return (double) totalTime / totalMatchedPlayers;
    } else {
        return 0.0;
    }
}

/**
 *  Ping-pong feature to check for user connectivity
 */
private CompletableFuture<Boolean> checkHeartbeatAsync(String sessionId) {
  return CompletableFuture.supplyAsync(() -> {
      try {
          // Send ping message to the client
          String message = String.format("{\"message\" : \"%s\"}", "PING");
          simpMessagingTemplate.convertAndSend("/topic/ping" + sessionId, message);
          
          // Wait for pong response within a timeout period
          Thread.sleep(250); // Wait for .5 second (adjust as needed)
          
          // Check if pong response was received within the timeout
          if(pings.contains(sessionId)){
            pings.remove(sessionId);
            return true;
          }
          return false;
      } catch (InterruptedException e) {
          // Handle interrupted exception if needed
          e.printStackTrace();
          return false;
      }
  });
}


}
