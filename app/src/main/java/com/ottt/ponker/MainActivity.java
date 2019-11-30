package com.ottt.ponker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_setup);
        ArrayList<Card> cards = new ArrayList<Card>();

        cards.add(new Card(Tier.ACE, Suit.CLUBS));
        cards.add(new Card(Tier.ACE, Suit.HEARTS));
        cards.add(new Card(Tier.ACE, Suit.DIAMONDS));
        cards.add(new Card(Tier.TEN, Suit.SPADES));
        cards.add(new Card(Tier.TEN, Suit.DIAMONDS));

        Hand h = new Hand(cards);

        h.getHandValue(h);
    }

    //getActionBar.setTitle()


    public void initialiseGame(View v) {
        setContentView(R.layout.activity_main);
    }
}