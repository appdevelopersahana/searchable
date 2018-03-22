package com.example.user.searchablerecyclerview.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 3/22/2018.
 */

public class Seller {
    @SerializedName("sellerName")
    public String sellerName;
    @SerializedName("applianceName")
    public String applainceName;
    @SerializedName("price")
    public String price;
    @SerializedName("imageUrl")
    public String imageUrl;

}