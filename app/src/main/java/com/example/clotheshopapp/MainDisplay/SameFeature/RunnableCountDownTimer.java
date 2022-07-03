package com.example.clotheshopapp.MainDisplay.SameFeature;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class RunnableCountDownTimer {

    private static final String TAG = "RunnableCountDownTimer";

    Context context;
    public RunnableCountDownTimer(Context context) {
        this.context = context;

    }

    public void countDownTimer(int countTimeInt, TextView timerItemTextView){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //DO SOMETHING
                new CountDownTimer(countTimeInt, 1000){

                    @Override
                    public void onTick(long millisUntilFinished) {
                        int seconds = (int) (millisUntilFinished / 1000);
                        //int minutes = seconds / 60;
                        //seconds = seconds % 60;

                        int day = (int)TimeUnit.SECONDS.toDays(seconds);
                        long hours = TimeUnit.SECONDS.toHours(seconds) - (day *24);
                        long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds)* 60);
                        long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) *60);



                        timerItemTextView.setText(String.format("%02d", day) + ":" + String.format("%02d", hours)
                                + ":" + String.format("%02d", minute));

                    }

                    @Override
                    public void onFinish() {

                        timerItemTextView.setText("Ended Off ");
                    }
                }.start();
            }
        }, 1000);
    }

}

