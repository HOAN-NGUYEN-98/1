package com.test.models;

public class Detail {
    int idBook, quantitySell,idBill;


    public int getIdBook() {
        return idBook;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getQuantitySell() {
        return quantitySell;
    }

    public void setQuantitySell(int quantitySell) {
        this.quantitySell = quantitySell;
    }

    public Detail(int idBook, int quantitySell, int idBill) {
        this.idBook = idBook;
        this.quantitySell = quantitySell;
        this.idBill = idBill;
    }
}
