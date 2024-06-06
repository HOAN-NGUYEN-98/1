package com.test.models;

public class Detail {
    public int id, idBill, idBook, quantitySell;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBill() {
        return idBill;
    }

    public Detail(int idBook, int quantitySell) {
        this.idBook = idBook;
        this.quantitySell = quantitySell;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
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
