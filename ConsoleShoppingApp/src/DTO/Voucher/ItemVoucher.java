package DTO.Voucher;

public class ItemVoucher extends Voucher {
    public ItemVoucher(float discountRate, int amount, float minimumSpend) {
        super(discountRate, amount, minimumSpend);
        super.type = "ItemVoucher";
    }
}
