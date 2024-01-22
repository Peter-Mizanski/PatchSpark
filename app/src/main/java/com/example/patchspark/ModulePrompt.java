package com.example.patchspark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

public class ModulePrompt extends AppCompatActivity {

    TextView promptTitle;
    TextView specificPrompt;
    DatabaseOpener databaseOpener;
    String selectedMake;
    String selectedModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_prompt);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        promptTitle = (TextView) findViewById(R.id.prompt_title);
        specificPrompt = (TextView) findViewById(R.id.specific_module_text_view);

        databaseOpener = new DatabaseOpener(this);

        selectedMake = getIntent().getStringExtra("selectedMake");
        selectedModel = getIntent().getStringExtra("selectedModel");

        String[] selectedPrompt = databaseOpener.getRandomPrompt(selectedMake, selectedModel);

        promptTitle.setText(selectedPrompt[0]);
        specificPrompt.setText(selectedPrompt[1]);
    }
}