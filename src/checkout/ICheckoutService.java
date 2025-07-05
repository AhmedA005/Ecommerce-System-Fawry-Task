package checkout;

import model.Customer;
import model.cart.ShoppingCart;

public interface ICheckoutService {
    void chekout(Customer customer, ShoppingCart cart);
}
