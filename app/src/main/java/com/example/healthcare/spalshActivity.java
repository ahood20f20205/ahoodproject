package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class spalshActivity extends AppCompatActivity {
    Handler handler_20f20205;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        handler_20f20205=new Handler();
        handler_20f20205.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new
                        Intent(spalshActivity.this,login.class);
                startActivity(intent);
                finish();
            }
        },9000);
    }
}

