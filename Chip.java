package com.techelevator.view;

import java.util.HashMap;
import java.util.Map;

public class Chip extends Item {

    public Chip(String name, double price, String type, int quantity) {
        super(name, price, type, quantity);
    }

    public Chip() {

    }

    @Override
    public String getSound(){
    return "Crunch Crunch, Yum!";
    }




}
