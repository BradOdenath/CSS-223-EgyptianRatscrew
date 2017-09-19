/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deque;

import java.util.Scanner;

/**
 *
 * @author Brad Odenath
 */
public class EgyptionRatscrew {

    //Default amount of players needed to start a game
    public static final int MIN_PLR = 2;
    //Default amount of cards player flips during a turn
    public static final int DEF_AMT_FLIP = 1;
    //Default amount of cards player flips when previous player flips a Jack
    public static final int DEF_AMT_JACK_FLIP = 1;
    //Default amount of cards player flips when previous player flips a Queen
    public static final int DEF_AMT_QUEEN_FLIP = 2;
    //Default amount of cards player flips when previous player flips a King
    public static final int DEF_AMT_KING_FLIP = 3;
    //Default amount of cards player flips when previous player flips a Ace
    public static final int DEF_AMT_ACE_FLIP = 4;
    //Chance of computer recognizing a slap
    public static final double COMP_SLAP_CHANCE = 0.8;	// 80%
    //Chance of computer accidentally slapping
    public static final double COMP_SLAP_ERROR = 0.001;	// .1%

    //Reports if Card previous Player layed down is J/Q/K/A
    private boolean specialCard = false;
    //Player who layed down J/Q/K/A
    private DLLNode<Player> specialPlr;

    private final Scanner cin = new Scanner(System.in);

    //Pile of Cards to be claimed
    private DLLDeque<Card> pile = new DLLDeque<>();
    //getLast = last card put down
    //getFirst = first card put down/bottom of the pile

    //List of players playing the game
    private DLLCircularQueue<Player> plrList = new DLLCircularQueue<>();

    //Player taking their turn
    private DLLNode<Player> focusPlr;

    //Moves taken to end game
    private int amt_moves = 0;

    public EgyptionRatscrew() {
    }

    //Grab Pre Put Together list of players.
    public EgyptionRatscrew(DLLCircularQueue<Player> pL) {
        plrList = pL;
        focusPlr = plrList.getHead();
    }

    public void playGame() {
        startGame();
        runGame();
        endGame();
    }

    private void startGame() {
        System.out.println(" * * * * EGYPTION RAT SCREW * * * *");
        //Add Players
        do {
            if (plrList.size() < MIN_PLR) {
                System.out.println("Player count needs to be 2 or more:");
            }
            addPlayers();
        } while (plrList.size() < MIN_PLR);
        System.out.println("Valid amount of Players, dealing cards.");
        //Deal Cards
        dealCards();
    }

    private void runGame() {
        do {
            //focusPlr = focusPlr.getNext();
            takeTurn();
            //displayPile();
            offerSlap();
        } while (!isEndGame());
    }

    public void displayPile() {
        if (pile.isEmpty()) {
            System.out.println(pile.size() + "\n\tEmpty Pile\n");
        } else {
            System.out.println("Pile: " + pile);
        }
    }

    private void endGame() {
        if (isEndGame()) {
            Player winner = focusPlr.getElement();
            System.out.println(winner + " wins the game!");
        } else {
            System.out.println("Invalid End Game");
            runGame();
        }
        System.out.println("Game over, thanks for playing!" + amt_moves);
    }

    private void addPlayers() {
        String plrName;
        boolean isComp;
        while (getAnswer("Would you like to add another Player?", "y")) {
            System.out.println("\nEnter Player's name:");
            plrName = cin.nextLine();
            isComp = getAnswer("Is " + plrName + " a computer? ", "y");

            plrList.enqueue(new Player(plrName, isComp));
        }
        //System.out.println(plrList);
    }

    private void dealCards() {
        Deck deck = new Deck();
        deck.shuffle();
        focusPlr = plrList.getHead();
        for (int i = 0; i < deck.NUMCARDS; i++) {
            focusPlr.getElement().addCard(deck.dealACard());
            focusPlr = focusPlr.getNext();
        }
    }

    private boolean getAnswer(String statement, String answer) {
        System.out.print("\n" + statement + "\n\tType '"
                + answer + "' for yes: ");
        return (cin.nextLine().equalsIgnoreCase(answer));
    }

    //Checks if the next card is special
    public boolean isSpecialCard() {
        if (pile.isEmpty()) {
            return false;
        }
        Card focusCard = pile.getLast();
        int fCVal = focusCard.getValue();
        return fCVal == 11 || fCVal == 12 || fCVal == 13 || fCVal == 14;
    }

    //How many cards the next player has to flip
    public int amtFlip() {
        if (!pile.isEmpty() && pile.getLast() != null) {
            Card focusCard = pile.getLast();
            int fCVal = focusCard.getValue();
            if (fCVal == 11) {
                //specialCard = true;
                return DEF_AMT_JACK_FLIP;
            } else if (fCVal == 12) {
                //specialCard = true;
                return DEF_AMT_QUEEN_FLIP;
            } else if (fCVal == 13) {
                //specialCard = true;
                return DEF_AMT_KING_FLIP;
            } else if (fCVal == 14) {
                //specialCard = true;
                return DEF_AMT_ACE_FLIP;
            }
        }
        return DEF_AMT_FLIP;
    }

    // Add a card to the pile.
    public void addToPile(Card focusCard) {
        pile.addLast(focusCard);
        System.out.println(focusPlr.getElement()
                + " laid down a " + focusCard);
        ++amt_moves;
        displayPile();
        offerSlap();
    }

