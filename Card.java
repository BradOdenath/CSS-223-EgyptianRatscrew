/**
 * Class Description: A Card has an int value (2 .. 14) that corresponds to its
 * face value for 2 - 10; J= 11, Q= 12; K = 13; A = 14. Each card also has a
 * Suit: diamonds, hearts, spades, or clubs. The user can set/get the value and
 * the suit.
 */
package deque;

/**
 *
 * @author A. Wright Color-coding updates by Brad Odenath & John Miller 9-8-2015
 */
public class Card {

    public enum Suit {

        diamonds, hearts, spades, clubs
    }

    public enum Color {

        red, black
    }
    private Suit suit;   // Card's suit
    private Color color; // Card's color
    private int value;   // Card's value (2 .. 14)
    public final char diamondsChar = ('\u2666');
    public final char heartsChar = ('\u2665');
    public final char spadesChar = ('\u2660');
    public final char clubsChar = ('\u2663');
    public final String red = (char) 27 + "[31m";
    public final String reset = (char) 27 + "[0m";

    /**
     * default constructor -- Creates a 7 of hearts
     */
    public Card() {
        value = 7;
        suit = Suit.hearts;
        color = Color.red;
    }

    /**
     * conversion constructor </br>
     * preconditions: input v is 2..14 and (valid Suit)</br>
     * postconditions: set the value to input v if valid and suit to input s
     * otherwise exit the program
     */
    public Card(int v, Suit s) {
        if (v < 15 && v > 1) {
            value = v;
            suit = s;
            if (suit == Suit.clubs || suit == Suit.spades) {
                color = Color.black;
            } else {
                color = Color.red;
            }
        } else {
            System.out.println(" Invalid card construction attempted");
            System.exit(1);
        }
    }

    /**
     * void setValue(): mutator method
     *
     * @param int v is input value for the Card (range must be 2 - 14)
     * preconditions: input v is 2 <= v <= 14 postconditions: if v is valid;
     * change the value, otherwise ignore input
     */
    public void setValue(int v) {
        if (2 <= v && v <= 14) {
            value = v;
        }

    }

    /**
     * int getValue(): Accessor
     *
     * @return int value -- face value of the card (2..10) J:11, Q:12, K:13,
     * A:14
     */
    public int getValue() {
        return value;
    }

    /**
     * void setSuit(): Mutator: used to change a Card's suit
     *
     * @param s suit for card {must be Suit. hearts, diamonds, spades, or clubs)
     * preconditions: none postconditions: changes the suit of the card to input
     * s
     */
    public void setSuit(Suit s) {
        suit = s;
        if (suit == Suit.clubs || suit == Suit.spades) {
            color = Color.black;
        } else {
            color = Color.red;
        }
    }

    /**
     * Suit getSuit(): Accessor - returns a copy of the Card's suit
     *
     * @return suit of the card preconditions: none postconditions: returns the
     * suit of the card to caller
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Suit getColor(): Accessor - returns a copy of the Card's color
     * (red/black)
     *
     * @return Color of the card preconditions: none postconditions: returns the
     * color of the card to caller
     */
    public Color getColor() {
        return color;
    }

    /**
     * String toString() : Accessor
     *
     * @return A String output for the Card..
     */
    public String toString() {
        String cVal = "\t";
        switch (value) {
            case 11:
                cVal += "J";
                break;
            case 12:
                cVal += "Q";
                break;
            case 13:
                cVal += "K";
                break;
            case 14:
                cVal += "A";
                break;
            default:
                cVal += value;
                break;
        }
        String cSuit = "";
        if (suit == Suit.hearts) {
            cSuit += red + heartsChar + reset;
        } else if (suit == Suit.diamonds) {
            cSuit += red + diamondsChar + reset;
        } else if (suit == Suit.spades) {
            cSuit += spadesChar;
        } else // suit is clubs
        {
            cSuit += clubsChar;
        }

        return (cVal.concat(cSuit));
    }

}
