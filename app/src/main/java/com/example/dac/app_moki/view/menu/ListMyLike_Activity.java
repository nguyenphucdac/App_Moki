package com.example.dac.app_moki.view.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.adapter.AdapterMenuListMyLike;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 11/2/2017.
 */
public class ListMyLike_Activity extends AppCompatActivity {
    RecyclerView recyclerViewListMyLike;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_list_my_like);

        addControls();
        addEvents();
    }

    private void addEvents() {

    }

    private void addControls() {
        recyclerViewListMyLike = (RecyclerView) findViewById(R.id.recycle_list_my_like_menu);

        List<String> data = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
            data.add("name " + i);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewListMyLike.setLayoutManager(layoutManager);
        AdapterMenuListMyLike adapterMenuListMyLike = new AdapterMenuListMyLike(this, data);
        recyclerViewListMyLike.setAdapter(adapterMenuListMyLike);
        adapterMenuListMyLike.notifyDataSetChanged();
    }
}
