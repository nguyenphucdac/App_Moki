package com.example.dac.app_moki.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dac.app_moki.view.fragment.FragmentImageDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 11/15/2017.
 */

public class AdapterImageDetail extends FragmentPagerAdapter {
    List<Fragment> lstFragment = new ArrayList<Fragment>();
    public AdapterImageDetail(android.support.v4.app.FragmentManager fm, List<String> lstImages) {
        super(fm);
        if(lstImages == null || lstImages.size() <=0 ){
            lstFragment.add(new FragmentImageDetail());
        }
        else {
            for(int i = 0 ; i < lstImages.size(); i++){
                lstFragment.add(new FragmentImageDetail(lstImages.get(i)));
            }
        }
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

