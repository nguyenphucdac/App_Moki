package com.example.dac.app_moki.view.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.adapter.RouterViewPager;
import com.example.dac.app_moki.view.fragment.FragmentDialog;
import com.example.dac.app_moki.view.fragment.FragmentDialogExit;
import com.example.dac.app_moki.view.search.Search_Activity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Dac on 10/12/2017.
 */
public class Home_Activity extends AppCompatActivity{
    private Menu menu;
    private DrawerLayout drawMenu;
    private ActionBarDrawerToggle drawMenuToggel;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar homeHeader;
    private TabLayout tabLayout;
    private ViewPager viewPagerListProduct;

    private FragmentDialog dialogFilter;
    private FragmentDialogExit dialogExit;
    private Button filter;
    private Button sort;
    private Button rada;

    private ViewPager slide_Pager;
    private int currentPage = 0;
    private Integer[] slideImage= {R.drawable.prof_bg, R.drawable.prof_bg};
    private ArrayList<Integer> arrSlideImage = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_2);
        addControls();
        addEvents();
        addSlideShow();
    }

    private void addEvents() {
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.FragmentManager fm = getFragmentManager();
                dialogFilter = FragmentDialog.newInstance("Some Title");
                dialogFilter.show(fm, "Sample Fragment");
            }
        });
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.FragmentManager fm = getFragmentManager();
                dialogFilter = FragmentDialog.newInstance("Some Title");
                dialogFilter.show(fm, "Sample Fragment");
            }
        });
        rada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home_Activity.this, "press rada", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void addControls() {
        homeHeader = (Toolbar) findViewById(R.id.home_header);
        homeHeader.setTitle("");
        setSupportActionBar(homeHeader);
        setSizeItemHeader();

        viewPagerListProduct = (ViewPager) findViewById(R.id.view_pager);
        RouterViewPager router = new RouterViewPager(getSupportFragmentManager());
        viewPagerListProduct.setAdapter(router);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPagerListProduct);

        drawMenu = (DrawerLayout) findViewById(R.id.draw_menu);
        drawMenuToggel = new ActionBarDrawerToggle(this, drawMenu,R.string.open_menu,R.string.close_menu);
        drawMenu.setDrawerListener(drawMenuToggel);

        setSupportActionBar(homeHeader);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawMenuToggel.syncState();

        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collaps_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);

        //FragmentManager fragmentManager = getSupportFragmentManager();
        dialogExit = new FragmentDialogExit();
        dialogFilter = new FragmentDialog();
        filter = (Button) findViewById(R.id.filter);
        sort = (Button)findViewById(R.id.sort);
        rada = (Button)findViewById(R.id.rada);
    }

    private void addSlideShow() {
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
        }, 5000, 5000);
    }

    private void setSizeItemHeader(){
        if(getSupportActionBar()!=null){
            Drawable drawable= getResources().getDrawable(R.drawable.icon_menu);
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Drawable newdrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 140, 140, true));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(newdrawable);

            Drawable logo = getResources().getDrawable(R.drawable.logo_login);
            homeHeader.setLogo(logo);
            for (int i = 0; i < homeHeader.getChildCount(); i++) {
                View child = homeHeader.getChildAt(i);
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
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.header_item, menu);

        this.menu = menu;

        MenuItem itemSearch = menu.findItem(R.id.menu_search);
        View menuSearch = MenuItemCompat.getActionView(itemSearch);
        menuSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_Activity.this, Search_Activity.class);
                startActivity(intent);
            }
        });

        final MenuItem itemOptionView = menu.findItem(R.id.menu_option_view);
        View menuOptionView = MenuItemCompat.getActionView(itemOptionView);
        menuOptionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home_Activity.this, "press grid", Toast.LENGTH_LONG).show();
                menu.getItem(0).setIcon(ContextCompat.getDrawable(Home_Activity.this, R.drawable.icon_grid));
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawMenuToggel.onOptionsItemSelected(item)){
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        android.app.FragmentManager fm1 = getFragmentManager();
        dialogExit = FragmentDialogExit.newInstance();
        dialogExit.show(fm1,"");
    }
}
