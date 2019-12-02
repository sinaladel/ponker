package com.ottt.ponker;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Deck {
    private static Random rng = new Random();
    private LinkedList<Card> cards = new LinkedList<>();

    public Deck(boolean shuffle) {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                cards.add(new Card(Tier.values()[i], Suit.values()[j]));
            }
        }
        if (shuffle) shuffle();
    }

    public Deck() {
        this(false);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Card c : cards) {
            s.append(c.toString()).append(" | ");
        }
        return s.toString();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card takeCard() {
        return cards.remove(rng.nextInt(cards.size()));
    }

    public Card nextCard() {
        return cards.pop();
    }

    public void sort() {
        Collections.sort(cards);
    }
}
