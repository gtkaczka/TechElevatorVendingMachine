package com.techelevator.view;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.DecimalFormat;

public class VendingMachine {

    private double balance = 0.00;
    public DecimalFormat df = new DecimalFormat("$00.00");
    Map<String, Item> menuMap = new TreeMap<>(getInventory());


    public Map<String, Item> getInventory()  {
        Map<String, Item> inventoryMap = new TreeMap<>();
        File fileInput = new File("vendingmachine.csv");
        try (Scanner vendingMachineItems = new Scanner(fileInput)) {
            while (vendingMachineItems.hasNextLine()) {
                String line = vendingMachineItems.nextLine();
                String[] newLine = line.split("\\|");
                if (newLine[0].contains("A")) {
                    inventoryMap.put(newLine[0], new Chip(newLine[1], Double.parseDouble(newLine[2]), newLine[3], 5));
                } else if (newLine[0].contains("B")) {
                    inventoryMap.put(newLine[0], new Candy(newLine[1], Double.parseDouble(newLine[2]), newLine[3], 5));
                } else if (newLine[0].contains("C")) {
                    inventoryMap.put(newLine[0], new Drink(newLine[1], Double.parseDouble(newLine[2]), newLine[3], 5));
                } else if (newLine[0].contains("D"))
                    inventoryMap.put(newLine[0], new Gum(newLine[1], Double.parseDouble(newLine[2]), newLine[3], 5));
            }

        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        return inventoryMap;
    }



    public void displayInventory() {

        for(Map.Entry<String, Item> entry : menuMap.entrySet()){
            System.out.print(entry.getKey() + " ");
            System.out.print(entry.getValue().getName() + " ");
            System.out.print(entry.getValue().getPrice() + " ");
            System.out.print(entry.getValue().getQuantity());
            System.out.println();
        }
    }

    public void feedMoney() throws IOException {
        try {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Current balance: " + df.format(balance));
            System.out.println("Feed whole dollar amounts into the machine no change.");
            double userMoney = Double.parseDouble(userInput.nextLine());
            balance += userMoney;

            System.out.println("Current balance: " + df.format(balance));
            log("FEED MONEY", userMoney, balance);
        } catch (Exception e) {
            System.out.println("Sorry not the right input, please try again");
        }
    }


    public void buyProduct() throws FileNotFoundException {
        try {
            displayInventory();
            int balanceInPennies = (int)(balance *100);
            System.out.println(df.format(balance) + " is left.");
            Scanner userInput = new Scanner(System.in);
            System.out.println("Select a product: ");
            String productSelected = userInput.nextLine().toUpperCase();
            Item selected = menuMap.get(productSelected);


            if (balance >= selected.getPrice() && selected.getQuantity() > 0) {
                balanceInPennies -= (int)(selected.getPrice()*100);
                balance = balanceInPennies/100.00;
                balance = change(balance, 2);
                selected.setQuantity(selected.getQuantity() - 1);
                System.out.println(selected.getName() + " " + selected.getPrice() + " " + df.format(balance)+ " is left. " + selected.getSound());
            } else if (balance >= selected.getPrice() && selected.getQuantity() == 0) {
                System.out.println("Unfortunately " + selected.getName() + " is sold out please buy a different item.");
            } else if (balance < selected.getPrice()) {
                System.out.println("Unfortunately your balance is too low to purchase " + selected.getName() + ".");
            }

            log(selected.getName(), selected.getPrice(), balance);

        } catch (NullPointerException e){
            System.out.println("Sorry not valid option, please try again");
        }
    }

    public double change(double value, int decimalpoint) {
        value = value * Math.pow(10, decimalpoint);
        value = Math.floor(value);
        value = value / Math.pow(10, decimalpoint);
        return value;
    }

    public void dispenseChange() throws FileNotFoundException {
        log("GIVE CHANGE", balance, 0);


        int balanceInPennies = (int)(balance *100);

        int quarter = 25;
        int dime = 10;
        int nickle = 5;
        int quarterCounter = 0;
        int dimeCounter = 0;
        int nickleCounter = 0;

        if(balanceInPennies >= quarter) {
            while (balanceInPennies >= quarter) {
                balanceInPennies -= 25;
                quarterCounter++;
            }
        }
            if(balanceInPennies >= dime){
                while(balanceInPennies >= dime){
                    balanceInPennies -= 10;
                    dimeCounter++;
                }
            }

            if(balanceInPennies >= nickle){
                while(balanceInPennies >= nickle){
                    balanceInPennies = balanceInPennies - 5;
                    nickleCounter++;
            }
        }

        this.balance = 0;
        System.out.println("Your change is " + quarterCounter + " quarters, " + dimeCounter + " dimes, " + "and " + nickleCounter + " nickles.");
        System.out.println("Balance is now: " + df.format(balance));
        System.out.println("Thank you :D");
    }



        public void log (String action, double balanceDeposit, double balanceChange) throws FileNotFoundException{

            LocalDateTime date = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
            String text = date.format(formatter);


            File file = new File("Log.txt");
            boolean append = file.exists() ? true : false;

             try(PrintWriter write = new PrintWriter(new FileOutputStream(file, append))) {
                 write.println(text + " " + action + " " + balanceDeposit + " " + balanceChange);
             }
        }
    }





