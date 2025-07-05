package model.product;

import java.time.LocalDate;

public interface IExpirableProduct {
    LocalDate getExpiryDate();
    boolean isExpired();
}
