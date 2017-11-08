package com.example.dac.app_moki.view.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Product;
import com.example.dac.app_moki.model.object.User;
import com.example.dac.app_moki.presentation.product.PresentationProduct;
import com.example.dac.app_moki.view.adapter.AdapterMenuListMyLike;
import com.example.dac.app_moki.view.fragment.FragmentMenu;

import java.util.List;

/**
 * Created by Dac on 11/2/2017.
 */
public class ListMyLike_Activity extends AppCompatActivity {
    RecyclerView recyclerViewListMyLike;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_list_my_like);

        addControls();
        addEvents();
    }

    private void addEvents() {

    }

    private void addControls() {

        Intent myInten = getIntent();
        User user = (User) myInten.getSerializableExtra("user");

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentMenu fragmentMenu = new FragmentMenu(user);

        fragmentTransaction.add(R.id.menu_view, fragmentMenu);
        fragmentTransaction.commit();

        recyclerViewListMyLike = (RecyclerView) findViewById(R.id.recycle_list_my_like_menu);

        PresentationProduct presentationProduct = new PresentationProduct();
        List<Product> lstProducts = presentationProduct.getListMyLike(user.getToken());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewListMyLike.setLayoutManager(layoutManager);
        AdapterMenuListMyLike adapterMenuListMyLike = new AdapterMenuListMyLike(this, lstProducts);
        recyclerViewListMyLike.setAdapter(adapterMenuListMyLike);
        adapterMenuListMyLike.notifyDataSetChanged();
    }
}
