package com.example.dac.app_moki.view.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.User;
import com.example.dac.app_moki.view.adapter.AdapterMenuNews;
import com.example.dac.app_moki.view.fragment.FragmentMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 10/28/2017.
 */
public class News_Activity extends AppCompatActivity {
    private RecyclerView recyclerViewNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_news);
        addControls();
        addEvents();

    }

    private void addControls() {

        Intent myInten = getIntent();
        User user = (User) myInten.getSerializableExtra("user");

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentMenu fragmentMenu = new FragmentMenu(user);

        fragmentTransaction.add(R.id.menu_view, fragmentMenu);
        fragmentTransaction.commit();

        recyclerViewNews = (RecyclerView) findViewById(R.id.recycle_news_menu);
        List<String> data = new ArrayList<>();
        for(int i = 0 ; i < 20 ; i++){
            data.add("name " + i);
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewNews.setLayoutManager(layoutManager);
        AdapterMenuNews adapterMenuNews = new AdapterMenuNews(this, data);
        recyclerViewNews.setAdapter(adapterMenuNews);
        adapterMenuNews.notifyDataSetChanged();
    }

    private void addEvents() {

    }
}
