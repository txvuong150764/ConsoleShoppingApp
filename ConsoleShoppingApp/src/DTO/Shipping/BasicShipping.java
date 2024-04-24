package DTO.Shipping;


public class BasicShipping extends Shipping{
    public BasicShipping() {
        super.type = "Basic";
        super.price = 10F;
        super.duration = 5;
    }
}
