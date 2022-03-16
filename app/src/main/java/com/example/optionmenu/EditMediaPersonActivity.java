package com.example.optionmenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import classes.MediaPerson;

public class EditMediaPersonActivity extends AppCompatActivity {
    EditText editChannelName;
    EditText editChannelSubscribers;
    Button btnSave;
    MediaPerson mp = new MediaPerson(1, "default", "default mln");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_media_person);

        editChannelName = findViewById(R.id.editChannelName);
        editChannelSubscribers = findViewById(R.id.editChannelSubscribers);
        btnSave = findViewById(R.id.btnSave);


        Intent intent = getIntent();
        int tempChannelPlace = (int) intent.getExtras().get("editChannelPlace");
        String tempChannelName = intent.getExtras().get("editChannelName").toString();
        String tempChannelSubscribers = intent.getExtras().get("editChannelSubscribers").toString();
        mp.setChannelPlace(tempChannelPlace);
        mp.setChannelName(tempChannelName);
        mp.setChannelSubscribers(tempChannelSubscribers);

        intent.putExtra("mediaPerson", mp);

        editChannelName.setText(tempChannelName);
        editChannelSubscribers.setText(tempChannelSubscribers);
    }


    public void btnClicked(View view) {
        if (String.valueOf(editChannelName.getText()).equals("") || editChannelName == null) {
            editChannelName.setError("Введите название канала!");
        } else if (String.valueOf(editChannelSubscribers.getText()).equals("") || editChannelSubscribers == null) {
            editChannelSubscribers.setError("Введите количество подписчиков!");
        } else {
            // AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(EditMediaPersonActivity.this);
            builder.setTitle("Сохранить изменения в выбранном вами пункт?");
            builder.setCancelable(false);
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == DialogInterface.BUTTON_POSITIVE) {
                        Intent intent = new Intent();
                        MediaPerson addMediaPerson = new MediaPerson(mp.getChannelPlace(), String.valueOf(editChannelName.getText()), String.valueOf(editChannelSubscribers.getText()));
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