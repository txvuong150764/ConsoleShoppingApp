package DTO;

public class Item {
    String name;
    float price;
    public Item(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return this.name + " " + this.price + " ";
    }
}
