package com.example.dac.app_moki.view.user;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.view.adapter.AdapterViewPagerUserInfo;

/**
 * Created by Dac on 10/31/2017.
 */
public class UserInfo_Activity extends AppCompatActivity {

    ImageButton btnBack;

    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPagerListProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_user);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        btnBack = (ImageButton) findViewById(R.id.user_info_btn_back);

        appBarLayout = (AppBarLayout) findViewById(R.id.user_info_appbar);

        viewPagerListProduct = (ViewPager) findViewById(R.id.view_pager_user_info);
        AdapterViewPagerUserInfo adapterViewPagerUserInfo = new AdapterViewPagerUserInfo(getSupportFragmentManager());
        viewPagerListProduct.setAdapter(adapterViewPagerUserInfo);

        tabLayout = (TabLayout) findViewById(R.id.user_info_tabbar);
        tabLayout.setupWithViewPager(viewPagerListProduct);

        TextView tabProduct = (TextView) LayoutInflater.from(this).inflate(R.layout.user_info_custom_tab_item, null);
        tabProduct.setText("Sản phẩm");
        tabLayout.getTabAt(0).setCustomView(tabProduct);

        TextView tabFllowing = (TextView) LayoutInflater.from(this).inflate(R.layout.user_info_custom_tab_item, null);
        tabFllowing.setText("Đang theo dõi");
        tabLayout.getTabAt(1).setCustomView(tabFllowing);

        TextView tabPersonPeople = (TextView) LayoutInflater.from(this).inflate(R.layout.user_info_custom_tab_item, null);
        tabPersonPeople.setText("Người theo dõi");
        tabLayout.getTabAt(2).setCustomView(tabPersonPeople);

    }
}
