    package com.example.patchspark;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.appcompat.widget.Toolbar;

    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
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
            Intent intentMake = getIntent();
            if (intentMake != null && intentMake.hasExtra("selectedMake")) {
                String selectedMake = intentMake.getStringExtra("selectedMake");
                Log.d("MODULE_SELECTOR_DEBUG", "Received Make: " + selectedMake);
                populateModelSpinner(selectedMake);
            }

            Button continueBtn = findViewById(R.id.continueBtn);
            continueBtn.setOnClickListener((click) -> {
                // get Model
                String selectedModel = modelSpinner.getSelectedItem().toString();
                // pass Make and Model
                Intent intentModel = new Intent(this, Specific.class);
                intentModel.putExtra("selectedMake", getIntent().getStringExtra("selectedMake"));
                intentModel.putExtra("selectedModel", selectedModel);
                Log.d("MODEL_DEBUG", "Selected Model in ModSelector: " + selectedModel);
                startActivity(intentModel);
            });
        }


        private void populateModelSpinner(String selectedMake) {
            List<String> models = csvSimpleUtility.getModels(selectedMake);
            ArrayAdapter<String> modelAdapter = new ArrayAdapter<>
                    (this, android.R.layout.simple_spinner_item, models);
            modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            modelSpinner.setAdapter(modelAdapter);
        }
    }