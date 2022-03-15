package com.example.optionmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.YoutubersItem) {
            Intent intent = new Intent(this, YoutubersListActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.WrldYoutubersItem) {
            Intent intent = new Intent(this, WrldYoutubersListActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.TwitchStreamersItem) {
            Intent intent = new Intent(this, TwitchStreamersListActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.WrldTwitchStreamersItem) {
            Intent intent = new Intent(this, WrldTwitchStreamersActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}