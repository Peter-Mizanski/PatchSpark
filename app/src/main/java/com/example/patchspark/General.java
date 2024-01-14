package com.example.patchspark;

import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class General extends ToolbarNavigation {

    private List<String[]> csvData;
    private TextView generalIdea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        generalIdea = (TextView) findViewById(R.id.general_patch_text_view);
        csvData = readCSV();
        setRandomGeneralIdea();

        Button regen = (Button) findViewById(R.id.general_regen);
        regen.setOnClickListener(view -> setRandomGeneralIdea());
    }


    private int getRandomIndex() {
        int rnd;
        int lastRandomIndex = -1;
        do {
            rnd = new Random().nextInt(csvData.size());
        } while (rnd == lastRandomIndex);
        return rnd;
    }


    private void setRandomGeneralIdea() {
        if (csvData != null && !csvData.isEmpty()) {
            int randomIndex = getRandomIndex();
            generalIdea.setText(csvData.get(randomIndex)[1]);
        } else {
            generalIdea.setText(R.string.no_data);
        }
    }


    private List<String[]> readCSV() {
        List<String[]> csvData = new ArrayList<>();
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.general_prompts);
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