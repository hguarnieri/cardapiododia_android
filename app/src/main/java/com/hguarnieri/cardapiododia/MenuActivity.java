package com.hguarnieri.cardapiododia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hguarnieri.cardapiododia.fragments.MenuFragment;
import com.hguarnieri.cardapiododia.models.Menu;
import com.hguarnieri.cardapiododia.services.MenusService;

import java.util.Calendar;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    SectionsPagerAdapter pagerAdapter;
    ViewPager viewMenusPager;
    Button lunchButton;
    Button dinnerButton;

    static boolean isLunch = true;

    int currentDay = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);

        this.lunchButton = (Button) findViewById(R.id.fragment_menu_lunch_button);
        this.dinnerButton = (Button) findViewById(R.id.fragment_menu_dinner_button);

        createButtonListeners();

        getCurrentDay();

        getMenu();

        getSupportActionBar().hide();
    }

    public void createButtonListeners() {
        this.lunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLunch = true;
                refreshScreen();
            }
        });

        this.dinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLunch = false;
                refreshScreen();
            }
        });
    }

    public void refreshScreen() {
        this.pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        this.viewMenusPager = (ViewPager) findViewById(R.id.pager);
        this.viewMenusPager.setAdapter(pagerAdapter);

        this.viewMenusPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                currentDay = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        if (isLunch) {
            this.lunchButton.setBackgroundResource(R.drawable.menu_selected_button);
            this.dinnerButton.setBackgroundResource(R.drawable.menu_button);
        } else {
            this.dinnerButton.setBackgroundResource(R.drawable.menu_selected_button);
            this.lunchButton.setBackgroundResource(R.drawable.menu_button);
        }

        changeDay();
    }

    public void changeDay() {
        this.viewMenusPager.setCurrentItem(currentDay);
    }

    public void getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK);

        // We don't have Sunday and Saturday
        if (currentDay == 1 || currentDay == 7) {
            this.currentDay = 0;
        } else {
            this.currentDay = currentDay - 1;
        }
    }

    public void getMenu() {
        (new MenusService.DownloadMenu() {
            @Override
            protected void onPostExecute(List<Menu> menus) {
                super.onPostExecute(menus);

                MenusService.menus = menus;

                refreshScreen();
            }
        }).execute();
    }

    public static class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new MenuFragment();
            Bundle bundle = new Bundle();

            bundle.putInt("day_of_week", position);
            bundle.putBoolean("isLunch", isLunch);
            fragment.setArguments(bundle);

            return fragment;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}