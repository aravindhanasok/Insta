package com.example.aasok1.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.aasok1.myapplication.model.ProductDetails;
import com.example.aasok1.myapplication.model.UserDetails;

import java.util.ArrayList;

/**
 * Created by aasok1 on 3/28/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private Context MyContext;
    private static final String DATABASE_NAME = "ProdDatabase";
    private static final String TABLE_PRODUCTS = "productdetails";
    private static final String TABLE_USERS = "userdetails";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_UNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_MOBILE = "mobile";
    private static final String PRODUCT_ID = "id";
    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_IMAGE = "image";
    private static final String PRODUCT_PRICE = "price";
    private static final String PRODUCT_FAVORITE = "favorites";
    private static final String PRODUCT_STARS = "stars";
    private static final String PRODUCT_DESCRIPTION = "description";
    private static final String PRODUCT_REVIEWS = "reviews";
    private static final String USER_ID = "userid";
    private SQLiteDatabase db;

    private static final String TABLE_USER_CREATE = "create table userdetails (id integer primary key not null , " +
            "name text not null, email text not null, username text not null UNIQUE, password text not null,mobile integer not null)";

    private static final String TABLE_PRODUCT_CREATE = "create table productdetails (id integer primary key not null ," +
            "name text not null, image text not null, price text not null, favorites text, stars real, description text, reviews text, userid integer not null, " +
            "FOREIGN KEY(userid) REFERENCES userdetails(id) ) ";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.MyContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("inside table creation");
        db.execSQL(TABLE_USER_CREATE);
        db.execSQL(TABLE_PRODUCT_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertUserDetails(UserDetails userDetails) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = db.rawQuery("select * from userdetails", null);
        int count = cursor.getCount() + 5;
        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, userDetails.getName());
        values.put(COLUMN_EMAIL, userDetails.getEmail());
        values.put(COLUMN_UNAME, userDetails.getUsername());
        values.put(COLUMN_PASSWORD, userDetails.getPassword());
        values.put(COLUMN_MOBILE, userDetails.getMobile());
        long id = db.insert(TABLE_USERS, null, values);
        System.out.println("New USer " + id);
        db.close();
    }

    public String searchPassword(String username) {
        db = this.getReadableDatabase();
        String query = " select username,password from " + TABLE_USERS;
        String firstElement, secondElement;
        secondElement = "Not Found";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                firstElement = cursor.getString(0);
                if (firstElement.equals(username)) {
                    secondElement = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }
        db.close();
        return secondElement;

    }

    public void insertProductDetails() {
        db = this.getWritableDatabase();
        Toast.makeText(MyContext, "inside insertdd", Toast.LENGTH_LONG).show();
        ContentValues values = new ContentValues();
        Cursor cursor = db.rawQuery("select * from productdetails", null);
        int count = cursor.getCount() + 1;

        Toast.makeText(MyContext, "inside insert", Toast.LENGTH_LONG).show();

        values.put(PRODUCT_ID, count);
        values.put(PRODUCT_NAME, "Excalibur Women's Casual Tee");
        values.put(PRODUCT_IMAGE, "nap_queen");
        values.put(PRODUCT_PRICE, "Rs.599");
        values.put(PRODUCT_FAVORITE, "FALSE");
        values.put(PRODUCT_STARS, 3);
        values.put(PRODUCT_DESCRIPTION, "This quirky top with a deep cut back with button detailing is a must have!");
        values.put(PRODUCT_REVIEWS, "Decent and good looking");
        values.put(USER_ID, 5);
        long id = db.insert(TABLE_PRODUCTS, null, values);
        System.out.println("Product data inserted" + id);
        db.close();
        System.out.println("Product data inserted" + id);

    }

    public ArrayList<ProductDetails> retrieveProductDetails() {
        ArrayList<ProductDetails> allProducts = new ArrayList<>();
        String queryProducts = "select * from " + TABLE_PRODUCTS;

        db = this.getReadableDatabase();
        Cursor c = db.rawQuery(queryProducts, null);
        System.out.println("cursor count" + c.getCount());


        if (c.moveToFirst()) {
            do {

                int product_id = c.getInt(c.getColumnIndex(PRODUCT_ID));
                String imageName = c.getString(c.getColumnIndex(PRODUCT_IMAGE));

                int image_id = MyContext.getResources().getIdentifier(imageName, "drawable",
                        MyContext.getPackageName());
                String productName = c.getString(c.getColumnIndex(PRODUCT_NAME));
                String productPrice = c.getString(c.getColumnIndex(PRODUCT_PRICE));
                String productfav = c.getString(c.getColumnIndex(PRODUCT_FAVORITE));
                float productstar = c.getFloat(c.getColumnIndex(PRODUCT_STARS));
                String productdesc = c.getString(c.getColumnIndex(PRODUCT_DESCRIPTION));
                String productreviews = c.getString(c.getColumnIndex(PRODUCT_REVIEWS));
                ProductDetails product = new ProductDetails(product_id, image_id, productName, productPrice, productfav, productstar, productdesc, productreviews);
                allProducts.add(product);

            } while (c.moveToNext());
        }
        db.close();
        System.out.println("List" + allProducts);
        return allProducts;
    }

    public void updateProductDetail() {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRODUCT_STARS, 1);
        db.update(TABLE_PRODUCTS, values, PRODUCT_ID + "=" + 1, null);
        db.close();
    }

    public void updateFavorites(int id, String favcorite) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRODUCT_FAVORITE, favcorite);
        db.update(TABLE_PRODUCTS, values, PRODUCT_ID + "=" + id, null);
        db.close();
    }

    public void updateProductRatings(int id, float rating) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRODUCT_STARS, rating);
        db.update(TABLE_PRODUCTS, values, PRODUCT_ID + "=" + id, null);
        db.close();
    }

    public String returnProductFavouties(int id) {
        String queryProducts = "select * from " + TABLE_PRODUCTS + " where " + PRODUCT_ID + " =" + id;
        String fav = null;
        db = this.getReadableDatabase();
        Cursor c = db.rawQuery(queryProducts, null);
        System.out.println("cursor count" + c.getCount());

        if (c.moveToFirst()) {
            fav = c.getString(c.getColumnIndex(PRODUCT_FAVORITE));
        }
        db.close();
        return fav;
    }

    public ProductDetails returnProductDetails(int id) {
        String queryProducts = "select * from " + TABLE_PRODUCTS + " where " + PRODUCT_ID + " =" + id;
        ProductDetails product = null;
        db = this.getReadableDatabase();
        Cursor c = db.rawQuery(queryProducts, null);

        if (c.moveToFirst()) {
            String imageName = c.getString(c.getColumnIndex(PRODUCT_IMAGE));
            int image_id = MyContext.getResources().getIdentifier(imageName, "drawable",
                    MyContext.getPackageName());
            String productName = c.getString(c.getColumnIndex(PRODUCT_NAME));
            String productPrice = c.getString(c.getColumnIndex(PRODUCT_PRICE));
            String productfav = c.getString(c.getColumnIndex(PRODUCT_FAVORITE));
            float productstar = c.getFloat(c.getColumnIndex(PRODUCT_STARS));
            String productdesc = c.getString(c.getColumnIndex(PRODUCT_DESCRIPTION));
            String productreviews = c.getString(c.getColumnIndex(PRODUCT_REVIEWS));
            product = new ProductDetails(id, image_id, productName, productPrice, productfav, productstar, productdesc, productreviews);


        }
        db.close();
        return product;

    }
}
