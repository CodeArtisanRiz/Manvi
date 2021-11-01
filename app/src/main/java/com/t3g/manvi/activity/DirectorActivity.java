package com.t3g.manvi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.t3g.manvi.R;

public class DirectorActivity extends AppCompatActivity {

    Button mainAct, homeAct, allPostAct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_director);

        mainAct = findViewById(R.id.main_act);
        mainAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DirectorActivity.this, MainActivity.class));
            }
        });

        homeAct = findViewById(R.id.home_act);
        homeAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DirectorActivity.this, HomeActivity.class));
            }
        });

        allPostAct = findViewById(R.id.all_post_act);
        allPostAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DirectorActivity.this, AllPostActivity.class));
            }
        });



    }
}