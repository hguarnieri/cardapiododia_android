package com.hguarnieri.cardapiododia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.hguarnieri.cardapiododia.fragments.MenuFragment;
import com.hguarnieri.cardapiododia.models.Menu;
import com.hguarnieri.cardapiododia.services.MenusService;

import java.util.List;

public class MenuActivity extends AppCompatActivity {

    SectionsPagerAdapter pagerAdapter;
    ViewPager viewMenusPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);

        getMenu();

        getSupportActionBar().hide();
    }

    public void getMenu() {
        (new MenusService.DownloadMenu() {
            @Override
            protected void onPostExecute(List<Menu> menus) {
                super.onPostExecute(menus);

                MenusService.menus = menus;

                refresh();
            }
        }).execute();
    }

    public void refresh() {
        pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        viewMenusPager = (ViewPager) findViewById(R.id.pager);
        viewMenusPager.setAdapter(pagerAdapter);
    }

    public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new MenuFragment();
            Bundle bundle = new Bundle();

            bundle.putInt("dia", position);
            fragment.setArguments(bundle);

            return fragment;
        }

        @Override
        public int getCount() {
            return 5;
        }

    }
}