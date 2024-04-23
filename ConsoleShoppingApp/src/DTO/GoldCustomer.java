package DTO;

import java.util.ArrayList;

public class GoldCustomer extends SilverCustomer{
    public GoldCustomer(String name, String password, Shop shop, float loyalPoints, ArrayList<Item> shoppingCart) {
        super(name, password, shop, loyalPoints, shoppingCart);
        super.discountRate = 0.2F;
    }
}
