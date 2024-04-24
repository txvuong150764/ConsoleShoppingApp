package DTO.Shop;

import DTO.Item;
import DTO.Voucher.Voucher;

import java.util.ArrayList;
import java.util.HashMap;

public class Shop {
    String name;
    ArrayList<Item> itemList;
    HashMap<Integer, ArrayList<Voucher>> voucherList;

    public Shop(String name, ArrayList<Item> itemList) {
        this.name = name;
        this.itemList = itemList;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public String getName() {
        return name;
    }

    public Shop classifyShop(String name) {
        switch (name) {
            case "Flower Shop" -> {
                return new FlowerShop(this.name, this.itemList);
            }
            case "Meat Shop" -> {
                return new MeatShop(this.name, this.itemList);
            }
            default -> {
                return new Shop(this.name, this.itemList);
            }
        }
    }
}
