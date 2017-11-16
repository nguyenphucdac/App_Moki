package com.example.dac.app_moki.view.fragment;

import android.os.Bundle;
import android.os.Handler;
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
import android.widget.ProgressBar;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.local.data.ProductLocal;
import com.example.dac.app_moki.local.value.ValueLocal;
import com.example.dac.app_moki.model.object.Product;
import com.example.dac.app_moki.presentation.product.PresentationProduct;
import com.example.dac.app_moki.view.adapter.AdapterListProductType1;
import com.example.dac.app_moki.view.adapter.AdapterListProductType2;
import com.example.dac.app_moki.view.adapter.AdapterProductType1;
import com.example.dac.app_moki.view.adapter.AdapterProductType2;
import com.example.dac.app_moki.view.adapter.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 11/5/2017.
 */

public class FragmentListProductType extends Fragment{

    private int aboveItem = 0;
    private int numberItem = 10;
    private View toolBar_header;
    private View bottomViewButtonSale;
    private View slideHome;
    private String categoryId;
    private ProgressBar progressBar;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AdapterListProductType1 adapterListProductType1;
    private  AdapterListProductType2 adapterListProductType2;
    private List<Product> lstProduct;
    private PresentationProduct presentationProduct;
    private AdapterProductType1 adapterProductType1;
    private AdapterProductType2 adapterProductType2;

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
        presentationProduct = new PresentationProduct();
        lstProduct = new ArrayList<>();


        if( ProductLocal.getLstProducs(categoryId) != null){
            lstProduct = ProductLocal.getLstProducs(categoryId);
        }
        else {
            lstProduct = presentationProduct.getListProductsOfCategory(categoryId);
        }
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        if(ValueLocal.getOptionView() == true){
            recyclerView = (RecyclerView) view.findViewById(R.id.recycle_home_list_product);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            adapterProductType1 = new AdapterProductType1(getActivity(), lstProduct, recyclerView);
            recyclerView.setAdapter(adapterProductType1);
            adapterProductType1.notifyDataSetChanged();

            adapterProductType1.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore() {

                    lstProduct.add(null);
                    adapterProductType1.notifyItemInserted(lstProduct.size() - 1);
                    //Load more data for reyclerview
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            //Remove loading item
                            lstProduct.remove(lstProduct.size() - 1);
                            adapterProductType1.notifyItemRemoved(lstProduct.size());
                            //Load data
                            int index = lstProduct.size();
                            int end = index + 10;

                            List<Product> lstProductMore = presentationProduct.getMoreProductsOfCategory(categoryId, lstProduct.size(), Integer.parseInt(ProductLocal.getLastId(categoryId)));
                            lstProduct.addAll(lstProductMore);

                            adapterProductType1.notifyDataSetChanged();
                            adapterProductType1.setLoaded();
                        }
                    }, 4000);
                }
            });
        }
        else {
            recyclerView = (RecyclerView) view.findViewById(R.id.recycle_home_list_product);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapterProductType2 = new AdapterProductType2(getActivity(), lstProduct, recyclerView);
            recyclerView.setAdapter(adapterProductType2);
            adapterProductType2.notifyDataSetChanged();

            adapterProductType2.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore() {

                    lstProduct.add(null);
                    adapterProductType2.notifyItemInserted(lstProduct.size() - 1);
                    //Load more data for reyclerview
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            //Remove loading item
                            lstProduct.remove(lstProduct.size() - 1);
                            adapterProductType2.notifyItemRemoved(lstProduct.size());
                            //Load data
                            int index = lstProduct.size();
                            int end = index + 10;

                            List<Product> lstProductMore = presentationProduct.getMoreProductsOfCategory(categoryId, lstProduct.size(), Integer.parseInt(ProductLocal.getLastId(categoryId)));
                            lstProduct.addAll(lstProductMore);

                            adapterProductType2.notifyDataSetChanged();
                            adapterProductType2.setLoaded();
                        }
                    }, 4000);
                }
            });
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int currentAboveItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                if(currentAboveItem >= 3){
                    toolBar_header.setVisibility(View.GONE);
                    bottomViewButtonSale.setVisibility(View.GONE);
                }
                else {
                    toolBar_header.setVisibility(View.VISIBLE);
                    bottomViewButtonSale.setVisibility(View.VISIBLE);
                }
                if(currentAboveItem <= 1){
                    AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) slideHome.getLayoutParams();
                    params.setScrollFlags(
                            AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                                    | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                    );

                }
                else {
                    AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) slideHome.getLayoutParams();
                    params.setScrollFlags(
                            AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                                    | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                                    | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED
                                    | AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP
                    );

                }
            }
        });

        return view;

    }

}
