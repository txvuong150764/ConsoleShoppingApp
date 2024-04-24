package DTO.Voucher;

public class Voucher {
    String type;
    float discountRate;
    int amount;

    public void setAmount(int amount) {
        this.amount = amount;
    }

    float minimumSpend;

    public float getDiscountRate() {
        return discountRate;
    }

    public float getMinimumSpend() {
        return minimumSpend;
    }

    public int getAmount() {
        return amount;
    }

    public Voucher(float discountRate, int amount, float minimumSpend) {
        this.discountRate = discountRate;
        this.amount = amount;
        this.minimumSpend = minimumSpend;
    }
    public String getType() {
        return type;
    }
}
