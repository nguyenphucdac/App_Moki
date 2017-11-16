package com.example.dac.app_moki.view.my_tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.view.adapter.AdapterTutorial;
import com.example.dac.app_moki.view.login.Login_Activity;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Dac on 10/30/2017.
 */
public class Tutorial_Activity extends AppCompatActivity {

    private ViewPager viewPagerTutorial;
    private Button btnskip;
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

        btnskip = (Button) findViewById(R.id.btn_skip_tutorial);
        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Tutorial_Activity.this, "Đâfsdf", Toast.LENGTH_SHORT);
                Intent intent = new Intent(Tutorial_Activity.this, Login_Activity.class);
                startActivity(intent);
            }
        });

    }
}
