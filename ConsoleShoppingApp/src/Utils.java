import DTO.Customer;
import DTO.Item;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
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
                        ArrayList<Item> shoppingCart = readShoppingCart(customerInfoSplited[4]);

                        Customer customer = new Customer(customerInfoSplited[0].trim(), customerInfoSplited[1].trim(), customerInfoSplited[2].trim(), Float.parseFloat(customerInfoSplited[3]), shoppingCart, Float.parseFloat(customerInfoSplited[5]));
                        customers.add(customer);
                    }
                }
                sc.close();
            }
            else {
                myFile.createNewFile();
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
}
