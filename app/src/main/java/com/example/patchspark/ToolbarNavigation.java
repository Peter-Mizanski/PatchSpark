package com.example.patchspark;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class ToolbarNavigation extends AppCompatActivity {
    public void setupToolbar(int toolbarId) {
        Toolbar toolbar = findViewById(toolbarId);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else if (menuItem.getItemId() == R.id.menu_general) {
            Intent intent = new Intent(this, General.class);
            startActivity(intent);
            finish();
            return true;
        } else if (menuItem.getItemId() == R.id.menu_challenge) {
            Intent intent = new Intent(this, Challenges.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}