package com.example.patchspark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class Specific extends AppCompatActivity {

    TextView promptTitle;
    TextView specificPrompt;
    CSVSimpleUtility csvSimpleUtility;
    String selectedMake;
    String selectedModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        promptTitle = (TextView) findViewById(R.id.prompt_title);
        specificPrompt = (TextView) findViewById(R.id.specific_module_text_view);
        csvSimpleUtility = new CSVSimpleUtility(this, R.raw.module_prompts);

        Intent intent = getIntent();
        if (intent != null) {
            selectedMake = intent.getStringExtra("selectedMake");
            selectedModel = intent.getStringExtra("selectedModel");
            Log.d("SPECIFIC_DEBUG", "Received Make: " + selectedMake);
            Log.d("SPECIFIC_DEBUG", "Received Model: " + selectedModel);
        } else {
            Log.d("SPECIFIC_DEBUG", "Intent is null");
        }

        setPromptTitle();
        setSpecificPromptIdea();

        Button regen = (Button) findViewById(R.id.specific_regen);
        regen.setOnClickListener(view -> setSpecificPromptIdea());
    }


    private void setPromptTitle() {
        String title = csvSimpleUtility.setSpecificPromptTitle(selectedMake, selectedModel);
        Log.d("DEBUG", "Title: " + title);
        promptTitle.setText(title);
    }


    private void setSpecificPromptIdea() {
        String prompt = csvSimpleUtility.setSpecificPromptIdea(selectedMake, selectedModel);
        Log.d("DEBUG", "Prompt: " + prompt);
        specificPrompt.setText(prompt);
    }
}