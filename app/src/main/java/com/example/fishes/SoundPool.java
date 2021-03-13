package com.example.fishes;

import android.media.AudioManager;

/**
 * Created by aptem on 26.09.2016.
 */
public class SoundPool implements android.media.SoundPool.OnLoadCompleteListener {
    private static android.media.SoundPool sp;
    private static int sound1;
    private static int sound2;
    private static final int MAX_STREAMS = 5;

    SoundPool() {
        sp = new android.media.SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        sp.setOnLoadCompleteListener(this);
        sound1 = sp.load(Fishes.newGame, R.raw.e, 1);
        sound2 = sp.load(Fishes.newGame, R.raw.e10, 1);
    }

    public static void playSound(int choice) {
        switch (choice) {
            case 1:
                sp.play(sound1, 1, 1, 0, 0, 1);
                break;
            case 2:
                sp.play(sound2, 1, 1, 0, 0, 1);
                break;
        }
    }

    @Override
    public void onLoadComplete(android.media.SoundPool soundPool, int sampleId, int status) {
    }
}
