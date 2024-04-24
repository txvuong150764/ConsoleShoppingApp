package DTO.Shipping;

public class SavingShipping extends Shipping {
    public SavingShipping() {
        super.type = "Saving";
        super.price = 5F;
        super.duration = 7;
    }
}
