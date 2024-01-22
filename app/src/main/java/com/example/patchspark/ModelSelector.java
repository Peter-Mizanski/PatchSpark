package com.example.patchspark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

public class ModelSelector extends ToolbarNavigation {

    private Spinner modelSpinner;
    private DatabaseOpener databaseOpener;
    private String selectedMake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_selector);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        modelSpinner = (Spinner) findViewById(R.id.modelSpinner);
        databaseOpener = new DatabaseOpener(this);

        selectedMake = getIntent().getStringExtra("selectedMake");

        // fill spinner with MODEL values:
        List<String> models = databaseOpener.getModelsForMake(selectedMake);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, models);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelSpinner.setAdapter(adapter);

        Button continueBtn = (Button) findViewById(R.id.continueBtn);
        continueBtn.setOnClickListener((click) -> {
            String selectedModel = modelSpinner.getSelectedItem().toString();
            Intent intent = new Intent(ModelSelector.this, ModulePrompt.class);
            intent.putExtra("selectedMake", selectedMake);
            intent.putExtra("selectedModel", selectedModel);
            startActivity(intent);
        });
    }
}