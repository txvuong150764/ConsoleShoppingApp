package Service;

import DTO.Item;
import DTO.Shop;
import Utils.Utils;

public class ShopService {
    public void viewItems(Shop shop) {
        System.out.println("Welcome to " + shop.getName() + " items list");
        Utils.printItemHeader();
        for(Item item : shop.getItemList()) {
            Utils.printItem(item.getName(), item.getPrice());
        }
        Utils.printItemEnd();
    }
}
