package com.example.optionmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import classes.MediaPerson;

public class YouTubeAndTwitchAdapter extends ArrayAdapter<MediaPerson> {
    Context context;
    ArrayList<MediaPerson> youtubersOrTwitchStreamers;

    public YouTubeAndTwitchAdapter(Context context, ArrayList<MediaPerson> youtubers) {
        super(context, R.layout.item_youtubers_and_twitch, youtubers);
        this.context = context;
        this.youtubersOrTwitchStreamers = youtubers;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MediaPerson youtuber = this.youtubersOrTwitchStreamers.get(position);

        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.item_youtubers_and_twitch, null, false);

        TextView tvChannelName = view.findViewById(R.id.tvChannelName);
        TextView tvChannelPlace = view.findViewById(R.id.tvChannelPlace);
        TextView tvChannelSubscribers = view.findViewById(R.id.tvChannelSubscribers);

        tvChannelName.setText(youtuber.getChannelName());
        tvChannelPlace.setText(youtuber.getChannelPlace() + "");
        tvChannelSubscribers.setText(youtuber.getChannelSubscribers());

        view.setTag(youtuber.getChannelPlace());

        return view;
    }
}
