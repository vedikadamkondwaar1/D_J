package com.example.d_j.ui.home;

import android.graphics.Bitmap;

public class CategoryList {
    private String categoryname;
    private int imgId;



    public CategoryList(String categoryname, int imgId) {
        this.categoryname = categoryname;
        this.imgId = imgId;

    }

    public String getCategoryname(){
        return categoryname;
    }
    public void setCategoryname(String categoryname){
        this.categoryname = categoryname;
    }

    public int getImgId() {
        return imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }


}
