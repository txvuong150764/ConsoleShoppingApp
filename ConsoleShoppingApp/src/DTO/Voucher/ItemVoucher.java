package DTO.Voucher;

public class ItemVoucher extends Voucher {
    float minimumSpend;
    public ItemVoucher(float discountRate, int amount, float minimumSpend) {
        super(discountRate, amount);
        super.type = "ItemVoucher";
        this.minimumSpend = minimumSpend;
    }
}
