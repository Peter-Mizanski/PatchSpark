package com.example.patchspark;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
public class MainActivity extends ToolbarNavigation {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button general = (Button) findViewById(R.id.general);
        general.setOnClickListener(view ->
                startActivity(new Intent(this, General.class)));

        Button specific = (Button) findViewById(R.id.specific);
        specific.setOnClickListener(view ->
                startActivity(new Intent(this, ManuSelector.class)));

        Button challenge = (Button) findViewById(R.id.challenges);
        challenge.setOnClickListener(view ->
                startActivity(new Intent(this, Challenges.class)));
    }
}