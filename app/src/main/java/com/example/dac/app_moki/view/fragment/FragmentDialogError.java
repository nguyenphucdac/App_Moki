package com.example.dac.app_moki.view.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.dac.app_moki.R;

/**
 * Created by Dac on 11/8/2017.
 */

public class FragmentDialogError extends android.app.DialogFragment {

    private Button btnCancle;
    private TextView contentError;


    public static FragmentDialogError newInstance(String errorContent) {
        FragmentDialogError frag = new FragmentDialogError();
        Bundle args = new Bundle();
        args.putString("errorContent", errorContent);
        frag.setArguments(args);
        return frag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog_erorr, container, false);
        setStyle(STYLE_NO_TITLE, 0);

        btnCancle = (Button) rootView.findViewById(R.id.btn_close_dialog);
        btnCancle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        contentError = (TextView) rootView.findViewById(R.id.content_error);
        contentError.setText(this.getArguments().getString("errorContent"));

        return rootView;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}
