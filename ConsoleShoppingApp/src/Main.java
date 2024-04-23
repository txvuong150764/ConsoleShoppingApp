import DTO.Customer;
import Service.CustomerService;
import Utils.Utils;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    enum MainMenu {
        VIEW_CART(1),
        VIER_RANK(2),
        VIEW_SHOP_ITEMS(3);

        private final int value;
        private MainMenu(int value) {
            this.value = value;
        }
        private int getValue() {
            return this.value;
        }

        public static MainMenu fromInput(int input) {
            for(MainMenu type : values()) {
                if(type.getValue() == input) {
                    return type;
                }
            }

            return null;
        }
    }
    static CustomerService customerService = new CustomerService();
    public static Customer login(ArrayList<Customer> customers) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n---------------------------- Login Screen ----------------------------");
        System.out.print("                      Name: ");
        String name = sc.nextLine();
        System.out.print("                      Password    : ");
        String password = sc.nextLine();

        Customer loginUser = customerService.getCustomer(customers, name, password);
        if(loginUser != null) {
            System.out.println("\n                        Login Successfully!!!!");
        }
        else {
            System.out.println("\nName or Password is incorrect. Please try again!!!!!!!!");
            loginUser = login(customers);
        }
        return loginUser;
    }
    public static void main(String[] args) {
        ArrayList<Customer> customers = Utils.readCustomersFile("C:\\Users\\Tran Xuan Vuong\\OneDrive\\Máy tính\\Java Course\\ConsoleShoppingApp\\ConsoleShoppingApp\\src\\Database\\customers.txt");
        for(Customer customer : customers) {
            System.out.println(customer.toString());
        }

        Customer loggedInCustomer = login(customers);

        while(true) {
            MainMenu mainMenuOption = MainMenu.fromInput(Utils.getMainMenuCustomerInput(loggedInCustomer.getName()));
            if(mainMenuOption == null) {
                System.out.println("Your option is invalid. Please try again with 1 or 2 or 3");
                continue;
            }
            switch (mainMenuOption) {
                case VIEW_CART -> customerService.viewCart(loggedInCustomer);
                case VIER_RANK -> customerService.viewRank(loggedInCustomer);
                case VIEW_SHOP_ITEMS -> {
                    break;
                }
            }
        }
    }
}