package com.example.mtglifetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    ConstraintLayout player1ConstraintLayout;
    ConstraintLayout player2ConstraintLayout;
    String player1ColorString;
    String player2ColorString;
    float player1ColorHue;
    float player1ColorSaturation;
    float player1ColorValue;
    float player2ColorHue;
    float player2ColorSaturation;
    float player2ColorValue;
    TextView player1ColorTextView;
    TextView player2ColorTextView;
    TextView player1HueTextView;
    TextView player1SaturationTextView;
    TextView player1ValueTextView;
    TextView player2HueTextView;
    TextView player2SaturationTextView;
    TextView player2ValueTextView;
    SeekBar player1HueSeekBar;
    SeekBar player1SaturationSeekBar;
    SeekBar player1ValueSeekBar;
    SeekBar player2HueSeekBar;
    SeekBar player2SaturationSeekBar;
    SeekBar player2ValueSeekBar;
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

        float[] hsv;
        hsv = ColorMethods.hexStringTohsv(player1ColorString);
        player1ColorHue = hsv[0];
        player1ColorSaturation = hsv[1];
        player1ColorValue = hsv[2];
        if (player1ColorHue < 0.0f)
            player1ColorHue = 0.0f;
        if (player1ColorSaturation < 0.0f)
            player1ColorSaturation = 0.0f;

        hsv = ColorMethods.hexStringTohsv(player2ColorString);
        player2ColorHue = hsv[0];
        player2ColorSaturation = hsv[1];
        player2ColorValue = hsv[2];
        if (player2ColorHue < 0.0f)
            player2ColorHue = 0.0f;
        if (player2ColorSaturation < 0.0f)
            player2ColorSaturation = 0.0f;

        setColorsPlayer1(player1ColorString);
        sethsvPlayer1(player1ColorString);
        setColorsPlayer2(player2ColorString);
        sethsvPlayer2(player2ColorString);

        player1EditText = findViewById(R.id.player1ColorTextInput);
        player2EditText = findViewById(R.id.player2ColorTextInput);

        player1EditText.setText(player1ColorString.substring(1));
        player2EditText.setText(player2ColorString.substring(1));

        player1Hashtag = findViewById(R.id.player1HashtagTextView);
        player2Hashtag = findViewById(R.id.player2HashtagTextView);

        defaultsButton = findViewById(R.id.defaultsButton);
        defaultsButton.setOnClickListener(view -> {
            player1ColorString = "#f7d79d";
            player2ColorString = "#be9df7";
            setColorsPlayer1(player1ColorString);
            sethsvPlayer1(player1ColorString);
            setColorsPlayer2(player2ColorString);
            sethsvPlayer2(player2ColorString);
            saveColors(player1ColorString, player2ColorString);
        });

        player1HueSeekBar = findViewById(R.id.player1HueSeekBar);
        player1SaturationSeekBar = findViewById(R.id.player1SaturationSeekBar);
        player1ValueSeekBar = findViewById(R.id.player1ValueSeekBar);

        player2HueSeekBar = findViewById(R.id.player2HueSeekBar);
        player2SaturationSeekBar = findViewById(R.id.player2SaturationSeekBar);
        player2ValueSeekBar = findViewById(R.id.player2ValueSeekBar);

        player1HueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int hue, boolean b) {
                player1ColorHue = (float) hue;
                player1ColorString = ColorMethods.hsvToHexString(player1ColorHue, player1ColorSaturation, player1ColorValue);
                setColorsPlayer1(player1ColorString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        player1SaturationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int saturation, boolean b) {
                player1ColorSaturation = (float) saturation / 1000.0f;
                player1ColorString = ColorMethods.hsvToHexString(player1ColorHue, player1ColorSaturation, player1ColorValue);
                setColorsPlayer1(player1ColorString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        player1ValueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int value, boolean b) {
                player1ColorValue = (float) value / 1000.0f;
                player1ColorString = ColorMethods.hsvToHexString(player1ColorHue, player1ColorSaturation, player1ColorValue);
                setColorsPlayer1(player1ColorString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        player2HueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int hue, boolean b) {
                player2ColorHue = (float) hue;
                player2ColorString = ColorMethods.hsvToHexString(player2ColorHue, player2ColorSaturation, player2ColorValue);
                setColorsPlayer2(player2ColorString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        player2SaturationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int saturation, boolean b) {
                player2ColorSaturation = (float) saturation / 1000.0f;
                player2ColorString = ColorMethods.hsvToHexString(player2ColorHue, player2ColorSaturation, player2ColorValue);
                setColorsPlayer2(player2ColorString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        player2ValueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int value, boolean b) {
                player2ColorValue = (float) value / 1000.0f;
                player2ColorString = ColorMethods.hsvToHexString(player2ColorHue, player2ColorSaturation, player2ColorValue);
                setColorsPlayer2(player2ColorString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        player1Button = findViewById(R.id.player1ColorButton);
        player1Button.setOnClickListener(view -> {
            String newPlayer1ColorString = "#" + player1EditText.getText().toString();
            if (ColorMethods.checkIfColorStringValid(newPlayer1ColorString)) {
                player1ColorString = newPlayer1ColorString;
                sethsvPlayer1(player1ColorString);
                setColorsPlayer1(player1ColorString);
                saveColors(player1ColorString, player2ColorString);
            } else {
                player1EditText.setTextColor(Color.parseColor("#ff0000"));
                player1EditText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                player1Hashtag.setTextColor(Color.parseColor("#ff0000"));
            }
        });

        player2Button = findViewById(R.id.player2ColorButton);
        player2Button.setOnClickListener(view -> {
            String newPlayer2ColorString = "#" + player2EditText.getText().toString();
            if (ColorMethods.checkIfColorStringValid(newPlayer2ColorString)) {
                player2ColorString = newPlayer2ColorString;
                sethsvPlayer2(player2ColorString);
                setColorsPlayer2(player2ColorString);
                saveColors(player1ColorString, player2ColorString);
            } else {
                player2EditText.setTextColor(Color.parseColor("#ff0000"));
                player2EditText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                player2Hashtag.setTextColor(Color.parseColor("#ff0000"));
            }
        });

        doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(view -> {
            saveColors(player1ColorString, player2ColorString);
            setResult(Activity.RESULT_OK);
            finish();
        });
    }

    public void setColorsPlayer1(String colorString) {
        player1ColorString = colorString;

        player1ColorTextView = findViewById(R.id.player1ColorTextView);
        player1HueTextView = findViewById(R.id.player1HueTextView);
        player1SaturationTextView = findViewById(R.id.player1SaturationTextView);
        player1ValueTextView = findViewById(R.id.player1ValueTextView);
        player1HueSeekBar = findViewById(R.id.player1HueSeekBar);
        player1SaturationSeekBar = findViewById(R.id.player1SaturationSeekBar);
        player1ValueSeekBar = findViewById(R.id.player1ValueSeekBar);
        player1Hashtag = findViewById(R.id.player1HashtagTextView);
        player1ConstraintLayout = findViewById(R.id.player1LayoutColor);
        player1EditText = findViewById(R.id.player1ColorTextInput);
        player1Button = findViewById(R.id.player1ColorButton);

        player1ConstraintLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(player1ColorString)));
        player1Button.setTextColor(Color.parseColor(player1ColorString));
        player1EditText.setText(player1ColorString.substring(1));

        if (ColorMethods.isFontBlackForBackground(player1ColorString)) {
            player1ColorTextView.setTextColor(Color.parseColor("#000000"));
            player1HueTextView.setTextColor(Color.parseColor("#000000"));
            player1SaturationTextView.setTextColor(Color.parseColor("#000000"));
            player1ValueTextView.setTextColor(Color.parseColor("#000000"));
            player1HueSeekBar.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            player1HueSeekBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            player1HueSeekBar.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            player1SaturationSeekBar.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            player1SaturationSeekBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            player1SaturationSeekBar.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            player1ValueSeekBar.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            player1ValueSeekBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            player1ValueSeekBar.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            player1Hashtag.setTextColor(Color.parseColor("#000000"));
            player1EditText.setTextColor(Color.parseColor("#000000"));
            player1EditText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            player1Button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        } else {
            player1ColorTextView.setTextColor(Color.parseColor("#ffffff"));
            player1HueTextView.setTextColor(Color.parseColor("#ffffff"));
            player1SaturationTextView.setTextColor(Color.parseColor("#ffffff"));
            player1ValueTextView.setTextColor(Color.parseColor("#ffffff"));
            player1HueSeekBar.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            player1HueSeekBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            player1HueSeekBar.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            player1SaturationSeekBar.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            player1SaturationSeekBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            player1SaturationSeekBar.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            player1ValueSeekBar.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            player1ValueSeekBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            player1ValueSeekBar.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            player1Hashtag.setTextColor(Color.parseColor("#ffffff"));
            player1EditText.setTextColor(Color.parseColor("#ffffff"));
            player1EditText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            player1Button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
        }
    }

    public void sethsvPlayer1(String colorString) {
        player1HueSeekBar = findViewById(R.id.player1HueSeekBar);
        player1SaturationSeekBar = findViewById(R.id.player1SaturationSeekBar);
        player1ValueSeekBar = findViewById(R.id.player1ValueSeekBar);

        float[] hsv;
        hsv = ColorMethods.hexStringTohsv(colorString);
        if (hsv[0] >= 0.0f)
            player1ColorHue = hsv[0];
        if (hsv[1] >= 0.0f)
            player1ColorSaturation = hsv[1];
        player1ColorValue = hsv[2];

        player1HueSeekBar.setProgress(Math.round(player1ColorHue));
        player1SaturationSeekBar.setProgress(Math.round(player1ColorSaturation * 1000));
        player1ValueSeekBar.setProgress(Math.round(player1ColorValue * 1000));
    }

    public void setColorsPlayer2(String colorString) {
        player2ColorString = colorString;

        player2ColorTextView = findViewById(R.id.player2ColorTextView);
        player2HueTextView = findViewById(R.id.player2HueTextView);
        player2SaturationTextView = findViewById(R.id.player2SaturationTextView);
        player2ValueTextView = findViewById(R.id.player2ValueTextView);
        player2HueSeekBar = findViewById(R.id.player2HueSeekBar);
        player2SaturationSeekBar = findViewById(R.id.player2SaturationSeekBar);
        player2ValueSeekBar = findViewById(R.id.player2ValueSeekBar);
        player2Hashtag = findViewById(R.id.player2HashtagTextView);
        player2ConstraintLayout = findViewById(R.id.player2LayoutColor);
        player2EditText = findViewById(R.id.player2ColorTextInput);
        player2Button = findViewById(R.id.player2ColorButton);

        player2ConstraintLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(player2ColorString)));
        player2Button.setTextColor(Color.parseColor(player2ColorString));
        player2EditText.setText(player2ColorString.substring(1));

        if (ColorMethods.isFontBlackForBackground(player2ColorString)) {
            player2ColorTextView.setTextColor(Color.parseColor("#000000"));
            player2HueTextView.setTextColor(Color.parseColor("#000000"));
            player2SaturationTextView.setTextColor(Color.parseColor("#000000"));
            player2ValueTextView.setTextColor(Color.parseColor("#000000"));
            player2HueSeekBar.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            player2HueSeekBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            player2HueSeekBar.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            player2SaturationSeekBar.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            player2SaturationSeekBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            player2SaturationSeekBar.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            player2ValueSeekBar.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            player2ValueSeekBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            player2ValueSeekBar.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            player2Hashtag.setTextColor(Color.parseColor("#000000"));
            player2EditText.setTextColor(Color.parseColor("#000000"));
            player2EditText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            player2Button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        } else {
            player2ColorTextView.setTextColor(Color.parseColor("#ffffff"));
            player2HueTextView.setTextColor(Color.parseColor("#ffffff"));
            player2SaturationTextView.setTextColor(Color.parseColor("#ffffff"));
            player2ValueTextView.setTextColor(Color.parseColor("#ffffff"));
            player2HueSeekBar.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            player2HueSeekBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            player2HueSeekBar.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            player2SaturationSeekBar.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            player2SaturationSeekBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            player2SaturationSeekBar.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            player2ValueSeekBar.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            player2ValueSeekBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            player2ValueSeekBar.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            player2Hashtag.setTextColor(Color.parseColor("#ffffff"));
            player2EditText.setTextColor(Color.parseColor("#ffffff"));
            player2EditText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            player2Button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
        }
    }

    public void sethsvPlayer2(String colorString) {
        player2HueSeekBar = findViewById(R.id.player2HueSeekBar);
        player2SaturationSeekBar = findViewById(R.id.player2SaturationSeekBar);
        player2ValueSeekBar = findViewById(R.id.player2ValueSeekBar);

        float[] hsv;
        hsv = ColorMethods.hexStringTohsv(colorString);
        if (hsv[0] >= 0.0f)
            player2ColorHue = hsv[0];
        if (hsv[1] >= 0.0f)
            player2ColorSaturation = hsv[1];
        player2ColorValue = hsv[2];

        player2HueSeekBar.setProgress(Math.round(player2ColorHue));
        player2SaturationSeekBar.setProgress(Math.round(player2ColorSaturation * 1000));
        player2ValueSeekBar.setProgress(Math.round(player2ColorValue * 1000));
    }

    public void saveColors(String newPlayer1ColorString, String newPlayer2ColorString) {
        player1ColorString = newPlayer1ColorString;
        player2ColorString = newPlayer2ColorString;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("player1Color", player1ColorString);
        editor.putString("player2Color", player2ColorString);
        editor.commit();
    }
}