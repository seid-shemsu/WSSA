package com.example.seid.wssa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SEID on 3/28/2019.
 */

public class LocalHistory extends SQLiteOpenHelper {

    public LocalHistory(Context context) {
        super(context, "localHistory.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table history(name text, month text, day text, amount text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists history");
        onCreate(db);
    }
}
