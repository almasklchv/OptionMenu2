package com.example.optionmenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import classes.MediaPerson;

public class EditMediaPersonActivity extends AppCompatActivity {
    EditText editChannelPlace;
    EditText editChannelName;
    EditText editChannelSubscribers;
    Button btnSave;
    MediaPerson mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_media_person);

        editChannelPlace = findViewById(R.id.editChannelPlace);
        editChannelName = findViewById(R.id.editChannelName);
        editChannelSubscribers = findViewById(R.id.editChannelSubscribers);
        btnSave = findViewById(R.id.btnSave);


        Intent intent = getIntent();

        mp.setChannelPlace((int) intent.getExtras().get("editChannelPlace"));


    }


}