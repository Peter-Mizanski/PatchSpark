package com.example.patchspark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

public class ModuleSelector extends AppCompatActivity {

    private Spinner modelSpinner;
    private CSVSimpleUtility csvSimpleUtility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_selector);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        modelSpinner = (Spinner) findViewById(R.id.modelSpinner);
        csvSimpleUtility = new CSVSimpleUtility(this, R.raw.module_prompts);

        // get Make from ManufacturerSelector intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selectedMake")) {
            String selectedMake = intent.getStringExtra("selectedMake");
            populateModelSpinner(selectedMake);
        }

        //Button continueBtn = (Button) findViewById(R.id.continueBtn);
        //continueBtn.setOnClickListener((click) -> startActivity(new Intent(this, ModulePrompt.class)));
    }


    private void populateModelSpinner(String selectedMake) {
        List<String> models = csvSimpleUtility.getModels(selectedMake);
        ArrayAdapter<String> modelAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_item, models);
        modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelSpinner.setAdapter(modelAdapter);
    }
}