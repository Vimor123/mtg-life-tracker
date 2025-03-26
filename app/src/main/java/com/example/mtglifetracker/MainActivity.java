package com.example.mtglifetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int player1Life;
    int player2Life;
    TextView player1LifeTextView;
    TextView player2LifeTextView;
    Button player1LifeUpButton;
    Button player1LifeDownButton;
    Button player2LifeUpButton;
    Button player2LifeDownButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        player1Life = 20;
        player2Life = 20;

        player1LifeTextView = findViewById(R.id.player1LifeTextView);
        player2LifeTextView = findViewById(R.id.player2LifeTextView);

        player1LifeUpButton = findViewById(R.id.player1LifeUpButton);
        player1LifeUpButton.setOnClickListener(view -> {
            player1Life += 1;
            player1LifeTextView.setText(Integer.toString(player1Life));
        });

        player1LifeDownButton = findViewById(R.id.player1LifeDownButton);
        player1LifeDownButton.setOnClickListener(view -> {
            player1Life -= 1;
            player1LifeTextView.setText(Integer.toString(player1Life));
        });

        player2LifeUpButton = findViewById(R.id.player2LifeUpButton);
        player2LifeUpButton.setOnClickListener(view -> {
            player2Life += 1;
            player2LifeTextView.setText(Integer.toString(player2Life));
        });

        player2LifeDownButton = findViewById(R.id.player2LifeDownButton);
        player2LifeDownButton.setOnClickListener(view -> {
            player2Life -= 1;
            player2LifeTextView.setText(Integer.toString(player2Life));
        });

        player1LifeTextView.setText(Integer.toString(player1Life));
        player2LifeTextView.setText(Integer.toString(player2Life));
    }
}