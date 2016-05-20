package com.hguarnieri.cardapiododia.utils;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ScheduleAdapter extends BaseAdapter {
    private Activity activity;
    private int type;

    public ScheduleAdapter(Activity activity, Schedule schedule){
        this.activity = activity;
        this.type = schedule.toInt();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView scheduleTextView = new TextView(this.activity);
        scheduleTextView.setLayoutParams(new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, ListView.LayoutParams.WRAP_CONTENT));
        scheduleTextView.setText(Schedules.getSchedule(this.type, position));
        scheduleTextView.setTextSize(16);
        scheduleTextView.setGravity(Gravity.CENTER_HORIZONTAL);

        String next = Schedules.getNextTime(this.type);
        if (next.equals(Schedules.getSchedule(type, position))) {
            scheduleTextView.setTypeface(null, Typeface.BOLD);
        }

        return scheduleTextView;
    }

    @Override
    public int getCount() {
        return Schedules.getLength(type);
    }

    @Override
    public Object getItem(int position) {
        return Schedules.getSchedule(this.type, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}