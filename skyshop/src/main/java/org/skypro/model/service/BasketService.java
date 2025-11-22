package org.skypro.model.service;

import org.skypro.model.basket.BasketItem;
import org.skypro.model.basket.ProductBasket;
import org.skypro.model.basket.UserBasket;
import org.skypro.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket basket;
    private final StorageService storageService;

    public BasketService(ProductBasket basket, StorageService storageService) {
        this.basket = basket;
        this.storageService = storageService;
    }

    public void addToBasket(UUID id){
        Optional<Product> product = storageService.getProductById(id);

        if (product.isEmpty()){
            throw new IllegalArgumentException("Товар с ID " + id + " не найден")
        }
        basket.addProduct(id);
    }

    public UserBasket getUserBasket(){
        return new UserBasket(basket.getProducts().entrySet().stream()
                .map(entry ->{
                    UUID productId =entry.getKey();
                    int quantity = entry.getValue();

                    Product product = storageService.getProductById(productId)
                            .orElseThrow(() -> new IllegalArgumentException("Товар с ID " + productId + " отсутствует в хранилище"));
                    return new BasketItem(product, quantity);
                })
                .collect(Collectors.toUnmodifiableList())
        );
    }
}
