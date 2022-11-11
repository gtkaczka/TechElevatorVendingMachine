package com.techelevator.view;

import java.util.HashMap;
import java.util.Map;

public class Candy extends Item{


    public Candy(String name, double price, String type, int quantity) {
        super(name, price, type, quantity);
    }


    public Candy() {
    }

    @Override
        public String getSound(){
        return "Munch Munch, Yum!";
        }

}
