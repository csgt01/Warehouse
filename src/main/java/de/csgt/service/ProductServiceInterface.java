package de.csgt.service;


import de.csgt.domain.Product;

public interface ProductServiceInterface {
    Iterable<Product> listAllProducts();

    Product getProductById(Integer id);

    Product saveProduct(Product product);

    void deleteProduct(Integer id);
}