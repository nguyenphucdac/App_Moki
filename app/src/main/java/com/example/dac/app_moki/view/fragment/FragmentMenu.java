package com.example.dac.app_moki.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.view.home.Home_Activity;
import com.example.dac.app_moki.view.menu.News_Activity;

/**
 * Created by Dac on 10/28/2017.
 */
public class FragmentMenu extends Fragment {
    private View itemHomeMenu;
    private View itemNewsmenu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment, container, false);

        itemHomeMenu = view.findViewById(R.id.item_home_menu);
        itemNewsmenu = view.findViewById(R.id.item_news_menu);

        itemHomeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Home_Activity.class);
                startActivity(intent);
            }
        });
        itemNewsmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), News_Activity.class);
                startActivity(intent);
            }
        });


        return  view;
    }

}
