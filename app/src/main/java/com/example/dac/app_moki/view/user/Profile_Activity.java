package com.example.dac.app_moki.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.User;
import com.example.dac.app_moki.view.adapter.AdapterViewPagerUserInfo;
import com.squareup.picasso.Picasso;

/**
 * Created by Dac on 11/17/2017.
 */

public class Profile_Activity extends AppCompatActivity {

    ImageButton btnBack;

    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPagerListProduct;
    private User user;

    private TextView profileName;
    private View profileImage;

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

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        btnBack = (ImageButton) findViewById(R.id.user_info_btn_back);
        appBarLayout = (AppBarLayout) findViewById(R.id.user_info_appbar);
        profileName = (TextView) findViewById(R.id.profile_name);
        profileImage = findViewById(R.id.profile_image);

        if(user.getImage() != null){
            Picasso.with(Profile_Activity.this).load(user.getImage()).into((ImageView) profileImage);
        }
        else {
            Picasso.with(Profile_Activity.this).load(R.drawable.unknown_user).into((ImageView) profileImage);
        }
        profileName.setText(user.getUserName());

        viewPagerListProduct = (ViewPager) findViewById(R.id.view_pager_user_info);
        AdapterViewPagerUserInfo adapterViewPagerUserInfo = new AdapterViewPagerUserInfo(getSupportFragmentManager(), user.getId());
        viewPagerListProduct.setAdapter(adapterViewPagerUserInfo);

        tabLayout = (TabLayout) findViewById(R.id.user_info_tabbar);
        tabLayout.setupWithViewPager(viewPagerListProduct);

        TextView tabProduct = (TextView) LayoutInflater.from(this).inflate(R.layout.user_info_custom_tab_item, null);
        tabProduct.setText("Sản phẩm");
        tabLayout.getTabAt(0).setCustomView(tabProduct);

    }
}

