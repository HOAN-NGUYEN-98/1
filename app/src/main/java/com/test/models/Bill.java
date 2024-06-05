package com.test.models;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class Bill extends RecyclerView.ItemDecoration implements Serializable {
    public String idBill;
    public  String dateOfBuy;
    public ArrayList<Detail> detailList;

    public Bill( String dateOfBuy, ArrayList<Detail> detailList) {
        this.dateOfBuy = dateOfBuy;
        this.detailList = detailList;
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

    public ArrayList<Detail> getDetailList() {
        return detailList;
    }

    public void setDetailList(ArrayList<Detail> detailList) {
        this.detailList = detailList;
    }
}
