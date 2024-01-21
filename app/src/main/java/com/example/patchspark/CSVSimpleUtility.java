package com.example.patchspark;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CSVSimpleUtility {

    private List<String[]> csvData;
    private int lastRandomIndex = -1;


    public CSVSimpleUtility(Context context, int csvResourceId) {
        csvData = readCSV(context, csvResourceId);
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


    public Set<String> getMake() {
        Set<String> makes = new HashSet<>();
        for (String[] row : csvData) {
            makes.add(row[1]);
        }
        return makes;
    }


    public List<String> getModels(String selectedMake) {
        List<String> models = new ArrayList<>();
        for (String[] row : csvData) {
            if (row.length >=4 && row[1].equals(selectedMake)) {
                models.add(row[2]);
            }
        }
        return models;
    }


    public String setSpecificPromptTitle(String selectedMake, String selectedModel) {
        Log.d("MODEL_DEBUG", "Selected Model: " + selectedModel);
        Log.d("MAKE_DEBUG", "Selected Make: " + selectedMake);
        for (String[] row : csvData) {
            Log.d("ROW_DEBUG", "Row: " + Arrays.toString(row));
            if (row.length >= 4 && row[1].equals(selectedMake) && row[2].equals(selectedModel)) {
                String title = row[3];
                Log.d("TITLE_DEBUG", "Title: " + title);
                return title;
            }
        }
        Log.d("TITLE_DEBUG", "No title found");
        return "No title found";
    }

    public String setSpecificPromptIdea(String selectedMake, String selectedModel) {
        Log.d("MODEL_DEBUG", "Selected Model: " + selectedModel);
        Log.d("DEBUG", "Selected Make: " + selectedMake + ", Selected Model: " + selectedModel);

        for (String[] row : csvData) {
            Log.d("ROW_DEBUG", "Row: " + Arrays.toString(row));
            if (row.length >= 4 && row[1].equals(selectedMake) && row[2].equals(selectedModel)) {
                String idea = row[4];
                Log.d("IDEA_DEBUG", "Idea: " + idea);
                return idea;
            }
        }
        Log.d("IDEA_DEBUG", "No idea found");
        return "No idea found";
    }



}
