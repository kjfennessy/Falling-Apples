package com.example.kellyfennessy.applesfalling;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class AnimatedView extends ImageView {
    Context mContext;
    Handler h;
    private final int FRAME_RATE = 50;
    BitmapDrawable apple;
    int appleX;
    int appleY;
    int appleHeight;
    int appleWidth;
    Random myRandomGenerator;
    int applePixelSpeed;
    TextView winsView;
    TextView lossesView;

    public AnimatedView(Context context, AttributeSet attrs)  {
        super(context, attrs);
        mContext = context;
        h = new Handler();

        myRandomGenerator = new Random();

        apple = (BitmapDrawable) ContextCompat.getDrawable(mContext, R.drawable.myapple);
        appleHeight = apple.getIntrinsicHeight()/5;
        appleWidth = apple.getIntrinsicWidth()/5;
        Bitmap bitmapResized = Bitmap.createScaledBitmap(apple.getBitmap(), appleWidth, appleHeight, false);
        apple = new BitmapDrawable(getResources(), bitmapResized);

        appleY = -appleHeight;
        appleX = myRandomGenerator.nextInt(600);
        applePixelSpeed = 10;

    }
    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    @Override
    protected void onDraw(Canvas c) {

        c.drawBitmap(apple.getBitmap(), appleX, appleY, null);



        if (appleY-appleHeight>this.getHeight()) {
            appleY=-appleHeight;
            appleX = myRandomGenerator.nextInt(this.getWidth()-appleWidth);
            incrementLosses();
        }
        else {
            appleY+=applePixelSpeed;
        }

        h.postDelayed(r,FRAME_RATE);

    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {



        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            int X = (int) ev.getX();
            int Y = (int) ev.getY();


            boolean withinX = (X > appleX) && (X < appleX + appleWidth);
            boolean withinY = (Y > appleY) && (Y < appleY + appleHeight);

            if (withinX && withinY) {
                appleY=-appleHeight;
                appleX = myRandomGenerator.nextInt(this.getWidth()-appleWidth);
                incrementWins();
                applePixelSpeed +=5;
            }
        }
        return true;
    }
    public void setWinsLosses(TextView mywins, TextView mylosses) {
        winsView = mywins;
        lossesView = mylosses;
    }
    public void incrementWins() {

        if (winsView != null) {
            String winString = winsView.getText().toString();
            int winInt = Integer.parseInt(winString);
            winInt++;
            winString = Integer.toString(winInt);
            winsView.setText(winString);
        }
    }
    public void incrementLosses() {
        if (lossesView != null) {
            String losString = lossesView.getText().toString();
            int lossInt = Integer.parseInt(losString);
            lossInt++;
            losString = Integer.toString(lossInt);
            lossesView.setText(losString);
        }

    }
    public void reset() {
        appleY=-appleHeight;
        appleX = myRandomGenerator.nextInt(this.getWidth()-appleWidth);
        applePixelSpeed = 10;
        winsView.setText("0");
        lossesView.setText("0");
    }

}
