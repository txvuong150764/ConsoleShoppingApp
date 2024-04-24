package Enum;

public enum ShippingMethod {
    BASIC(1),
    FAST(2),
    SAVING(3),
    RETURN(4);

    private final int value;
    private ShippingMethod(int value) {
        this.value = value;
    }
    private int getValue() {
        return this.value;
    }

    public static ShippingMethod fromInput(int input) {
        for(ShippingMethod type : values()) {
            if(type.getValue() == input) {
                return type;
            }
        }

        return null;
    }
}
