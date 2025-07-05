import checkout.CheckoutService;
import checkout.ICheckoutService;
import model.Customer;
import model.cart.ShoppingCart;
import model.product.*;
import model.shipping.IShippingService;
import model.shipping.ShippingService;
import payment.IPaymentService;
import payment.PaymentService;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
// Create services
        IShippingService shippingService = new ShippingService();
        IPaymentService paymentService = new PaymentService();
        ICheckoutService checkoutService = new CheckoutService(shippingService, paymentService);

        // Create products
        IProduct cheese = new ExpirableShippableProduct("Cheese", 100.0, 10, 0.4, LocalDate.now().plusDays(7));
        IProduct tv = new ShippableProduct("TV", 1000.0, 5, 15.0);
        IProduct scratchCard = new DigitalProduct("Mobile Scratch Card", 50.0, 20);
        IProduct biscuits = new ExpirableShippableProduct("Biscuits", 150.0, 8, 0.7, LocalDate.now().plusDays(30));

        // Create customer
        Customer customer = new Customer("John Doe", 2000.0);

        // Create shopping cart
        ShoppingCart cart = new ShoppingCart();

        try {
            // Add items to cart
            cart.addItem(cheese, 2);
            cart.addItem(biscuits, 1);
            cart.addItem(scratchCard, 1);

            // Checkout
            checkoutService.checkout(customer, cart);

        } catch (Exception e) {
            System.err.println("Checkout failed: " + e.getMessage());
        }

        // Test error cases
        testErrorCases(checkoutService, customer, tv);
    }

    private static void testErrorCases(ICheckoutService checkoutService, Customer customer, IProduct tv) {
        System.out.println("\n=== Testing Error Cases ===");

        try {
            ShoppingCart emptyCart = new ShoppingCart();
            checkoutService.checkout(customer, emptyCart);
        } catch (Exception e) {
            System.out.println("Empty cart error: " + e.getMessage());
        }

        try {
            ShoppingCart expensiveCart = new ShoppingCart();
            expensiveCart.addItem(tv, 5); // 5 TVs = 5000, but customer has less
            checkoutService.checkout(customer, expensiveCart);
        } catch (Exception e) {
            System.out.println("Insufficient balance error: " + e.getMessage());
        }

        try {
            IProduct expiredProduct = new ExpirableProduct("Expired Milk", 25.0, 5, LocalDate.now().minusDays(1));
            ShoppingCart expiredCart = new ShoppingCart();
            expiredCart.addItem(expiredProduct, 1);
        } catch (Exception e) {
            System.out.println("Expired product error: " + e.getMessage());
        }
    }
}
