package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.service.BasketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public class BasketController {
    private final BasketService basketService;
    public BasketController(BasketService basketService){
        this.basketService = basketService;
    }

    @GetMapping("/{id}")
    public String addProduct (@PathVariable("id")UUID id){
        basketService.addToBasket(id);
        return "Продукт успешно добавлен";
    }

    @GetMapping
    public UserBasket getUserBasket(){
        return basketService.getUserBasket();
    }
}
