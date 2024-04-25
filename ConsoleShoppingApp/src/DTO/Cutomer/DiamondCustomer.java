package DTO.Cutomer;

import DTO.Item;
import DTO.Shop.Shop;
import DTO.Voucher.ItemVoucher;
import DTO.Voucher.ShippingVoucher;

import java.util.ArrayList;

public class DiamondCustomer extends GoldCustomer{
    public DiamondCustomer(String name, String password, Shop shop, float loyalPoints, ArrayList<Item> shoppingCart) {
        super(name, password, shop, loyalPoints, shoppingCart);
        super.voucherList.add(new ShippingVoucher(0.3F, 4, 0));
        super.voucherList.add(new ItemVoucher(0.1F, 3,150));
        super.voucherList.add(new ItemVoucher(0.25F, 3,200));
    }
}
