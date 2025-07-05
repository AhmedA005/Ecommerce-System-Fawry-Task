package model.product;

import java.io.Serializable;
import java.time.LocalDate;

public class ExpirableShippableProduct extends BaseProduct implements IShippableProduct, IExpirableProduct {
    private double weight;
    private LocalDate expirationDate;
    public ExpirableShippableProduct(String name, int price, int quantity, double weight, LocalDate expirationDate) {
        super(name, price, quantity);
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be greater than 0");
        }
        this.weight = weight;
        this.expirationDate = expirationDate;
    }

    @Override
    public LocalDate getExpiryDate() {
        return expirationDate;
    }

    @Override
    public boolean isExpired() {
        return expirationDate.isAfter(LocalDate.now());
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
