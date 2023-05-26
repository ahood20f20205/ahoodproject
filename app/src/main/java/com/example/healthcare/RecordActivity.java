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

        db = new DBHelper(this);

        Cursor res = db.getUser("a");
        if (res.getCount() == 0) {
            Toast.makeText(RecordActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
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
            /*String nameTXT = name.getText().toString();
            Boolean isUpdated = db.deleteData(nameTXT);
            if (isUpdated) Toast.makeText(RecordActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
            else Toast.makeText(RecordActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();*/
        });

        logout.setOnClickListener(view -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

    }
}

