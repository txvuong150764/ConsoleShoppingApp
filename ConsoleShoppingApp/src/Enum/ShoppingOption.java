package Enum;

public enum ShoppingOption {
    BUY(1),
    RETURN(2);

    private final int value;
    private ShoppingOption(int value) {
        this.value = value;
    }
    private int getValue() {
        return this.value;
    }

    public static ShoppingOption fromInput(int input) {
        for(ShoppingOption type : values()) {
            if(type.getValue() == input) {
                return type;
            }
        }

        return null;
    }
}
