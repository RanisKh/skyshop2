package org.skypro.skyshop.model.basket;

import java.math.BigDecimal;
import java.util.List;

public record UserBasket (
    List<BasketItem> items,
    BigDecimal total
){
    public UserBasket (List<BasketItem> items){
        this(items, calculateTotal(items));
    }

    private static BigDecimal calculateTotal(List<BasketItem> items) {
        return items.stream()
                .map(item -> item.product().getPrice()
                        .multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
}
}
