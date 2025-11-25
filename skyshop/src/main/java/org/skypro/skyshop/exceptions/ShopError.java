package org.skypro.skyshop.exceptions;

public record ShopError (
        String code,
        String massage
){
    public ShopError {
        if (code == null){
            throw new IllegalArgumentException("Поле не может быть пустым");
        }
        if (massage == null){
            throw new IllegalArgumentException("Поле не может быть пустым");
        }
    }
}
