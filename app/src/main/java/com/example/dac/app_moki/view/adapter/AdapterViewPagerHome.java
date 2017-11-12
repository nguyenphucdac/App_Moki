package com.example.dac.app_moki.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dac.app_moki.local.data.CategoryLocal;
import com.example.dac.app_moki.local.value.ValueLocal;
import com.example.dac.app_moki.model.object.Category;
import com.example.dac.app_moki.presentation.category.PresentationCategory;
import com.example.dac.app_moki.view.fragment.FragmentListProductType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 10/13/2017.
 */
public class AdapterViewPagerHome extends FragmentPagerAdapter {
    List<Fragment> lstFragment = new ArrayList<>();
    List<String> lstTitle = new ArrayList<String>();
    List<Category> lstCategories = new ArrayList<>();

    public AdapterViewPagerHome(FragmentManager fm) {
        super(fm);
        if(CategoryLocal.getLstCategoriesRoot() != null && CategoryLocal.getLstCategoriesRoot().size() > 0){
            lstCategories = CategoryLocal.getLstCategoriesRoot();
        }
        else {
            PresentationCategory presentationCategory = new PresentationCategory();
            lstCategories = presentationCategory.getListCategoryRoot();
            ValueLocal.setCurrenTab(0);
            ValueLocal.setCurrentCategory("9");
        }

        if(lstCategories.size() > 0){
            lstTitle.add("Tất cả");
            lstFragment.add(new FragmentListProductType("9"));
            for(int i = 0 ; i < lstCategories.size(); i++){
                lstTitle.add(lstCategories.get(i).getName());
                lstFragment.add(new FragmentListProductType(String.valueOf(lstCategories.get(i).getId())));
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

    @Override
    public CharSequence getPageTitle(int position) {
        return lstTitle.get(position);
    }
}
