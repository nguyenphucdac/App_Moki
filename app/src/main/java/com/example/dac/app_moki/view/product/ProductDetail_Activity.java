package com.example.dac.app_moki.view.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.example.dac.app_moki.R;
import com.example.dac.app_moki.view.adapter.AdapterProductComment;
import com.example.dac.app_moki.view.user.UserInfo_Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 10/15/2017.
 */
public class ProductDetail_Activity extends AppCompatActivity {
    private ImageButton btnBack;
    private PullToZoomScrollViewEx scrollView;
    private RecyclerView recyclerViewListComment;
    private Button btnToPageComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        addControls();
        addEvents();
    }

    private void addControls() {
        btnBack = (ImageButton) findViewById(R.id.product_detail_btn_back);

        loadViewForCode();
        scrollView = (PullToZoomScrollViewEx) findViewById(R.id.scroll_view);

        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        scrollView.setHeaderLayoutParams(localObject);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("name " + i);
        }
        recyclerViewListComment = (RecyclerView) findViewById(R.id.recycle_product_detail_comment);
        recyclerViewListComment.setLayoutManager(new LinearLayoutManager(this));
        AdapterProductComment adapterProductComment = new AdapterProductComment(this, data);
        recyclerViewListComment.setAdapter(adapterProductComment);
        adapterProductComment.notifyDataSetChanged();

        btnToPageComment = (Button) findViewById(R.id.btnToPageComment);

    }

    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnToPageComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetail_Activity.this, ProductCommentPage_Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.scroll_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        scrollView.setParallax(true);
        scrollView.setZoomEnabled(true);
        return super.onOptionsItemSelected(item);
    }

    private void loadViewForCode() {
        PullToZoomScrollViewEx scrollView = (PullToZoomScrollViewEx) findViewById(R.id.scroll_view);
        View zoomView = LayoutInflater.from(this).inflate(R.layout.product_detail_zoom_image, null, false);
        View contentView = LayoutInflater.from(this).inflate(R.layout.product_detail_content, null, false);

        View userInfo = contentView.findViewById(R.id.view_user_info);
        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetail_Activity.this, UserInfo_Activity.class);
                startActivity(intent);
            }
        });

        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);
    }
}
