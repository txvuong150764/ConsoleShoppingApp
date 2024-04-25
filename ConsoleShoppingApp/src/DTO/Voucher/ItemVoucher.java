package DTO.Voucher;

public class ItemVoucher extends Voucher {
    public ItemVoucher(float discountRate, int amount, int minimumSpend) {
        super(discountRate, amount, minimumSpend);
        super.type = "ItemVoucher";
    }
}
