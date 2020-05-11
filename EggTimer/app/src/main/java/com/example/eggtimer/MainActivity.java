package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView;
    boolean isCounterActive= false;
    CountDownTimer countDownTimer;
    Button controllerButton;

    public void resetTimer()
    {
        textView.setText("00:30");
        seekBar.setProgress(30);
        countDownTimer.cancel();
        controllerButton.setText("Go!");
        seekBar.setEnabled(true);
        isCounterActive = false;
    }
    public void timeConversionSetOnView(int progress)
    {
        int minutes = (int)progress/60;
        int seconds = progress - minutes*60;
        String min ="";
        String sec = "";

        if(minutes < 10) {
            min = Integer.toString(minutes);
            min = "0" + min;
        }else {
            min = Integer.toString(minutes);
        }
        if(seconds <10){
            sec = Integer.toString(seconds);
            sec = "0" + sec;
        } else {
            sec = Integer.toString(seconds);
        }

        textView.setText(min +" : " + sec);
    }

    public void onClick(View view)
    {
        if(isCounterActive == false)
        {
        isCounterActive = true;
        seekBar.setEnabled(false);
        controllerButton.setText("Reset!");
            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("MillisecondLeft", ""+millisUntilFinished);
                timeConversionSetOnView((int)millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                resetTimer();


            }
        }.start();
    }
        else {
            resetTimer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textViewTimer);
        controllerButton = (Button)findViewById(R.id.controllerButton);


        //Seekbar
        seekBar = (SeekBar)findViewById(R.id.timerSeekBar);

        seekBar.setMax(600);
        seekBar.setProgress(30);
        textView.setText("00:30");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                timeConversionSetOnView(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
