package com.example.fishes;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;

/**
 * Created by aptem on 15.07.2016.
 */
public class Oval extends androidx.appcompat.widget.AppCompatTextView {
    public Vector location;
    public float size;
    public int color;

    public Oval() {
        super(Fishes.newGame);
        location = new Vector(Math.random() * Data.width, Math.random() * Data.height);
        setClickable(false);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawOval(0, 0, size, size, paint);
    }

    protected void updateGeom() {
        setWidth((int) size);
        setHeight((int) size);
        setTranslationX((float) (location.x - size / 2));
        setTranslationY((float) (location.y - size / 2));
    }
}
