package Enum;

public enum CheckoutOption {
    CHECK_OUT(1),
    RETURN(2);

    private final int value;
    private CheckoutOption(int value) {
        this.value = value;
    }
    private int getValue() {
        return this.value;
    }

    public static CheckoutOption fromInput(int input) {
        for(CheckoutOption type : values()) {
            if(type.getValue() == input) {
                return type;
            }
        }

        return null;
    }
}
