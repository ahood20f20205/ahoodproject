package com.example.healthcare;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.healthcare.db.DBHelper;

public class RecordActivity extends AppCompatActivity {
    TextView name, contact, dob;
    Button update, delete, logout;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);

        update = findViewById(R.id.btn_update);
        delete = findViewById(R.id.btn_delete);
        logout = findViewById(R.id.btn_logout);

        db = DBHelper.getInstance(this);

        String username = PreferenceManager.getDefaultSharedPreferences(this).getString("username", "username");

        Cursor res = db.getUser(username);// problem from here//
        if (res.getCount() == 0) {
            Toast.makeText(RecordActivity.this, "No user found!", Toast.LENGTH_SHORT).show();
            return;
        }

        name.setText(res.getString(0));
        contact.setText(res.getString(1));
        dob.setText(res.getString(2));

        update.setOnClickListener(view -> {
            PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("isToUpdate", true).apply();
            startActivity(new Intent(this, RegisterActivity.class));
        });

        delete.setOnClickListener(view -> {
            db.deleteAll();
            Toast.makeText(this, "Account deleted successfully!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        logout.setOnClickListener(view -> {
            Toast.makeText(this, "logout successfully!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

    }

    //Inside the activity that makes a connection to the helper class
    @Override
    protected void onDestroy () {
        super.onDestroy();
        //call close() of the helper class
        db.close();
    }
}

