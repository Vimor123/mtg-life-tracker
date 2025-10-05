package com.example.mtglifetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    ConstraintLayout player1ConstraintLayout;
    ConstraintLayout player2ConstraintLayout;
    String player1ColorString;
    String player2ColorString;
    TextView player1Hashtag;
    TextView player2Hashtag;
    EditText player1EditText;
    EditText player2EditText;
    Button player1Button;
    Button player2Button;
    Button defaultsButton;
    Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        getWindow().getDecorView().setSystemUiVisibility(flags);

        setContentView(R.layout.activity_settings);

        sharedPreferences = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        player1ColorString = sharedPreferences.getString("player1Color", "#f7d79d");
        player2ColorString = sharedPreferences.getString("player2Color", "#be9df7");

        setColors(player1ColorString, player2ColorString);

        player1EditText = findViewById(R.id.player1ColorTextInput);
        player2EditText = findViewById(R.id.player2ColorTextInput);

        player1EditText.setText(player1ColorString.substring(1));
        player2EditText.setText(player2ColorString.substring(1));

        player1Hashtag = findViewById(R.id.player1HashtagTextView);
        player2Hashtag = findViewById(R.id.player2HashtagTextView);

        defaultsButton = findViewById(R.id.defaultsButton);
        defaultsButton.setOnClickListener(view -> {
            setColors("#f7d79d", "#be9df7");
            player1EditText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            player1Hashtag.setTextColor(Color.parseColor("#000000"));
            player2EditText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            player2Hashtag.setTextColor(Color.parseColor("#000000"));
        });

        player1Button = findViewById(R.id.player1ColorButton);
        player1Button.setOnClickListener(view -> {
            String newPlayer1ColorString = "#" + player1EditText.getText().toString();
            if (checkIfColorStringValid(newPlayer1ColorString)) {
                setColors(newPlayer1ColorString, player2ColorString);
                player1EditText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                player1Hashtag.setTextColor(Color.parseColor("#000000"));
            } else {
                player1EditText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                player1Hashtag.setTextColor(Color.parseColor("#ff0000"));
            }
        });

        player2Button = findViewById(R.id.player2ColorButton);
        player2Button.setOnClickListener(view -> {
            String newPlayer2ColorString = "#" + player2EditText.getText().toString();
            if (checkIfColorStringValid(newPlayer2ColorString)) {
                setColors(player1ColorString, newPlayer2ColorString);
                player2EditText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                player2Hashtag.setTextColor(Color.parseColor("#000000"));
            } else {
                player2EditText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                player2Hashtag.setTextColor(Color.parseColor("#ff0000"));
            }
        });

        doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(view -> {
            setResult(Activity.RESULT_OK);
            finish();
        });
    }

    public void setColors(String newPlayer1ColorString, String newPlayer2ColorString) {
        player1ColorString = newPlayer1ColorString;
        player2ColorString = newPlayer2ColorString;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("player1Color", player1ColorString);
        editor.putString("player2Color", player2ColorString);
        editor.commit();

        player1ConstraintLayout = findViewById(R.id.player1LayoutColor);
        player2ConstraintLayout = findViewById(R.id.player2LayoutColor);

        player1ConstraintLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(player1ColorString)));
        player2ConstraintLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(player2ColorString)));

        player1Button = findViewById(R.id.player1ColorButton);
        player2Button = findViewById(R.id.player2ColorButton);

        player1Button.setTextColor(Color.parseColor(player1ColorString));
        player2Button.setTextColor(Color.parseColor(player2ColorString));
    }

    private boolean checkIfColorStringValid(String colorString) {
        if (colorString.length() != 7 || colorString.charAt(0) != '#') {
            return false;
        }
        for (int i = 1; i <= 6; i++) {
            char c = colorString.charAt(i);
            if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'))) {
                return false;
            }
        }
        return true;
    }
}