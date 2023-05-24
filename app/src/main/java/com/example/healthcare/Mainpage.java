package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Mainpage extends AppCompatActivity {
    Button pag_20f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        pag_20f=(Button)findViewById(R.id.pag_20f);
        pag_20f.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent u=new Intent(getApplicationContext(), Mainhomepage.class);
                startActivity(u);

            }
        });
    }
}