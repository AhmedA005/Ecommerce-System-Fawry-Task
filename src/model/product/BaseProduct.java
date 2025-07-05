package model.product;

public abstract class BaseProduct implements IProduct {
    protected String name;
    protected int price;
    protected int quantity;

    public BaseProduct(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void reduceQuantity(int amount) {
        if (amount > quantity) {
            throw new IllegalArgumentException("Amount exceeds quantity");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Negative amount");
        }
        this.quantity -= amount;
    }

    @Override
    public boolean isAvailable(int requestedQuantity) {
        return quantity >= requestedQuantity && requestedQuantity > 0;
    }
}
