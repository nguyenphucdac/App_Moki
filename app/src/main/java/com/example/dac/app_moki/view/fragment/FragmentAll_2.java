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
import com.example.dac.app_moki.adapter.AdapterTabAll_2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 10/25/2017.
 */
public class FragmentAll_2 extends Fragment {
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_all, container, false);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("name " + i);
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_taball);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        AdapterTabAll_2 adapterTabAll = new AdapterTabAll_2(getActivity(), data);
        recyclerView.setAdapter(adapterTabAll);
        adapterTabAll.notifyDataSetChanged();

        return view;

    }

}
