package com.example.patchspark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Set;

public class ManufacturerSelector extends AppCompatActivity {

    private Spinner makeSpinner;
    private CSVSimpleUtility csvSimpleUtility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manufacturer_selector);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        makeSpinner = (Spinner) findViewById(R.id.makeSpinner);
        csvSimpleUtility = new CSVSimpleUtility(this, R.raw.module_prompts);
        getMakes();

        Button continueBtn = (Button) findViewById(R.id.continueBtn);
        continueBtn.setOnClickListener((click) -> {
            // get Make
            String selectedMake = makeSpinner.getSelectedItem().toString();
            // pass Make to ModuleSelector and start that Activity
            Intent intent = new Intent(this, ModuleSelector.class);
            intent.putExtra("selectedMake", selectedMake);
            startActivity(intent);
        });
    }


    private void getMakes() {
        Set<String> makes = csvSimpleUtility.getMake();
        ArrayAdapter<String> manufacturerAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_item, new ArrayList<>(makes));
        manufacturerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        makeSpinner.setAdapter(manufacturerAdapter);
    }
}