    // Offer a slap to all players.  
    private void offerSlap() {
        DLLCircularQueue<Player> temp_plrList = new DLLCircularQueue<>();
        DLLNode<Player> temp_plr = plrList.getRandom();
        //  Offer Slap Human
        for (int i = 1; i < plrList.size(); i++) {
            if (!temp_plr.getElement().isComp()) {
                if (getAnswer(temp_plr.getElement()
                        + ", would you like to slap?", "s")) {
                    slapPile(temp_plr.getElement());
                }
            } else {
                temp_plrList.enqueue(temp_plr.getElement());
            }
            temp_plr = temp_plr.getNext();
        }
        //  Offer Slap for Computer
        temp_plr = temp_plrList.getRandom();
        for (int i = 0; i < temp_plrList.size(); i++) {
            double randomChance = 1 - Math.random();
            //System.out.println("RANDOM NUMBER" + randomChance);
            if (isValidSlap()) {
                //Chance of slapping an actual card
                if (randomChance < COMP_SLAP_CHANCE && !pile.isEmpty()) {
                    slapPile(temp_plr.getElement());
                }
            } else {
                //No chance of slapping an actual card
                if (randomChance < COMP_SLAP_ERROR && !pile.isEmpty()
                        && temp_plr.getElement().getHandSize() == 0) {
                    slapPile(temp_plr.getElement());
                }
            }
            temp_plr = temp_plr.getNext();
        }

    }

    //Allow player to take their turn
    public void takeTurn() {
        int cardsToFlip = amtFlip();
        for (int i = 0; i < cardsToFlip; i++) {
            //Check for empty hand
            if (focusPlr.getElement().isEmptyHand()) {
                System.out.println(focusPlr.getElement()
                        + " has an empty hand");
                focusPlr = focusPlr.getNext();
                return;
            } else {    //If not an empty hand.
                Card focusCard = focusPlr.getElement().flipCard();
                //System.out.println("\t\t\t\t\t" + focusCard);
                addToPile(focusCard);

            }
            //System.out.println(amtFlip());
            if (isSpecialCard()) {
                //System.out.println("SPECIAL CARD");
                specialPlr = focusPlr;
                focusPlr = focusPlr.getNext();
                specialCard = true;
//                if (specialPlr.getElement().equals(focusPlr.getElement())) {
//                    specialCard = false;
//                    specialPlr = null;
//                } else {
//                    specialCard = true;
//                }
                return;
            }
        }
        if (specialCard) {
            //ADD ALL CARDS to Player's hand
            //while (!pile.isEmpty()) {
            int pileSize = pile.size();
            for (int i = 0; i < pileSize; i++) {
                specialPlr.getElement().addCard(pile.removeFirst());
            }
            System.out.println(specialPlr.getElement()
                    + " picked up the pile!");
            specialCard = false;
            focusPlr = specialPlr;
            specialPlr = null;
        } else {
            focusPlr = focusPlr.getNext();
        }
    }

    //Player slaps the pile.  If the slap is invalid, burn a card.  
    //                          If valid, collect the pile.
    public void slapPile(Player plr) {
        if (isValidSlap()) {
            //while (!pile.isEmpty()) {
            int pileSize = pile.size();
            for (int i = 0; i < pileSize; i++) {
                plr.addCard(pile.removeFirst());
            }
            System.out.println(plr + " slapped a match! (Valid Slap)");
            specialCard = false;
            specialPlr = null;
        } else {
            // Player must burn a card 
            if (!plr.isEmptyHand()) {
                pile.addFirst(plr.flipCard());
                System.out.println(plr + " burnt a card. (Invalid Slap)");
            } else {
                System.out.println(plr + " has an empty hand (Invalid Slap)");
            }
        }
        displayPile();
    }

    //Return if there is valid slap rules
    public boolean isValidSlap() {
        if (pile.size() < 2) {
            //System.out.println("Less than 3");
            return false;
        }
        //Recall last 3 cards placed on the pile
        Card lastThree[] = new Card[3];
        lastThree[0] = pile.getLast();
        lastThree[1] = pile.getTail().getPrevious().getElement();
        if (pile.size() < 3) {
            lastThree[2] = null;
            return lastThree[0].getValue() == lastThree[1].getValue();
        } else {
            lastThree[2] = pile.getTail().getPrevious().getPrevious().getElement();
            return lastThree[0].getValue() == lastThree[1].getValue()
                    || lastThree[0].getValue() == lastThree[2].getValue();
        }
        //Compare last 3 card values for pair or sandwich to validate slap

    }

    //Checks for the end of a game.  If 
    public boolean isEndGame() {
        DLLNode<Player> temp_plr = plrList.getHead();
        for (int i = 0; i < plrList.size(); ++i) {
//            if (temp_plr.getElement().getHandSize() == 0 && pile.size() == 0) {
//                //Remove player if they have an empty hand
//                System.out.println(temp_plr.getElement() + " droped out");
//                Player remove_plr = temp_plr.getElement();
//                temp_plr = temp_plr.getNext();
//                plrList.remove(remove_plr);  
//            } else 
            if (temp_plr.getElement().getHandSize() == 52) {
                //|| plrList.size() == 1) {
                //System.out.println(temp_plr.getElement() + " has " 
                //        + temp_plr.getElement().getHandSize() + " cards!");
                focusPlr = plrList.traverse(temp_plr.getElement(), temp_plr);
                return true;
            } else {
                temp_plr = temp_plr.getNext();
            }
        }
        return false;
    }

}
