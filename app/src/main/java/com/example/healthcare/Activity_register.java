package com.example.healthcare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_register extends AppCompatActivity {
    EditText na_20f, co_20f, Da_20f;
    Button btnInsert_20f, btnUpdate_20f, btn_20f, btnView_20f;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        na_20f = findViewById(R.id.na_20f);
        co_20f = findViewById(R.id.co_20f);
        Da_20f = findViewById(R.id.Da_20f);
        btnInsert_20f = findViewById(R.id.btnInsert_20f);
        btnUpdate_20f = findViewById(R.id.btnUpdate_20f);
        btn_20f = findViewById(R.id.btn_20f);
        btnView_20f = findViewById(R.id.btnView_20f);
        DB = new DBHelper(this);

        btnInsert_20f.setOnClickListener(view -> {
            String nameTXT = na_20f.getText().toString();
            String contactTXT = co_20f.getText().toString();
            String dobTXT = Da_20f.getText().toString();
            Boolean checkinsertdata = DB.insertuserdata(nameTXT, contactTXT, dobTXT);
            if (checkinsertdata)
                Toast.makeText(Activity_register.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(Activity_register.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();

        });

        btnUpdate_20f.setOnClickListener(view -> {
            String nameTXT = na_20f.getText().toString();
            String contactTXT = co_20f.getText().toString();
            String dobTXT = Da_20f.getText().toString();
            Boolean checkUpdatedata = DB.insertuserdata(nameTXT, contactTXT, dobTXT);
            if (checkUpdatedata)
                Toast.makeText(Activity_register.this, "Entry Update", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(Activity_register.this, "Entry Not Update", Toast.LENGTH_SHORT).show();
        });

        btn_20f.setOnClickListener(view -> {
            String nameTXT = na_20f.getText().toString();
            Boolean checkudeletedata = DB.deletedata(nameTXT);
            if (checkudeletedata)
                Toast.makeText(Activity_register.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(Activity_register.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
        });


        btnView_20f.setOnClickListener(view -> {
            Cursor res = DB.getdata();
            if (res.getCount() == 0) {
                Toast.makeText(Activity_register.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuilder buffer = new StringBuilder();
            while (res.moveToNext()) {
                buffer.append("Name :").append(res.getString(0)).append("\n");
                buffer.append("Contact :").append(res.getString(1)).append("\n");
                buffer.append("Date of Birth :").append(res.getString(2)).append("\n\n");
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(Activity_register.this);
            builder.setCancelable(true);
            builder.setTitle("User Entries");
            builder.setMessage(buffer.toString());
            builder.show();
        });
    }
}

