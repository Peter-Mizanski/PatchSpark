package com.example.patchspark;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOpener extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PatchSparkDB";
    private static final int DATABASE_VERSION = 4;
    public static final String TABLE_NAME = "MODULE_PROMPTS";
    public static final String COLUMN_MAKE = "MAKE";
    public static final String COLUMN_MODULE = "MODULE";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_PROMPT = "PROMPT";
    public static final String COLUMN_INCLUDE = "INCLUDE";
    Context context;



    public DatabaseOpener(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
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
        insertDataFromCSV(database);
    }


    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }


    private void insertDataFromCSV(SQLiteDatabase database) {
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.module_prompts);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");

                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_MAKE, values[1].trim());
                contentValues.put(COLUMN_MODULE, values[2].trim());
                contentValues.put(COLUMN_TITLE, values[3].trim());
                contentValues.put(COLUMN_PROMPT, values[4].trim());
                contentValues.put(COLUMN_INCLUDE, Integer.parseInt(values[5].trim()));

                database.insert(TABLE_NAME, null, contentValues);
            }

            bufferedReader.close();
            inputStream.close();
        } catch (IOException e) {
            Log.e("DatabaseOpener", "Error reading CSV file: " + e.getMessage());
        }
    }


    public List<String> getUniqueMakes() {
        List<String> makes = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(true, TABLE_NAME, new String[]{COLUMN_MAKE},
                null, null, null,
                null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                makes.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MAKE)));
            }
            cursor.close();
        }
        database.close();
        return makes;
    }


    public List<String> getModelsForMake(String make) {
        List<String> models = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(true, TABLE_NAME, new String[]{COLUMN_MODULE},
                COLUMN_MAKE + "=?", new String[]{make},
                null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                models.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MODULE)));
            }
            cursor.close();
        }
        database.close();
        return models;
    }


    public String[] getRandomPrompt(String make, String model) {
        SQLiteDatabase database = this.getReadableDatabase();
        String[] columns = {COLUMN_TITLE, COLUMN_PROMPT};
        String selection = COLUMN_MAKE + "=? AND " + COLUMN_MODULE + "=?";
        String[] selectionArgs = {make, model};
        Cursor cursor = database.query(TABLE_NAME, columns, selection, selectionArgs,
                null, null, "RANDOM()", "1");
        String[] selectedPrompt = new String[2];
        if (cursor != null && cursor.moveToFirst()) {
            selectedPrompt[0] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
            selectedPrompt[1] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PROMPT));
            cursor.close();
        }
        database.close();
        return selectedPrompt;
    }
}
