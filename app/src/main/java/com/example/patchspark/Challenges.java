package com.example.patchspark;

import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Challenges extends ToolbarNavigation {

    private TextView challengeIdea;
    private CSVSimpleUtility csvSimpleUtility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        challengeIdea = (TextView) findViewById(R.id.challenge_text_view);
        csvSimpleUtility = new CSVSimpleUtility(this, R.raw.challenge_prompts);
        setRandomIdea();

        Button regen = (Button) findViewById(R.id.challenge_regen);
        regen.setOnClickListener(view -> setRandomIdea());
    }


    private void setRandomIdea() {
        String randomIdea = csvSimpleUtility.setRandomIdea();
        challengeIdea.setText(randomIdea);
    }
}