package org.skypro.skyshop.controller;


import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.service.StorageService;

import java.util.Collection;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    private final StorageService storageService;
    public ShopController(StorageService storageService){
        this.storageService = storageService;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts(){
        return storageService.getAllProducts();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles(){
        return storageService.getAllArticle();
    }
}
