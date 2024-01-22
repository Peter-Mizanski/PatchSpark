package com.example.patchspark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

public class ManuSelector extends ToolbarNavigation {

    private Spinner makeSpinner;
    private DatabaseOpener databaseOpener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu_selector);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        makeSpinner = (Spinner) findViewById(R.id.makeSpinner);
        databaseOpener = new DatabaseOpener(this);

        // fill spinner with MAKE values:
        List<String> makes = databaseOpener.getUniqueMakes();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, makes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        makeSpinner.setAdapter(adapter);

        Button continueBtn = (Button) findViewById(R.id.continueBtn);
        continueBtn.setOnClickListener((click) -> {
            String selectedMake = makeSpinner.getSelectedItem().toString();
            Intent intent = new Intent(ManuSelector.this, ModelSelector.class);
            intent.putExtra("selectedMake", selectedMake);
            startActivity(intent);
        });
    }
}