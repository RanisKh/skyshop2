package org.skypro.skyshop.product;

import java.util.Objects;
import java.util.UUID;

public class SimpleProduct extends Product {
    private double price;


    public SimpleProduct(UUID id, String name, double price) {
        super(id, name, price);
        this.price = price;

        if (price <= 0) {
            throw new IllegalArgumentException("Цена продукта должна быть строго больше 0");
        }

    }

    public static SimpleProduct create(String name, double price) {
        return new SimpleProduct(UUID.randomUUID(), name, price);
    }
    @Override
    public double getPrice() {
        return price;
    }

    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return "Продукт: " + name + "стоимость " + price;
    }


    @Override
    public String getStringRepresentation() {
        return super.getStringRepresentation();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SimpleProduct product = (SimpleProduct) o;
        return Double.compare(price, product.price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), price);
    }
}