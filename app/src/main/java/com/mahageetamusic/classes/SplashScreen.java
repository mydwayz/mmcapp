package com.mahageetamusic.classes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.mahageetamusic.R;


public class SplashScreen extends MahageetaMusicBaseActivity {
    @Override
    public void onResponseListener(int requestFor, Object mResponse) {

    }

    Handler handler;
    private long timeDelay = 3000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        final Intent i = new Intent(this, IntroActivity.class);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                startActivity(i);
                finish();
            }
        }, timeDelay);

    }
}
