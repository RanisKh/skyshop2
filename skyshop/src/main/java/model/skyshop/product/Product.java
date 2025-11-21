package model.skyshop.product;
import model.skyshop.search.Searchable;
import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private String name;
    private final UUID id;
    private double price;

    public Product(UUID id, String name, double price) {
        this.id = Objects.requireNonNull(id, "ID не может быть null");
        validateName(name);
        this.name = name;
        this.price = price;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть null, пустой строкой или состоять только из пробелов");
        }
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract double getPrice();

    public boolean isSpecial() {
        return true;
    }

    @JsonIgnore
    public String getSearchTerm() {
        return name;
    }



    @Override
    @JsonIgnore
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    public static Product create(String name, double price){
        return new SimpleProduct(UUID.randomUUID(), name, price);
    }
}
