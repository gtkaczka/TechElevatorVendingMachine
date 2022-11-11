package com.techelevator.view;

import java.util.HashMap;
import java.util.Map;

public class Drink extends Item {


    public Drink(String name, double price, String type, int quantity) {
        super(name, price, type, quantity);
    }

    public Drink() {
        super();
    }


    @Override
    public String getSound(){
    return "Glug Glug, Yum!";
    }

}
