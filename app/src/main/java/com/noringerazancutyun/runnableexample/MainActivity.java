package com.noringerazancutyun.runnableexample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;

import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final int max = 10;
    private Handler handler;
    private ProgressBar pbCount;
    private int cnt;
    private Button startButton;
    private Button stopButton;
    private Switch mSwitch;
    private Thread thread;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTread();
        init();
        startButton();
    }

    private void initTread() {
        thread = new Thread(new Runnable() {
            public void run() {
                try {
                    for (cnt = 0; cnt < max; cnt++) {
                        TimeUnit.MILLISECONDS.sleep(1000);
                        Log.e(LOG_TAG, toString().valueOf(cnt));
                        handler.post(updateProgress);
//                        if(cnt == 4){
//                            handler.post(changeButtonText);
//                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void init() {
        handler = new Handler();
        pbCount = findViewById(R.id.pbCount);
        pbCount.setMax(max);
        pbCount.setProgress(0);
        startButton = findViewById(R.id.button3);
        stopButton = findViewById(R.id.button4);
        mSwitch = findViewById(R.id.switch2);
    }


    private void startButton() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.start();
                changeText();
            }
        });
    }

    Runnable updateProgress = new Runnable() {
        public void run() {
            pbCount.setProgress(cnt);
        }
    };

//    this is method2 for change button text

//    Runnable changeButtonText = new Runnable() {
//        @Override
//        public void run() {
//                startButton.setText(R.string.newStart);
//        }
//    };

    private void changeText(){

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startButton.setText(R.string.newStart);
            }
        },5000);
    }
}

