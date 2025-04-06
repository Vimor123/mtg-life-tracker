package com.example.mtglifetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    int player1Life;
    int player2Life;
    TextView player1LifeTextView;
    TextView player2LifeTextView;
    TextView player1LifeDeltaTextView;
    TextView player2LifeDeltaTextView;
    Button player1LifeUpButton;
    Button player1LifeDownButton;
    Button player2LifeUpButton;
    Button player2LifeDownButton;
    int player1Delta;
    int player2Delta;
    Timer player1Timer;
    Timer player2Timer;
    TimerTask player1TimerTask;
    TimerTask player2TimerTask;
    Double player1Time;
    Double player2Time;

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

        setContentView(R.layout.activity_main);

        player1Life = 20;
        player2Life = 20;

        player1Delta = 0;
        player2Delta = 0;

        player1Time = 0.0;
        player2Time = 0.0;

        player1LifeTextView = findViewById(R.id.player1LifeTextView);
        player2LifeTextView = findViewById(R.id.player2LifeTextView);

        player1LifeDeltaTextView = findViewById(R.id.player1LifeDeltaTextView);
        player2LifeDeltaTextView = findViewById(R.id.player2LifeDeltaTextView);

        player1LifeUpButton = findViewById(R.id.player1LifeUpButton);
        player1LifeUpButton.setOnClickListener(view -> {
            player1Life += 1;
            player1LifeTextView.setText(Integer.toString(player1Life));
            if (player1TimerTask != null) {
                player1Timer.cancel();
                player1Timer.purge();
                player1Delta += 1;
            } else {
                player1Delta = 1;
            }
            player1LifeDeltaTextView.setText(getDeltaString(player1Delta));
            startPlayer1Timer();
            player1Time = 0.0;
        });

        player1LifeDownButton = findViewById(R.id.player1LifeDownButton);
        player1LifeDownButton.setOnClickListener(view -> {
            player1Life -= 1;
            player1LifeTextView.setText(Integer.toString(player1Life));
            if (player1TimerTask != null) {
                player1Timer.cancel();
                player1Timer.purge();
                player1Delta -= 1;
            } else {
                player1Delta = -1;
            }
            player1LifeDeltaTextView.setText(getDeltaString(player1Delta));
            startPlayer1Timer();
            player1Time = 0.0;
        });

        player2LifeUpButton = findViewById(R.id.player2LifeUpButton);
        player2LifeUpButton.setOnClickListener(view -> {
            player2Life += 1;
            player2LifeTextView.setText(Integer.toString(player2Life));
            if (player2TimerTask != null) {
                player2Timer.cancel();
                player2Timer.purge();
                player2Delta += 1;
            } else {
                player2Delta = 1;
            }
            player2LifeDeltaTextView.setText(getDeltaString(player2Delta));
            startPlayer2Timer();
            player2Time = 0.0;
        });

        player2LifeDownButton = findViewById(R.id.player2LifeDownButton);
        player2LifeDownButton.setOnClickListener(view -> {
            player2Life -= 1;
            player2LifeTextView.setText(Integer.toString(player2Life));
            if (player2TimerTask != null) {
                player2Timer.cancel();
                player2Timer.purge();
                player2Delta -= 1;
            } else {
                player2Delta = -1;
            }
            player2LifeDeltaTextView.setText(getDeltaString(player2Delta));
            startPlayer2Timer();
            player2Time = 0.0;
        });

        player1LifeTextView.setText(Integer.toString(player1Life));
        player2LifeTextView.setText(Integer.toString(player2Life));
        player1LifeDeltaTextView.setText("");
        player2LifeDeltaTextView.setText("");
    }

    private String getDeltaString(int delta) {
        if (delta > 0) {
            return "+" + delta;
        } else {
            return Integer.toString(delta);
        }
    }

    private void startPlayer1Timer() {
        player1TimerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    player1Time += 0.5;
                    if (player1Time >= 1.5) {
                        player1Time = 0.0;
                        player1Delta = 0;
                        player1LifeDeltaTextView.setText("");
                        player1Timer.cancel();
                    }
                });
            }
        };
        player1Timer = new Timer();
        player1Timer.scheduleAtFixedRate(player1TimerTask, 0, 500);
    }

    private void startPlayer2Timer() {
        player2TimerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    player2Time += 0.5;
                    if (player2Time >= 1.5) {
                        player2Time = 0.0;
                        player2Delta = 0;
                        player2LifeDeltaTextView.setText("");
                        player2Timer.cancel();
                    }
                });
            }
        };
        player2Timer = new Timer();
        player2Timer.scheduleAtFixedRate(player2TimerTask, 0, 500);
    }
}