package com.ottt.ponker;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private static Random rng = new Random();
    private ArrayList<Card> cards = new ArrayList<Card>();

    public Deck(boolean shuffle) {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                cards.add(new Card(Tier.values()[i], Suit.values()[j]));
            }
        }
        if (shuffle)
            shuffle();
    }
    public Deck() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                cards.add(new Card(Tier.values()[i], Suit.values()[j]));
            }
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (Card c:cards) {s += c.toString() + " | ";}
        return s;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card takeCard() {
        Card c = cards.get(rng.nextInt(cards.size()));
        cards.remove(c);
        return c;
    }

    public Card nextCard() {
        Card c = cards.get(0);
        cards.remove(c);
        return c;
    }

    public void sort() {
        Collections.sort(cards);
    }
}
