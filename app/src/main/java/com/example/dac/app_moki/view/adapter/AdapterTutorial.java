package com.example.dac.app_moki.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dac.app_moki.view.fragment.FragmentTutorialPage1;
import com.example.dac.app_moki.view.fragment.FragmentTutorialPage2;
import com.example.dac.app_moki.view.fragment.FragmentTutorialPage4;
import com.example.dac.app_moki.view.fragment.FragmentTutorialpage3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 10/30/2017.
 */
public class AdapterTutorial extends FragmentPagerAdapter {
    List<Fragment> lstFragment = new ArrayList<Fragment>();
    public AdapterTutorial(android.support.v4.app.FragmentManager fm) {
        super(fm);
        lstFragment.add(new FragmentTutorialPage1());
        lstFragment.add(new FragmentTutorialPage2());
        lstFragment.add(new FragmentTutorialpage3());
        lstFragment.add(new FragmentTutorialPage4());

    }
    @Override
    public Fragment getItem(int position) {

        return lstFragment.get(position);
    }

    @Override
    public int getCount() {
        return lstFragment.size();
    }


}
