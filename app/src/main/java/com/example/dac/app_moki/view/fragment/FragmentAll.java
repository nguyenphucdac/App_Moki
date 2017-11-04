package com.example.dac.app_moki.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.view.adapter.AdapterTabAll_1;
import com.example.dac.app_moki.view.adapter.AdapterTabAll_2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 10/13/2017.
 */
public class FragmentAll extends Fragment {
    RecyclerView recyclerView;
    boolean optionView = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_all, container, false);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("name " + i);
        }

        if(optionView == true){
            recyclerView = (RecyclerView) view.findViewById(R.id.recycle_taball);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            AdapterTabAll_1 adapterTabAll = new AdapterTabAll_1(getActivity(), data);
            recyclerView.setAdapter(adapterTabAll);
            adapterTabAll.notifyDataSetChanged();
            optionView = !optionView;
            return view;
        }
        else{
            recyclerView = (RecyclerView) view.findViewById(R.id.recycle_taball);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            AdapterTabAll_2 adapterTabAll = new AdapterTabAll_2(getActivity(), data);
            recyclerView.setAdapter(adapterTabAll);
            adapterTabAll.notifyDataSetChanged();
            optionView = !optionView;
            return view;
        }

    }

}
