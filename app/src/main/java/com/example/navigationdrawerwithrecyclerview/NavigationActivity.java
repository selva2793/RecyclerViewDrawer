package com.example.navigationdrawerwithrecyclerview;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.navigationdrawerwithrecyclerview.constants.Constants;
import com.example.navigationdrawerwithrecyclerview.fragment.AboutFragment;
import com.example.navigationdrawerwithrecyclerview.fragment.FeedsFragment;
import com.example.navigationdrawerwithrecyclerview.fragment.NavigationFragment;
import com.example.navigationdrawerwithrecyclerview.fragment.NewsFragment;
import com.example.navigationdrawerwithrecyclerview.fragment.PopularTagsFragment;
import com.example.navigationdrawerwithrecyclerview.fragment.SettingsFragment;
import com.example.navigationdrawerwithrecyclerview.fragment.WatchLiveFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.drm.DrmStore.Playback.START;
import static android.view.Gravity.RIGHT;
import static android.view.Gravity.LEFT;




public class NavigationActivity extends AppCompatActivity {


   //gitcode
    //https://github.com/dnitinverma/Recyclerview-In-Navigation-Drawer

    
    DrawerLayout drawer;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.toolbar_ivNavigation) ImageView toolbar_ivNavigation;
    @BindView(R.id.toolbar_tvTitle) TextView tvTitle;

    @OnClick(R.id.toolbar_ivNavigation) void onClickNavigation() {

        openCloseDrawer();
    }

    Unbinder unbinder;

    @SuppressWarnings("deprecation")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        unbinder = ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbarTitle(getString(R.string.title_navigation_activity));
        toolbar.setNavigationIcon(null);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        replaceFragment(0);
        replaceNavigationFragment();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(LEFT)) {
            drawer.closeDrawer(LEFT);
        }
        else {
            super.onBackPressed();
        }
    }

    private void openCloseDrawer() {
//        if (drawer.isDrawerOpen(RIGHT)) drawer.closeDrawer(RIGHT);
//        else drawer.openDrawer(RIGHT);

        if (drawer.isDrawerOpen(Gravity.LEFT)) {
            drawer.closeDrawer(Gravity.LEFT);
        }
        else {
            drawer.openDrawer(Gravity.LEFT);
        }

       // return false;
    }


    public void replaceNavigationFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContainerNavigationMenu, NavigationFragment.newInstance(), "Navigation").commit();
    }

    public void replaceFragment(int position) {
        Fragment fragment = null;
        String tag = null;

        switch (position) {
            case 0:
                fragment = NewsFragment.newInstance();
                tag = Constants.TAG_FRG_NEWS;
                break;
            case 1:
                fragment = FeedsFragment.newInstance();
                tag = Constants.TAG_FRG_FEEDS;
                break;
            case 2:
                fragment = WatchLiveFragment.newInstance();
                tag = Constants.TAG_FRG_WATCH_LIVE;
                break;
            case 3:
                fragment = PopularTagsFragment.newInstance();
                tag = Constants.TAG_FRG_POPULAR_TAGS;
                break;
            case 4:
                fragment = SettingsFragment.newInstance();
                tag = Constants.TAG_FRG_SETTINGS;
                break;
            case 5:
                fragment = AboutFragment.newInstance();
                tag = Constants.TAG_FRG_ABOUT;
                break;
        }

        replaceFragment(fragment,tag);

    }

    public void replaceFragment(Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContainerFragment, fragment,tag)
                .commit();

        setToolbarTitle(tag);
        closeNavigationDrawer();
    }

    public void setToolbarTitle(String title) {
        tvTitle.setText(title);
    }

    public void closeNavigationDrawer() {
        if (drawer.isDrawerOpen(LEFT)) drawer.closeDrawer(LEFT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


}
