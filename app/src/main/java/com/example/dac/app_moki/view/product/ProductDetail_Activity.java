package com.example.dac.app_moki.view.product;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.example.dac.app_moki.R;

/**
 * Created by Dac on 10/15/2017.
 */
public class ProductDetail_Activity extends AppCompatActivity {

    private PullToZoomScrollViewEx scrollView;
    ImageButton btnBack;
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
        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);
    }
}
