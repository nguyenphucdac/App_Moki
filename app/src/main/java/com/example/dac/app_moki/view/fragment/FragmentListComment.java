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
import com.example.dac.app_moki.adapter.AdapterProductComment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 11/3/2017.
 */

public class FragmentListComment extends Fragment {
    RecyclerView recyclerViewListComment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_fragment_list_comment, container, false);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("name " + i);
        }
        recyclerViewListComment = (RecyclerView) view.findViewById(R.id.recycleListComment);
        recyclerViewListComment.setLayoutManager(new LinearLayoutManager(getActivity()));
        AdapterProductComment adapterProductComment = new AdapterProductComment(getActivity(), data);
        recyclerViewListComment.setAdapter(adapterProductComment);
        adapterProductComment.notifyDataSetChanged();

        return  view;
    }
}
