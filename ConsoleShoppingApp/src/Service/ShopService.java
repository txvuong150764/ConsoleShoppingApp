package Service;

import DTO.Customer;
import DTO.Item;
import Utils.Utils;
import Service.CartService;

import java.util.Scanner;

public class ShopService {
    CartService cartService = new CartService();
    public void viewItems(Customer customer) {
        System.out.println("Welcome to " + customer.getShop().getName() + " items list");
        Utils.printItemHeader();
        for(Item item : customer.getShop().getItemList()) {
            Utils.printItem(item.getName(), item.getPrice(), item.getAmount());
        }
        Utils.printItemEnd();
    }
    public int getCustomerAmount() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter amount you want to buy: ");
        return sc.nextInt();
    }
    public void buyItem(Customer customer) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter item you want to buy: ");
        String itemName = sc.nextLine();
        for (Item item : customer.getShop().getItemList()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                int amount = getCustomerAmount();
                while (amount < 0 || amount > item.getAmount()) {
                    amount = getCustomerAmount();
                    System.out.println("Invalid amount. Please re-enter.");
                }
                item.setAmount(item.getAmount() - amount);
                cartService.updateCart(customer, item, amount);
                return;
            }
        }
        System.out.println("Invalid item. Please re-enter.");
    }
}
