package com.ottt.ponker;

import java.util.ArrayList;

public class Player {


    private String name;
    private Hand hand;

    public Player(String name) {
        this.name = name;
        hand = new Hand(this);
    }

    public Hand getHand() {
        return hand;
    }


    Card getLastCard() {
        //
        return null;
    }

    public void dealMeIn() {

    }

    //remove all of the cards from the hand using the removeAll method in the Hand class
    public void discard(ArrayList<Card> cardsToDiscard) {
        hand.removeAll(cardsToDiscard);
    }

    //add as many cards to the hand as necessary to make it a five card hand using the addCard method in the Hand class
    public void refillHand() {
        getHand();

        //Method stub
        //addCard is already functional
    }

    public String getName() {
        return this.name != null ? this.name : "Unnamed player";
    }
}
