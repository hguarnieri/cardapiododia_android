package com.hguarnieri.fzea;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.hguarnieri.fzea.R;
import com.hguarnieri.fzea.utils.MenuArrayAdapter;


/**
 * Created by Henrique on 05/08/2014.
 */
public class MenuDrawerActivity extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private ListView menu;

    protected void onCreate(Bundle savedInstanceState, Fragment placeholderFragment,
                               int layout) {
        super.onCreate(savedInstanceState);

        setContentView(layout);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, placeholderFragment)
                    .commit();
        }

        //getSupportActionBar().setTitle("Início");

        navigationDrawer();
    }

    protected void navigationDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getSupportActionBar().setTitle("Início");
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getSupportActionBar().setTitle("Menu");
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        /* Cria os itens do menu */
        menu = (ListView) findViewById(R.id.menu);

        String[] valuesMenu = new String[] { "Cardápio", "Laranjinha" };
        int[] iconsMenu = new int[] {R.drawable.food_color, R.drawable.bus_color};

        menu.addHeaderView(getLayoutInflater().inflate(R.layout.menu_header, null));
        menu.setAdapter(new MenuArrayAdapter(this, valuesMenu, iconsMenu));
        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        //startActivity(new Intent(MenuDrawerActivity.this, LevelSelectActivity.class));
                        break;
                    case 2:
                        //startActivity(new Intent(MenuDrawerActivity.this, ListVideosActivity.class));
                        break;
                    case 3:
                        //startActivity(new Intent(MenuDrawerActivity.this, ListPatentesActivity.class));
                        break;
                }
            }
        });
    }

    protected void open() {
        /* Abre o navigation drawer */
        mDrawerLayout.openDrawer(Gravity.LEFT);

        /* Animação da Gear */
        final ImageView gear = (ImageView)findViewById(R.id.icon_gear);
        final Animation rotationRight = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_right);
        gear.startAnimation(rotationRight);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
