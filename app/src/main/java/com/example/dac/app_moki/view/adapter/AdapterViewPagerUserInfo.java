package com.example.dac.app_moki.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dac.app_moki.view.fragment.FragmentListProducResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 11/1/2017.
 */
public class AdapterViewPagerUserInfo extends FragmentPagerAdapter {
    List<Fragment> lstFragment = new ArrayList<Fragment>();
    List<String> lstTitle = new ArrayList<String>();
    public AdapterViewPagerUserInfo(FragmentManager fm, int userId) {
        super(fm);
        lstFragment.add(new FragmentListProducResult(userId));
        lstTitle.add("Sản phẩm");
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
