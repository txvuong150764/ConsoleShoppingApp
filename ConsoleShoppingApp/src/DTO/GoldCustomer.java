package DTO;

import java.util.ArrayList;

public class GoldCustomer extends Customer{
    public GoldCustomer(String name, String password, Shop shop, float loyalPoints, ArrayList<Item> shoppingCart, float discountRate) {
        super(name, password, shop, loyalPoints, shoppingCart);
        super.discountRate = discountRate;
    }
}
