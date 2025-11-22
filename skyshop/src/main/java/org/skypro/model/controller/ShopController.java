package org.skypro.model.controller;


import org.skypro.model.article.Article;
import org.skypro.model.product.Product;
import org.skypro.model.service.StorageService;

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
