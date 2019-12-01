package com.ottt.ponker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    TextView player4_txtBx, player1_txtBx, player2_txtBx, player3_txtBx;


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
        //Method stub

    }


    //getActionBar.setTitle()



    public void initialiseGame(View v) {
        setContentView(R.layout.activity_main);

        Game.initialiseGame(player1_txtBx.getText().toString(), player2_txtBx.getText().toString(),
                            player3_txtBx.getText().toString(), player4_txtBx.getText().toString());
        Game.dealPlayers();

        populateViewsForGame();
        updateViews();
    }

    private void updateViews() {
        //Method stub


    }


}