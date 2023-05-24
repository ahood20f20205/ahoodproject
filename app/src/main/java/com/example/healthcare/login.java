package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    EditText n20, p20;
    Button b20, b220;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        n20 = (EditText) findViewById(R.id.n20);
        p20 = (EditText) findViewById(R.id.p20);
        //get the button
        b20 = (Button) findViewById(R.id.bu20);
        b220 = (Button) findViewById(R.id.bu220);

        b20.setOnClickListener(view -> {
            // get the edit text
            String username = n20.getText().toString();
            String password = p20.getText().toString();

            if (username.isBlank() || password.isBlank())
            //Toast
            {
                Toast.makeText(login.this, "Enter Fields", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(getBaseContext(), Mainhomepage.class));
            }
        });
        b220.setOnClickListener(view -> {
            Intent aho = new Intent(getApplicationContext(), Activity_register.class);
            startActivity(aho);
        });

    }
}



