package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.VendingMachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private static final String SUB_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String SUB_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String SUB_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] SUB_MENU_OPTIONS = { SUB_MENU_OPTION_FEED_MONEY, SUB_MENU_OPTION_SELECT_PRODUCT, SUB_MENU_OPTION_FINISH_TRANSACTION };

	private Menu menu;



	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}


	public void run() throws IOException {

		VendingMachine vm = new VendingMachine();

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				vm.displayInventory();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				while (true) {
					String choice2 = (String) menu.getChoiceFromOptions(SUB_MENU_OPTIONS);
					if (choice2.equals(SUB_MENU_OPTION_FEED_MONEY)) {
						vm.feedMoney();
					} else if (choice2.equals(SUB_MENU_OPTION_SELECT_PRODUCT)) {
						vm.buyProduct();
					} else if (choice2.equals(SUB_MENU_OPTION_FINISH_TRANSACTION)) {
						vm.dispenseChange();
						break;
					}
				}
				} else if(choice.equals(MAIN_MENU_OPTION_EXIT)) {
				break;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();//

	}
}
