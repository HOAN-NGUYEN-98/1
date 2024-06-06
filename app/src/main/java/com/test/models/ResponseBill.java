package com.test.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseBill {
    @SerializedName("idBill")
    int idBill;
    @SerializedName("dateOfBuy")
    String dateOfBuy;
    @SerializedName("listbook")
    List<Detail> list;

    public List<Detail> getList() {
        return list;
    }

    public int getIdBill() {
        return idBill;
    }

    public String getDateOfBuy() {
        return dateOfBuy;
    }
}
