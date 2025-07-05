package model.product;

public interface IProduct {
    String getName();
    double getPrice();
    int getQuantity();
    void reduceQuantity(int quantity);
    boolean isAvailable(int requestedQuantity);
}
