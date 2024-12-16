import com.example.hellojavafx.models.Player;
import com.example.hellojavafx.models.Card;
import com.example.hellojavafx.models.Deck;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Player model.
 */
public class PlayerTest {

    /**
     * Tests the drawCard method of the Player class.
     */
    @Test
    public void testDrawCard() {
        Player player = new Player("Test Player", false);
        Deck deck = Deck.getInstance();
        int initialHandSize = player.getHand().size();
        player.drawCard(deck);
        assertEquals(initialHandSize + 1, player.getHand().size(), "The player's hand size should increase by 1");
    }

    /**
     * Tests the playCard method of the Player class.
     */
    @Test
    public void testPlayCard() {
        Player player = new Player("Test Player", false);
        Deck deck = Deck.getInstance();
        player.drawCard(deck);
        Card card = player.getHand().get(0);
        player.playCard(card);
        assertFalse(player.getHand().contains(card), "The played card should not be in the player's hand");
    }

    /**
     * Tests the canPlayCard method of the Player class.
     */
    @Test
    public void testCanPlayCard() {
        Player player = new Player("Test Player", false);
        Card card = new Card("5", "Hearts", 5);
        assertTrue(player.canPlayCard(3, card), "The player should be able to play the card if the sum is <= 50");
        assertFalse(player.canPlayCard(46, card), "The player should not be able to play the card if the sum is > 50");
    }
}