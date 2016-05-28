package com.hguarnieri.cardapiododia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.hguarnieri.cardapiododia.services.SchedulesService;
import com.hguarnieri.cardapiododia.utils.AnalyticsApplication;
import com.hguarnieri.cardapiododia.utils.Functions;

public class MainActivity extends AppCompatActivity {

    Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Google Analytics
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        ((LinearLayout) findViewById(R.id.main_screen_menus)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));

            }
        });

        ((LinearLayout) findViewById(R.id.main_screen_schedules)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ScheduleActivity.class));

            }
        });

        ((TextView) findViewById(R.id.main_screen_leaves_central_building_text)).setText("- " + SchedulesService.getNextTime(0));
        ((TextView) findViewById(R.id.main_screen_main_gate_text)).setText("- " + SchedulesService.getNextTime(1));
        ((TextView) findViewById(R.id.main_screen_arrives_central_building_text)).setText("- " + SchedulesService.getNextTime(2));

        ((TextView) findViewById(R.id.main_screen_date_text)).setText(Functions.getDateAsString(this));

        getSupportActionBar().hide();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mTracker.setScreenName("Opened the MainActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
