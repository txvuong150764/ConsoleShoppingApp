package Utils;

import DTO.Customer;
import DTO.Item;
import DTO.Shop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
    public static void printItemHeader() {
        System.out.println("-------------------------------------");
        System.out.format("| %-15s | %-15s |", "Item", "Price");
        System.out.println("\n-------------------------------------");
    }
    public static void printItem(String name, float price) {
        System.out.format("| %-15s | %-15s |\n", name, price);
    }
    public static void printItemEnd() {
        System.out.println("-------------------------------------");
    }
    public static int getMainMenuCustomerInput(String name) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome " + name + " to Main Menu");
        System.out.println("1. View Cart");
        System.out.println("2. View Rank");
        System.out.println("3. View Shopping Item");
        System.out.print("Please enter your option: ");
        return sc.nextInt();
    }
    public static int getCartCustomerInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Check Out");
        System.out.println("2. Return to Main Menu");
        System.out.print("Please enter your option: ");
        return sc.nextInt();
    }
    public static ArrayList<Customer> readCustomersFile(String fileName) {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            File myFile = new File(fileName);
            if(myFile.exists() && !myFile.isDirectory()) {
                Scanner sc = new Scanner(myFile);
                while(sc.hasNextLine()) {
                    String customerInfo = sc.nextLine();
                    if(!customerInfo.isEmpty()) {
                        String[] customerInfoSplited = customerInfo.split(",", 6);
                        Shop shop = readShopFile(customerInfoSplited[2].trim());
                        ArrayList<Item> shoppingCart = readShoppingCart(customerInfoSplited[4]);

                        Customer customer = new Customer(customerInfoSplited[0].trim(), customerInfoSplited[1].trim(), shop, Float.parseFloat(customerInfoSplited[3]), shoppingCart);
                        customers.add(customer);
                    }
                }
                sc.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }
    public static ArrayList<Item> readShoppingCart(String itemList) {
        ArrayList<Item> shoppingCart = new ArrayList<>();

        String[] items = itemList.split("\\|");
        for(String item : items) {
            String itemName = item.split(":")[0].trim();
            float price = Float.parseFloat(item.split(":")[1]);
            Item i = new Item(itemName, price);
            shoppingCart.add(i);
        }

        return shoppingCart;
    }

    public static Shop readShopFile(String shopName) {
        try {
            File myFile = new File("C:\\Users\\Tran Xuan Vuong\\OneDrive\\Máy tính\\Java Course\\ConsoleShoppingApp\\ConsoleShoppingApp\\src\\Database\\" + shopName + ".txt");
            if(!myFile.isDirectory()) {
                Scanner sc = new Scanner(myFile);
                ArrayList<Item> itemList = new ArrayList<>();
                while(sc.hasNextLine()) {
                    String itemInfo = sc.nextLine();
                    if(!itemInfo.isEmpty()) {
                        String[] itemInfoSplited = itemInfo.split(",", 2);
                        itemList.add(new Item(itemInfoSplited[0].trim(), Float.parseFloat(itemInfoSplited[1])));
                    }
                }
                sc.close();
                return new Shop(shopName, itemList);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
