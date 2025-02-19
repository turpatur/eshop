package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Test Product");
        product.setProductQuantity(1);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);
        assertNotNull(createdProduct);

        assertEquals(product.getProductId(), createdProduct.getProductId());
        assertEquals(product.getProductName(), createdProduct.getProductName());
        assertEquals(product.getProductQuantity(), createdProduct.getProductQuantity());
    }

    @Test
    void testFindAllProducts() {
        Product product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Another Product");
        product2.setProductQuantity(2);

        List<Product> productList = Arrays.asList(product, product2);
        Iterator<Product> productIterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> result = productService.findAll();
        assertEquals(product, result.getFirst());
        assertEquals(product2, result.get(1));
        assertEquals(2, result.size());
    }

    @Test
    void testDeleteProductById() {
        doNothing().when(productRepository).delete(product.getProductId());
        productService.deleteProductById(product.getProductId());
        verify(productRepository, times(1)).delete(product.getProductId());
    }

    @Test
    void testFindProductById() {
        when(productRepository.findProductById(product.getProductId())).thenReturn(product);
        Product foundProduct = productService.findProductById(product.getProductId());
        assertNotNull(foundProduct);

        assertEquals(product.getProductId(), foundProduct.getProductId());
        assertEquals(product.getProductName(), foundProduct.getProductName());
        assertEquals(product.getProductQuantity(), foundProduct.getProductQuantity());
    }

    @Test
    void testUpdateProduct() {
        Product product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Another Product");
        product2.setProductQuantity(2);

        doNothing().when(productRepository).update(product.getProductId(), product2);
        productService.updateProduct(product.getProductId(), product2);
        verify(productRepository, times(1)).update(product.getProductId(), product2);
    }
}
