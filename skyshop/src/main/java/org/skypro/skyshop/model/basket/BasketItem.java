package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

public record BasketItem {
    private final Product product;
    private final int quantity;

    public BasketItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
