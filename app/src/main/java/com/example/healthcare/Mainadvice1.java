package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mainadvice1 extends AppCompatActivity {
    Button advice2_20f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainadvice1);

        advice2_20f=(Button)findViewById(R.id.advice2_20f);
        advice2_20f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent h=new Intent(getApplicationContext(), Mainhomepage.class);
                startActivity(h);

            }
        });


    }
}