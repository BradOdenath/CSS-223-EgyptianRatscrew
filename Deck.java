/*
 * Class Description:  A deck consists of 52 playing cards comprised of
 * 2 - Ace (int value 2 - 14) of 4 suits (clubs, diamonds, hearts, and spades)
 * The user can construct a deck, deal a card, and shuffle a deck, and display
 * the deck using its toString method
 */
package deque;

import java.util.Random;

/**
 *
 * @author A. Wright
 * @date 03/05/2013
 *
 */
public class Deck {

    private Card[] deck;   // deck of 52 cards
    private int topCard;   // keeps track of the "top card" left in the deck
    // as we deal, we will increment this
    public final int NUMCARDS = 52;  // 52 standard card deck

    /**
     * default class constructor
     *
     */
    public Deck() {
        deck = new Card[52];
        topCard = 0;
        int iVal = 0;                   // used to construct the Card value
        Card.Suit sSuit = Card.Suit.clubs;   // used to construct the Card suit
        for (int i = 0; i < NUMCARDS; i++) {
            iVal = i % 13 + 2;
            switch (i / 13) {
                case 0:
                    sSuit = Card.Suit.diamonds;
                    break;
                case 1:
                    sSuit = Card.Suit.hearts;
                    break;
                case 2:
                    sSuit = Card.Suit.spades;
                    break;
                case 3:
                    sSuit = Card.Suit.clubs;
                    break;
            }
            deck[i] = new Card(iVal, sSuit);
        }
    }

    /**
     *
     * @return the entire 52 cards of the deck as one string
     */
    public String toString() {
        String sDeck = "";

        for (int i = 0; i < 52; i++) {
            if (i % 4 == 0) {
                sDeck += "\n";
            }
            sDeck += ("\t" + deck[i]);
        }
        sDeck += "\n";
        return sDeck;
    }

    /**
     * dealACard
     *
     * @return top card from the deck preconditions: we haven't exhausted the
     * deck...topCard &lt; NUMCARDS postconditions: return the top Card from the
     * deck and increment topCard
     */
    public Card dealACard() {
        return deck[topCard++];
    }

    /**
     * shuffle() -- mixes up the cards in the deck preconditions: none
     * postconditions: mixes up the cards (shuffles them) and reset topCard to 0
     */
    public void shuffle() {
        Random rand = new Random();
        Card temp = new Card();

        for (int i = 0; i < 3; i++) {
            for (int c = 0; c < NUMCARDS; c++) {
                int itemp = rand.nextInt(NUMCARDS);
                temp = deck[c];
                deck[c] = deck[itemp];
                deck[itemp] = temp;

            }
        }
        topCard = 0;
    }
}
