package payment;

import model.Customer;

public class PaymentService implements IPayemntService {
    @Override
    public boolean processPayment(Customer customer, double amount) {
        if (customer.getBalance() < amount) {
            return false;
        }
        customer.deductBalance(amount);
        return true;
    }
}
