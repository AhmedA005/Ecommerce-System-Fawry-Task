package model.shipping;

import model.product.IShippableProduct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingService implements IShippingService {
    private final double SHIPPING_RATE_PER_KG = 10.0;

    @Override
    public double calculateShippingFee(List<IShippableProduct> items) {
        if (items.isEmpty()) {
            return 0;
        }
        double totalWeight = items.stream().mapToDouble(IShippableProduct::getWeight).sum();
        return totalWeight * SHIPPING_RATE_PER_KG;
    }

    @Override
    public void processShipment(List<IShippableProduct> items) {
        if (items.isEmpty()) return;

        Map<String, Double> itemWeight = new HashMap<>();
        Map<String, Integer> itemCount = new HashMap<>();
        for (IShippableProduct item : items) {
            itemWeight.merge(item.getName(), item.getWeight(), Double::sum);
            itemCount.merge(item.getName(), 1, Integer::sum);
        }
        for (String itemName : itemWeight.keySet()) {
            int count = itemCount.get(itemName);
            double weight = itemWeight.get(itemName);
            System.out.printf("%dx %s %.0fg%n", count, itemName, weight * 1000);
        }
        double totalWeight = items.stream().mapToDouble(IShippableProduct::getWeight).sum();
        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }
}
