package com.example.optionmenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
            // AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(AddMediaPersonActivity.this);
            builder.setTitle("Добавить новый пункт?");
            builder.setCancelable(false);
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == DialogInterface.BUTTON_POSITIVE) {
                        Intent intent = new Intent();
                        MediaPerson addMediaPerson = new MediaPerson(String.valueOf(etChannelName.getText()), String.valueOf(etChannelSubscribers.getText()));
                        intent.putExtra("mediaPerson", addMediaPerson);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else if (i == DialogInterface.BUTTON_NEGATIVE) {
                        finish();
                    }
                }
            };
            builder.setPositiveButton("Да", listener);
            builder.setNegativeButton("Нет", listener);
            builder.setNeutralButton("Отмена", null);
            builder.create().show();
            // AlertDialog


        }
    }
}