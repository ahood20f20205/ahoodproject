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

public class LoginActivity extends AppCompatActivity {
    EditText et_username, et_password;
    Button btn_login;
    TextView textView_register;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = DBHelper.getInstance(this);

        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        textView_register = (TextView) findViewById(R.id.register_text);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("isToUpdate", false).apply();

        btn_login.setOnClickListener(view -> {
            // get the edit text
            String username = et_username.getText().toString();
            String password = et_password.getText().toString();

            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(LoginActivity.this, "Enter Fields", Toast.LENGTH_SHORT).show();
            } else {
                if (db.containsUsername(username)) {
                    if (db.containsPassword(password)) {
                        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("username", username).apply();
                        startActivity(new Intent(getBaseContext(), MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Wrong username or password!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Wrong username or password!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        textView_register.setOnClickListener(view -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

    }
}



