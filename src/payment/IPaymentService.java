package payment;

import model.Customer;

public interface IPaymentService {
    boolean processPayment(Customer customer, double amount);
}
