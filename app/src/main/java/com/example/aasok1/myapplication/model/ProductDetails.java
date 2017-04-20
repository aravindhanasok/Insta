package com.example.aasok1.myapplication.model;

/**
 * Created by aasok1 on 3/27/2017.
 */

public class ProductDetails {
    private int product_ID;
    private int image_ID;
    private String product_name;
    private String product_price;
    private String product_favorites;
    private float product_stars;
    private String product_description;
    private String product_reviews;

    public String getDescription() {
        return product_description;
    }

    public void setDescription(String description) {
        this.product_description = description;
    }

    public String getReviews() {
        return product_reviews;
    }

    public void setReviews(String reviews) {
        this.product_reviews = reviews;
    }



    public ProductDetails(int id, int image, String name, String price, String favorites, float stars, String description, String reviews) {
        this.setProduct_ID(id);
        this.setImage_ID(image);
        this.setProduct_name(name);
        this.setProduct_price(price);
        this.setProduct_favorites(favorites);
        this.setProduct_stars(stars);
        this.setDescription(description);
        this.setReviews(reviews);

    }


    public int getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(int product_ID) {
        this.product_ID = product_ID;
    }


    public float getProduct_stars() {
        return product_stars;
    }

    public void setProduct_stars(float product_stars) {
        this.product_stars = product_stars;
    }


    public String getProduct_favorites() {
        return product_favorites;
    }

    public void setProduct_favorites(String product_favorites) {
        this.product_favorites = product_favorites;
    }


    public int getImage_ID() {
        return image_ID;
    }

    public void setImage_ID(int image_ID) {
        this.image_ID = image_ID;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }
}
