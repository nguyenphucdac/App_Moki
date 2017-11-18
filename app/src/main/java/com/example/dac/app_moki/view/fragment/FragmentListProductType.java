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
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.local.data.ProductLocal;
import com.example.dac.app_moki.local.value.ValueLocal;
import com.example.dac.app_moki.model.object.Product;
import com.example.dac.app_moki.presentation.product.PresentationProduct;
import com.example.dac.app_moki.view.adapter.AdapterProductType1;
import com.example.dac.app_moki.view.adapter.AdapterProductType2;
import com.example.dac.app_moki.view.adapter.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by Dac on 11/5/2017.
 */

public class FragmentListProductType extends Fragment implements PtrHandler {
    private View toolBar_header;
    private View bottomViewButtonSale;
    private View slideHome;
    private String categoryId;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Product> lstProduct;
    private PresentationProduct presentationProduct;
    private AdapterProductType1 adapterProductType1;
    private AdapterProductType2 adapterProductType2;

    private PtrClassicFrameLayout frameLayoutPullToRefesh;

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
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    lstProduct = presentationProduct.getListProductsOfCategory(categoryId);
//                }
//            }).start();
        }



        frameLayoutPullToRefesh = (PtrClassicFrameLayout) view.findViewById(R.id.recycler_view_ptr_frame);
        frameLayoutPullToRefesh.setPtrHandler(this);
        frameLayoutPullToRefesh.setDurationToClose(200);
        frameLayoutPullToRefesh.setDurationToCloseHeader(1000);
        frameLayoutPullToRefesh.setLastUpdateTimeRelateObject(this);



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

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        if (this.recyclerView.getChildCount() == 0) {
            return true;
        }
        int top = this.recyclerView.getChildAt(0).getTop();
        if (top != 0) {
            return false;
        }
        final RecyclerView recyclerView = this.recyclerView;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            int position = ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
            if (position == 0) {
                return true;
            } else if (position == -1) {
                position = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                return position == 0;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            boolean allViewAreOverScreen = true;
            int[] positions = ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(null);
            for (int i = 0; i < positions.length; i++) {
                if (positions[i] == 0) {
                    return true;
                }
                if (positions[i] != -1) {
                    allViewAreOverScreen = false;
                }
            }
            if (allViewAreOverScreen) {
                positions = ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(null);
                for (int i = 0; i < positions.length; i++) {
                    if (positions[i] == 0) {
                        return true;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void onRefreshBegin(final PtrFrameLayout frame) {
        frame.postDelayed(new Runnable() {
            @Override
            public void run() {
                lstProduct = presentationProduct.getListProductsOfCategory(categoryId);
                adapterProductType1.notifyDataSetChanged();
                //adapterProductType2.notifyDataSetChanged();
                frame.refreshComplete();
            }
        }, 1800);
    }
}
