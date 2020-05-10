package com.example.fadeoutanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void fade(View view){

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ImageView imageView1 = (ImageView) findViewById(R.id.imageView2);
        imageView.animate().alpha(0).setDuration(2000);

        imageView1.animate().alpha(1).setDuration(2000);
    }

    public void fade1(View view){

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ImageView imageView1 = (ImageView) findViewById(R.id.imageView2);
        imageView.animate().alpha(1).setDuration(2000);

        imageView1.animate().alpha(0).setDuration(2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
