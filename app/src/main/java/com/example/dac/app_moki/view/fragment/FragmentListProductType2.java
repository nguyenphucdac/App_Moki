package com.example.dac.app_moki.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.view.adapter.AdapterListProductType2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 11/5/2017.
 */

public class FragmentListProductType2 extends Fragment {
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_list_product_fragment, container, false);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("name " + i);
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_home_list_product);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        AdapterListProductType2 adapterTabAll = new AdapterListProductType2(getActivity(), data);
        recyclerView.setAdapter(adapterTabAll);
        adapterTabAll.notifyDataSetChanged();

        return view;

    }

}
