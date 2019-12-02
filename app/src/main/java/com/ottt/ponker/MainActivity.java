package com.ottt.ponker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ottt.ponker.enums.Suit;
import com.ottt.ponker.enums.Tier;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView player4_txtBx, player1_txtBx, player2_txtBx, player3_txtBx;

    String gameLog = "";

    public CheckBox checkCard0, checkCard1, checkCard2, checkCard3, checkCard4;
    TextView txtViewCard0, txtViewCard1, txtViewCard2, txtViewCard3, txtViewCard4, txtViewWinner, txtViewGameLog;
    Button buttonDiscard, buttonNextTurn;
    ArrayList<CheckBox> checkBoxList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_setup);

        populateViewsForSetup();


    }

    private void populateViewsForSetup() {
        player1_txtBx = findViewById(R.id.player1_txtBx);
        player2_txtBx = findViewById(R.id.player2_txtBx);
        player3_txtBx = findViewById(R.id.player3_txtBx);
        player4_txtBx = findViewById(R.id.player4_txtBx);
        checkBoxList.add(checkCard0);
        checkBoxList.add(checkCard1);
        checkBoxList.add(checkCard2);
        checkBoxList.add(checkCard3);
        checkBoxList.add(checkCard4);

    }

    private void populateViewsForGame() {

        txtViewCard0 = findViewById(R.id.txtViewCard0);
        txtViewCard1 = findViewById(R.id.txtViewCard1);
        txtViewCard2 = findViewById(R.id.txtViewCard2);
        txtViewCard3 = findViewById(R.id.txtViewCard3);
        txtViewCard4 = findViewById(R.id.txtViewCard4);

        checkCard0 = findViewById(R.id.checkCard0);
        checkCard1 = findViewById(R.id.checkCard1);
        checkCard2 = findViewById(R.id.checkCard2);
        checkCard3 = findViewById(R.id.checkCard3);
        checkCard4 = findViewById(R.id.checkCard4);

        txtViewWinner = findViewById(R.id.txtViewWinner);

        buttonDiscard = findViewById(R.id.buttonDiscard);
        buttonNextTurn = findViewById(R.id.buttonNextTurn);

        txtViewGameLog = findViewById(R.id.txtViewGameLog);
    }


    //getActionBar.setTitle()


    public void initialiseGame(View v) {
        setContentView(R.layout.activity_main);

        Game.initialiseGame(player1_txtBx.getText().toString(), player2_txtBx.getText().toString(),
                player3_txtBx.getText().toString(), player4_txtBx.getText().toString());
        Game.dealPlayers();
        gameLog += "Each player has been dealt a five card hand\n";

        populateViewsForGame();
        buttonNextTurn.setEnabled(false);
        updateViews();
    }

    private void updateViews() {
        getActionBar().setTitle(Game.getCurrentPlayer().getName()); //Enable this once Game class is functional

        //String[] cards = Game.getCurrentPlayer().getHand().toArray(); // Enable this once Game class is functional

        //region Debugging hand //Remove when Game class is functional
        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(new Card(Tier.ACE, Suit.SPADES));
        cardList.add(new Card(Tier.SEVEN, Suit.CLUBS));
        cardList.add(new Card(Tier.JACK, Suit.SPADES));
        cardList.add(new Card(Tier.TWO, Suit.HEARTS));
        cardList.add(new Card(Tier.FOUR, Suit.SPADES));

        Hand h = new Hand(cardList);
        System.out.println(h.cards);
        String[] cards = h.toArray();
        //endregion

        int i = 0;
        for (String s : cards) {
            cards[i] = formatForTxtView(s);
            i++;
        }
        txtViewCard0.setText(cards[0]);
        txtViewCard1.setText(cards[1]);
        txtViewCard2.setText(cards[2]);
        txtViewCard3.setText(cards[3]);
        txtViewCard4.setText(cards[4]);

        txtViewGameLog.setText(gameLog);
    }

    private String formatForTxtView(String s) {
        char[] charArray = s.toCharArray();
        return charArray[0] + "\n" + charArray[1];
    }

    public void discard(View v) {
        ArrayList<Card> selectedCards = new ArrayList<>();

        // Enable once Game class is functional
        if (checkCard0.isChecked()) {
            selectedCards.add(Game.getCurrentPlayer().getHand().cards.get(0));
        }
        if (checkCard1.isChecked()) {
            selectedCards.add(Game.getCurrentPlayer().getHand().cards.get(1));
        }
        if (checkCard2.isChecked()) {
            selectedCards.add(Game.getCurrentPlayer().getHand().cards.get(2));
        }
        if (checkCard3.isChecked()) {
            selectedCards.add(Game.getCurrentPlayer().getHand().cards.get(3));
        }
        if (checkCard4.isChecked()) {
            selectedCards.add(Game.getCurrentPlayer().getHand().cards.get(4));
        }


        //region debug //delete when Game class is functional
        /*
        if (checkCard0.isChecked()) {
            selectedCards.add(null);
        }
        if (checkCard1.isChecked()) {
            selectedCards.add(null);
        }
        if (checkCard2.isChecked()) {
            selectedCards.add(null);
        }
        if (checkCard3.isChecked()) {
            selectedCards.add(null);
        }
        if (checkCard4.isChecked()) {
            selectedCards.add(null);
        }
         */
        //endregion

        //Enable blahhhhh blahh
        gameLog += Game.getCurrentPlayer().getName() + " discards " + selectedCards.size() + " cards\n";
        //gameLog += "PLAAAFYRE" + " discards " + selectedCards.size() + " cards\n"; //debug

        Game.discard(selectedCards); //Enable once Game class is functional

        buttonDiscard.setEnabled(false);
        hideHand();
        resetCheckBoxes();
        buttonNextTurn.setEnabled(true);
    }

    public void nextTurn(View v) {
        if (Game.getCurrentTurn() == 3) {
            endGame();
        }
        Game.nextTurn();
        buttonNextTurn.setEnabled(false);
        buttonDiscard.setEnabled(true);
        updateViews();
    }

    private void endGame() {
        resetCheckBoxes();
        hideHand();
        buttonDiscard.setEnabled(false);
        buttonNextTurn.setEnabled(false);
        txtViewWinner.setText(Game.getWinner().getName());
    }

    private void resetCheckBoxes() {
        checkCard0.setChecked(false);
        checkCard1.setChecked(false);
        checkCard2.setChecked(false);
        checkCard3.setChecked(false);
        checkCard4.setChecked(false);
    }

    private void hideHand() {
        txtViewCard0.setText("\u26aa\n\u26ab");
        txtViewCard1.setText("\u26aa\n\u26ab");
        txtViewCard2.setText("\u26aa\n\u26ab");
        txtViewCard3.setText("\u26aa\n\u26ab");
        txtViewCard4.setText("\u26aa\n\u26ab");
    }

}