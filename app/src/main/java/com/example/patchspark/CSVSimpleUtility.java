package com.example.patchspark;

import android.content.Context;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CSVSimpleUtility {

    private List<String[]> csvData;
    private int lastRandomIndex = -1;


    public CSVSimpleUtility(Context context, int csvResourceId) {
        csvData = readCSV(context, csvResourceId);
    }


    private int getRandomIndex() {
        int rnd;
        do {
            rnd = new Random().nextInt(csvData.size());
        } while (rnd == lastRandomIndex);
        return rnd;
    }


    public String setRandomIdea() {
        if (csvData != null && !csvData.isEmpty()) {
            int randomIndex = getRandomIndex();
            lastRandomIndex = randomIndex;
            return csvData.get(randomIndex)[1];
        } else {
            return String.valueOf(R.string.no_data);
        }
    }


    private List<String[]> readCSV(Context context, int csvResourceId) {
        List<String[]> csvData = new ArrayList<>();
        try {
            InputStream inputStream = context.getResources().openRawResource(csvResourceId);
            BufferedReader reader = new BufferedReader(new InputStreamReader((inputStream)));
            String line;
            while ((line = reader.readLine()) != null ) {
                String[] row = line.split(",");
                csvData.add(row);
            }
            reader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvData;
    }
}
