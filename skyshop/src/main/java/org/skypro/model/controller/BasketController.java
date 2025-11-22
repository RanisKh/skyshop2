package org.skypro.model.controller;

import org.skypro.model.basket.UserBasket;
import org.skypro.model.service.BasketService;

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
