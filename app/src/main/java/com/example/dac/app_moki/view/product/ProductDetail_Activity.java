package com.example.dac.app_moki.view.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
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
import com.example.dac.app_moki.local.value.ValueLocal;
import com.example.dac.app_moki.model.object.Comment;
import com.example.dac.app_moki.model.object.Product;
import com.example.dac.app_moki.presentation.comment.PresentationComment;
import com.example.dac.app_moki.presentation.product.PresentationProduct;
import com.example.dac.app_moki.view.adapter.AdapterProductComment;
import com.example.dac.app_moki.view.adapter.AdapterViewPageImage;
import com.example.dac.app_moki.view.login.Login_Activity;
import com.example.dac.app_moki.view.user.UserInfo_Activity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dac on 10/15/2017.
 */
public class ProductDetail_Activity extends AppCompatActivity {
    private ImageButton btnBack;
    private Button btnToPageComment;
    private ImageButton btnLikeProduct;
    private TextView txtStatusProduct;
    private Product product;
    private ViewPager viewListImage;
    private View boundListImage;

    private PullToZoomScrollViewEx scrollView;
    private View contentView;
    private  View zoomView;

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
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (15.0F * (mScreenWidth / 16.0F)));
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
        btnLikeProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ValueLocal.getToken() == ""){
                    Intent intent = new Intent(ProductDetail_Activity.this, Login_Activity.class);
                    intent.putExtra("logined", false);
                    startActivity(intent);
                }
                else {
                    PresentationProduct presentationProduct = new PresentationProduct();
                    String numberLike = presentationProduct.LikeProduct(String.valueOf(product.getId()));

                    Picasso.with(zoomView.getContext()).load(R.drawable.icon_like_on).into((ImageView) btnLikeProduct);
                    txtStatusProduct.setText(numberLike + " thích và " + product.getNumberComment() + " bình luận");
                }

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
        scrollView = (PullToZoomScrollViewEx) findViewById(R.id.scroll_view);
        zoomView = LayoutInflater.from(this).inflate(R.layout.product_detail_zoom_image, null, false);
        contentView = LayoutInflater.from(this).inflate(R.layout.product_detail_content, null, false);
        View userInfo = contentView.findViewById(R.id.view_user_info);

        contentView.invalidate();

        Intent myIntent = getIntent();
        final String productId = myIntent.getStringExtra("productId");
        PresentationProduct presentationProduct = new PresentationProduct();
        product = presentationProduct.getProduct(Integer.parseInt(productId));

        TextView nameProduct = (TextView) findViewById(R.id.name_product_detail);
        nameProduct.setText(product.getName());

        txtStatusProduct = (TextView) contentView.findViewById(R.id.status_product);
        txtStatusProduct.setText(product.getNumberLike() + " thích và " + product.getNumberComment() + " bình luận");

        TextView decription = (TextView) contentView.findViewById(R.id.decription_product_detail);
        decription.setText(product.getDescription());

        Button nameCategory = (Button) contentView.findViewById(R.id.name_category_product_detail);
        nameCategory.setText(product.getCategory().getName());

        TextView nameBrand = (TextView) contentView.findViewById(R.id.name_brand);

        TextView conditionProduct = (TextView) contentView.findViewById(R.id.state_product_detail);
        if(product.getCodition() != null){
            conditionProduct.setText(product.getCodition());
        }

        TextView sizeProduct = (TextView) contentView.findViewById(R.id.size_product_detail);
        if(product.getSize() != null){
            sizeProduct.setText(product.getSize());
        }

        TextView weightProduct = (TextView) contentView.findViewById(R.id.weigt_product_detail);
        if(product.getWeigh() != null){
            weightProduct.setText(product.getWeigh());
        }

        TextView shipFrom = (TextView) contentView.findViewById(R.id.ship_from);
        shipFrom.setText(product.getShipFrom());

        TextView price = (TextView) findViewById(R.id.price_product_detail);
        price.setText("Giá : " +String.format("%,d", product.getPrice())  + " VNĐ");

        viewListImage = (ViewPager) zoomView.findViewById(R.id.viewpager_list_image);
        AdapterViewPageImage adapterViewPageImage = new AdapterViewPageImage(getSupportFragmentManager(), product.getListImage(), productId);
        viewListImage.setAdapter(adapterViewPageImage);

        View avatarSeller = contentView.findViewById(R.id.avatarSeller_product_detail);
        if(product.getSeller().getImage() != null){
            Picasso.with(zoomView.getContext()).load(product.getSeller().getImage()).into((ImageView) avatarSeller);
        }
        else {
            Picasso.with(zoomView.getContext()).load(R.drawable.unknown_user).into((ImageView) avatarSeller);
        }

        TextView shopName = (TextView) contentView.findViewById(R.id.shop_name_product_detail);
        shopName.setText(product.getSeller().getNameShop());

        TextView scoreList = (TextView) contentView.findViewById(R.id.score_list);
        scoreList.setText("Điểm " + product.getSeller().getScore() + " sản phẩm " + product.getSeller().getNumberProduct());

        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetail_Activity.this, UserInfo_Activity.class);
                intent.putExtra("seller", product.getSeller());
                System.out.println(product.getSeller());
                startActivity(intent);
            }
        });

        PresentationComment presentationComment = new PresentationComment();
        List<Comment> lstComment = presentationComment.getListComment(Integer.parseInt(productId));

        RecyclerView recyclerViewListComment = (RecyclerView) contentView.findViewById(R.id.recycle_product_detail_comment);
        recyclerViewListComment.setLayoutManager(new LinearLayoutManager(this));
        AdapterProductComment adapterProductComment = new AdapterProductComment(this, lstComment);
        recyclerViewListComment.setAdapter(adapterProductComment);
        adapterProductComment.notifyDataSetChanged();
        recyclerViewListComment.setMinimumHeight(lstComment.size()*320);

        btnToPageComment = (Button) contentView.findViewById(R.id.btnToPageComment);
        btnLikeProduct = (ImageButton) contentView.findViewById(R.id.button_like_product_detail);

        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);
    }
}
