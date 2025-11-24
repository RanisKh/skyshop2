package org.skypro.skyshop.controller;



import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.exceptions.ShopError;

@ControllerAdvice
public class ShopControllerAdvice {

    @ExeptionHandler(NoSuchProductException.class)
    public responseEntity<ShopError> handleNoSuchProductException(NoSuchProductException ex) {
        ShopError error = new ShopError(
                "PRODUCT_NOT_FOUND",
                ex.getMessage()
        );
    return new responseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
