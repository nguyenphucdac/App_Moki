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
import com.example.dac.app_moki.adapter.AdapterTabFree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 10/13/2017.
 */
public class FragmentFree extends Fragment {
    RecyclerView recyclerView;
    AdapterTabFree adapterTabFree;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_free,container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_tabfree);

        List<String> data = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
            data.add("name " + i);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapterTabFree = new AdapterTabFree(getActivity(),data);
        recyclerView.setAdapter(adapterTabFree);
        adapterTabFree.notifyDataSetChanged();
        return view;
    }
}
