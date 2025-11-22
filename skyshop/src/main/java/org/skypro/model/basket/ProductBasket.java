package org.skypro.model.basket;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Scope("session")
public class ProductBasket {
    private final Map<UUID, Integer> products = new HashMap<>();

    public void addProduct(UUID id){
        products.compute(id,(key, value) -> (value == null) ? 1 : value + 1);
    }
    public Map<UUID, Integer> getProducts(){
        return Collections.unmodifiableMap(products);
    }
}
