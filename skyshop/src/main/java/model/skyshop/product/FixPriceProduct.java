package model.skyshop.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final double fixPrice = 99;

    public FixPriceProduct(UUID id, String name, double fixPrice) {
        super(id, name, fixPrice);
    }

    public static FixPriceProduct createFromProduct(Product product) {
        return new FixPriceProduct(
                UUID.randomUUID(),
                product.getName(),
                product.getPrice()
        );
    }
    public static FixPriceProduct create(String name, double fixPrice) {
        return new FixPriceProduct(UUID.randomUUID(), name, fixPrice);
    }

    @Override
    public double getPrice() {
        return fixPrice;
    }

    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return "Продукция с фиксированной ценой" +
                "name='" + getName() + '\'' +
                fixPrice;
    }
}

