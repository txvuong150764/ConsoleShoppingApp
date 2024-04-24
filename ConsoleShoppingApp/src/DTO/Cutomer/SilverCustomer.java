package DTO.Cutomer;

import DTO.Item;
import DTO.Shop.Shop;
import DTO.Voucher.ItemVoucher;
import DTO.Voucher.ShippingVoucher;

import java.util.ArrayList;

public class SilverCustomer extends Customer{
    public SilverCustomer(String name, String password, Shop shop, float loyalPoints, ArrayList<Item> shoppingCart) {
        super(name, password, shop, loyalPoints, shoppingCart);
        super.discountRate = 0.1F;
        super.voucherList.add(new ShippingVoucher(0.1F, 2));
        super.voucherList.add(new ItemVoucher(0.1F, 1,200));
        super.voucherList.add(new ItemVoucher(0.15F, 1,250));
    }
}
