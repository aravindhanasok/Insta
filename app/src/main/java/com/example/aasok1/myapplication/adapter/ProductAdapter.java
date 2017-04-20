package com.example.aasok1.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.aasok1.myapplication.R;
import com.example.aasok1.myapplication.activity.ProductDisplayActivity;
import com.example.aasok1.myapplication.database.DatabaseHandler;
import com.example.aasok1.myapplication.model.ProductDetails;

import java.util.ArrayList;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context mAdapterContext;

    ArrayList<ProductDetails> mProducts = new ArrayList<>();
    DatabaseHandler mHelper;

    public ProductAdapter(Context context, ArrayList<ProductDetails> prod) {
        this.mProducts = prod;
        this.mAdapterContext = context;
        this.mHelper = new DatabaseHandler(context);
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(view);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final ProductDetails product = mProducts.get(position);
        holder.prod_image.setImageResource(product.getImage_ID());
        holder.prod_image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mAdapterContext, ProductDisplayActivity.class);
                intent.putExtra("productid", product.getProduct_ID());
                mAdapterContext.startActivity(intent);
            }
        });
        holder.prod_fav_image.setImageResource(returnResourceIDofFavorites(product.getProduct_favorites()));
        holder.product_ratinsgs.setRating(product.getProduct_stars());
        holder.prod_name.setText(product.getProduct_name());
        holder.prod_price.setText(product.getProduct_price());
        holder.prod_fav_image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                updateFavorites(product.getProduct_ID(), mHelper.returnProductFavouties(product.getProduct_ID()));
                if (mHelper.returnProductFavouties(product.getProduct_ID()).equals("TRUE")) {
                    ((ImageView) v).setImageResource(R.drawable.yes_heart);

                } else {
                    ((ImageView) v).setImageResource(R.drawable.no_heart);
                }
            }
        });

        holder.product_ratinsgs.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mHelper.updateProductRatings(product.getProduct_ID(), rating);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView prod_image, prod_fav_image;
        RatingBar product_ratinsgs;
        TextView prod_name, prod_price;

        ProductViewHolder(View view) {
            super(view);
            prod_image = (ImageView) view.findViewById(R.id.product_image);
            prod_name = (TextView) view.findViewById(R.id.product_name);
            prod_price = (TextView) view.findViewById(R.id.product_price);
            prod_fav_image = (ImageView) view.findViewById(R.id.favourite_heart);
            product_ratinsgs = (RatingBar) view.findViewById(R.id.ratingsbar);
        }
    }

    private int returnResourceIDofFavorites(String favo) {
        int resourceId = 0;
        if (favo.equals("TRUE")) {
            resourceId = R.drawable.yes_heart;
        }
        if (favo.equals("FALSE")) {
            resourceId = R.drawable.no_heart;
        }
        return resourceId;
    }

    private void updateFavorites(int id, String favo) {
        if (favo.equals("TRUE")) {
            mHelper.updateFavorites(id, "FALSE");
        }
        if (favo.equals("FALSE")) {
            mHelper.updateFavorites(id, "TRUE");
        }
    }

}
