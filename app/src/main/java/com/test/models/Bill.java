package com.test.models;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bill extends RecyclerView.ItemDecoration implements Serializable {
    public String idBill;
    public String dateOfBuy;
    public List<Detail> books;

    public Bill(String dateOfBuy, List<Detail> list) {
        this.dateOfBuy = dateOfBuy;
        this.books = list;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public String getDateOfBuy() {
        return dateOfBuy;
    }

    public void setDateOfBuy(String dateOfBuy) {
        this.dateOfBuy = dateOfBuy;
    }

    public List<Detail> getList() {
        return books;
    }

    public void setList(List<Detail> list) {
        this.books = list;
    }
}
