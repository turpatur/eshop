package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        if (product.getProductId() == null) {
            UUID uuid = UUID.randomUUID();
            product.setProductId(uuid.toString());
        }
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findProductById(String productId) {
        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        throw new RuntimeException(
                String.format("Product with ID %s not found.", productId)
        );
    }

    public void update(String productId, Product updatedProduct) {
        for (Product currentProduct : productData) {
            if (currentProduct.getProductId().equals(productId)) {
                currentProduct.setProductName(updatedProduct.getProductName());
                currentProduct.setProductQuantity(updatedProduct.getProductQuantity());
            }
        }
            if (productData.isEmpty()) {
                throw new RuntimeException(
                        "Products are empty"
                );
            }
        }
    }
