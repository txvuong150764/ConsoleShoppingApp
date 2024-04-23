package Service;

import DTO.Customer;
import DTO.Item;
import Utils.Utils;

public class CartService {
    public void viewCart(Customer customer) {
        System.out.println("Welcome to Shopping Cart");
        Utils.printItemHeader();
        for(Item item : customer.getShoppingCart()) {
            Utils.printItem(item.getName(), item.getPrice());
        }
        Utils.printItemEnd();
    }
}
