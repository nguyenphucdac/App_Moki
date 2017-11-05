package com.example.dac.app_moki.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.view.adapter.AdapterListProductType1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 10/13/2017.
 */
public class FragmentListProductType1 extends Fragment {
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
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("name " + i);
        }

        toolBar_header = getActivity().findViewById(R.id.toolbar_header);
        slideHome = getActivity().findViewById(R.id.slide_home);
        bottomViewButtonSale = getActivity().findViewById(R.id.bottom_buttonsale);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_home_list_product);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        AdapterListProductType1 adapterListProductType1 = new AdapterListProductType1(getActivity(), data);
        recyclerView.setAdapter(adapterListProductType1);
        adapterListProductType1.notifyDataSetChanged();


        final Animation slide_down = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.slide_down);
        final Animation slide_up = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.slide_up);

        //recyclerView.addOnScrollListener(new LoadMore(recyclerView.getLayoutManager()));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int currentAboveItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();


                if(currentAboveItem > 5){
                    toolBar_header.setVisibility(View.GONE);

                    bottomViewButtonSale.startAnimation(slide_down);
                    bottomViewButtonSale.setVisibility(View.GONE);
                }
                else {
                    toolBar_header.setVisibility(View.VISIBLE);
                    bottomViewButtonSale.setVisibility(View.VISIBLE);
                }
                System.out.println(aboveItem + " --- " + currentAboveItem);

                if(currentAboveItem <= 4){
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

        System.out.println("init fragment");
        return view;

    }

}
