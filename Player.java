/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deque;

/**
 *
 * @author Brad Odenath
 */
public class Player {

    //Player's name
    private String name;
    //Player is true
    private boolean isComp;

    //How many players have been declared
    private static int playerCount = 0;
    //Player's specific ID based on playerCount
    private int playerID;

    //Cards in hand
    private DLLCircularQueue<Card> hand = new DLLCircularQueue<Card>();

    
    public Player() {
        name = "Computer";
        isComp = true;
        playerID = ++playerCount;
    }

    public Player(String n, boolean c) {
        name = n;
        isComp = c;
        playerID = ++playerCount;
    }

    public void addCard(Card c) {
        hand.enqueue(c);
    }

    public Card flipCard() {
        if (hand.size() > 0) {
            return hand.dequeue();
        }
        return null;
    }

    public boolean isComp() {
        return isComp;
    }

    public boolean isEmptyHand() {
        return hand.size() == 0;
    }

    public int getHandSize() {
        return hand.size();
    }

    public int getID() {
        return playerID;
    }

    public boolean equals(Player plr) {
        return this.getID() == plr.getID();
    }

    public String toString() {
        String out = "";

        out += "[ID#" + playerID + "]";
        out += "[" + hand.size() + "] ";
        out += name;
//        if (isComp) {
//            out += "\t[AI]";
//        }
        //out += hand;
        return out;
    }

}
