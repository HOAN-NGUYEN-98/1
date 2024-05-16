package com.test.models;

import java.io.Serializable;

public class BillDetailRespone implements Serializable {
    public String idDetailBill, idBook, idBill, quantitySell, price;

    public String getIdDetailBill() {
        return idDetailBill;
    }

    public void setIdDetailBill(String idDetailBill) {
        this.idDetailBill = idDetailBill;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public String getQuantitySell() {
        return quantitySell;
    }

    public void setQuantitySell(String quantitySell) {
        this.quantitySell = quantitySell;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public BillDetailRespone(String idDetailBill, String idBook, String idBill, String quantitySell, String price) {
        this.idDetailBill = idDetailBill;
        this.idBook = idBook;
        this.idBill = idBill;
        this.quantitySell = quantitySell;
        this.price = price;
    }
}
