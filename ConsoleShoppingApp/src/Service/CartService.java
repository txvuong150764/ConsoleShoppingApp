package Service;

import DTO.Cutomer.Customer;
import DTO.Item;
import DTO.Shipping.BasicShipping;
import DTO.Shipping.Shipping;
import DTO.Voucher.Voucher;
import Utils.Utils;

import java.util.ArrayList;

public class CartService {
    public void viewCart(Customer customer) {

        Utils.printItemHeader();
        for(Item item : customer.getShoppingCart()) {
            Utils.printItem(item.getName(), item.getPrice(), item.getAmount());
        }
        Utils.printItemEnd();
        System.out.println("Total: " + this.totalItemPrice(customer));
    }
    public float totalItemPrice(Customer customer) {
        float ans = 0;
        for(Item item : customer.getShoppingCart()) {
            ans += item.getPrice() * item.getAmount();
        }

        return ans;
    }
    public Voucher bestShippingVoucher(Customer customer) {
        float maxDiscount = 0F;
        Voucher bestShippingVoucher = null;
        for(Voucher voucher : customer.getVoucherList()) {
            if(voucher.getType().equals("ShippingVoucher")) {
                if(voucher.getDiscountRate() > maxDiscount) {
                    maxDiscount = voucher.getDiscountRate();
                    bestShippingVoucher = voucher;
                }
            }
        }
        return bestShippingVoucher;
    }
    public Voucher bestItemVoucher(Customer customer) {
        float maxDiscount = 0F;
        Voucher bestItemVoucher = null;
        for(Voucher voucher : customer.getVoucherList()) {
            if(voucher.getType().equals("ItemVoucher")) {
                if(voucher.getDiscountRate() > maxDiscount && this.totalItemPrice(customer) >= voucher.getMinimumSpend()) {
                    maxDiscount = voucher.getDiscountRate();
                    bestItemVoucher = voucher;
                }
            }
        }
        return bestItemVoucher;
    }
    public float totalPrice(float totalItemPrice, float shippingPrice, Voucher shippingVoucher, Voucher itemVoucher) {
        float shippingDiscount = shippingVoucher == null ? 0 : shippingVoucher.getDiscountRate();
        float itemDiscount = itemVoucher == null ? 0 : itemVoucher.getDiscountRate();
        return totalItemPrice * (1 - itemDiscount) + shippingPrice * (1 - shippingDiscount);
    }
    public void viewCartToCheckout(Customer customer, Shipping shipping) {
        float totalItemPrice = this.totalItemPrice(customer);

        System.out.println("Cart summarization: ");
        viewCart(customer);

        System.out.println("Shipping method: ");
        Utils.printShippingHeader();
        Utils.printShipping(shipping.getType(), shipping.getDuration(), shipping.getPrice());
        Utils.printShippingEnd();

        System.out.println("Best voucher will be applied: ");
        Utils.printVoucherHeader();
        Voucher bestShippingVoucher = this.bestShippingVoucher(customer);
        Voucher bestItemVoucher = this.bestItemVoucher(customer);
        Utils.printVoucher(bestShippingVoucher.getType(), bestShippingVoucher.getDiscountRate(), bestShippingVoucher.getMinimumSpend(), bestShippingVoucher.getAmount());
        if (bestItemVoucher != null) {
            Utils.printVoucher(bestItemVoucher.getType(), bestItemVoucher.getDiscountRate(), bestItemVoucher.getMinimumSpend(), bestItemVoucher.getAmount());
        }
        Utils.printVoucherEnd();

        System.out.println("Total: " + this.totalPrice(totalItemPrice, shipping.getPrice(), bestShippingVoucher, bestItemVoucher));
    }
    public void checkOut(Customer customer, Shipping shipping) {
        float totalItemPrice = this.totalItemPrice(customer);
        Voucher bestShippingVoucher = this.bestShippingVoucher(customer);
        Voucher bestItemVoucher = this.bestItemVoucher(customer);

        float priceAfterDiscount = this.totalPrice(totalItemPrice, shipping.getPrice(), bestShippingVoucher, bestItemVoucher);
        float loyalPointsGained = totalItemPrice * 0.1F;

        customer.removeVoucher(bestItemVoucher);
        customer.removeVoucher(bestShippingVoucher);

        customer.setLoyalPoints(customer.getLoyalPoints() + loyalPointsGained);
        customer.setRank(customer.getLoyalPoints());
        customer.setShoppingCart(new ArrayList<>());
        customer = customer.classifyCustomer(customer.getRank());
        System.out.println("You paid " + priceAfterDiscount);
        System.out.println("You received " + loyalPointsGained + " points.");
    }
    public void updateCart(Customer customer, Item item, int amount) {
        for(Item i : customer.getShoppingCart()) {
            if(i.getName().equalsIgnoreCase(item.getName())) {
                item.setAmount(i.getAmount() + amount);
                return;
            }
        }
        customer.addNewItemToCart(new Item(item.getName(), item.getPrice(), amount));
    }

    public boolean isEmpty(Customer customer) {
        return customer.getShoppingCart().isEmpty();
    }
}
