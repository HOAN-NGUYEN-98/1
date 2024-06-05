package com.test.models;

import java.io.Serializable;

public class BookRespone implements Serializable {
    String idBook, idType, creator, name, producer, price, quantity;

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public BookRespone( String name,String idType,String price,String quantity,String creator,String producer) {

        this.name = name;
        this.idType = idType;
        this.price = price;
        this.quantity = quantity;
        this.creator = creator;
        this.producer = producer;
    }
}
