package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Mainhomepage extends AppCompatActivity {
    Button advice_20f, advice1_20f, page_20f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainhomepage);
        advice_20f = (Button) findViewById(R.id.advice_20f);
        advice1_20f = (Button) findViewById(R.id.advice1_20f);
        page_20f = (Button) findViewById(R.id.page_20f);

        advice_20f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(), Mainadvice1.class);
                startActivity(a);
            }
        });


        advice1_20f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(), Mainadvice2.class);
                startActivity(a);
            }
        });


        page_20f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(), Mainpage.class);
                startActivity(a);


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            /*case R.id.menu_settings:
                Toast.makeText(this, "Settings clicked!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_file:
                Toast.makeText(this, "File clicked!", Toast.LENGTH_SHORT).show();
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}