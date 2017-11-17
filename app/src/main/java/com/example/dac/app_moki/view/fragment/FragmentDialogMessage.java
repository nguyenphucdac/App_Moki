package com.example.dac.app_moki.view.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.view.adapter.AdapterConversation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 10/24/2017.
 */
public class FragmentDialogMessage extends DialogFragment {

    private RecyclerView recyclerViewListConvesation;

    public static FragmentDialogMessage newInstance() {
        FragmentDialogMessage frag = new FragmentDialogMessage();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment_dialog_messeage, container, false);

        List<String> lstData = new ArrayList<>();

        for(int i = 0 ; i < 10 ; i++){
            lstData.add("Ádfsf");
        }

        recyclerViewListConvesation = (RecyclerView) rootView.findViewById(R.id.recycler_list_conversation);
        recyclerViewListConvesation.setLayoutManager(new LinearLayoutManager(getActivity()));
        AdapterConversation adapterConversation = new AdapterConversation(getActivity(), lstData);
        recyclerViewListConvesation.setAdapter(adapterConversation);
        adapterConversation.notifyDataSetChanged();


        return rootView;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}
