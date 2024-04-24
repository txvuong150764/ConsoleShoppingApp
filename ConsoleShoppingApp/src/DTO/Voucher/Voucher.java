package DTO.Voucher;

public class Voucher {
    String type;
    float discountRate;
    int amount;
    public Voucher(float discountRate, int amount) {
        this.discountRate = discountRate;
        this.amount = amount;
    }
    public String getType() {
        return type;
    }
}
