package Service;

import DTO.Cutomer.Customer;
import DTO.Item;
import DTO.Shipping.Shipping;
import DTO.Voucher.Voucher;
import Utils.Utils;

import java.util.ArrayList;

public class CartService {
    VoucherService voucherService = new VoucherService();
    public void viewCart(ArrayList<Item> shoppingCart) {

        Utils.printItemHeader();
        for(Item item : shoppingCart) {
            Utils.printItem(item.getName(), item.getPrice(), item.getAmount());
        }
        Utils.printItemEnd();
        System.out.println("Total: " + this.totalItemPrice(shoppingCart));
    }
    public float totalItemPrice(ArrayList<Item> shoppingCart) {
        float ans = 0;
        for(Item item : shoppingCart) {
            ans += item.getPrice() * item.getAmount();
        }

        return ans;
    }
    public float totalPrice(float totalItemPrice, float shippingPrice, Voucher shippingVoucher, Voucher itemVoucher) {
        float shippingDiscount = shippingVoucher == null ? 0 : shippingVoucher.getDiscountRate();
        float itemDiscount = itemVoucher == null ? 0 : itemVoucher.getDiscountRate();
        return totalItemPrice * (1 - itemDiscount) + shippingPrice * (1 - shippingDiscount);
    }
    public void viewCartToCheckout(Customer customer, Shipping shipping) {
        float totalItemPrice = this.totalItemPrice(customer.getShoppingCart());

        System.out.println("Cart summarization: ");
        viewCart(customer.getShoppingCart());

        System.out.println("Shipping method: ");
        Utils.printShippingHeader();
        Utils.printShipping(shipping.getType(), shipping.getDuration(), shipping.getPrice());
        Utils.printShippingEnd();

        System.out.println("Best voucher will be applied: ");
        Utils.printVoucherHeader();
        Voucher bestShippingVoucher = voucherService.bestShippingVoucher(customer.getVoucherList());
        Voucher bestItemVoucher = voucherService.bestItemVoucher(customer.getVoucherList(), totalItemPrice);
        Utils.printVoucher(bestShippingVoucher.getType(), bestShippingVoucher.getDiscountRate(), bestShippingVoucher.getMinimumSpend(), bestShippingVoucher.getAmount());
        if (bestItemVoucher != null) {
            Utils.printVoucher(bestItemVoucher.getType(), bestItemVoucher.getDiscountRate(), bestItemVoucher.getMinimumSpend(), bestItemVoucher.getAmount());
        }
        Utils.printVoucherEnd();

        System.out.println("Total: " + this.totalPrice(totalItemPrice, shipping.getPrice(), bestShippingVoucher, bestItemVoucher));
    }
    public void checkOut(Customer customer, Shipping shipping) {
        float totalItemPrice = this.totalItemPrice(customer.getShoppingCart());
        Voucher bestShippingVoucher = voucherService.bestShippingVoucher(customer.getVoucherList());
        Voucher bestItemVoucher = voucherService.bestItemVoucher(customer.getVoucherList(), totalItemPrice);

        float priceAfterDiscount = this.totalPrice(totalItemPrice, shipping.getPrice(), bestShippingVoucher, bestItemVoucher);
        float loyalPointsGained = totalItemPrice * 0.1F;

        voucherService.removeVoucher(customer.getVoucherList(), bestItemVoucher);
        voucherService.removeVoucher(customer.getVoucherList(), bestShippingVoucher);

        customer.setLoyalPoints(customer.getLoyalPoints() + loyalPointsGained);
        customer.setRank(customer.getLoyalPoints());
        customer.setShoppingCart(new ArrayList<>());
        customer = customer.classifyCustomer(customer.getRank());
        System.out.println("You paid " + priceAfterDiscount);
        System.out.println("You received " + loyalPointsGained + " points.");
    }
    public void updateCart(ArrayList<Item> shoppingCart, Item item, int amount) {
        for(Item i : shoppingCart) {
            if(i.getName().equalsIgnoreCase(item.getName())) {
                item.setAmount(i.getAmount() + amount);
                return;
            }
        }
        shoppingCart.add(new Item(item.getName(), item.getPrice(), amount));
    }
    public boolean isEmpty(ArrayList<Item> shoppingCart) {
        return shoppingCart.isEmpty();
    }
}
