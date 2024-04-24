package Utils;

import DTO.Cutomer.Customer;
import DTO.Item;
import DTO.Shop.Shop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
    public static void printItemHeader() {
        System.out.println("-------------------------------------------------------");
        System.out.format("| %-15s | %-15s | %-15s |", "Item", "Price", "Amount");
        System.out.println("\n-------------------------------------------------------");
    }
    public static void printItem(String name, float price, int amount) {
        System.out.format("| %-15s | %-15s | %-15s |\n", name, price, amount);
    }
    public static void printItemEnd() {
        System.out.println("-------------------------------------------------------");
    }

    public static void printVoucherHeader() {
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.format("| %-25s | %-15s | %-15s | %-15s |", "Voucher Type", "Discount Rate", "Minimum Spend", "Amount");
        System.out.println("\n-----------------------------------------------------------------------------------");
    }
    public static void printVoucher(String type, float discountRate, float minimumSpend, int amount) {
        System.out.format("| %-25s | %-15s | %-15s | %-15s |\n", type, discountRate, minimumSpend, amount);
    }
    public static void printVoucherEnd() {
        System.out.println("-----------------------------------------------------------------------------------");
    }
    public static void printShippingHeader() {
        System.out.println("-------------------------------------------------------");
        System.out.format("| %-15s | %-15s | %-15s |", "Type", "Duration", "Price");
        System.out.println("\n-------------------------------------------------------");
    }
    public static void printShipping(String type, int duration, float price) {
        System.out.format("| %-15s | %-15s | %-15s |\n", type, duration, price);
    }
    public static void printShippingEnd() {
        System.out.println("-------------------------------------------------------");
    }
    public static int getMainMenuCustomerInput(String name) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nWelcome " + name + " to Main Menu");
        System.out.println("1. View Cart");
        System.out.println("2. View Rank");
        System.out.println("3. View Shopping Item");
        System.out.println("4. Exit");
        System.out.print("Please enter your option: ");
        return sc.nextInt();
    }
    public static int getCartCustomerInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Select shipping method");
        System.out.println("2. Return to Main Menu");
        System.out.print("Please enter your option: ");
        return sc.nextInt();
    }
    public static int getShopCustomerInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Buy");
        System.out.println("2. Return to Main Menu");
        System.out.print("Please enter your option: ");
        return sc.nextInt();
    }
    public static int getShippingCustomerInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please select shipping method");
        System.out.println("1. Basic");
        System.out.println("2. Fast");
        System.out.println("3. Saving");
        System.out.println("4. Return to Cart Menu");
        System.out.print("Please enter your option: ");
        return sc.nextInt();
    }
    public static int getCheckoutCustomerInput() {
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

        if(itemList.trim().isEmpty()) {
            return shoppingCart;
        }

        String[] items = itemList.split("\\|");
        for (String item : items) {
            String itemName = item.split(":")[0].trim();
            float price = Float.parseFloat(item.split(":")[1].trim());
            int amount = Integer.parseInt(item.split(":")[2].trim());
            Item i = new Item(itemName, price, amount);
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
                        String[] itemInfoSplited = itemInfo.split(",", 3);
                        itemList.add(new Item(itemInfoSplited[0].trim(), Float.parseFloat(itemInfoSplited[1].trim()), Integer.parseInt(itemInfoSplited[2].trim())));
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
