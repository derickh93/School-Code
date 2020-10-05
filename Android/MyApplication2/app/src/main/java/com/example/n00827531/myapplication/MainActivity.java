package com.example.n00827531.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        view.setBackgroundColor(Color.argb(255, 255, 255, 255));
        TextView textView = (TextView) findViewById(R.id.text_view);

        Random rnd = new Random();
        int n = rnd.nextInt(5);
        if(n == 1)
         textView.setTextColor(Color.RED);
        else if (n == 2)
        textView.setTextColor(Color.GREEN);
        else if (n == 3)
            textView.setTextColor(Color.BLUE);
        else if (n == 4)
            textView.setTextColor(Color.MAGENTA);
        else
            textView.setTextColor(Color.BLACK);

    }
}
