package com.fitternity.urbanfresh.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UrbanFreshRequest
{

    @SerializedName("category_id")
    @Expose
    private String categoryId;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "UrbanFreshRequest{" +
                "categoryId='" + categoryId + '\'' +
                '}';
    }
}
