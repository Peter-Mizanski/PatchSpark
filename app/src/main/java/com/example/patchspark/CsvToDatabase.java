package com.example.patchspark;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CsvToDatabase {
    private static final int CSV_FILE_ID = R.raw.module_prompts;
    private Context context;
    private SQLiteDatabase database;


    public CsvToDatabase(Context context, SQLiteDatabase database) {
        this.context = context;
        this.database = database;
    }



    public void importCsvToDatabase() {
        try {
            InputStream inputStream = context.getResources().openRawResource(CSV_FILE_ID);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                insertData(values);
            }
            reader.close();
            inputStream.close();
            database.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void insertData(String[] values) {
        String insertQuery = "INSERT INTO " + DatabaseOpener.TABLE_NAME + " ("
                + DatabaseOpener.COLUMN_MAKE + ", "
                + DatabaseOpener.COLUMN_MODULE + ", "
                + DatabaseOpener.COLUMN_TITLE + ", "
                + DatabaseOpener.COLUMN_PROMPT + ", "
                + DatabaseOpener.COLUMN_INCLUDE + ") VALUES (?, ?, ?, ?, ?);";

        database.execSQL(insertQuery, values);
    }
}
