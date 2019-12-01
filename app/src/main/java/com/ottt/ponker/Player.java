package com.ottt.ponker;

import java.util.ArrayList;
import java.util.Collections;

public class Player {

    public Player(String name) {
        this.name = name;
        hand = new Hand(this);
    }

    private String name;
    private Hand hand;



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
        //Method stub
        //removeAll is already functional
    }

    //add as many cards to the hand as necessary to make it a five card hand using the addCard method in the Hand class
    public void refillHand() {
        //Method stub
        //addCard is already functional
    }

    public String getName() {
        return this.name != null ? this.name : "Unamed player";
    }
}
