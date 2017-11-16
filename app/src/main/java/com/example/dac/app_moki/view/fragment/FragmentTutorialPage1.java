package com.example.dac.app_moki.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dac.app_moki.R;

/**
 * Created by Dac on 10/30/2017.
 */
public class FragmentTutorialPage1 extends Fragment {

    private ImageView imageViewStartTuTorial;
    private Button btnskip;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tutorial_page_1, container, false);

        imageViewStartTuTorial = (ImageView) view.findViewById(R.id.image_start_tutorial);
        //imageViewStartTuTorial.setOnTouchListener(new Touch());

        btnskip = (Button) getActivity().findViewById(R.id.btn_skip_tutorial);

        btnskip.setVisibility(View.GONE);

        return  view;
    }

}
