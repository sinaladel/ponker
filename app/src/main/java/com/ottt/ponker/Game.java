package com.ottt.ponker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Game {
    public static Deck deck = new Deck(true);

    private static ArrayList<Player> players = new ArrayList<>();
    private static Player turn;
    private static int playerIndex = 0;

    //fill the player arraylist with new players, pass the Strings into the Player constructors

    public static void initialiseGame(String... p) {
        for (String s : p) {
            players.add(new Player(s));
        }
    }

    /**
     * Each player is given a five-card hand
     * each player is given the opportunity to choose any number of cards to discard from their hand
     * each player is given the number of cards to complete their hand
     * a winner is chosen
     */

    //give each player a full five-card deck using the dealmeIn method from the player class
    public static void dealPlayers() {
        for (Player o : players) {
            o.dealMeIn();
        }


    }

    /**
     * Discard the cards from the list that is passed in from the current player's hand using the discard method in the Player class
     * The player's discard method is also a stub, please fill out the logic for that
     */
    public static void discard(ArrayList<Card> cardsToDiscard) {
        Player p = getCurrentPlayer();
        p.discard(cardsToDiscard);
    }

    //refill the current player's hand with the refillHand method in the player class
    public static void refillHand() {
        turn.refillHand();
    }

    //use the Collections.sort method to sort the players' hands, then return the *owner* of the best hand
    public static Player getWinner() {
        //Methods you will use:
        //getHand in the player class
        //Collections.sort
        //getOwner in the Hand class
        LinkedList<Hand> allHands = new LinkedList<>();
        for (Player i : players) {
            allHands.add(i.getHand());
        }
        Collections.sort(allHands);
        return allHands.pop().owner;
        //you will also need a loop


        //Method stub
    }

    public static Player getCurrentPlayer() {
        turn = players.get(playerIndex);
        return turn;
    }

    public static void nextTurn() {
        if (playerIndex == players.size() - 1)
            playerIndex = 0;
        else
            playerIndex++;
    }

    public static int getCurrentTurn() {
        return playerIndex;
    }


    public static ArrayList<Player> getPlayers() {
        return players;
    }
}
