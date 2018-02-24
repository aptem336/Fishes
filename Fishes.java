package com.example.aptem.fishes;

import android.app.Activity;
import android.os.Bundle;

public class Fishes extends Activity {
    public static Fishes newGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newGame = this;
        Game.start();
    }
}
