package com.example.aasok1.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.aasok1.myapplication.R;
import com.example.aasok1.myapplication.adapter.ProductAdapter;
import com.example.aasok1.myapplication.database.DatabaseHandler;
import com.example.aasok1.myapplication.model.ProductDetails;

import java.util.ArrayList;

public class CategoryDisplayActivity extends AppCompatActivity {


    DatabaseHandler mHelper = new DatabaseHandler(this);
    ImageView mFavorites;
    RatingBar mRating;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<ProductDetails> mProductList = new ArrayList<>();
    int[] mImage_id = {R.drawable.bear_tshirt, R.drawable.blue_tshirt, R.drawable.creed_tshirt, R.drawable.nap_queen, R.drawable.red_tshirt};
    String[] mName, mPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_display);
        this.setTitle(getResources().getString(R.string.Shirts));
        mProductList = mHelper.retrieveProductDetails();
        mFavorites = (ImageView) findViewById(R.id.favourite_heart);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ProductAdapter(this, mProductList);
        mRecyclerView.setAdapter(mAdapter);
        mRating = (RatingBar) findViewById(R.id.ratingsbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.accounts:
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.account_select), Toast.LENGTH_LONG).show();
                return true;
            case R.id.wishlist:
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.wishlist_select), Toast.LENGTH_LONG).show();
                return true;
            case R.id.rewards:
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.orders_select), Toast.LENGTH_LONG).show();
                return true;
            case R.id.orders:
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.rewards_select), Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
