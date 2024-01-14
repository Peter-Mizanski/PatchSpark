package com.example.patchspark;

import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class General extends ToolbarNavigation {

    private TextView generalIdea;
    private CSVSimpleUtility csvSimpleUtility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        generalIdea = (TextView) findViewById(R.id.general_patch_text_view);
        csvSimpleUtility = new CSVSimpleUtility(this, R.raw.general_prompts);
        setRandomIdea();

        Button regen = (Button) findViewById(R.id.general_regen);
        regen.setOnClickListener(view -> setRandomIdea());
    }


    private void setRandomIdea() {
        String randomIdea = csvSimpleUtility.setRandomIdea();
        generalIdea.setText(randomIdea);
    }
}