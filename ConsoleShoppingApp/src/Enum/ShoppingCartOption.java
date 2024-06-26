package Enum;

public enum ShoppingCartOption {
    SELECT_SHIPPING_METHOD(1),
    RETURN(2);

    private final int value;
    private ShoppingCartOption(int value) {
        this.value = value;
    }
    private int getValue() {
        return this.value;
    }

    public static ShoppingCartOption fromInput(int input) {
        for(ShoppingCartOption type : values()) {
            if(type.getValue() == input) {
                return type;
            }
        }

        return null;
    }
}
