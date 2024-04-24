package DTO.Cutomer;

import DTO.Item;
import DTO.Shop.Shop;
import DTO.Voucher.Voucher;

import java.util.ArrayList;

public class Customer {
    String name;
    String password;
    Shop shop;
    float loyalPoints;
    String rank;
    ArrayList<Item> shoppingCart;
    ArrayList<Voucher> voucherList;
    float discountRate;
    public Customer(String name, String password, Shop shop, float loyalPoints, ArrayList<Item> shoppingCart) {
        this.name = name;
        this.password = password;
        this.shop = shop;
        this.loyalPoints = loyalPoints;
        this.setRank(loyalPoints);
        this.shoppingCart = shoppingCart;
        this.discountRate = 0;
        this.voucherList = new ArrayList<>();
    }

    public void setShoppingCart(ArrayList<Item> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    public void addNewItemToCart(Item item) {
        this.shoppingCart.add(item);
    }

    public void setRank(float loyalPoints) {
        if(loyalPoints < 100) {
            this.rank = "Common";
        }
        else if(loyalPoints >= 100 && loyalPoints < 200) {
            this.rank = "Silver";
        }
        else if(loyalPoints >= 200 && loyalPoints < 300) {
            this.rank = "Gold";
        }
        else {
            this.rank = "Diamond";
        }
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Shop getShop() {
        return shop;
    }

    public float getLoyalPoints() {
        return loyalPoints;
    }

    public String getRank() {
        return rank;
    }

    public void setLoyalPoints(float loyalPoints) {
        this.loyalPoints = loyalPoints;
    }

    public ArrayList<Item> getShoppingCart() {
        return shoppingCart;
    }

    public float getDiscountRate() {
        return discountRate;
    }

    public Customer classifyCustomer(String rank) {
        switch (rank) {
            case "Silver" -> {
                return new SilverCustomer(this.name, this.password, this.shop, this.loyalPoints, this.shoppingCart);
            }
            case "Gold" -> {
                return new GoldCustomer(this.name, this.password, this.shop, this.loyalPoints, this.shoppingCart);
            }
            case "Diamond" -> {
                return new DiamondCustomer(this.name, this.password, this.shop, this.loyalPoints, this.shoppingCart);
            }
            default -> {
                return new Customer(this.name, this.password, this.shop, this.loyalPoints, this.shoppingCart);
            }
        }
    }

    @Override
    public String toString() {
        String tmp = "";
        for(Item i : this.shoppingCart) {
            tmp += i.toString();
        }

        return this.name + " " + this.password + " " + this.loyalPoints + " " + this.rank + " " + tmp;
    }
}
