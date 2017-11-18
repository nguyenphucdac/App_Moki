package com.example.dac.app_moki.view.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageButton;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Product;
import com.example.dac.app_moki.presentation.product.PresentationProduct;
import com.example.dac.app_moki.view.adapter.AdapterListProductResult;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by Dac on 10/21/2017.
 */
public class ResultSearch_Activity extends AppCompatActivity implements PtrHandler {

    private RecyclerView resultSearch;
    private ImageButton btnBack;
    private PtrClassicFrameLayout frameLayout;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result_layout);

        addCotnrols();
        addEvents();
    }

    private void addCotnrols() {
        Intent myIntent = getIntent();
        String keyword = myIntent.getStringExtra("keyword");
        String categoryId = myIntent.getStringExtra("category_id");
        String brandId = myIntent.getStringExtra("brand_id");
        String sizeid = myIntent.getStringExtra("size_id");
        String conditionId = myIntent.getStringExtra("condition_id");
        frameLayout = (PtrClassicFrameLayout) findViewById(R.id.recycler_view_ptr_frame);
        frameLayout.setPtrHandler(this);
        //set last update time in header
        frameLayout.setLastUpdateTimeRelateObject(this);


        btnBack = (ImageButton) findViewById(R.id.btnBack);
        resultSearch = (RecyclerView) findViewById(R.id.recycle_result_search);
        PresentationProduct presentationProduct = new PresentationProduct();
        List<Product> lstProduct = presentationProduct.getSerachResult(keyword, categoryId, brandId, sizeid, conditionId);

        resultSearch = (RecyclerView)findViewById(R.id.recycle_result_search);
        resultSearch.setLayoutManager(new GridLayoutManager(ResultSearch_Activity.this, 2));
        AdapterListProductResult adapterListProductResult = new AdapterListProductResult(ResultSearch_Activity.this, lstProduct);
        resultSearch.setAdapter(adapterListProductResult);
        adapterListProductResult.notifyDataSetChanged();

    }
    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        if (resultSearch.getChildCount() == 0) {
            return true;
        }
        int top = resultSearch.getChildAt(0).getTop();
        if (top != 0) {
            return false;
        }
        final RecyclerView recyclerView = resultSearch;
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
        return false;
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
