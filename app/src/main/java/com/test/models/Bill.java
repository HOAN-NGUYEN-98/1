package com.test.models;

import java.io.Serializable;

public class Bill implements Serializable {
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
