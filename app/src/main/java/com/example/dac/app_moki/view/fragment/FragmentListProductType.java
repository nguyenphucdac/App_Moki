package com.example.dac.app_moki.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.local.data.ProductLocal;
import com.example.dac.app_moki.local.value.ValueLocal;
import com.example.dac.app_moki.model.object.Product;
import com.example.dac.app_moki.presentation.product.PresentationProduct;
import com.example.dac.app_moki.view.adapter.AdapterListProductType1;
import com.example.dac.app_moki.view.adapter.AdapterListProductType2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 11/5/2017.
 */

public class FragmentListProductType extends Fragment {

    private int aboveItem = 0;
    private int numberItem = 10;
    private View toolBar_header;
    private View bottomViewButtonSale;
    private View slideHome;
    private String categoryId;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AdapterListProductType1 adapterListProductType1;
    private  AdapterListProductType2 adapterListProductType2;
    private List<Product> lstProduct = new ArrayList<>();

    public FragmentListProductType(String categoryId){
        this.categoryId = categoryId;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_list_product_fragment, container, false);

        toolBar_header = getActivity().findViewById(R.id.toolbar_header);
        slideHome = getActivity().findViewById(R.id.slide_home);
        bottomViewButtonSale = getActivity().findViewById(R.id.bottom_buttonsale);
        final Animation slide_down = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.slide_down);

        if( ProductLocal.getLstProducs(categoryId) != null){
            lstProduct = ProductLocal.getLstProducs(categoryId);
        }
        else {
            PresentationProduct presentationProduct = new PresentationProduct();
            lstProduct = presentationProduct.getListProductsOfCategory(categoryId);
        }

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                PresentationProduct presentationProduct = new PresentationProduct();
                lstProduct = presentationProduct.getListProductsOfCategory(categoryId);
                adapterListProductType1.notifyDataSetChanged();
                adapterListProductType2.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);

            }
        });

        if(ValueLocal.getOptionView() == true){
            recyclerView = (RecyclerView) view.findViewById(R.id.recycle_home_list_product);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            adapterListProductType1 = new AdapterListProductType1(getActivity(), lstProduct);
            adapterListProductType2 = new AdapterListProductType2(getActivity(), lstProduct);
            recyclerView.setAdapter(adapterListProductType1);
            adapterListProductType1.notifyDataSetChanged();
        }
        else {
            recyclerView = (RecyclerView) view.findViewById(R.id.recycle_home_list_product);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapterListProductType1 = new AdapterListProductType1(getActivity(), lstProduct);
            adapterListProductType2 = new AdapterListProductType2(getActivity(), lstProduct);
            recyclerView.setAdapter(adapterListProductType2);
            adapterListProductType2.notifyDataSetChanged();
        }


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

                if(lstProduct.size() < currentAboveItem + 3){
                    System.out.println("begin load more");
                    PresentationProduct presentationProduct = new PresentationProduct();
                    List<Product> lstProductMore = presentationProduct.getMoreProductsOfCategory(categoryId, currentAboveItem, Integer.parseInt(ProductLocal.getLastId(categoryId)));

                    lstProduct.addAll(lstProductMore);

                    System.out.println("so san pham load them :" + lstProductMore.size());
                    System.out.println("tong so san pham : " + lstProduct.size());

                    adapterListProductType2.notifyDataSetChanged();
                    adapterListProductType1.notifyDataSetChanged();
                }
            }
        });


        return view;

    }

}
