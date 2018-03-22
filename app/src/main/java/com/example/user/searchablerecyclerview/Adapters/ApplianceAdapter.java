package com.example.user.searchablerecyclerview.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.searchablerecyclerview.R;
import com.example.user.searchablerecyclerview.models.Seller;
import com.example.user.searchablerecyclerview.models.SellerData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by User on 3/22/2018.
 */

public class ApplianceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> msellerData = new ArrayList<>();
    private static final int SELLER_VIEW_TYPE = 0;

    public void setData(List<Object> sellerData) {
        msellerData = sellerData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (msellerData.get(position) instanceof Seller) {
            return SELLER_VIEW_TYPE;
        }
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SELLER_VIEW_TYPE) {
            return new SellerViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_appliance, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SellerViewHolder) {
            ((SellerViewHolder) holder).setData((Seller) msellerData.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return (msellerData != null) ? (msellerData.size()) : 0;
    }

    public class SellerViewHolder extends RecyclerView.ViewHolder {


        public TextView mApplianceNameTextView;

        public TextView mSellerTextView;

        public TextView mPriceTextView;

        public ImageView mProductImageView;
        private View mLayout;

        public SellerViewHolder(View itemView) {
            super(itemView);
            mLayout = itemView;
            mSellerTextView = itemView.findViewById(R.id.sellerTextView);
            mApplianceNameTextView = itemView.findViewById(R.id.applianceNameTextView);
            mProductImageView = itemView.findViewById(R.id.productImageView);
            mPriceTextView = itemView.findViewById(R.id.priceTextView);
        }

        public void setData(Seller seller) {
            mApplianceNameTextView.setText(seller.applainceName);
            mPriceTextView.setText(seller.price);
            Glide.with(mProductImageView.getContext()).load(seller.imageUrl).into(mProductImageView);
            mSellerTextView.setText(seller.sellerName);
        }
    }
}
