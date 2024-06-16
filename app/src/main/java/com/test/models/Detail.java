package com.test.models;

public class Detail {
    public int id;
    public int idBill;
    public int price;
    public int idBook;

    public String  name;
    public int quantitySell;
    public String dateOfBuy;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBuy() {
        return dateOfBuy;
    }
    public void setDateOfBuy(String dateOfBuy) {
        this.dateOfBuy = dateOfBuy;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

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
