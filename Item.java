package com.techelevator.view;

import java.util.*;

public abstract class Item {

    private String name;
    private double price;
    private String type;
    private int quantity;

    public Item(String name, double price, String type, int quantity) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
    }

    public Item() {

    }

    public String getName() {
        return name;
    }

    public double getPrice(){
        return price;
    }

    public String getType(){
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

        public abstract String getSound();


    }





