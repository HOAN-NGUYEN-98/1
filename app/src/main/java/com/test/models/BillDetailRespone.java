package com.test.models;

import java.io.Serializable;
import java.util.List;

public class BillDetailRespone implements Serializable {
    public String idBill,dateOfBuy, quantitySell, Total;

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

    public String getQuantitySell() {
        return quantitySell;
    }

    public void setQuantitySell(String quantitySell) {
        this.quantitySell = quantitySell;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    List<Book> bookList;
}
