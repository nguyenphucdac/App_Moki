package com.example.dac.app_moki.view.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.adapter.AdapterMenuNews;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 10/28/2017.
 */
public class News extends AppCompatActivity {
    RecyclerView recyclerViewNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_news);
        addControls();
        addEvents();

    }

    private void addControls() {
        recyclerViewNews = (RecyclerView) findViewById(R.id.recycle_news_menu);

        List<String> data = new ArrayList<>();
        for(int i = 0 ; i < 20 ; i++){
            data.add("name " + i);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewNews.setLayoutManager(layoutManager);
        AdapterMenuNews adapterResultSearch = new AdapterMenuNews(this, data);
        recyclerViewNews.setAdapter(adapterResultSearch);
        adapterResultSearch.notifyDataSetChanged();
    }

    private void addEvents() {

    }
}
