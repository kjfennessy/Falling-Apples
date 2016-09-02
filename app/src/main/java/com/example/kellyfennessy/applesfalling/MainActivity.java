package com.example.kellyfennessy.applesfalling;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final com.example.kellyfennessy.applesfalling.AnimatedView myAV = (com.example.kellyfennessy.applesfalling.AnimatedView) findViewById(R.id.anim_view);

        TextView wins = (TextView) findViewById(R.id.clickedID);
        TextView losses = (TextView) findViewById(R.id.applesDeadID);

        myAV.setWinsLosses(wins,losses);

        Button resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAV.reset();
            }
        });

    }
}
