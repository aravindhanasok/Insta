package com.example.aasok1.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aasok1.myapplication.R;
import com.example.aasok1.myapplication.database.DatabaseHandler;
import com.example.aasok1.myapplication.model.ProductDetails;

public class ProductDisplayActivity extends AppCompatActivity {

    ImageView mDisplayImage;
    TextView mDisplayText,mDisplayPrice,mDisplayDescription,mDisplayReview;
    DatabaseHandler mHelper = new DatabaseHandler(this);
    ProductDetails mProductDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productdisplay);
        initiateViews();
        Intent intent = getIntent();
        int id = intent.getIntExtra("productid", 0);
        displayProductDetails(id);

    }

    public void initiateViews() {
        mDisplayImage = (ImageView) findViewById(R.id.display_image);
        mDisplayText = (TextView) findViewById(R.id.display_name);
        mDisplayPrice = (TextView) findViewById(R.id.display_price);
        mDisplayDescription = (TextView) findViewById(R.id.display_description);
        mDisplayReview = (TextView) findViewById(R.id.display_review);
    }

    public void displayProductDetails(int id) {
        mProductDetails = mHelper.returnProductDetails(id);
        mDisplayImage.setImageResource(mProductDetails.getImage_ID());
        mDisplayText.setText(mProductDetails.getProduct_name());
        mDisplayPrice.setText(mProductDetails.getProduct_price());
        mDisplayDescription.setText(mProductDetails.getDescription());
        mDisplayReview.setText(mProductDetails.getReviews());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
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
