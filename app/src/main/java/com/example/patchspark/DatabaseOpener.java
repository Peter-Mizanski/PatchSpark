package com.example.patchspark;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpener extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PatchSparkDB";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "MODULE_PROMPTS";
    public static final String COLUMN_MAKE = "MAKE";
    public static final String COLUMN_MODULE = "MODULE";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_PROMPT = "PROMPT";
    public static final String COLUMN_INCLUDE = "INCLUDE";



    public DatabaseOpener(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase database) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_MAKE + " TEXT, " +
                COLUMN_MODULE + " TEXT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_PROMPT + " TEXT, " +
                COLUMN_INCLUDE + " INTEGER);";
        database.execSQL(createTableQuery);
    }


    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
