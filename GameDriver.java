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
public class GameDriver {

    public static void main(String[] args) {
        DLLCircularQueue<Player> set_plrList = new DLLCircularQueue<>();
        set_plrList.enqueue(new Player("Anthony", true));
        set_plrList.enqueue(new Player("Brad", true));
        set_plrList.enqueue(new Player("Dan", true));
        set_plrList.enqueue(new Player("Dom", true));
        set_plrList.enqueue(new Player("John", true));
        set_plrList.enqueue(new Player("Kyle", true));
        //set_plrList.enqueue(new Player("Paige", true));
        set_plrList.enqueue(new Player("Sang", true));
        set_plrList.enqueue(new Player("Sarim", true));
        set_plrList.enqueue(new Player("Professor Wright", true));
        

        EgyptionRatscrew game = new EgyptionRatscrew(set_plrList);
        game.playGame();

    }

}
