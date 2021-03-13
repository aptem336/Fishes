package com.example.fishes;

import android.view.MotionEvent;
import android.view.View;

public class Listener implements View.OnTouchListener {
    public static Vector location = new Vector(Data.width / 2, Data.height / 2);

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        double x, y;
        x = event.getX();
        y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                location.x = x;
                location.y = y;
                break;
            case MotionEvent.ACTION_MOVE:
                location.x = x;
                location.y = y;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }
}
