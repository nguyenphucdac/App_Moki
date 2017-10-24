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
import com.example.dac.app_moki.adapter.AdapterTabFree_1;
import com.example.dac.app_moki.adapter.AdapterTabFree_2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 10/13/2017.
 */
public class FragmentFree extends Fragment {
    RecyclerView recyclerView;
    boolean optionView = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_free, container, false);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("name " + i);
        }

        if(optionView == true){
            recyclerView = (RecyclerView) view.findViewById(R.id.recycle_tabfree);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            AdapterTabFree_1 adapterTabFree = new AdapterTabFree_1(getActivity(), data);
            recyclerView.setAdapter(adapterTabFree);
            adapterTabFree.notifyDataSetChanged();
            optionView = !optionView;
            return view;
        }
        else{
            recyclerView = (RecyclerView) view.findViewById(R.id.recycle_tabfree);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            AdapterTabFree_2 adapterTabFree = new AdapterTabFree_2(getActivity(), data);
            recyclerView.setAdapter(adapterTabFree);
            adapterTabFree.notifyDataSetChanged();
            optionView = !optionView;
            return view;
        }

    }
}
