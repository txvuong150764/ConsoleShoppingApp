package DTO.Cutomer;

import DTO.Item;
import DTO.Shop.Shop;
import DTO.Voucher.ItemVoucher;
import DTO.Voucher.ShippingVoucher;

import java.util.ArrayList;

public class GoldCustomer extends SilverCustomer{
    public GoldCustomer(String name, String password, Shop shop, float loyalPoints, ArrayList<Item> shoppingCart) {
        super(name, password, shop, loyalPoints, shoppingCart);
        super.discountRate = 0.2F;
        super.voucherList.add(new ShippingVoucher(0.2F, 3, 0));
        super.voucherList.add(new ItemVoucher(0.05F, 3,150));
        super.voucherList.add(new ItemVoucher(0.2F, 3,200));
    }
}
