package DTO.Voucher;

public class ShippingVoucher extends Voucher{
    public ShippingVoucher(float discountRate, int amount) {
        super(discountRate, amount);
        super.type = "ShippingVoucher";
    }
}
