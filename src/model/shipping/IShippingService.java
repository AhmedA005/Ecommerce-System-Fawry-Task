package model.shipping;

import model.product.IShippableProduct;

import java.util.List;

public interface IShippingService {
    double calculateShippingFee(List<IShippableProduct> items);
    void processShipment(List<IShippableProduct> items);
}
