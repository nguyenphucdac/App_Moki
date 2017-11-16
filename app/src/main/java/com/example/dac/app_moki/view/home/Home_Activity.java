package com.example.dac.app_moki.view.home;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewAnimator;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.local.value.ValueLocal;
import com.example.dac.app_moki.model.object.User;
import com.example.dac.app_moki.view.adapter.AdapterViewPagerHome;
import com.example.dac.app_moki.view.fragment.FragmentDialogAlert;
import com.example.dac.app_moki.view.fragment.FragmentDialogExit;
import com.example.dac.app_moki.view.fragment.FragmentDialogMessage;
import com.example.dac.app_moki.view.fragment.FragmentMenu;
import com.example.dac.app_moki.view.search.Search_Activity;
import com.tekle.oss.android.animation.AnimationFactory;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

import static com.example.dac.app_moki.R.drawable.icon_menu;

/**
 * Created by Dac on 10/12/2017.
 */
public class Home_Activity extends AppCompatActivity {
    private Menu menu;
    private DrawerLayout drawMenu;
    private ActionBarDrawerToggle drawMenuToggel;
    private AppBarLayout appBarLayout;
    private Toolbar homeHeader;
    private View toolBar;
    private TabLayout tabLayout;
    AdapterViewPagerHome adapterViewPagerHome;
    private ViewPager viewPagerListProduct;

    private FragmentDialogMessage dialogMessage;
    private FragmentDialogAlert dialogAlert;
    private FragmentDialogExit dialogExit;

    private ViewPager slide_Pager;
    private int currentPage;
    private Integer[] slideImage = {R.drawable.prof_bg, R.drawable.prof_bg};
    private ArrayList<Integer> arrSlideImage;

    private ImageButton btnCameraSale;
    private MenuItem itemOptionView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_2);
        addControls();
        addEvents();
        addSlideShow();

    }

    private void addEvents() {
        btnCameraSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                //Intent intent = new Intent(Home_Activity.this, PostProduct.class);
                startActivity(intent);

            }
        });
    }


    private void addControls() {
        Intent myInten = getIntent();
        User user = (User) myInten.getSerializableExtra("user");

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentMenu fragmentMenu = new FragmentMenu(user);

        fragmentTransaction.add(R.id.menu_view, fragmentMenu);
        fragmentTransaction.commit();

        homeHeader = (Toolbar) findViewById(R.id.home_header);
        homeHeader.setTitle("");
        toolBar = findViewById(R.id.toolbar_header);

        setSupportActionBar(homeHeader);
        setSizeItemHeader();
        drawMenu = (DrawerLayout) findViewById(R.id.draw_menu);
        drawMenuToggel = new ActionBarDrawerToggle(Home_Activity.this, drawMenu, R.string.open_menu, R.string.close_menu);
        drawMenuToggel.syncState();

        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);

        viewPagerListProduct = (ViewPager) findViewById(R.id.view_pager);
        adapterViewPagerHome = new AdapterViewPagerHome(getSupportFragmentManager());
        viewPagerListProduct.setAdapter(adapterViewPagerHome);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPagerListProduct);

        dialogMessage = new FragmentDialogMessage();
        dialogAlert = new FragmentDialogAlert();
        dialogExit = new FragmentDialogExit();

        btnCameraSale = (ImageButton) findViewById(R.id.btn_camera_sale);
    }

    private void addSlideShow() {

        currentPage = 0;
        arrSlideImage = new ArrayList<Integer>();

        for (int i = 0; i < slideImage.length; i++)
            arrSlideImage.add(slideImage[i]);

        slide_Pager = (ViewPager) findViewById(R.id.slide_pager);
        slide_Pager.setAdapter(new RouterSlide(Home_Activity.this, arrSlideImage));
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

    private void setSizeItemHeader() {
        if (getSupportActionBar() != null) {
            Drawable drawable = getResources().getDrawable(icon_menu);
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
                        iv2.setPadding(20, 50, 60, 30);
                        if (iv2.getDrawable() == logo) {
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

        MenuItem itemMessage = menu.findItem(R.id.menu_message);
        View menuMessage = MenuItemCompat.getActionView(itemMessage);
        menuMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm1 = getFragmentManager();
                dialogMessage = FragmentDialogMessage.newInstance();
                dialogMessage.show(fm1, "");
            }
        });

        MenuItem itemAlert = menu.findItem(R.id.menu_alert);
        View menuAlert = MenuItemCompat.getActionView(itemAlert);
        menuAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm1 = getFragmentManager();
                dialogAlert = FragmentDialogAlert.newInstance();
                dialogAlert.show(fm1, "");
            }
        });

        itemOptionView = menu.findItem(R.id.menu_option_view);
        View menuOptionView = MenuItemCompat.getActionView(itemOptionView);


        menuOptionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueLocal.setOptionView(!ValueLocal.getOptionView());
                int index = tabLayout.getSelectedTabPosition();

                ViewAnimator viewAnimatorFragmentAll = (ViewAnimator) findViewById(R.id.viewFlipper1);
                AnimationFactory.flipTransition(viewAnimatorFragmentAll, AnimationFactory.FlipDirection.LEFT_RIGHT);

                adapterViewPagerHome = new AdapterViewPagerHome(getSupportFragmentManager());
                viewPagerListProduct.setAdapter(adapterViewPagerHome);
                tabLayout.setupWithViewPager(viewPagerListProduct);
                tabLayout.setScrollPosition(index, 0f, true);
                viewPagerListProduct.setCurrentItem(index);

            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawMenuToggel.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm1 = getFragmentManager();
        dialogExit = FragmentDialogExit.newInstance();
        dialogExit.show(fm1, "");
    }

}
