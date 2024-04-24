package DTO.Voucher;

public class ShippingVoucher extends Voucher{
    public ShippingVoucher(float discountRate, int amount, float minimumSpend) {
        super(discountRate, amount, minimumSpend);
        super.type = "ShippingVoucher";
    }
}
