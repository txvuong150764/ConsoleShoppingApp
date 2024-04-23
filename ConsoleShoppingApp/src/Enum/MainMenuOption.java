package Enum;

public enum MainMenuOption {
    VIEW_CART(1),
    VIEW_RANK(2),
    VIEW_SHOP_ITEMS(3);

    private final int value;
    private MainMenuOption(int value) {
        this.value = value;
    }
    private int getValue() {
        return this.value;
    }

    public static MainMenuOption fromInput(int input) {
        for(MainMenuOption type : values()) {
            if(type.getValue() == input) {
                return type;
            }
        }

        return null;
    }
}
