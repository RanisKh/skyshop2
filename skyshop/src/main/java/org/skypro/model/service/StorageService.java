package org.skypro.model.service;

import org.skypro.model.article.Article;
import org.skypro.model.product.DiscountedProduct;
import org.skypro.model.product.FixPriceProduct;
import org.skypro.model.product.Product;
import org.skypro.model.product.SimpleProduct;
import org.skypro.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;

    public StorageService(Map<UUID, Product> productMap, Map<UUID, Article> articleMap) {
        this.productMap = new HashMap<>();
        this.articleMap = new HashMap<>();
        initializeTestData();
    }

    private void initializeTestData(){
        initializeProducts();
        initializeArticle();
    }

    private void initializeProducts(){
        productMap.put(UUID.randomUUID(), SimpleProduct.create("Apple",98.0));
        productMap.put(UUID.randomUUID(), SimpleProduct.create("Parrot",78.0));
        productMap.put(UUID.randomUUID(), SimpleProduct.create("Grape",128.0));
        productMap.put(UUID.randomUUID(), SimpleProduct.create("Orange",100.0));
        productMap.put(UUID.randomUUID(), SimpleProduct.create("Bread",54.0));
        productMap.put(UUID.randomUUID(), SimpleProduct.create("Butter",298.0));

        productMap.put(UUID.randomUUID(), DiscountedProduct.create("Napkins",25.0, 15.99));
        productMap.put(UUID.randomUUID(), DiscountedProduct.create("Toothpaste", 29.0, 20.0));

        productMap.put(UUID.randomUUID(), FixPriceProduct.create("Pen", 15.0));
        productMap.put(UUID.randomUUID(), FixPriceProduct.create("Pensil", 10.0));

    }

    public void initializeArticle(){
        articleMap.put(UUID.randomUUID(), Article.create("Обзор новейших смартфонов 2023 года",
                "В этом обзоре мы рассмотрим самые интересные новинки мобильного рынка. " +
                        "Apple iPhone 15, Samsung Galaxy S23 и Google Pixel 8 - что же выбрать?"));

        articleMap.put(UUID.randomUUID(), Article.create("Техника для работы из дома",
                "Лучшие устройства для повышения продуктивности при работе из дома. " +
                        "Ноутбуки, мониторы, клавиатуры и другие гаджеты для комфортной работы."));
    }

    public Collection<Searchable> getAllSerchables(){
        return Stream.concat(
                productMap.values().stream(),
                articleMap.values().stream()
        ).collect(Collectors.toList());
    }

    public Collection<Product> getAllProducts(){
        return Collections.unmodifiableCollection(productMap.values());
    }

    public Collection<Article> getAllArticle(){
        return Collections.unmodifiableCollection(articleMap.values());
    }


    public Optional<Product> getProductById(UUID productId) {
        return Optional.ofNullable(productMap.get(productId));
    }
}
