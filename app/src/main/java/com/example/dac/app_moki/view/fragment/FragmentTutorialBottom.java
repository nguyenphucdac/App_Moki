package com.example.dac.app_moki.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.view.home.Home_Activity;

/**
 * Created by Dac on 10/30/2017.
 */
public class FragmentTutorialBottom extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tutorial_bottom, container, false);
        Button btnSkip = (Button) view.findViewById(R.id.btn_skip_tutorial);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Home_Activity.class);
                startActivity(intent);
            }
        });
        return  view;
    }
}
