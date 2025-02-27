package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository implements RepositoryInterface<Product> {

    private List<Product> productData = new ArrayList<>();

    @Override
    public Product create(Product product) {

        if (product.getProductQuantity() < 0){
            throw new IllegalArgumentException( "Product quantity cannot be negative");
        }

        if (product.getProductId() == null){
            UUID uuid = UUID.randomUUID();
            product.setProductId(uuid.toString());
        }

        productData.add(product);
        return product;
    }

    @Override
    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    @Override
    public Product findById(String productId) {
        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        throw new RuntimeException(
                String.format("Product with ID %s not found.", productId)
        );
    }

    @Override
    public void update(Product updatedProduct, String productId) {
        if (productData.isEmpty()) {
            throw new RuntimeException(
                    "Products are empty"
            );
        }

        Product targetProduct = findById(productId);
        targetProduct.setProductName(updatedProduct.getProductName());
        targetProduct.setProductQuantity(updatedProduct.getProductQuantity());
    }

    @Override
    public void delete(String productId) {
        productData.removeIf(product -> product.getProductId().equals(productId));
        }
    }
