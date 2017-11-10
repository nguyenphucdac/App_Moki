package com.example.dac.app_moki.view.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.dac.app_moki.R;

/**
 * Created by Dac on 11/10/2017.
 */

public class FragmentChosePrice extends android.app.DialogFragment {
    public static FragmentChosePrice newInstance() {
        FragmentChosePrice frag = new FragmentChosePrice();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.search_fragment_chose_price, container, false);
        setStyle(STYLE_NO_TITLE, 0);
        return rootView;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}

