package model.skyshop.product;

import java.util.Objects;
import java.util.UUID;

public class DiscountedProduct extends Product {
    private double basePrice;
    private double discountInPercentages;

    public DiscountedProduct(UUID id, String name, double basePrice, double discountInPercentages) {
        super(id, name, calculateFinalPrice(basePrice, discountInPercentages));
        validateBasePrice(basePrice);
        validateDiscountPercentages();
        this.basePrice = basePrice;
        this.discountInPercentages = discountInPercentages;
    }

    public static DiscountedProduct create(String name, double basePrice, double discountInPercentages) {
        return new DiscountedProduct(UUID.randomUUID(), name, basePrice, discountInPercentages);
    }

    private static double calculateFinalPrice(double basePrice, double discountPercentage) {
        return basePrice * (1 - discountPercentage / 100);
    }

    private void validateBasePrice(double basePrice) {
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Базовая цена продукта должна быть строго больше 0. Передано: " + basePrice);
        }
    }

    private void validateDiscountPercentages() {
        if (discountInPercentages < 0 || discountInPercentages > 100) {
            throw new IllegalArgumentException("Процент скидки должен быть в диапазоне от 0 до 100 включительно. Передано: " + discountInPercentages);
        }
    }

    public double discountInPercentages() {
        return basePrice - (basePrice / 100 * discountInPercentages);
    }

    @Override
    public double getPrice() {
        return basePrice;
    }

    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return "Скидочный продут: " + getName() + ", стоимость=" + basePrice +
                "скидка" + discountInPercentages;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DiscountedProduct that = (DiscountedProduct) o;
        return Double.compare(basePrice, that.basePrice) == 0 && Double.compare(discountInPercentages, that.discountInPercentages) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(basePrice, discountInPercentages);
    }

}
