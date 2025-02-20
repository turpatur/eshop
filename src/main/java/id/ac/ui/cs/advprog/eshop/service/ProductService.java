package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    Product create(Product product);
    List<Product> findAll();
    Product findProductById(String productId);
    void updateProduct(String carId, Product product);
    void deleteProductById(String productId);
}
