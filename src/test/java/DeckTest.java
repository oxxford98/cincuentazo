import com.example.hellojavafx.models.Deck;
import com.example.hellojavafx.models.Card;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Deck model.
 */
public class DeckTest {

    /**
     * Tests the shuffle method of the Deck class.
     */
    @Test
    public void testShuffle() {
        Deck deck = Deck.getInstance();
        deck.initializeDeck();
        deck.shuffle();
        // Since shuffle is random, we can't predict the order, but we can check the size remains the same
        assertEquals(52, deck.getRemainingCards() + deck.getUsedCards(), "Total number of cards should be 52");
    }

    /**
     * Tests the singleton instance of the Deck class.
     */
    @Test
    public void testSingletonInstance() {
        Deck deck1 = Deck.getInstance();
        Deck deck2 = Deck.getInstance();
        assertSame(deck1, deck2, "Instances should be the same");
    }

    /**
     * Tests the drawCard method of the Deck class.
     */
    @Test
    public void testDrawCard() {
        Deck deck = Deck.getInstance();
        int initialSize = deck.getRemainingCards();
        Card card = deck.drawCard();
        assertNotNull(card, "Card should not be null");
        assertEquals(initialSize - 1, deck.getRemainingCards(), "Deck size should decrease by 1");
    }
}