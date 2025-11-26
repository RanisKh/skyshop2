package org.skypro.skyshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    private UUID productId;
    private UUID productId1;
    private Product product;
    private Product product1;

    @BeforeEach
    void setUp(){
        productId = UUID.randomUUID();
        productId1 = UUID.randomUUID();

        product = new Product(productId, "Планшет", 12000) {
            @Override
            public double getPrice() {
                return 0;
            }
        };
        product1 = new Product(productId1, "Монитор", 15000) {
            @Override
            public double getPrice() {
                return 0;
            }
        };
    }

    @Test
    void search_whenStorageEmpty(){
        when(storageService.getAllProducts()).thenReturn(List.of());

        List<SearchResult> result = searchService.search("Планшет");

        assertTrue(result.isEmpty());
        verify(storageService).getAllProducts();
    }

    @Test
    void search_whenNoMatchingPosititon(){
        when(storageService.getAllProducts()).thenReturn(List.of(product, product1));

        List<SearchResult> result = searchService.search("Планшет");

        assertTrue(result.isEmpty());
        verify(storageService).getAllProducts();
    }

    @Test
    void search_whenMatchingProductsExists(){
        when(storageService.getAllProducts()).thenReturn(List.of(product, product1));

        List<SearchResult> result = searchService.search("Планшет");

        assertEquals(1,result.size());
        assertEquals(productId,result.get(0).getId());
        assertTrue(result.contains(product));
        verify(storageService).getAllProducts();
    }

    @Test
    void search_shouldBeCaseInsensitive(){
        when(storageService.getAllProducts()).thenReturn(List.of(product));

        List<SearchResult> result1 = searchService.search("планшет");
        List<SearchResult> result = searchService.search("монитор");

        assertEquals(1, result.size());
        assertEquals(1, result1.size());
        assertEquals(productId, result.get(0).getId());
        assertEquals(productId1, result1.get(0).getId());
    }

    @Test
    void search_whenQueryIsNullOrEmpty(){
        when(storageService.getAllProducts()).thenReturn(List.of(product, product1));

        List<SearchResult> result = searchService.search("");
        assertTrue(result.isEmpty());

        List<SearchResult> result1 = searchService.search(null);
        assertTrue(result1.isEmpty());

        verify(storageService, times(2)).getAllProducts();
    }
}
