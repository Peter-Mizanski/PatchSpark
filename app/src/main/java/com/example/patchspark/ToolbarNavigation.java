package com.example.patchspark;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class ToolbarNavigation extends AppCompatActivity {
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
            Intent intent = new Intent(this, GeneralPrompt.class);
            startActivity(intent);
            finish();
            return true;
        } else if (menuItem.getItemId() == R.id.menu_challenge) {
            Intent intent = new Intent(this, Challenges.class);
            startActivity(intent);
            finish();
            return true;
        } else if (menuItem.getItemId() == R.id.menu_module_select) {
            Intent intent = new Intent(this, MakeSelector.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}