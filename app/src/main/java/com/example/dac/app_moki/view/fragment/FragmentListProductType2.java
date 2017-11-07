package com.example.dac.app_moki.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Product;
import com.example.dac.app_moki.presentation.product.PresentationProduct;
import com.example.dac.app_moki.view.adapter.AdapterListProductType2;

import java.util.List;

/**
 * Created by Dac on 11/5/2017.
 */

public class FragmentListProductType2 extends Fragment {
    private RecyclerView recyclerView;

    private static int aboveItem = 0;
    private int numberItem = 0;
    private int itemMore = 0;
    private View toolBar_header;
    private View bottomViewButtonSale;
    private View slideHome;
    private int category;

    public FragmentListProductType2(int category){
        this.category = category;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_list_product_fragment, container, false);

        toolBar_header = getActivity().findViewById(R.id.toolbar_header);
        slideHome = getActivity().findViewById(R.id.slide_home);
        bottomViewButtonSale = getActivity().findViewById(R.id.bottom_buttonsale);
        final Animation slide_down = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.slide_down);
        PresentationProduct presentationProduct = new PresentationProduct();
        List<Product> lstProduct = presentationProduct.getListProducts(category);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_home_list_product);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        AdapterListProductType2 adapterTabAll = new AdapterListProductType2(getActivity(), lstProduct);
        recyclerView.setAdapter(adapterTabAll);
        adapterTabAll.notifyDataSetChanged();


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int currentAboveItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();


                if(currentAboveItem > 3){
                    toolBar_header.setVisibility(View.GONE);

                    bottomViewButtonSale.startAnimation(slide_down);
                    bottomViewButtonSale.setVisibility(View.GONE);
                }
                else {
                    toolBar_header.setVisibility(View.VISIBLE);
                    bottomViewButtonSale.setVisibility(View.VISIBLE);
                }
                System.out.println(aboveItem + " --- " + currentAboveItem);

                if(currentAboveItem <= 1){
                    AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) slideHome.getLayoutParams();
                    params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                            | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);

                }
                else {
                    AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) slideHome.getLayoutParams();
                    params.setScrollFlags(
                            AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                                    | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                                    | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED
                                    | AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);

                }
            }
        });


        return view;

    }

}
