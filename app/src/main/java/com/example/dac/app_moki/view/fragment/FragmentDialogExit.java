package com.example.dac.app_moki.view.fragment;

import android.app.Dialog;
import android.os.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.example.dac.app_moki.R;

/**
 * Created by Dac on 10/21/2017.
 */
public class FragmentDialogExit extends android.app.DialogFragment {
    public static FragmentDialogExit newInstance() {
        FragmentDialogExit frag = new FragmentDialogExit();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment_dialog_exit, container, false);
        setStyle(STYLE_NO_TITLE, 0);
        Button btnCancle = (Button) rootView.findViewById(R.id.btnCancle);
        Button exitApp = (Button) rootView.findViewById(R.id.btnOK);
        btnCancle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        exitApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        return rootView;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}
