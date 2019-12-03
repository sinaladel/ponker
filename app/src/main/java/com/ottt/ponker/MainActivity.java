package com.ottt.ponker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ottt.ponker.enums.HandValue;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView player4_txtBx, player1_txtBx, player2_txtBx, player3_txtBx;

    String gameLog = "";

    TextView txtViewCard0, txtViewCard1, txtViewCard2, txtViewCard3, txtViewCard4, txtViewWinner, txtViewShowDown, txtViewGameLog;
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

    }

    private void populateViewsForGame() {

        txtViewCard0 = findViewById(R.id.txtViewCard0);
        txtViewCard1 = findViewById(R.id.txtViewCard1);
        txtViewCard2 = findViewById(R.id.txtViewCard2);
        txtViewCard3 = findViewById(R.id.txtViewCard3);
        txtViewCard4 = findViewById(R.id.txtViewCard4);

        checkBoxList.add((CheckBox) findViewById(R.id.checkCard0));
        checkBoxList.add((CheckBox) findViewById(R.id.checkCard1));
        checkBoxList.add((CheckBox) findViewById(R.id.checkCard2));
        checkBoxList.add((CheckBox) findViewById(R.id.checkCard3));
        checkBoxList.add((CheckBox) findViewById(R.id.checkCard4));
        // Safe casting, we know that these will always be CheckBoxes

        txtViewShowDown = findViewById(R.id.txtViewShowdown);

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
        System.out.println(Game.deck.toString());
    }

    private void updateViews() {
        //String titleBar = Game.getCurrentPlayer().getName(); // This doesn't work on Sina's laptop so idk fix it Daemond
        Hand h = Game.getCurrentPlayer().getHand();
        System.out.println(h.cards);
        String[] cards = h.toArray();


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

        if (Game.getCurrentTurn() == 3) {
            endGame();
        }
        ArrayList<Card> selectedCards = new ArrayList<>();

        // Enable once Game class is functional
        if (checkBoxList.get(0).isChecked()) {
            selectedCards.add(Game.getCurrentPlayer().getHand().cards.get(0));
        }
        if (checkBoxList.get(1).isChecked()) {
            selectedCards.add(Game.getCurrentPlayer().getHand().cards.get(1));
        }
        if (checkBoxList.get(2).isChecked()) {
            selectedCards.add(Game.getCurrentPlayer().getHand().cards.get(2));
        }
        if (checkBoxList.get(3).isChecked()) {
            selectedCards.add(Game.getCurrentPlayer().getHand().cards.get(3));
        }
        if (checkBoxList.get(4).isChecked()) {
            selectedCards.add(Game.getCurrentPlayer().getHand().cards.get(4));
        }


        gameLog += Game.getCurrentPlayer().getName() + " discards " + selectedCards.size() + " cards\n";


        Game.discard(selectedCards); //Enable once Game class is functional

        buttonDiscard.setEnabled(false);
        hideHand();
        resetCheckBoxes();
        buttonNextTurn.setEnabled(true);
    }

    public void nextTurn(View v) {
        if (Game.getCurrentTurn() == 3) {
            endGame();
            return;
        }
        Game.nextTurn();
        buttonNextTurn.setEnabled(false);
        buttonDiscard.setEnabled(true);
        updateViews();
    }

    private void endGame() {

        Player winner = Game.getWinner();
        HandValue hv = winner.getHand().getHandValue();
        String hand;

        switch (hv) {
            case HIGH_CARD:
                hand = "a high-card";
                break;

            case PAIR:
                hand = "a pair";
                break;
            case TWO_PAIR:
                hand = "two-pair";
                break;
            case THREE_OF_A_KIND:
                hand = "three of a kind";
                break;
            case STRAIGHT:
                hand = "a straight";
                break;
            case FLUSH:
                hand = "a flush";
                break;
            case FULL_HOUSE:
                hand = "a full house";
                break;
            case FOUR_OF_A_KIND:
                hand = "four of a kind";
                break;
            case STRAIGHT_FLUSH:
                hand = "a straight flush";
                break;
                default:
                    hand = "somtin";
        }

        resetCheckBoxes();
        hideHand();
        buttonDiscard.setEnabled(false);
        buttonNextTurn.setEnabled(false);
        txtViewWinner.setText(Game.getWinner().getName() + " wins with " + hand);

        for(Player p: Game.getPlayers()) {
            txtViewShowDown.append(p.getName() + ": " + p.getHand().toString() + "\n");
        }
    }

    private void resetCheckBoxes() {
        for (CheckBox checkBox : checkBoxList) {
            checkBox.setChecked(false);
        }
    }

    private void hideHand() {
        txtViewCard0.setText("\u26aa\n\u26ab");
        txtViewCard1.setText("\u26aa\n\u26ab");
        txtViewCard2.setText("\u26aa\n\u26ab");
        txtViewCard3.setText("\u26aa\n\u26ab");
        txtViewCard4.setText("\u26aa\n\u26ab");
    }

}