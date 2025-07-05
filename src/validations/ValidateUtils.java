package validations;

import model.Customer;
import model.cart.CartItem;
import model.cart.ShoppingCart;
import model.product.IExpirableProduct;
import model.product.IProduct;

public class ValidateUtils {
    public static <Product> void validateCheckout(Customer customer, ShoppingCart cart) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        if (cart == null || cart.isEmpty()) {
            throw new IllegalArgumentException("Cart cannot be null or empty");
        }

        // Validate all items in cart
        for (CartItem item : cart.getItems()) {
            IProduct product = item.getProduct();

            if (!product.isAvailable(item.getQuantity())) {
                throw new RuntimeException("Product is not available");
            }

            if (product instanceof IExpirableProduct && ((IExpirableProduct) product).isExpired()) {
                throw new RuntimeException("Product is expired");
            }
        }
    }
    public static void validateAddToCart(IProduct product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be less than or equal to 0");
        }

        if (!product.isAvailable(quantity)) {
            throw new RuntimeException("Product is not available");
        }

        if (product instanceof IExpirableProduct && ((IExpirableProduct) product).isExpired()) {
            throw new RuntimeException("Product is expired");
        }
    }
}
