package com.test.models;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.Date;

public class Bill extends RecyclerView.ItemDecoration implements Serializable {
    String idBill;
    String dateOfBuy;
    String nameBook;
    String price;
    String quantitySell;
    String idBook;

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantitySell() {
        return quantitySell;
    }

    public void setQuantitySell(String quantitySell) {
        this.quantitySell = quantitySell;
    }


    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }


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
