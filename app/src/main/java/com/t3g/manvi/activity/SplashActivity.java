package com.t3g.manvi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.content.SharedPreferences;

import com.t3g.manvi.R;

public class SplashActivity extends AppCompatActivity {

    int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart",true);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

//                Intent homeIntent = new Intent(SplashActivity.this,MainActivity.class);
//                startActivity(homeIntent);
//                finish();
                if(!firstStart){
                    startActivity(new Intent(
                            SplashActivity.this, DirectorActivity.class));
                }else{
                    startActivity(new Intent(
                            SplashActivity.this, OnBoardingActivity.class));
                }
                finish();


            }
        },SPLASH_TIME_OUT);
    }
}