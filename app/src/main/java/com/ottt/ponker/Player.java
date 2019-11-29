package com.ottt.ponker;

public class Player {

    public Player(String name) {
        this.name = name;
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


}
