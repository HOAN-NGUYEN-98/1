package com.test.models;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class Bill extends RecyclerView.ItemDecoration implements Serializable {
    public String idBill;
    public String dateOfBuy;

    public String getIdBill() {
        return idBill;
    }

    public Bill(String dateOfBuy) {
        this.dateOfBuy = dateOfBuy;
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
}
