package com.example.seid.wssa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SEID on 3/27/2019.
 */

public class Local   extends SQLiteOpenHelper {
    public Local(Context context) {
        super(context,"PaymentHistory.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table history(id integer primary key autoincrement,name text,month text,amount text,date date)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists history");
    }
}