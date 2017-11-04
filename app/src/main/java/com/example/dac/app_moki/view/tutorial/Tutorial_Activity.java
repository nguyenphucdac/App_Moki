package com.example.dac.app_moki.view.tutorial;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.view.adapter.AdapterTutorial;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Dac on 10/30/2017.
 */
public class Tutorial_Activity extends AppCompatActivity {

    ViewPager viewPagerTutorial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_start);
        addControls();
        addEvents();
    }

    private void addEvents() {

    }

    private void addControls() {
        viewPagerTutorial = (ViewPager) findViewById(R.id.viewpager_tutorial);
        AdapterTutorial adapterViewPager = new AdapterTutorial(getSupportFragmentManager());
        viewPagerTutorial.setAdapter(adapterViewPager);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator_tutorial);

        indicator.setViewPager(viewPagerTutorial);

    }
}
