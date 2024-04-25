package Service;

import DTO.Cutomer.Customer;
import DTO.Item;
import DTO.Shop.Shop;
import DTO.Voucher.Voucher;
import Utils.Utils;

import java.util.Scanner;

public class ShopService {
    CartService cartService = new CartService();
    public void viewItems(Shop shop) {
        System.out.println("Welcome to " + shop.getName() + " items list");
        Utils.printItemHeader();
        for(Item item : shop.getItemList()) {
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
                cartService.updateCart(customer.getShoppingCart(), item, amount);
                System.out.println("You bought " + amount + " " + item.getName());
                return;
            }
        }
        System.out.println("Invalid item. Please re-enter.");
    }

}
