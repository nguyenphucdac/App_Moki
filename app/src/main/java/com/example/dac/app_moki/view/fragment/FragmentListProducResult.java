package com.example.dac.app_moki.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Product;
import com.example.dac.app_moki.presentation.product.PresentationProduct;
import com.example.dac.app_moki.view.adapter.AdapterListProductResult;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by Dac on 11/6/2017.
 */

public class FragmentListProducResult extends Fragment implements PtrHandler {
    private RecyclerView recyclerView;
    private static int aboveItem = 0;
    private int numberItem = 0;
    private int itemMore = 0;
    private View toolBar_header;
    private View bottomViewButtonSale;
    private View slideHome;
    private List<Product> lstProduct;
    private PtrClassicFrameLayout frameLayoutPullToRefesh;

    public FragmentListProducResult(int userId){
        PresentationProduct presentationProduct = new PresentationProduct();
        lstProduct = new ArrayList<>();
        lstProduct = presentationProduct.getUserListings(userId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_list_product_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_home_list_product);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        AdapterListProductResult adapterListProductResult = new AdapterListProductResult(getActivity(), lstProduct);
        recyclerView.setAdapter(adapterListProductResult);
        adapterListProductResult.notifyDataSetChanged();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int currentAboveItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

            }
        });

        frameLayoutPullToRefesh = (PtrClassicFrameLayout) view.findViewById(R.id.recycler_view_ptr_frame);
        frameLayoutPullToRefesh.setPtrHandler(this);
        frameLayoutPullToRefesh.setDurationToClose(200);
        frameLayoutPullToRefesh.setDurationToCloseHeader(1000);
        frameLayoutPullToRefesh.setLastUpdateTimeRelateObject(this);

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
                frame.refreshComplete();
            }
        }, 1800);
    }
}

