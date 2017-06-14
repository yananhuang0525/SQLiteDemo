package com.panku.sqlitedemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Date：2017/6/13
 * Time: 10:18
 * author: huangyanan
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pay_order.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE pay_order (code VARCHAR PRIMARY KEY, username VARCHAR, state VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS pay_order");
    }
}
