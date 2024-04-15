import adventchess.chessgame.model.*;
import adventchess.chessgame.logic.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChessGameTest {

    @Test
    public void testIsCheck() {
        String[][] whiteInCheckState = {
            {"BR", "--", "--", "BK", "--", "--", "--", "--"},
            {"BP", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "BP", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "--", "--", "--", "--", "--"},
            {"WR", "--", "--", "--", "--", "BQ", "--", "WK"}
        };
        ChessGame gameInCheck = new ChessGame("null", "Alice", "Bob", whiteInCheckState);
        assertTrue(gameInCheck.isChecked("White"));
        assertFalse(gameInCheck.isChecked("Black"));
    }

    @Test
    public void testIsCheck2() {
        String[][] whiteNotInCheckState = {
            {"BR", "--", "--", "BK", "--", "--", "--", "--"},
            {"BP", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "BP", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "--", "--", "--", "--", "--"},
            {"WR", "--", "--", "--", "--", "BQ", "WR", "WK"}
        };
        ChessGame gameInCheck = new ChessGame("null", "Alice", "Bob", whiteNotInCheckState);
        assertFalse(gameInCheck.isChecked("White"));
        assertFalse(gameInCheck.isChecked("Black"));
    }

    @Test
    public void testIsCheck3() {
        String[][] whiteInCheckState = {
            {"BR", "--", "--", "BK", "--", "--", "--", "--"},
            {"BP", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "BP", "BB", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "--", "--", "--", "--", "--"},
            {"WR", "--", "--", "--", "--", "BQ", "WR", "WK"}
        };
        ChessGame gameInCheck = new ChessGame("null", "Alice", "Bob", whiteInCheckState);
        assertTrue(gameInCheck.isChecked("White"));
        assertFalse(gameInCheck.isChecked("Black"));
    }


    @Test
    public void testIsCheck4() {
        String[][] whiteNotInCheckState = {
            {"BR", "BN", "BB", "BQ", "BK", "BB", "BN", "BR"},
            {"BP", "BP", "BP", "BP", "--", "--", "BP", "BP"},
            {"--", "--", "--", "--", "--", "BP", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "--", "--"},
            {"--", "--", "--", "--", "WP", "--", "--", "--"},
            {"--", "--", "--", "WP", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "--", "--", "WP", "WP", "WP"},
            {"WR", "WN", "WB", "WQ", "WK", "WB", "WN", "WR"}
        };
        ChessGame gameInCheck = new ChessGame("null", "Alice", "Bob", whiteNotInCheckState);
        assertFalse(gameInCheck.isChecked("White"));
        assertFalse(gameInCheck.isChecked("Black"));
    }

    @Test
    public void testIsCheck5() {
        String[][] blackInCheckState = {
            {"BR", "BN", "BB", "BQ", "BK", "BB", "BN", "BR"},
            {"BP", "BP", "BP", "BP", "--", "--", "BP", "BP"},
            {"--", "--", "--", "--", "--", "BP", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "--", "WQ"},
            {"--", "--", "--", "--", "WP", "--", "--", "--"},
            {"--", "--", "--", "WP", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "--", "--", "WP", "WP", "WP"},
            {"WR", "WN", "WB", "--", "WK", "WB", "WN", "WR"}
        };
        ChessGame gameInCheck = new ChessGame("null", "Alice", "Bob", blackInCheckState);
        assertFalse(gameInCheck.isChecked("White"));
        assertTrue(gameInCheck.isChecked("Black"));
    }

    @Test
    public void testIsCheck6() {
        String[][] blackNotInCheckState = {
            {"BR", "BN", "BB", "BQ", "--", "BB", "BN", "BR"},
            {"BP", "BP", "BP", "BP", "BK", "--", "BP", "BP"},
            {"--", "--", "--", "--", "--", "BP", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "--", "WQ"},
            {"--", "--", "--", "--", "WP", "--", "--", "--"},
            {"--", "--", "--", "WP", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "--", "--", "WP", "WP", "WP"},
            {"WR", "WN", "WB", "--", "WK", "WB", "WN", "WR"}
        };
        ChessGame gameInCheck = new ChessGame("null", "Alice", "Bob", blackNotInCheckState);
        assertFalse(gameInCheck.isChecked("White"));
        assertFalse(gameInCheck.isChecked("Black"));
    }

    @Test
    public void testIsCheck7() {
        String[][] blackInCheckState = {
            {"BR", "BN", "BB", "BQ", "--", "BB", "BN", "BR"},
            {"BP", "BP", "BP", "BP", "BK", "--", "BP", "BP"},
            {"--", "--", "--", "--", "--", "BP", "--", "--"},
            {"--", "--", "--", "WN", "BP", "--", "--", "WQ"},
            {"--", "--", "--", "--", "WP", "--", "--", "--"},
            {"--", "--", "--", "WP", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "--", "--", "WP", "WP", "WP"},
            {"WR", "--", "WB", "--", "WK", "WB", "WN", "WR"}
        };
        ChessGame gameInCheck = new ChessGame("null", "Alice", "Bob", blackInCheckState);
        assertFalse(gameInCheck.isChecked("White"));
        assertTrue(gameInCheck.isChecked("Black"));
    }

    @Test
    public void testIsCheck8() {
        String[][] blackInCheckState = {
            {"BR", "BN", "BB", "BQ", "--", "BB", "BN", "BR"},
            {"BP", "BP", "BP", "BP", "--", "BK", "BP", "BP"},
            {"--", "--", "--", "--", "--", "BP", "--", "--"},
            {"--", "--", "--", "WN", "BP", "--", "--", "WQ"},
            {"--", "--", "--", "--", "WP", "--", "--", "--"},
            {"--", "--", "--", "WP", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "--", "--", "WP", "WP", "WP"},
            {"WR", "--", "WB", "--", "WK", "WB", "WN", "WR"}
        };
        ChessGame gameInCheck = new ChessGame("null", "Alice", "Bob", blackInCheckState);
        assertFalse(gameInCheck.isChecked("White"));
        assertTrue(gameInCheck.isChecked("Black"));
    }


    @Test
    public void testIsCheck9() {
        String[][] whiteInCheckState = {
            {"--", "--", "BR", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "WK", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "BP", "--"},
            {"--", "--", "--", "--", "--", "--", "WR", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame gameInCheck = new ChessGame("null", "Alice", "Bob", whiteInCheckState);
        assertFalse(gameInCheck.isChecked("White"));
    }

    @Test
    public void testIsCheck10() {
        String[][] whiteInCheckState = {
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "WK", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "BP", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "BK", "--"}
        };
        ChessGame gameInCheck = new ChessGame("null", "Alice", "Bob", whiteInCheckState);
        assertFalse(gameInCheck.isChecked("White"));
    }

    @Test
    public void testKingHasLegalMoves() {
        String[][] board = {
            {"BR", "BN", "BB", "BQ", "--", "BB", "BN", "BR"},
            {"BP", "BP", "BP", "BP", "--", "BK", "BP", "BP"},
            {"--", "--", "--", "--", "--", "BP", "--", "--"},
            {"--", "--", "--", "WN", "BP", "WP", "--", "WQ"},
            {"--", "--", "--", "--", "WP", "--", "--", "--"},
            {"--", "--", "--", "WP", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "--", "--", "--", "WP", "WP"},
            {"WR", "--", "WB", "--", "WK", "WB", "WN", "WR"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertFalse(game.hasLegalKingMoves("Black"));
    }

    @Test
    public void testKingHasLegalMoves2() {
        String[][] board = {
            {"BR", "BN", "BB", "BQ", "--", "BB", "BN", "BR"},
            {"BP", "BP", "BP", "BP", "--", "BK", "BP", "BP"},
            {"--", "--", "--", "--", "--", "BP", "--", "--"},
            {"--", "--", "--", "WN", "BP", "--", "--", "WQ"},
            {"--", "--", "--", "--", "WP", "--", "--", "--"},
            {"--", "--", "--", "WP", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "--", "--", "WP", "WP", "WP"},
            {"WR", "--", "WB", "--", "WK", "WB", "WN", "WR"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertTrue(game.hasLegalKingMoves("Black"));
    }

    @Test
    public void testKingHasLegalMoves3() {
        String[][] board = {
            {"--", "--", "BR", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "BP", "WR", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertTrue(game.hasLegalKingMoves("White"));
    }

    @Test
    public void testKingHasLegalMoves4() {
        String[][] board = {
            {"--", "--", "BR", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "BP", "--"},
            {"--", "--", "--", "--", "--", "--", "WR", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertTrue(game.hasLegalKingMoves("White"));
    }

    @Test
    public void testKingHasLegalMoves5() {
        String[][] board = {
            {"--", "--", "BR", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "BB", "--"},
            {"--", "--", "--", "--", "--", "--", "WR", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertFalse(game.hasLegalKingMoves("White"));
    }

    @Test
    public void testCanAnyPieceInterfere() {
        String[][] board = {
            {"--", "--", "BR", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "BB", "--"},
            {"--", "--", "--", "--", "--", "--", "WR", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertFalse(game.hasLegalPieceMoves("White"));
    }

    @Test
    public void testCanAnyPieceInterfere2() {
        String[][] board = {
            {"--", "--", "BR", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "BN", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "WR", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertTrue(game.hasLegalPieceMoves("White"));
    }

    @Test
    public void testCanAnyPieceInterfere3() {
        String[][] board = {
            {"--", "--", "BR", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "BN", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "WN", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertFalse(game.hasLegalPieceMoves("White"));
    }

    @Test
    public void testCanAnyPieceInterfere4() {
        String[][] board = {
            {"--", "--", "BR", "--", "WK", "--", "--", "--"},
            {"--", "WP", "--", "--", "BP", "--", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "BN", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "WN", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertTrue(game.hasLegalPieceMoves("White"));
    }

    @Test
    public void testCanAnyPieceInterfere5() {
        String[][] board = {
            {"--", "BR", "--", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "WP", "BP", "--", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "BN", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "WN", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertTrue(game.hasLegalPieceMoves("White"));
    }


    @Test
    public void testCanAnyPieceInterfere6() {
        String[][] board = {
            {"--", "BR", "--", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "--", "--"},
            {"WB", "--", "--", "--", "--", "--", "BN", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "WN", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertTrue(game.hasLegalPieceMoves("White"));
    }

    @Test
    public void testCanAnyPieceInterfere7() {
        String[][] board = {
            {"--", "BR", "--", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "WP", "--"},
            {"--", "BP", "--", "BK", "--", "--", "--", "--"},
            {"WB", "--", "--", "--", "--", "--", "BN", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "WN", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertFalse(game.hasLegalPieceMoves("White"));
    }

    @Test
    public void testCanAnyPieceInterfere8() {
        String[][] board = {
            {"--", "BR", "--", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "WP", "--"},
            {"--", "BP", "--", "BK", "WN", "--", "--", "--"},
            {"WB", "--", "--", "--", "--", "--", "BN", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertTrue(game.hasLegalPieceMoves("White"));
    }


    @Test
    public void testIsCheckMated() {
        String[][] board = {
            {"--", "--", "BR", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "BB", "--"},
            {"--", "--", "--", "--", "--", "--", "WR", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertTrue(game.isCheckMated("White"));
    }

    @Test
    public void testIsCheckMated2() {
        String[][] board = {
            {"--", "--", "BR", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "BP", "--"},
            {"--", "--", "--", "--", "--", "--", "WR", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertFalse(game.isCheckMated("White"));
    }

    @Test
    public void testIsCheckMated3() {
        String[][] board = {
            {"WR", "--", "--", "--", "--", "BK", "--", "--"},
            {"--", "WR", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "WK", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertTrue(game.isCheckMated("Black"));
    }


    @Test
    public void testIsCheckMated4() {
        String[][] board = {
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"BK", "WQ", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "WK", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertTrue(game.isCheckMated("Black"));
    }


    @Test
    public void testIsCheckMated5() {
        String[][] board = {
            {"--", "BK", "WQ", "--", "--", "--", "--", "--"},
            {"--", "BP", "--", "--", "--", "--", "--", "--"},
            {"--", "WK", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertFalse(game.isCheckMated("Black"));
    }


    @Test
    public void testIsCheckMated6() {
        String[][] board = {
            {"--", "--", "--", "WR", "--", "--", "BK", "--"},
            {"--", "--", "--", "--", "--", "BP", "BP", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "BP"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "BN", "WB", "--", "--", "--", "WP"},
            {"--", "BR", "--", "--", "--", "WP", "WP", "--"},
            {"--", "--", "--", "--", "--", "--", "WK", "--"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertTrue(game.isCheckMated("Black"));
    }

    @Test
    public void testIsCheckMated7() {
        String[][] board = {
            {"--", "--", "--", "--", "BK", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "WQ", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertFalse(game.isCheckMated("Black"));
    }

    @Test
    public void testIsCheckMated8() {
        String[][] board = {
            {"--", "--", "--", "--", "WN", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "WB", "--", "--"},
            {"--", "--", "--", "WB", "BK", "--", "--", "--"},
            {"--", "--", "--", "--", "WP", "--", "--", "--"},
            {"--", "--", "--", "--", "WP", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "WK", "--"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertTrue(game.isCheckMated("Black"));
    }

    @Test
    public void testIsCheckMated9() {
        String[][] board = {
            {"BR", "BN", "BB", "--", "BK", "BB", "BN", "BR"},
            {"BP", "BP", "BP", "BP", "--", "BP", "BP", "BP"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "WP", "BQ"},
            {"--", "--", "--", "--", "--", "WP", "--", "--"},
            {"WP", "WP", "WP", "WP", "WP", "--", "--", "WP"},
            {"WR", "WN", "WB", "WQ", "WK", "WB", "WN", "WR"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertTrue(game.isChecked("White"));
        assertFalse(game.hasLegalKingMoves("White"));
        assertFalse(game.hasLegalPieceMoves("White"));
        assertTrue(game.isCheckMated("White"));
    }

    @Test
    public void testIsStaleMate() {
        String[][] board = {
            {"--", "--", "--", "--", "BK", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "WQ", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertTrue(game.isStaleMate("Black"));
        assertFalse(game.isStaleMate("White"));
    }

    @Test
    public void testIsStaleMate2() {
        String[][] board = {
            {"WK", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "WQ", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "BK"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertTrue(game.isStaleMate("Black"));
        assertFalse(game.isStaleMate("White"));
    }

    @Test
    public void testIsStaleMate3() {
        String[][] board = {
            {"--", "--", "--", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "BQ", "BK", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertTrue(game.isStaleMate("White"));
        assertFalse(game.isStaleMate("Black"));
    }

    @Test
    public void testIsStaleMate4() {
        String[][] board = {
            {"BK", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "BQ", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "WK"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertTrue(game.isStaleMate("White"));
        assertFalse(game.isStaleMate("Black"));
    }


    @Test
    public void testIsStaleMate5() {
        String[][] board = {
            {"WK", "--", "--", "--", "--", "--", "WQ", "WR"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "BP", "--", "--", "--", "--", "BB"},
            {"BP", "--", "WN", "--", "--", "--", "--", "--"},
            {"WP", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"WR", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "BK"}
        };
        ChessGame game = new ChessGame("null", "Alice", "Bob", board);
        assertTrue(game.isStaleMate("Black"));
        assertFalse(game.isStaleMate("White"));
    }

    @Test
    public void testMakeMove() {
        ChessGame game = new ChessGame("null", "Alice", "Bob");
        ChessBoard board = game.getBoard();
        Player playerWhite = game.getPlayer("White");
        Player playerBlack = game.getPlayer("Black");
        boolean moveMade;

        moveMade = game.makeMove("White", 1, 0, 3, 0);
        assertTrue(moveMade);
        assertTrue(board.getPieceAt(3,0).getHome()[0] == 1);
        assertTrue(board.getPieceAt(3,0).getHome()[1] == 0);
        assertTrue(board.getPieceAt(1,0) == null);

        moveMade = game.makeMove("White", 1, 0, 3, 0);
        assertFalse(moveMade);
        assertTrue(board.getPieceAt(3,0).getHome()[0] == 1);
        assertTrue(board.getPieceAt(3,0).getHome()[1] == 0);
        assertTrue(board.getPieceAt(1,0) == null);


        ChessPiece blackKnight = board.getPieceAt(7,1);
        board.placePiece(blackKnight, 4, 1);

        moveMade = game.makeMove("White", 3, 0, 4, 1);
        assertTrue(moveMade);
        assertTrue(board.getPieceAt(4,1) instanceof Pawn);
        ArrayList<ChessPiece> pieces = playerWhite.getPiecesEaten();
        assertTrue(pieces.size() == 1);
        assertTrue(board.getPieces("Black").size() == 15);
        assertTrue(board.getPieces("White").size() == 16);
        assertTrue(pieces.get(0) instanceof Knight);


        moveMade = game.makeMove("White", 3, 7, 4, 6);
        assertFalse(moveMade);

        moveMade = game.makeMove("White", 4, 1, 5, 0);
        assertFalse(moveMade);

        moveMade = game.makeMove("White", 4, 1, 5, 1);
        assertTrue(moveMade);

        assertTrue(board.getPieceAt(5,1).getHome()[0] == 1);
        assertTrue(board.getPieceAt(5,1).getHome()[1] == 0);
    }

    @Test
    public void testCreateRandom() {
        String[][] board = ChessGame.createRandom();
        System.out.println(board);
        // kings are intialized
        assertTrue(board[0][4].equals("BK") && board[7][4].equals("WK"));

        //board is mirrored
        for(int i = 0; i < 8; i++){
            if(!board[0][i].equals("--")){
                char pieceBlack = board[0][i].charAt(1);
                char pieceWhite = board[7][i].charAt(1);
                assertTrue(pieceBlack == pieceWhite);
            }
        }
        for(int i = 0; i < 8; i++){
            if(!board[1][i].equals("--")){
                char pieceBlack = board[1][i].charAt(1);
                char pieceWhite = board[6][i].charAt(1);
                assertTrue(pieceBlack == pieceWhite);
            }
        }

        // board has no pieces in the middle
        for (int i = 3; i < 6; i++) {
            // Loop through each column in the current row
            for (int j = 0; j < 8; j++) {
                assertTrue(board[i][j].equals("--"));
            }
        }

    }
}