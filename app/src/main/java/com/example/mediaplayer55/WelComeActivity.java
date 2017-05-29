package com.example.mediaplayer55;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class WelComeActivity extends AppCompatActivity {
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelComeActivity.this,MainActivity.class));
                finish();
            }
        }, 2000);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        handler.removeCallbacksAndMessages(null);
        startActivity(new Intent(WelComeActivity.this,MainActivity.class));
        finish();
        return super.onTouchEvent(event);
    }
}
