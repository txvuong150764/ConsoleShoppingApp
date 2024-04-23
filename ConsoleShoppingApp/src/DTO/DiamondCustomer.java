package DTO;

import java.util.ArrayList;

public class DiamondCustomer extends GoldCustomer{
    public DiamondCustomer(String name, String password, Shop shop, float loyalPoints, ArrayList<Item> shoppingCart) {
        super(name, password, shop, loyalPoints, shoppingCart);
        super.discountRate = 0.3F;
    }
}
