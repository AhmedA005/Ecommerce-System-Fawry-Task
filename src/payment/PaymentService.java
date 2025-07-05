package payment;

import model.Customer;

public class PaymentService implements IPaymentService {
    @Override
    public boolean processPayment(Customer customer, double amount) {
        if (customer.getBalance() < amount) {
            return false;
        }
        customer.deductBalance(amount);
        return true;
    }
}
