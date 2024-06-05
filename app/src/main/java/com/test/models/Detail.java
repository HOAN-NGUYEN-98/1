package com.test.models;

public class Detail {
    public int idBill, idBook, quantitySell;

    public Detail(int idBill, int idBook, int quantitySell) {
        this.idBill = idBill;
        this.idBook = idBook;
        this.quantitySell = quantitySell;
    }

    public int getIdBook() {
        return idBook;
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
}
