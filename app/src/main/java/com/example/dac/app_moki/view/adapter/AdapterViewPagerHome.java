package com.example.dac.app_moki.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dac.app_moki.model.object.Category;
import com.example.dac.app_moki.presentation.category.PresentationCategory;
import com.example.dac.app_moki.view.fragment.FragmentListProductType1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 10/13/2017.
 */
public class AdapterViewPagerHome extends FragmentPagerAdapter {
    List<Fragment> lstFragment = new ArrayList<>();
    List<String> lstTitle = new ArrayList<String>();
    PresentationCategory presentationCategory = new PresentationCategory();
    List<Category> lstCategories = presentationCategory.getListCategories();

    public AdapterViewPagerHome(FragmentManager fm) {
        super(fm);
        for(int i = 0 ; i < lstCategories.size(); i++){
            lstTitle.add(lstCategories.get(i).getName());
            lstFragment.add(new FragmentListProductType1());
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

    @Override
    public CharSequence getPageTitle(int position) {
        return lstTitle.get(position);
    }
}
