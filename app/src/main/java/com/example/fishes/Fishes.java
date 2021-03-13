package com.example.fishes;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class Fishes extends Activity {
    public static Fishes newGame;
    private static final String HIGH_SCORE_PREFERENCE = "HIGH_SCORE_PREFERENCE";
    private static final String SCORE = "SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        newGame = this;
        Game.start();

        SharedPreferences sharedPreferences = getSharedPreferences(HIGH_SCORE_PREFERENCE, Context.MODE_PRIVATE);
        String score = sharedPreferences.getString(SCORE, "35.0");
        TextView highScoreTextView = findViewById(R.id.score);
        highScoreTextView.setText(score);
        Data.score = score;
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getSharedPreferences(HIGH_SCORE_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SCORE, Data.score);
        editor.apply();
    }
}
