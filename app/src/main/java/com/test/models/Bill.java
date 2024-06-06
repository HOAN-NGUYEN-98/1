package com.test.models;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class Bill extends RecyclerView.ItemDecoration implements Serializable {
    public String idBill;
    public String dateOfBuy;
    ArrayList<Detail> details;

    public Bill(String dateOfBuy, ArrayList<Detail> details) {
        this.dateOfBuy = dateOfBuy;
        this.details = details;
    }

    public ArrayList<Detail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<Detail> details) {
        this.details = details;
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
