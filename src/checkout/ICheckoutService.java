package checkout;

import model.Customer;
import model.cart.ShoppingCart;

public interface ICheckoutService {
    void checkout(Customer customer, ShoppingCart cart);
}
