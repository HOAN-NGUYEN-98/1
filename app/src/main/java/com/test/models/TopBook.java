package com.test.models;

public class TopBook {
    String quantity,idBook, nameBook;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public TopBook(String quantity, String idBook, String nameBook) {
        this.quantity = quantity;
        this.idBook = idBook;
        this.nameBook = nameBook;
    }
}
