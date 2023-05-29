package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.healthcare.db.DBHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText et_username, et_contact, et_dob, et_password;
    Button btn_register;

    TextView txt_heading;
    DBHelper db;

    TextView textView_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        et_username = findViewById(R.id.et_username);
        et_contact = findViewById(R.id.et_contact);
        et_dob = findViewById(R.id.et_dob);
        et_password = findViewById(R.id.et_password);

        txt_heading = findViewById(R.id.txt_heading);

        btn_register = findViewById(R.id.btn_register);
        textView_login = findViewById(R.id.login_text);

        db = DBHelper.getInstance(this);

        boolean isToUpdate = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("isToUpdate", true);
        if (isToUpdate) {
            txt_heading.setText(R.string.update);
            btn_register.setText(R.string.update_account);
            btn_register.setOnClickListener(view -> {
                String username = et_username.getText().toString();
                String contact = et_contact.getText().toString();
                String dob = et_dob.getText().toString();
                String password = et_password.getText().toString();
                Boolean isUpdated = db.updateUserData(username, contact, dob, password);
                if (isUpdated) {
                    Toast.makeText(this, "Entry Update", Toast.LENGTH_SHORT).show();
                    PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("isToUpdate", false).apply();
                    startActivity(new Intent(this, RecordActivity.class));
                    finish();
                } else Toast.makeText(this, "Entry Not Update", Toast.LENGTH_SHORT).show();
            });
        } else {
            txt_heading.setText(R.string.register);
            btn_register.setText(R.string.register);
            btn_register.setOnClickListener(view -> {
                String username = et_username.getText().toString();
                String contact = et_contact.getText().toString();
                String dob = et_dob.getText().toString();
                String password = et_password.getText().toString();

                if (username.isBlank() || contact.isBlank() || dob.isBlank() || password.isBlank()) {
                    Toast.makeText(this, "Enter Fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (db.containsUsername(username)) {
                        Toast.makeText(this, "The user " + username + " already exist!", Toast.LENGTH_SHORT).show();
                    } else {
                        Boolean isInserted = db.insertUserData(username, contact, dob, password);
                        if (isInserted) {
                            Toast.makeText(this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, LoginActivity.class));
                            finish();
                        } else
                            Toast.makeText(this, "Registration failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


        textView_login.setOnClickListener(view -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}