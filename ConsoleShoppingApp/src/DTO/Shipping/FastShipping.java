package DTO.Shipping;

public class FastShipping extends Shipping{
    public FastShipping() {
        super.type = "Fast";
        super.price = 15F;
        super.duration = 3;
    }
}
