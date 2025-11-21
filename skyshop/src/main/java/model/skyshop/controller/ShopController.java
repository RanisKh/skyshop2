package model.skyshop.controller;


import model.skyshop.article.Article;
import model.skyshop.product.Product;
import model.skyshop.service.StorageService;

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
