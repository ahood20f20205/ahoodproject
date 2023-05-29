package com.example.healthcare.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper sInstance;
    private SQLiteDatabase db;

    public static DBHelper getInstance(Context context) {

        // Use the application context, which will ensure that you 
        // don't accidentally leak an Activity's context.
        if (sInstance == null) {
            sInstance = new DBHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private DBHelper(Context context) {
        super(context, "my_user.db", null, 1);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        this.db = db;
        db.execSQL("create Table user_table(name TEXT primary key, contact TEXT, dob TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists user_table");
    }

    //Inside your SQLite helper class
    @Override
    public synchronized void close() {
        if (db != null) {
            db.close();
            super.close();
        }
    }

    public Boolean insertUserData(String name, String contact, String dob, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);
        contentValues.put("password", password);
        long result = db.insert("user_table", null, contentValues);
        return result != -1;
    }

    public Boolean updateUserData(String name, String contact, String dob, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);
        contentValues.put("password", password);
        Cursor cursor = db.rawQuery("Select * from user_table where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = db.update("user_table", contentValues, "name=?", new String[]{name});
            cursor.close();
            return result != -1;
        } else {
            cursor.close();
            return false;
        }
    }

    public Boolean deleteData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from user_table where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = db.delete("user_table", "name=?", new String[]{name});
            cursor.close();
            return result != -1;
        } else {
            cursor.close();
            return false;
        }
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from user_table");
        db.close();
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("Select * from user_table", null);
    }

    public Cursor getUser(String value) {
        Cursor cursor = null;
        try {
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + "user_table" + " WHERE " + "name" + " = ?;", new String[]{value});
            if (cursor.moveToFirst()) {
                Log.e("Record Already Exists", "Table is:" + "user_table" + " ColumnName:" + "name");
            } else {
                Log.e("New Record ", "Table is:" + "user_table" + " ColumnName:" + "name" + " Column Value:" + value);
            }
            return cursor;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            Log.e("TAG", "sqlEx: " + sqlException);
        }
        return cursor;
    }

    public boolean containsUsername(String value) {
        try {
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + "user_table" + " WHERE " + "name" + " = ?;", new String[]{value});
            if (cursor.moveToFirst()) {
                Log.e("Record Already Exists", "Table is:" + "user_table" + " ColumnName:" + "name");
                cursor.close();
                return true;
            } else {
                Log.e("New Record ", "Table is:" + "user_table" + " ColumnName:" + "name" + " Column Value:" + value);
                cursor.close();
                return false;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            Log.e("TAG", "sqlEx: " + sqlException);
        }
        return false;
    }

    public boolean containsPassword(String value) {
        try {
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + "user_table" + " WHERE " + "password" + " = ?;", new String[]{value});
            if (cursor.moveToFirst()) {
                Log.e("Record Already Exists", "Table is:" + "user_table" + " ColumnName:" + "password");
                cursor.close();
                return true;
            } else {
                Log.e("New Record ", "Table is:" + "user_table" + " ColumnName:" + "password" + " Column Value:" + value);
                cursor.close();
                return false;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            Log.e("TAG", "sqlEx: " + sqlException);
        }
        return false;
    }
}





