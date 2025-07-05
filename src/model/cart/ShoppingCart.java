package model.cart;

import model.product.IShippableProduct;
import model.product.ShippableProduct;

import java.util.*;

public class ShoppingCart {
    private List<CartItem> items = new ArrayList<CartItem>();
    public void addItem(CartItem item) {
        // TODO: add validation
        items.add(item);
    }
    public void removeItem(CartItem item) {
        items.remove(item);
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

    public double getTotal() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public List<IShippableProduct> getShippableProducts() {
        List<IShippableProduct> shippableProducts = new ArrayList<>();
        for (CartItem item : items) {
            if (item.getProduct() instanceof IShippableProduct) {
                shippableProducts.add((IShippableProduct) item.getProduct());
            }
        }
        return shippableProducts;
    }
}
