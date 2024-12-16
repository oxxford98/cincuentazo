import com.example.hellojavafx.models.Card;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Card model.
 */
public class CardTest {

    /**
     * Tests the constructor and getter methods of the Card class.
     */
    @Test
    public void testCardConstructorAndGetters() {
        Card card = new Card("a", "Hearts", 1);
        assertEquals("a", card.getRank(), "Rank should be a");
        assertEquals("Hearts", card.getSuit(), "Suit should be Hearts");
        assertEquals(1, card.getValue(), "Value should be 1");
        assertEquals("/com/example/hellojavafx/images/ac.jpg", card.getImg(), "The image path should be correct");
    }

    /**
     * Tests the getImg method for different suits.
     */
    @Test
    public void testGetImgForDifferentSuits() {
        Card heartsCard = new Card("k", "Hearts", 10);
        assertEquals("/com/example/hellojavafx/images/kc.jpg", heartsCard.getImg(), "Image path for hearts");

        Card diamondsCard = new Card("q", "Diamonds", 10);
        assertEquals("/com/example/hellojavafx/images/qd.jpg", diamondsCard.getImg(), "Image path for diamonds");

        Card spadesCard = new Card("j", "Spades", 10);
        assertEquals("/com/example/hellojavafx/images/jp.jpg", spadesCard.getImg(), "Image path for spades");

        Card clubsCard = new Card("10", "Clovers", 10);
        assertEquals("/com/example/hellojavafx/images/10t.jpg", clubsCard.getImg(), "Image path for clovers");
    }

    /**
     * Tests the toString method of the Card class.
     */
    @Test
    public void testToString() {
        Card card = new Card("a", "Spades", 1);
        assertEquals("a of Spades", card.toString(), "toString returns the representation of the card");
    }
}