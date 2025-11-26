package org.skypro.skyshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    @Mock
    private ProductBasket basket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;
    private UUID existingProductId;
    private UUID nonExistingProductId;
    private Product existingProduct;

    @BeforeEach
    void setUp(){
        existingProductId= UUID.randomUUID();
        nonExistingProductId =UUID.randomUUID();

        existingProduct = new Product(existingProductId, "Ноутбук MSI", 84999.99) {
            @Override
            public double getPrice() {
                return 0;
            }
        };
    }

    @Test
    void addToBasket_whenProductDoesNotExist() {
        when(storageService.getProductById(nonExistingProductId)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> {
            basketService.addToBasket(nonExistingProductId);
        });

        verify(basket, never()).addProduct(any());
    }


    @Test
    void addToBasket_whenProductExists() {
        when(storageService.getProductById(existingProductId))
                .thenReturn(Optional.of(existingProduct));

        basketService.addToBasket(existingProductId);

        verify(basket, times(1)).addProduct(existingProductId);
    }


    @Test
    void getUserBasket_whenBasketIsEmpty() {

        when(basket.getProducts()).thenReturn(Map.of());

        UserBasket result = basketService.getUserBasket();

        assertEquals(0, result.items().size());
        assertEquals(BigDecimal.ZERO, result.total());
    }


    @Test
    void getUserBasket_whenBasketHasItems() {
        Map<UUID, Integer> basketContents = Map.of(
                existingProductId, 2,
                nonExistingProductId, 1;

        Product mouse = new Product(
                nonExistingProductId,
                "Мышь Logitech",
                1999.99) {
            @Override
            public double getPrice() {
                return 0;
            }
        };


        when(basket.getProducts()).thenReturn(basketContents);
        when(storageService.getProductById(existingProductId))
                .thenReturn(Optional.of(existingProduct));
        when(storageService.getProductById(nonExistingProductId))
                .thenReturn(Optional.of(mouse));


        UserBasket result = basketService.getUserBasket();


        assertEquals(2, result.items().size());


        BasketItem laptopItem = result.items().get(0);
        assertEquals(existingProductId, laptopItem.product().getId());
        assertEquals("Ноутбук MSI", laptopItem.product().getName());
        assertEquals(2, laptopItem.quantity());


        BasketItem mouseItem = result.items().get(1);
        assertEquals(nonExistingProductId, mouseItem.product().getId());
        assertEquals("Мышь Logitech", mouseItem.product().getName());
        assertEquals(1, mouseItem.quantity());


        BigDecimal expectedTotal = BigDecimal.valueOf(79999.99 * 2 + 1999.99);
        assertEquals(expectedTotal, result.total());
    }


    @Test
    void getUserBasket_whenProductInBasketNotInStorage() {

        Map<UUID, Integer> basketContents = Map.of(nonExistingProductId, 1);

        when(basket.getProducts()).thenReturn(basketContents);
        when(storageService.getProductById(nonExistingProductId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            basketService.getUserBasket();
        });
    }

}
