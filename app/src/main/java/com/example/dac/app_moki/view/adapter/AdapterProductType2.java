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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Product;
import com.example.dac.app_moki.view.product.ProductDetail_Activity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterProductType2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    Context context;
    List<Product> lstProduct;
    RecyclerView mRecyclerView;

    public AdapterProductType2(Context context, List<Product> lstProduct, RecyclerView mRecyclerView) {
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
            View view = LayoutInflater.from(context).inflate(R.layout.product_item_type_2, parent, false);
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

            ((UserViewHolder) holder).nameProduct.setText(product.getName());
            if(product.getImageOnList(0) != null){
                Picasso.with(context).load(product.getImageOnList(0)).into(((UserViewHolder) holder).imageProduct);
            }
            else {
                Picasso.with(context).load(R.drawable.no_image).into(((UserViewHolder) holder).imageProduct);
            }
            ((UserViewHolder) holder).priceProduct.setText(String.format("%,d", product.getPrice()) + " VNĐ");
            ((UserViewHolder) holder).btnLike.setText(String.valueOf(product.getNumberLike()));
            ((UserViewHolder) holder).btnComment.setText(String.valueOf(product.getNumberComment()));

            if(product.getSeller().getImage() != null){
                Picasso.with(context).load(product.getSeller().getImage()).into((ImageView) ((UserViewHolder) holder).avatarSeller);
            }
            else {
                Picasso.with(context).load(R.drawable.unknown_user).into((ImageView) ((UserViewHolder) holder).avatarSeller);
            }

            ((UserViewHolder) holder).nameSeller.setText(String.valueOf(product.getSeller().getNameShop()));

            ((UserViewHolder) holder).userInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context.getApplicationContext(), ProductDetail_Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent.putExtra("productId",(product.getId()+"").toString());
                    context.getApplicationContext().startActivity(intent);
                }
            });
            ((UserViewHolder) holder).imageProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context.getApplicationContext(), ProductDetail_Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent.putExtra("productId",(product.getId()+"").toString());
                    context.getApplicationContext().startActivity(intent);
                }
            });

            ((UserViewHolder) holder).infoProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context.getApplicationContext(), ProductDetail_Activity.class);
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
        private View userInfo;
        private ImageButton imageProduct;
        private View infoProduct;
        private View avatarSeller;
        private TextView nameSeller;
        private TextView nameProduct;
        private TextView priceProduct;
        private Button btnLike;
        private Button btnComment;

        public UserViewHolder(final View itemView) {
            super(itemView);
            userInfo = itemView.findViewById(R.id.user_info_product_type_2);
            imageProduct = (ImageButton) itemView.findViewById(R.id.img_product_type_2);
            infoProduct = itemView.findViewById(R.id.info_product_type_2);
            avatarSeller = itemView.findViewById(R.id.avatarSeller);
            nameSeller = (TextView) itemView.findViewById(R.id.nameSeller);
            nameProduct = (TextView) itemView.findViewById(R.id.name_product_type_2);
            priceProduct = (TextView) itemView.findViewById(R.id.price_product_type_2);
            btnLike = (Button) itemView.findViewById(R.id.button_like_product_type_2);
            btnComment = (Button) itemView.findViewById(R.id.button_comment_product_type_2);
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

