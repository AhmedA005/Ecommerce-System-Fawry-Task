package checkout;

import model.Customer;
import model.cart.CartItem;
import model.cart.ShoppingCart;
import model.product.IShippableProduct;
import model.shipping.IShippingService;
import payment.IPaymentService;
import validations.ValidateUtils;

import java.util.List;

public class CheckoutService implements ICheckoutService {
    private IShippingService shippingService;
    private IPaymentService paymentService;

    public CheckoutService(IShippingService shippingService, IPaymentService paymentService) {
        this.shippingService = shippingService;
        this.paymentService = paymentService;
    }

    @Override
    public void checkout(Customer customer, ShoppingCart cart) {
        ValidateUtils.validateCheckout(customer, cart);
        double subtotal = cart.getTotal();
        List<IShippableProduct> shippableProducts = cart.getShippableProducts();
        double shippingFee = shippingService.calculateShippingFee(shippableProducts);
        double total = subtotal + shippingFee;

        if (!paymentService.processPayment(customer, total)) {
            throw new RuntimeException("Payment failed");
        }

        for(CartItem cartItem : cart.getItems()) {
            cartItem.getProduct().reduceQuantity(cartItem.getQuantity());
        }

        shippingService.processShipment(shippableProducts);
        printReceipt(cart, subtotal, shippingFee, customer);
        cart.clear();
    }

    private void printReceipt(ShoppingCart cart, double subtotal, double shippingFee, Customer customer) {
        System.out.println("** Checkout receipt **");

        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %s %.0f%n",
                    item.getQuantity(),
                    item.getProduct().getName(),
                    item.getPrice());
        }

        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);
        System.out.printf("Shipping %.0f%n", shippingFee);
        System.out.printf("Amount %.0f%n", subtotal + shippingFee);
    }
}
