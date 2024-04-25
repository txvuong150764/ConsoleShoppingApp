package DTO.Voucher;

public class Voucher {
    String type;
    float discountRate;
    int amount;
    int minimumSpend;
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public float getDiscountRate() {
        return discountRate;
    }

    public int getMinimumSpend() {
        return minimumSpend;
    }

    public int getAmount() {
        return amount;
    }

    public Voucher(float discountRate, int amount, int minimumSpend) {
        this.discountRate = discountRate;
        this.amount = amount;
        this.minimumSpend = minimumSpend;
    }
    public String getType() {
        return type;
    }
}
