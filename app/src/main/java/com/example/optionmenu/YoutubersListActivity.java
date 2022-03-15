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

import java.util.ArrayList;

import classes.MediaPerson;

public class YoutubersListActivity extends AppCompatActivity {
    ArrayList<MediaPerson> youtubers;
    YouTubeAndTwitchAdapter adapter;
    public static final int ADD_MEDIA_PERSON_ACTIVITY_REQUEST_CODE = 1;
    int channelPlace = 10;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtubers_list);
        init();

        ListView youtubersList = findViewById(R.id.YoutubersList);
        adapter =
                new YouTubeAndTwitchAdapter(this, youtubers);
        youtubersList.setAdapter(adapter);

        youtubersList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = (int) view.getTag();
                MediaPerson delete = null;
                for (MediaPerson mp : youtubers) {
                    if (mp.getChannelPlace() == id) {
                        delete = mp;
                        break;
                    }
                }

                if (delete != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(YoutubersListActivity.this);
                    builder.setTitle("Удалить этот пункт?");
                    builder.setCancelable(false);
                    MediaPerson finalDelete = delete;
                    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (i == DialogInterface.BUTTON_POSITIVE) {
                                youtubers.remove(finalDelete);
                                adapter.notifyDataSetChanged();
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

        youtubersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = (int) view.getTag();

                for (MediaPerson mp : youtubers) {
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
        youtubers = new ArrayList<>();
        youtubers.add(new MediaPerson(1, "✿ Kids Diana Show", "91.3 млн"));
        youtubers.add(new MediaPerson(2, "Like Nastya", "88.5 млн"));
        youtubers.add(new MediaPerson(3, "A4", "38.6 млн"));
        youtubers.add(new MediaPerson(4, "Get Movies", "38.5 млн"));
        youtubers.add(new MediaPerson(5, "Маша и Медведь", "37.2 млн"));
        youtubers.add(new MediaPerson(6, "Masha and The Bear", "32.4 млн"));
        youtubers.add(new MediaPerson(7, "Mister Max", "22.5 млн"));
        youtubers.add(new MediaPerson(8, "Miss Katy", "21.2 млн"));
        youtubers.add(new MediaPerson(9, "SlivkiShow", "19.8 млн"));
        youtubers.add(new MediaPerson(10, "Мирошка ТВ", "18 млн"));
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
                channelPlace++;
                MediaPerson mp = (MediaPerson) data.getExtras().get("mediaPerson");
                mp.setChannelPlace(channelPlace);
                this.youtubers.add(mp);
                adapter.notifyDataSetChanged();
            }
        }
    }
}