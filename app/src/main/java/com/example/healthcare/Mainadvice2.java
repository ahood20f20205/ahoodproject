package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mainadvice2 extends AppCompatActivity {
    Button button_20f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainadvice2);
        button_20f= (Button)findViewById(R.id.button_20f);
        button_20f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent h=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(h);

            }
        });

    }
}