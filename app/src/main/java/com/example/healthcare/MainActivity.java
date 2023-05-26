package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button advice_20f, advice1_20f, page_20f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        advice_20f = findViewById(R.id.advice_20f);
        advice1_20f = findViewById(R.id.advice1_20f);
        page_20f = findViewById(R.id.page_20f);

        advice1_20f.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), RecordActivity.class));
        });

        advice_20f.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), AdviceActivity.class));
        });

        page_20f.setOnClickListener(view -> {
            Intent a = new Intent(getApplicationContext(), ContactActivity.class);
            startActivity(a);
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