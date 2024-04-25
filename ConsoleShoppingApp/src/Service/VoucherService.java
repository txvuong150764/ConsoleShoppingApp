package Service;

import DTO.Cutomer.Customer;
import DTO.Item;
import DTO.Shop.Shop;
import DTO.Voucher.Voucher;
import Utils.Utils;

import java.util.ArrayList;

public class VoucherService {
    public Voucher bestShippingVoucher(ArrayList<Voucher> vouchers) {
        float maxDiscount = 0F;
        Voucher bestShippingVoucher = null;
        for(Voucher voucher : vouchers) {
            if(voucher.getType().equals("ShippingVoucher")) {
                if(voucher.getDiscountRate() > maxDiscount) {
                    maxDiscount = voucher.getDiscountRate();
                    bestShippingVoucher = voucher;
                }
            }
        }
        return bestShippingVoucher;
    }
    public Voucher bestItemVoucher(ArrayList<Voucher> vouchers, float totalItemPriceInCart) {
        float maxDiscount = 0F;
        Voucher bestItemVoucher = null;
        for(Voucher voucher : vouchers) {
            if(voucher.getType().equals("ItemVoucher")) {
                if(voucher.getDiscountRate() > maxDiscount && totalItemPriceInCart >= voucher.getMinimumSpend()) {
                    maxDiscount = voucher.getDiscountRate();
                    bestItemVoucher = voucher;
                }
            }
        }
        return bestItemVoucher;
    }
    public void removeVoucher(ArrayList<Voucher> vouchers, Voucher v) {
        if (v == null) {
            return;
        }
        for(Voucher voucher : vouchers) {
            if(voucher.getType().equals(v.getType()) && voucher.getMinimumSpend() == v.getMinimumSpend() && voucher.getDiscountRate() == v.getDiscountRate()) {
                if(voucher.getAmount() > 0) {
                    voucher.setAmount(voucher.getAmount() - 1);
                }
                else {
                    vouchers.remove(v);
                }
            }
        }
    }
    public void printRankVoucher(ArrayList<Voucher> vouchers) {
        Utils.printVoucherHeader();
        for(Voucher voucher : vouchers) {
            if(isValidVoucher(voucher)) {
                Utils.printVoucher(voucher.getType(), voucher.getDiscountRate(), voucher.getMinimumSpend(), voucher.getAmount());
            }
            else {
                vouchers.remove(voucher);
            }
        }
        Utils.printVoucherEnd();
    }
    public void printShopVouchers(Shop shop) {
        Utils.printShopVoucherHeader();
        for(String itemName : shop.getVoucherList().keySet()) {
            for(Voucher voucher : shop.getVoucherList().get(itemName)) {
                Utils.printShopVoucher(itemName, voucher.getDiscountRate(), voucher.getMinimumSpend(), voucher.getAmount());
            }
        }
        Utils.printVoucherEnd();;
    }
    public boolean isValidVoucher(Voucher voucher) {
        return voucher != null;
    }
}
