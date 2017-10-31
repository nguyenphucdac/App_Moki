package com.example.dac.app_moki.view.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.adapter.AdapterResultSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 10/31/2017.
 */
public class UserInfo_Activity extends AppCompatActivity {

    RecyclerView recyclerViewProductOfUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);

        recyclerViewProductOfUser = (RecyclerView) findViewById(R.id.recycle_product_user);
        List<String> data = new ArrayList<>();
        for(int i = 0 ; i < 20 ; i++){
            data.add("name " + i);
        }

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewProductOfUser.setLayoutManager(layoutManager);
        AdapterResultSearch adapterResultSearch = new AdapterResultSearch(this, data);
        recyclerViewProductOfUser.setAdapter(adapterResultSearch);
        adapterResultSearch.notifyDataSetChanged();
    }
}
