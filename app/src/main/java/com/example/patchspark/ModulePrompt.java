package com.example.patchspark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ModulePrompt extends AppCompatActivity {

    TextView promptTitle, specificPrompt;
    DatabaseOpener databaseOpener;
    String selectedMake, selectedModel;


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

        updatePrompt();

        Button regen = (Button) findViewById(R.id.specific_regen);
        regen.setOnClickListener(view -> updatePrompt());
    }


    private void updatePrompt() {
        String[] selectedPrompt = databaseOpener.getRandomPrompt(selectedMake, selectedModel);
        if (selectedPrompt != null && selectedPrompt.length == 2) {
            promptTitle.setText(selectedPrompt[0]);
            specificPrompt.setText(selectedPrompt[1]);
        } else {
            promptTitle.setText(R.string.no_data);
            specificPrompt.setText(R.string.no_data);
        }
    }
}