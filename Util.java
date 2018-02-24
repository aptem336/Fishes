package com.example.aptem.fishes;

import java.util.concurrent.TimeUnit;

/**
 * Created by aptem on 27.08.2017.
 */
public class Util {

    public static void sleep(int time) {
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException ex) {
        }
    }
}
