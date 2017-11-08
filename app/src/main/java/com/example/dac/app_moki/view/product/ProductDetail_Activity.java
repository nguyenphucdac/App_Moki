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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Comment;
import com.example.dac.app_moki.model.object.Product;
import com.example.dac.app_moki.presentation.comment.PresentationComment;
import com.example.dac.app_moki.presentation.product.PresentationProduct;
import com.example.dac.app_moki.view.adapter.AdapterProductComment;
import com.example.dac.app_moki.view.user.UserInfo_Activity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dac on 10/15/2017.
 */
public class ProductDetail_Activity extends AppCompatActivity {
    private ImageButton btnBack;
    private PullToZoomScrollViewEx scrollView;
    private RecyclerView recyclerViewListComment;
    private Button btnToPageComment;
    private Intent myIntent;
    private PresentationProduct presentationProduct;
    private Product product;

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
        btnToPageComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetail_Activity.this, ProductCommentPage_Activity.class);
                intent.putExtra("productId", (product.getId()+"").toString());
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
        myIntent = getIntent();
        String productId = myIntent.getStringExtra("productId");
        presentationProduct = new PresentationProduct();
        product = presentationProduct.getProduct(Integer.parseInt(productId));

        PullToZoomScrollViewEx scrollView = (PullToZoomScrollViewEx) findViewById(R.id.scroll_view);
        View zoomView = LayoutInflater.from(this).inflate(R.layout.product_detail_zoom_image, null, false);
        View contentView = LayoutInflater.from(this).inflate(R.layout.product_detail_content, null, false);
        View userInfo = contentView.findViewById(R.id.view_user_info);

        contentView.invalidate();

        TextView nameProduct = (TextView) findViewById(R.id.name_product_detail);
        ImageView imageProduct = (ImageView) zoomView.findViewById(R.id.product_detail_image);
        TextView txtStatusProduct = (TextView) contentView.findViewById(R.id.status_product);
        TextView decription = (TextView) contentView.findViewById(R.id.decription_product_detail);
        TextView nameCategory = (TextView) contentView.findViewById(R.id.name_category);
        TextView nameBrand = (TextView) contentView.findViewById(R.id.name_brand);
        TextView stateProduct = (TextView) contentView.findViewById(R.id.state_product_detail);
        TextView shipFrom = (TextView) contentView.findViewById(R.id.ship_from);
        TextView price = (TextView) findViewById(R.id.price_product_detail);


        View avatarSeller = contentView.findViewById(R.id.avatarSeller_product_detail);
        TextView shopName = (TextView) contentView.findViewById(R.id.shop_name_product_detail);
        TextView scoreList = (TextView) contentView.findViewById(R.id.score_list);

        if(product.getSeller().getImage() != null){
            Picasso.with(zoomView.getContext()).load(product.getSeller().getImage()).into((ImageView) avatarSeller);
        }
        else {
            Picasso.with(zoomView.getContext()).load(R.drawable.unknown_user).into((ImageView) avatarSeller);
        }

        shopName.setText(product.getSeller().getNameShop());
        scoreList.setText("Điểm " + product.getSeller().getScore() + " sản phẩm " + product.getSeller().getNumberProduct());
        nameProduct.setText(product.getName());
        decription.setText(product.getDescription());
        txtStatusProduct.setText(product.getNumberLike() + " thích và " + product.getNumberComment() + " bình luận");
        nameBrand.setText(product.getBrand());
        stateProduct.setText(product.getCodition());
        shipFrom.setText(product.getShipFrom());
        price.setText(product.getPrice() + " VNĐ");

        if(product.getImageOnList(0) != null){
            Picasso.with(zoomView.getContext()).load(product.getImageOnList(0)).into(imageProduct);
        }
        else {
            Picasso.with(zoomView.getContext()).load(R.drawable.no_image).into(imageProduct);
        }


        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetail_Activity.this, UserInfo_Activity.class);
                startActivity(intent);
            }
        });

        PresentationComment presentationComment = new PresentationComment();
        List<Comment> lstComment = presentationComment.getListComment(Integer.parseInt(productId));
        System.out.println("Số comment : " + lstComment.size());

        recyclerViewListComment = (RecyclerView) contentView.findViewById(R.id.recycle_product_detail_comment);
        recyclerViewListComment.setLayoutManager(new LinearLayoutManager(this));
        AdapterProductComment adapterProductComment = new AdapterProductComment(this, lstComment);
        recyclerViewListComment.setAdapter(adapterProductComment);
        adapterProductComment.notifyDataSetChanged();

        recyclerViewListComment.setMinimumHeight(lstComment.size()*320);
        btnToPageComment = (Button) contentView.findViewById(R.id.btnToPageComment);

        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);
    }
}
