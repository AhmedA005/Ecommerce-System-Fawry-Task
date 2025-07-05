package payment;

import model.Customer;

public interface IPayemntService {
    boolean processPayment(Customer customer, double amount);
}
