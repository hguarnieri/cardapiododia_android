package com.hguarnieri.cardapiododia.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.hguarnieri.cardapiododia.R;
import com.hguarnieri.cardapiododia.enums.Schedule;
import com.hguarnieri.cardapiododia.adapters.ScheduleAdapter;

public class ScheduleFragment extends Fragment {

    Schedule schedule;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        schedule = Schedule.fromInteger(getArguments().getInt("schedule"));

        ListView listView = (ListView) getView().findViewById(R.id.fragment_schedule_list);
        TextView titleTextView = (TextView) getView().findViewById(R.id.fragment_schedule_title);
        TextView termTextView = (TextView) getView().findViewById(R.id.fragment_schedule_term_type);

        switch (schedule) {
            case LEAVES_CENTRAL_BUILDING:
                titleTextView.setText(R.string.leaves_central_building);
                termTextView.setText(R.string.term_days);
                break;
            case MAIN_GATE:
                titleTextView.setText(R.string.main_gate);
                termTextView.setText(R.string.term_days);
                break;
            case ARIVES_CENTRAL_BUILDING:
                titleTextView.setText(R.string.arrives_central_building);
                termTextView.setText(R.string.term_days);
                break;
            case LEAVES_CENTRAL_BUILDING_HOLIDAYS:
                titleTextView.setText(R.string.leaves_central_building);
                termTextView.setText(R.string.holidays);
                termTextView.setTextColor(Color.RED);
                break;
            case MAIN_GATE_HOLIDAYS:
                titleTextView.setText(R.string.main_gate);
                termTextView.setText(R.string.holidays);
                termTextView.setTextColor(Color.RED);
                break;
            case ARIVES_CENTRAL_BUILDING_HOLIDAYS:
                titleTextView.setText(R.string.arrives_central_building);
                termTextView.setText(R.string.holidays);
                termTextView.setTextColor(Color.RED);
                break;
        }

        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(getActivity(), schedule);
        listView.setAdapter(scheduleAdapter);
    }

}