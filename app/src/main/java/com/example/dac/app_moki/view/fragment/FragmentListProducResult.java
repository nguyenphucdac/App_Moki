package com.example.dac.app_moki.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Product;
import com.example.dac.app_moki.presentation.product.PresentationProduct;
import com.example.dac.app_moki.view.adapter.AdapterListProductType1;

import java.util.List;

/**
 * Created by Dac on 11/6/2017.
 */

public class FragmentListProducResult extends Fragment {
    private RecyclerView recyclerView;
    private static int aboveItem = 0;
    private int numberItem = 0;
    private int itemMore = 0;
    private View toolBar_header;
    private View bottomViewButtonSale;
    private View slideHome;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_list_product_fragment, container, false);

        PresentationProduct presentationProduct = new PresentationProduct();
        List<Product> lstProduct = presentationProduct.getListProducts("9");

        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_home_list_product);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        AdapterListProductType1 adapterListProductType1 = new AdapterListProductType1(getActivity(), lstProduct);
        recyclerView.setAdapter(adapterListProductType1);
        adapterListProductType1.notifyDataSetChanged();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int currentAboveItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

            }
        });

        return view;

    }

}

