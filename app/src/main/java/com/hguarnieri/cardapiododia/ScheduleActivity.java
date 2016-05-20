package com.hguarnieri.cardapiododia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.hguarnieri.cardapiododia.fragments.ScheduleFragment;

public class ScheduleActivity extends AppCompatActivity {

    SectionsPagerAdapter pagerAdapter;
    ViewPager viewTimesPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedules);

        pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        viewTimesPager = (ViewPager) findViewById(R.id.pager);
        viewTimesPager.setAdapter(pagerAdapter);

        getSupportActionBar().hide();
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