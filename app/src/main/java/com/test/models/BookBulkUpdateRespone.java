package com.test.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookBulkUpdateRespone {
    @SerializedName("data")
    List<Book> list;

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }
}
