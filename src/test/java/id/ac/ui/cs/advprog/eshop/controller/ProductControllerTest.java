package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Test Product");
        product.setProductQuantity(1);
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);
        verify(model, times(1)).addAttribute(eq("product"), any(Product.class));
        assertEquals("CreateProduct", viewName);
    }

    @Test
    void testCreateProductPost() {
        String viewName = productController.createProductPost(product, model);
        verify(productService, times(1)).create(product);
        assertEquals("redirect:list", viewName);
    }

    @Test
    void testProductListPage() {
        List<Product> productList = Collections.singletonList(product);
        when(productService.findAll()).thenReturn(productList);

        String viewName = productController.productListPage(model);

        verify(model, times(1)).addAttribute("products", productList);
        assertEquals("ProductList", viewName);
    }

    @Test
    void testEditProductPage() {
        when(productService.findProductById(product.getProductId())).thenReturn(product);

        String viewName = productController.editProductPage(product.getProductId(), model);

        verify(model, times(1)).addAttribute("product", product);
        assertEquals("EditProduct", viewName);
    }

    @Test
    void testEditProductPost() {
        String viewName = productController.editProductPost(product, model);

        verify(productService, times(1)).updateProduct(product.getProductId(), product);
        assertEquals("redirect:list", viewName);
    }

    @Test
    void testDeleteProductPost() {
        String viewName = productController.deleteProductPost(product.getProductId());

        verify(productService, times(1)).deleteProductById(product.getProductId());
        assertEquals("redirect:list", viewName);
    }
}
