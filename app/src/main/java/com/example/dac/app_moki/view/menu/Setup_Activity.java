package com.example.dac.app_moki.view.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.User;
import com.example.dac.app_moki.view.fragment.FragmentMenu;

/**
 * Created by Dac on 11/2/2017.
 */
public class Setup_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_setup);

        addControls();
        addEvents();
    }

    private void addEvents() {
        Intent myInten = getIntent();
        User user = (User) myInten.getSerializableExtra("user");

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentMenu fragmentMenu = new FragmentMenu(user);

        fragmentTransaction.add(R.id.menu_view, fragmentMenu);
        fragmentTransaction.commit();
    }

    private void addControls() {

    }
}
