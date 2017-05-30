package com.example.jakubaniola.patienttag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                openActivity();
            }
        }, 2000);
    }

    private void openActivity() {
        Intent newActivity = new Intent(this, CheckTagActivity.class);
        startActivity(newActivity);
    }

}
