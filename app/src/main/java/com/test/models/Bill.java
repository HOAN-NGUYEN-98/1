package com.test.models;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.Date;

public class Bill extends RecyclerView.ItemDecoration implements Serializable {
   String idBill, dateOfBuy;

    public Bill(String idBill, String dateOfBuy) {
        this.idBill = idBill;
        this.dateOfBuy = dateOfBuy;
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
}
