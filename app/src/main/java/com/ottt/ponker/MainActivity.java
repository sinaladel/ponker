package com.ottt.ponker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_setup);
        ArrayList<Card> cards = new ArrayList<>();

        cards.add(new Card(Tier.TEN, Suit.CLUBS));
        cards.add(new Card(Tier.TEN, Suit.SPADES));
        cards.add(new Card(Tier.SEVEN, Suit.DIAMONDS));
        cards.add(new Card(Tier.SEVEN, Suit.HEARTS));
        cards.add(new Card(Tier.SEVEN, Suit.CLUBS));

        Hand h = new Hand(cards);

        ArrayList<Card> cards2 = new ArrayList<>();

        cards2.add(new Card(Tier.ACE, Suit.HEARTS));
        cards2.add(new Card(Tier.ACE, Suit.SPADES));
        cards2.add(new Card(Tier.ACE, Suit.DIAMONDS));
        cards2.add(new Card(Tier.EIGHT, Suit.CLUBS));
        cards2.add(new Card(Tier.EIGHT, Suit.HEARTS));

        Hand h2 = new Hand(cards2);

        h.compareTo(h2);

        ArrayList<Hand> hands = new ArrayList<>();
        hands.add(h);
        hands.add(h2);

        Collections.sort(hands);
        System.out.println(hands.get(0).cards);

    }

    //getActionBar.setTitle()



    public void initialiseGame(View v) {
        setContentView(R.layout.activity_main);
    }
}