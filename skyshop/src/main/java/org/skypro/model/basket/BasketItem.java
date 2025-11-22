package org.skypro.model.basket;

import org.skypro.model.product.Product;

public record BasketItem {
    private final Product product;
    private final int quantity;

    public BasketItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
