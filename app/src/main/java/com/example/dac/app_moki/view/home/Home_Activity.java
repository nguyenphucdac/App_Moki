package com.example.dac.app_moki.view.home;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.adapter.RouterViewPager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Dac on 10/12/2017.
 */
public class Home_Activity extends AppCompatActivity{

    Toolbar home_header;
    TabLayout tab_layout;
    ViewPager view_pager;
    DrawerLayout draw_menu;
    ActionBarDrawerToggle draw_menu_toggel;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;

    private static ViewPager slide_Pager;
    private static int currentPage = 0;
    private static final Integer[] slideImage= {R.drawable.prof_bg, R.drawable.prof_bg};
    private ArrayList<Integer> arrSlideImage = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_2);
        addControls();
    }


    private void addControls() {
        home_header = (Toolbar) findViewById(R.id.home_header);
        home_header.setTitle("");
        setSupportActionBar(home_header);
        setSizeItemHeader();

        view_pager = (ViewPager) findViewById(R.id.view_pager);
        RouterViewPager router = new RouterViewPager(getSupportFragmentManager());
        view_pager.setAdapter(router);

        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        tab_layout.setupWithViewPager(view_pager);

        draw_menu = (DrawerLayout) findViewById(R.id.draw_menu);
        draw_menu_toggel = new ActionBarDrawerToggle(this, draw_menu,R.string.open_menu,R.string.close_menu);
        draw_menu.setDrawerListener(draw_menu_toggel);

        setSupportActionBar(home_header);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        draw_menu_toggel.syncState();

        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collaps_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);

        initSlideShow();

    }

    private void initSlideShow() {
        for(int i=0;i<slideImage.length;i++)
            arrSlideImage.add(slideImage[i]);

        slide_Pager = (ViewPager) findViewById(R.id.slide_pager);
        slide_Pager.setAdapter(new RouterSlide(Home_Activity.this,arrSlideImage));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(slide_Pager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == slideImage.length) {
                    currentPage = 0;
                }
                slide_Pager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }

    private void setSizeItemHeader(){
        if(getSupportActionBar()!=null){
            Drawable drawable= getResources().getDrawable(R.drawable.icon_menu);
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Drawable newdrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 140, 140, true));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(newdrawable);

            Drawable logo = getResources().getDrawable(R.drawable.logo_login);
            home_header.setLogo(logo);
            for (int i = 0; i < home_header.getChildCount(); i++) {
                View child = home_header.getChildAt(i);
                if (child != null)
                    if (child.getClass() == ImageView.class) {
                        ImageView iv2 = (ImageView) child;
                        iv2.setPadding(50,40,110,160);
                        if ( iv2.getDrawable() == logo ) {
                            iv2.setAdjustViewBounds(true);
                        }
                    }
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.header_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(draw_menu_toggel.onOptionsItemSelected(item)){
            return  true;
        }
        return true;
    }

}
