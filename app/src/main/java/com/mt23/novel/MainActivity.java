package com.mt23.novel;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.mt23.novel.novel.source.Novel;
import com.mt23.novel.novel.source.NovelManager;
import com.mt23.novel.novel.source.NovelResourceManager;
import com.mt23.novel.novel.source.SearchCallBack;
import com.mt23.novel.novel.source.imple.NovelManagerBiQuGe;
import com.mt23.novel.service.ServiceOne;
import com.mt23.novel.service.ServiceTwo;
import com.mt23.novel.ui.fragment.ChapterContent;
import com.mt23.novel.ui.fragment.ChapterList;
import com.mt23.novel.ui.fragment.NovelContent;
import com.mt23.novel.ui.fragment.SearchNovel;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ChapterList chapterList;
    private ChapterContent chapterContent;
    private SearchNovel searchNovel;
    private NovelContent novelContent;
    private int fragmentState = 0;
    final FragmentManager fm = getFragmentManager();
    private void ad()
    {
        AdManager.getInstance().initFromKeymobService(this, "10857", null, false);
        AdManager.getInstance().showRelationBanner(BannerSizeType.BANNER, BannerPositions.BOTTOM_CENTER,0,this);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        //ad();


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
        changeFragment(4,"");
//        setTitle(searchNovel.getFragmentTitle());

    }

    private void init() {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

//                moveTaskToBack(false);
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

            changeFragment(1,"");

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void changeFragment(int id,String str)
    {
        if (id == 2)
        {

            if (chapterList == null)
            {
                chapterList = new ChapterList();

            }
            if (chapterList.isAdded())
            {
                fm.beginTransaction()
                        .hide(searchNovel)
                        .show(chapterList)
                        .addToBackStack(null)
                        .commit();
            }
            else
            {

                fm.beginTransaction()
                        .hide(searchNovel)
                        .add(R.id.id_content,chapterList)
                        .addToBackStack(null)
                        .commit();
            }
            if (str.length() > 0)
            chapterList.updateList(str);

        }
        else if (id == 1)
        {

            if (searchNovel == null)
            {
                searchNovel = new SearchNovel();

            }
           if (searchNovel.isAdded())
           {
               fm.beginTransaction()
                       .hide(chapterContent)
                       .hide(chapterList)
                       .show(searchNovel)
                       .addToBackStack(null)
                       .commit();

           }
           else
           {
               fm.beginTransaction()
                       .add(R.id.id_content,searchNovel)
                       .addToBackStack(null)
                       .commit();
           }


        }
        else if (id == 3)
        {
            if (null == chapterContent)
            {
                chapterContent = new ChapterContent();

            }
            chapterContent.setLocalData(str);
            if (chapterContent.isAdded())
            {
                fm.beginTransaction()
                        .hide(chapterList)
                        .show(chapterContent)
                        .addToBackStack(null)
                        .commit();
            }
            else
            {
                fm.beginTransaction()
                        .hide(chapterList)
                        .add(R.id.id_content,chapterContent)
                        .addToBackStack(null)
                        .commit();
            }



        }
        else if (id == 4)
        {
            if (null == novelContent)
            {
                novelContent = new NovelContent();
            }
            fm.beginTransaction()
                    .add(R.id.id_content,novelContent)
                    .addToBackStack(null)
                    .commit();
        }

    }

}
