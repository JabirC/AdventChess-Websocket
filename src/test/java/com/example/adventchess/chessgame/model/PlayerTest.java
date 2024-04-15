import adventchess.chessgame.model.*;
import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testPlayerInitialization() {
        Player whitePlayer = new Player("Alice", "White");
        Player blackPlayer = new Player("Bob", "Black");

        // Test white player initialization
        assertEquals("Alice", whitePlayer.getName());
        assertEquals("White", whitePlayer.getColor());
        assertTrue(whitePlayer.isTurn());

        // Test black player initialization
        assertEquals("Bob", blackPlayer.getName());
        assertEquals("Black", blackPlayer.getColor());
        assertFalse(blackPlayer.isTurn());
    }

    @Test
    public void testPlayerGetters() {
        Player player = new Player("Charlie", "White");

        // Test getter methods
        assertEquals("Charlie", player.getName());
        assertEquals("White", player.getColor());
        assertTrue(player.isTurn());
    }

    // Add more test cases based on your specific requirements and additional methods
}