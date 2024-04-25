package DTO.Shop;

import DTO.Item;
import DTO.Voucher.Voucher;

import java.util.ArrayList;

public class FlowerShop extends Shop{
    public FlowerShop(String name, ArrayList<Item> itemList) {
        super(name, itemList);
        int max = 10;
        int min = 1;
        for(Item item : itemList) {
            super.voucherList.put(item.getName(), new ArrayList<>());
            for(int i = 0; i <= (int) (Math.random() * (max - min + 1)) + min; i++) {
                super.voucherList.get(item.getName()).add(new Voucher(Math.round(Math.random() * 100F) / 100F, (int) (Math.random() * (max - min + 1)) + min, (int) (Math.random() * (item.getAmount() - min + 1)) + min));
            }
        }
    }
}
