package com.example.dailylife;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "MoneyDataBase";
    private static final Integer DB_VERSION = 1;
    public static final String TBL_USER = "user";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_user = "CREATE TABLE " + TBL_USER + " (id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Email TEXT, Password TEXT);";
        db.execSQL(create_table_user);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public String getName(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM " + DBHelper.TBL_USER + " WHERE id = "+id;
        Cursor cursor = db.rawQuery(select,null);
        while(cursor.moveToNext()){
            return cursor.getString(1);
        }
        return "";
    }
}

