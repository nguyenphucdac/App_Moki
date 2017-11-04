package com.example.dac.app_moki.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dac.app_moki.view.fragment.FragmentAll;
import com.example.dac.app_moki.view.fragment.FragmentHealth;
import com.example.dac.app_moki.view.fragment.FragmentWear;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 11/1/2017.
 */
public class AdapterViewPagerUserInfo extends FragmentPagerAdapter {
    List<Fragment> lstFragment = new ArrayList<Fragment>();
    List<String> lstTitle = new ArrayList<String>();
    public AdapterViewPagerUserInfo(FragmentManager fm) {
        super(fm);
        lstFragment.add(new FragmentAll());
        lstFragment.add(new FragmentWear());
        lstFragment.add(new FragmentHealth());

        lstTitle.add("Sản phẩm");
        lstTitle.add("Đang theo dõi");
        lstTitle.add("Người theo dõi");
    }

    @Override
    public Fragment getItem(int position) {
        return lstFragment.get(position);
    }

    @Override
    public int getCount() {
        return lstFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return lstTitle.get(position);
    }
}
