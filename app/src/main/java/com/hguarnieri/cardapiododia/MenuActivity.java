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

import java.util.List;

public class MenuActivity extends AppCompatActivity {

    SectionsPagerAdapter pagerAdapter;
    ViewPager viewMenusPager;
    Button lunchButton;
    Button dinnerButton;

    static boolean isLunch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);

        getMenu();

        this.lunchButton = (Button) findViewById(R.id.fragment_menu_lunch_button);
        this.dinnerButton = (Button) findViewById(R.id.fragment_menu_dinner_button);

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

        getSupportActionBar().hide();
    }

    public void refreshScreen() {
        this.pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        this.viewMenusPager = (ViewPager) findViewById(R.id.pager);
        this.viewMenusPager.setAdapter(pagerAdapter);
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