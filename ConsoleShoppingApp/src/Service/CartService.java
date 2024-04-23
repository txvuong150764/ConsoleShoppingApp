package Service;

import DTO.Customer;
import DTO.Item;
import Utils.Utils;

import java.util.ArrayList;

public class CartService {
    public void viewCart(Customer customer) {
        System.out.println("Welcome to Shopping Cart");
        Utils.printItemHeader();
        for(Item item : customer.getShoppingCart()) {
            Utils.printItem(item.getName(), item.getPrice(), item.getAmount());
        }
        Utils.printItemEnd();
    }
    public float totalPrice(Customer customer) {
        float ans = 0;
        for(Item item : customer.getShoppingCart()) {
            ans += item.getPrice() * item.getAmount();
        }

        return ans;
    }
    public void checkOut(Customer customer) {
        float priceWithoutDiscount = totalPrice(customer);
        float priceAfterDiscount = priceWithoutDiscount * (1 - customer.getDiscountRate());
        float loyalPointsGained = priceWithoutDiscount * 0.1F;
        customer.setLoyalPoints(customer.getLoyalPoints() + loyalPointsGained);
        customer.setRank(customer.getLoyalPoints());
        customer.setShoppingCart(new ArrayList<>());
        customer = customer.classifyCustomer(customer.getRank());
        System.out.println("You paid " + priceAfterDiscount);
        System.out.println("You received " + loyalPointsGained + " points.");
    }
    public void updateCart(Customer customer, Item item, int amount) {
        for(Item i : customer.getShoppingCart()) {
            if(i.getName().equalsIgnoreCase(item.getName())) {
                item.setAmount(i.getAmount() + amount);
                return;
            }
        }
        customer.addNewItemToCart(new Item(item.getName(), item.getPrice(), amount));
    }
}
