package com.example.Product.services;

import com.example.Product.dtos.ProductRequest;
import com.example.Product.exception.InvalidProductException;
import com.example.Product.exception.ProductNotFoundException;
import com.example.Product.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {

    // here we are using, this service interface, because, there may be a scenario where we want to fetch data from multiple type of service
    // such as from Db, ThirdPartyServices, Our own service etc.
    // that service will implement this interface.
    Product getSingleProduct(Long id) throws InvalidProductException;

    List<Product> getAllProduct();

    Product addProduct(ProductRequest productRequest);
    Product updateProduct(Long Id, ProductRequest requestBody) throws ProductNotFoundException;
    Product getProductByName(String name) throws InvalidProductException;

    List<Product> getProductPriceGreaterThan(int price);

    Product addProduct(Product product);
}
