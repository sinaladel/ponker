package com.ottt.ponker;

import java.util.ArrayList;

public class Game {
    //starting with 4 players that will always have a name and a hand and removing them from the list
    //as the game goes on and players drop out.
    public static Deck deck = new Deck(true);

    private static ArrayList<Player> players = new ArrayList<Player>();
    private static Player turn;
    private static int playerIndex = 0;

    public static void removePlayer() {


    }
    //
}
