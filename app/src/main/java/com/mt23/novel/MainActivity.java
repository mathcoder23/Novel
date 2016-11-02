package com.mt23.novel;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.keymob.networks.AdManager;
import com.keymob.networks.core.BannerPositions;
import com.keymob.networks.core.BannerSizeType;
import com.keymob.networks.core.IAdEventListener;
import com.keymob.networks.core.PlatformAdapter;
import com.mt23.novel.service.ServiceOne;
import com.mt23.novel.service.ServiceTwo;
import com.mt23.novel.ui.fragment.ChapterContent;
import com.mt23.novel.ui.fragment.ChapterList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ChapterList chapterList;
    private ChapterContent chapterContent;
    final FragmentManager fm = getFragmentManager();
    private void ad()
    {
        AdManager.getInstance().initFromKeymobService(this, "10857", new IAdEventListener() {
            @Override
            public void onLoadedSuccess(int i, Object o, PlatformAdapter platformAdapter) {
                Log.i("xixi","onLoadedSuccess "+platformAdapter.getPlatformName());
            }

            @Override
            public void onLoadedFail(int i, Object o, PlatformAdapter platformAdapter) {
                Log.i("xixi","onLoadedFail");

            }

            @Override
            public void onAdOpened(int i, Object o, PlatformAdapter platformAdapter) {
                Log.i("xixi","onAdOpened");

            }

            @Override
            public void onAdClosed(int i, Object o, PlatformAdapter platformAdapter) {
                Log.i("xixi","onAdClosed");

            }

            @Override
            public void onAdClicked(int i, Object o, PlatformAdapter platformAdapter) {
                Log.i("xixi","onAdClicked");

            }

            @Override
            public void onOtherEvent(String s, int i, Object o, PlatformAdapter platformAdapter) {
              //  Log.i("xixi","onOtherEvent "+platformAdapter.getPlatformName());
            }
        }, false);
        AdManager.getInstance().showRelationBanner(BannerSizeType.BANNER, BannerPositions.BOTTOM_CENTER,0,this);
    }
    private void setDefaultFragment()
    {

        chapterList = new ChapterList();
        chapterList.setOnItemSelectListener(new ChapterList.OnItemSelectListener() {
            @Override
            public void SelectItem(String data,String title) {
                if (chapterContent == null)
                {
                    chapterContent = new ChapterContent();
                }
                setTitle(title);
               fm.beginTransaction()
               .replace(R.id.id_content,chapterContent)
               .commit();
                chapterContent.setLocalData(data);
            }
        });
        fm.beginTransaction()
                .replace(R.id.id_content,chapterList)
                .commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        ad();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent serviceOne = new Intent();
        serviceOne.setClass(MainActivity.this, ServiceOne.class);
        startService(serviceOne);

        Intent serviceTwo = new Intent();
        serviceTwo.setClass(MainActivity.this, ServiceTwo.class);
        startService(serviceTwo);
        setDefaultFragment();
        setTitle("不朽凡人传");
    }

    private void init() {
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(false);
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            // Handle the camera action
            if (chapterContent == null)
            {
                chapterContent = new ChapterContent();
            }
            // 使用当前Fragment的布局替代id_content的控件
            fm.beginTransaction()
                    .replace(R.id.id_content,chapterContent)
                    .commit();

        } else if (id == R.id.nav_gallery) {
            setTitle("不朽凡人传");
            if (chapterList == null)
            {
                chapterList = new ChapterList();
            }
            // 使用当前Fragment的布局替代id_content的控件
            fm.beginTransaction()
                    .replace(R.id.id_content,chapterList)
                    .commit();
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
