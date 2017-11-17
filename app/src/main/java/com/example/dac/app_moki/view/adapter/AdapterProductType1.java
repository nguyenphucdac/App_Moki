package com.example.dac.app_moki.view.adapter;

/**
 * Created by Dac on 11/15/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Product;
import com.example.dac.app_moki.view.product.ProductDetail_Activity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterProductType1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    Context context;
    List<Product> lstProduct;
    RecyclerView mRecyclerView;

    public AdapterProductType1(Context context, List<Product> lstProduct, RecyclerView mRecyclerView) {
        this.context = context;
        this.lstProduct = lstProduct;
        this.mRecyclerView = mRecyclerView;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (mOnLoadMoreListener != null) {
                        mOnLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    @Override
    public int getItemViewType(int position) {
        return lstProduct.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.product_item_type_1, parent, false);
            return new UserViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(context).inflate(R.layout.progress_bar_bottom, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserViewHolder) {
            final Product product = lstProduct.get(position);

            ((UserViewHolder) holder).price.setText((product.getPrice()) + " K");
            ((UserViewHolder) holder).nameProduct.setText(product.getName());
            ((UserViewHolder) holder).numbeComment.setText((product.getNumberComment()) + "");
            ((UserViewHolder) holder).numberLike.setText((product.getNumberLike()) + "");
            if(product.getImageOnList(0) != null){
                Picasso.with(context).load(product.getImageOnList(0)).into(((UserViewHolder) holder).imageProductType1);
            }
            else {
                Picasso.with(context).load(R.drawable.no_image).into(((UserViewHolder) holder).imageProductType1);
            }

            ((UserViewHolder) holder).itemProductType1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), ProductDetail_Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent.putExtra("productId",(product.getId()+"").toString());
                    context.getApplicationContext().startActivity(intent);
                }
            });
            ((UserViewHolder) holder).imageProductType1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), ProductDetail_Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent.putExtra("productId",(product.getId()+"").toString());
                    context.getApplicationContext().startActivity(intent);
                }
            });


        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return lstProduct == null ? 0 : lstProduct.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        private View itemProductType1;
        private ImageButton imageProductType1;
        private TextView nameProduct;
        private TextView numberLike;
        private TextView numbeComment;
        private TextView price;

        public UserViewHolder(final View itemView) {
            super(itemView);
            itemProductType1 = itemView.findViewById(R.id.item_product_type_1);
            imageProductType1 = (ImageButton) itemView.findViewById(R.id.image_product_type_1);
            nameProduct = (TextView) itemView.findViewById(R.id.name_product_type_1);
            numberLike = (TextView) itemView.findViewById(R.id.number_like_product_type_1   );
            numbeComment = (TextView) itemView.findViewById(R.id.number_comment_product_type_1);
            price = (TextView) itemView.findViewById(R.id.price_product_type_1);

        }
    }

    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar_bottom);
        }
    }
}
