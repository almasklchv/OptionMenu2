package com.example.optionmenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import classes.MediaPerson;

public class WrldYoutubersListActivity extends AppCompatActivity {
    ArrayList<MediaPerson> wrldYoutubers;
    YouTubeAndTwitchAdapter adapter;
    public static final int ADD_MEDIA_PERSON_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_MEDIA_PERSON_ACTIVITY_REQUEST_CODE = 2;
    int channelPlace = 10;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrld_youtubers_list);
        init();

        ListView wrldYoutubersList = findViewById(R.id.WrldYoutubersList);
        adapter =
                new YouTubeAndTwitchAdapter(this, wrldYoutubers);
        wrldYoutubersList.setAdapter(adapter);

        wrldYoutubersList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = (int) view.getTag();
                MediaPerson delete = null;
                for (MediaPerson mp : wrldYoutubers) {
                    if (mp.getChannelPlace() == id) {
                        delete = mp;
                        break;
                    }
                }

                if (delete != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(WrldYoutubersListActivity.this);
                    builder.setTitle("Удалить этот пункт?");
                    builder.setCancelable(false);
                    MediaPerson finalDelete = delete;
                    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (i == DialogInterface.BUTTON_POSITIVE) {
                                wrldYoutubers.remove(finalDelete);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "Выбранный вами пункт удален.", Toast.LENGTH_LONG).show();
                            }
                        }
                    };
                    builder.setPositiveButton("Да", listener);
                    builder.setNegativeButton("Нет", null);
                    builder.setNeutralButton("Отмена", null);
                    builder.create().show();
                }
                return true;
            }
        });

        wrldYoutubersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = (int) view.getTag();

                for (MediaPerson mp : wrldYoutubers) {
                    if (mp.getChannelPlace() == id) {
                        Intent intent = new Intent(getApplicationContext(), EditMediaPersonActivity.class);
                        int editChannelPlace = mp.getChannelPlace();
                        String editChannelName = mp.getChannelName();
                        String editChannelSubscribers = mp.getChannelSubscribers();

                        intent.putExtra("editChannelPlace", editChannelPlace);
                        intent.putExtra("editChannelName", editChannelName);
                        intent.putExtra("editChannelSubscribers", editChannelSubscribers);
                        startActivityForResult(intent, 2);
                        break;
                    }
                }
            }
        });
    }

    public void init() {
        wrldYoutubers = new ArrayList<>();
        wrldYoutubers.add(new MediaPerson(1, "T-Series", "210 млн"));
        wrldYoutubers.add(new MediaPerson(2, "Cocomelon - Nursery Rhymes", "130 млн"));
        wrldYoutubers.add(new MediaPerson(3, "SET India", "128 млн"));
        wrldYoutubers.add(new MediaPerson(4, "PewDiePie", "111 млн"));
        wrldYoutubers.add(new MediaPerson(5, "MrBeast", "91.5 млн"));
        wrldYoutubers.add(new MediaPerson(6, "✿ Kids Diana Show", "91.4 млн"));
        wrldYoutubers.add(new MediaPerson(7, "Like Nastya", "88.5 млн"));
        wrldYoutubers.add(new MediaPerson(8, "WWE", "86.2 млн"));
        wrldYoutubers.add(new MediaPerson(9, "Zee Music Company", "82.1 млн"));
        wrldYoutubers.add(new MediaPerson(10, "Vlad and Niki", "79.7 млн"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_youtubers_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addYoutubersList) {
            Intent intent = new Intent(this, AddMediaPersonActivity.class);
            startActivityForResult(intent, ADD_MEDIA_PERSON_ACTIVITY_REQUEST_CODE);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_MEDIA_PERSON_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Новый пункт добавлен.", Toast.LENGTH_LONG).show();

                channelPlace++;
                MediaPerson mp = (MediaPerson) data.getExtras().get("mediaPerson");
                mp.setChannelPlace(channelPlace);
                this.wrldYoutubers.add(mp);
                adapter.notifyDataSetChanged();
            }
        } else if (requestCode == EDIT_MEDIA_PERSON_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Выбранный вами пункт изменен.", Toast.LENGTH_LONG).show();

                MediaPerson mediaPerson = (MediaPerson) data.getExtras().get("mediaPerson");
                for (MediaPerson mp : wrldYoutubers) {
                    if (mp.getChannelPlace() == mediaPerson.getChannelPlace()) {
                        mp.setChannelName(mediaPerson.getChannelName());
                        mp.setChannelSubscribers(mediaPerson.getChannelSubscribers());
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }
}