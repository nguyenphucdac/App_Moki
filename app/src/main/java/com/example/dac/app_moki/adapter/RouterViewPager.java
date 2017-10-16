package com.example.dac.app_moki.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dac.app_moki.view.fragment.FragmentAll;
import com.example.dac.app_moki.view.fragment.FragmentEat;
import com.example.dac.app_moki.view.fragment.FragmentFree;
import com.example.dac.app_moki.view.fragment.FragmentHealth;
import com.example.dac.app_moki.view.fragment.FragmentSleep;
import com.example.dac.app_moki.view.fragment.FragmentWear;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 10/13/2017.
 */
public class RouterViewPager extends FragmentPagerAdapter {
    List<Fragment> lstFragment = new ArrayList<Fragment>();
    List<String> lstTitle = new ArrayList<String>();
    public RouterViewPager(FragmentManager fm) {
        super(fm);
        lstFragment.add(new FragmentAll());
        lstFragment.add(new FragmentFree());
        lstFragment.add(new FragmentEat());
        lstFragment.add(new FragmentSleep());
        lstFragment.add(new FragmentWear());
        lstFragment.add(new FragmentHealth());

        lstTitle.add("Tất cả");
        lstTitle.add("Miến phí");
        lstTitle.add("Bé ăn");
        lstTitle.add("Bé ngủ");
        lstTitle.add("Bé mặc");
        lstTitle.add("Bé khỏe");
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
