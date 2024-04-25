package DTO.Shop;

import DTO.Item;
import DTO.Voucher.Voucher;

import java.util.ArrayList;
import java.util.HashMap;

public class Shop {
    String name;
    ArrayList<Item> itemList;
    HashMap<String, ArrayList<Voucher>> voucherList;

    public HashMap<String, ArrayList<Voucher>> getVoucherList() {
        return voucherList;
    }

    public Shop(String name, ArrayList<Item> itemList) {
        this.name = name;
        this.itemList = itemList;
        this.voucherList = new HashMap<>();
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public String getName() {
        return name;
    }

    public Shop classifyShop(String name) {
        switch (name) {
            case "FlowerShop" -> {
                return new FlowerShop(this.name, this.itemList);
            }
            case "MeatShop" -> {
                return new MeatShop(this.name, this.itemList);
            }
            default -> {
                return new Shop(this.name, this.itemList);
            }
        }
    }
}
