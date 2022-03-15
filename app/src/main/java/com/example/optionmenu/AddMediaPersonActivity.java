package com.example.optionmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import classes.MediaPerson;

public class AddMediaPersonActivity extends AppCompatActivity {
    EditText etChannelName;
    EditText etChannelSubscribers;
    Button addSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_media_person);

        etChannelName = findViewById(R.id.etChannelName);
        etChannelSubscribers = findViewById(R.id.etChannelSubscribers);
        addSave = findViewById(R.id.addSave);

    }

    public void btnClicked(View v) {
        if (String.valueOf(etChannelName.getText()).equals("") || etChannelName == null) {
            etChannelName.setError("Введите название канала!");
        } else if (String.valueOf(etChannelSubscribers.getText()).equals("") || etChannelSubscribers == null) {
            etChannelSubscribers.setError("Введите количество подписчиков!");
        } else {
            Intent intent = new Intent();
            MediaPerson addMediaPerson = new MediaPerson(String.valueOf(etChannelName.getText()), String.valueOf(etChannelSubscribers.getText()));
            intent.putExtra("mediaPerson", addMediaPerson);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}