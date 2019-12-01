package com.ottt.ponker;

import java.util.ArrayList;

public class Game {
    public static Deck deck = new Deck(true);

    private static ArrayList<Player> players = new ArrayList<Player>();
    private static Player turn;
    private static int playerIndex = 0;


    //fill the player arraylist with new players, pass the Strings into the Player constructors
    public static void initialiseGame(String p1, String p2, String p3, String p4) {

    }

    /*
    Each player is given a five-card hand
    each player is given the opportunity to choose any number of cards to discard from their hand
    each player is given the number of cards to complete their hand
    a winner is chosen
     */

    //give each player a full five-card deck using the dealmeIn method from the player class
    public static void dealPlayers() {
        //Method stub

    }

    //Discard the cards from the list that is passed in from the current player's hand using the discard method in the Player class
    //The player's discard method is also a stub, please fill out the logic for that
    public static void discard(ArrayList<Card> cardsToDiscard) {

        turn.discard(cardsToDiscard);
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

        //you will also need a loop



        //Method stub
        return null;
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


}
