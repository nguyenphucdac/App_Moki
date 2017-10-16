package com.example.dac.app_moki.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.adapter.AdapterTabAll;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 10/13/2017.
 */
public class FragmentAll extends Fragment {
    RecyclerView recyclerView;
    AdapterTabAll adapterTabAll;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_all,container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_taball);
        List<String> data = new ArrayList<>();
        for(int i = 0 ; i < 20 ; i++){
            data.add("name " + i);
        }
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapterTabAll = new AdapterTabAll(getActivity(), data);
        recyclerView.setAdapter(adapterTabAll);

        adapterTabAll.notifyDataSetChanged();
        return view;
    }

}
