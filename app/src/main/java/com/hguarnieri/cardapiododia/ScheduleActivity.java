package com.hguarnieri.cardapiododia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.hguarnieri.cardapiododia.fragments.ScheduleFragment;
import com.hguarnieri.cardapiododia.utils.AnalyticsApplication;

public class ScheduleActivity extends AppCompatActivity {

    SectionsPagerAdapter pagerAdapter;
    ViewPager viewTimesPager;

    Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedules);

        // Google Analytics
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        viewTimesPager = (ViewPager) findViewById(R.id.pager);
        viewTimesPager.setAdapter(pagerAdapter);

        getSupportActionBar().hide();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mTracker.setScreenName("Opened the ScheduleActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new ScheduleFragment();
            Bundle bundle = new Bundle();

            bundle.putInt("schedule", position);
            fragment.setArguments(bundle);

            return fragment;
        }

        @Override
        public int getCount() {
            return 6;
        }

    }

}