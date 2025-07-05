package model.product;

public class ShippableProduct extends BaseProduct implements IShippableProduct{
    private double weight;
    public ShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        if (weight <= 0){
            throw new IllegalArgumentException("Weight must be greater than 0");
        }
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }
}
