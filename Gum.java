package com.techelevator.view;

import java.util.HashMap;
import java.util.Map;

public class Gum extends Item{

    public Gum(String name, double price, String type, int quantity) {
        super(name, price, type, quantity);
    }


    public Gum() {

    }

    @Override
    public String getSound(){
    return "Chew Chew, Yum!";
    }


}
