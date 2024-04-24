package DTO.Shipping;

import Enum.ShippingMethod;

public class Shipping {
    String type;
    float price;
    int duration;

    public String getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    public int getDuration() {
        return duration;
    }

    public static Shipping classifyShippingMethod(ShippingMethod shippingMethod) {
        switch (shippingMethod) {
            case BASIC -> {
                return new BasicShipping();
            }
            case FAST -> {
                return new FastShipping();
            }
            case SAVING -> {
                return new SavingShipping();
            }
            default -> {
                return new Shipping();
            }
        }
    }
}
