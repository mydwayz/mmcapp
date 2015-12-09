package com.mahageetamusic.classes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mahageetamusic.R;


public class IntroActivity extends MahageetaMusicBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.login) {
            startActivity(new Intent(IntroActivity.this, LoginScreen.class));
        } else if (view.getId() == R.id.signup) {
            startActivity(new Intent(IntroActivity.this, SignUp.class));
        }
    }

    @Override
    public void onResponseListener(int requestFor, Object mResponse) {

    }
}
