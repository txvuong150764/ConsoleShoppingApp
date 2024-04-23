import DTO.Customer;
import Service.CartService;
import Service.CustomerService;
import Utils.Utils;

import java.util.ArrayList;
import java.util.Scanner;

import Enum.MainMenuOption;
import Enum.ShoppingCartOption;

public class Main {
    static CustomerService customerService = new CustomerService();
    public static void main(String[] args) {
        ArrayList<Customer> customers = Utils.readCustomersFile("C:\\Users\\Tran Xuan Vuong\\OneDrive\\Máy tính\\Java Course\\ConsoleShoppingApp\\ConsoleShoppingApp\\src\\Database\\customers.txt");
        for(Customer customer : customers) {
            System.out.println(customer.toString());
        }

        Customer loggedInCustomer = customerService.login(customers);

        while(true) {
            MainMenuOption mainMenuOption = MainMenuOption.fromInput(Utils.getMainMenuCustomerInput(loggedInCustomer.getName()));
            if(mainMenuOption == null) {
                System.out.println("Your option is invalid. Please try again with 1 or 2 or 3");
                continue;
            }
            switch (mainMenuOption) {
                case VIEW_CART -> {
                    boolean backToMainMenu = false;
                    customerService.viewCart(loggedInCustomer);
                    while(!backToMainMenu) {
                        ShoppingCartOption shoppingCartOption = ShoppingCartOption.fromInput(Utils.getCartCustomerInput());
                        if(shoppingCartOption == null) {
                            System.out.println("Your option is invalid. Please try again with 1 or 2");
                            continue;
                        }
                        switch (shoppingCartOption) {
                            case CHECK_OUT -> {
                                break;
                            }
                            case RETURN -> backToMainMenu = true;
                        }
                    }
                }
                case VIEW_RANK -> customerService.viewRank(loggedInCustomer);
                case VIEW_SHOP_ITEMS -> customerService.viewItems(loggedInCustomer);
            }
        }
    }
}