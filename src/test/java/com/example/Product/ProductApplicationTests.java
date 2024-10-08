package com.example.Product;

import com.example.Product.controllers.ProductControllers;
import com.example.Product.dtos.ProductRequest;
import com.example.Product.exception.InvalidProductException;
import com.example.Product.exception.ProductNotFoundException;
import com.example.Product.models.Product;
import com.example.Product.repository.CategoryRepo;
import com.example.Product.repository.ProductRepo;
import com.example.Product.repository.ProductWithPriceAndUrl;
import org.apache.juli.logging.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductApplicationTests {

    @Autowired
    ProductControllers productControllers;

    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepo categoryRepo;

    @Test
    void contextLoads() {
    }

    @Test
    void getProductByName() throws InvalidProductException {
        Product product = productControllers.getProductByName("one plus");
        System.out.println("Kanti : " + product.getName() + " " + product.getPrice());
    }

    @Test
    void getProductByPriceGreaterThan() {
        List<Product> products = productControllers.getProductPriceGreaterThan(500);
        for (Product product : products) {
            System.out.println("Kanti : " + product.getName() + " " + product.getPrice());
        }

    }

    @Test
    void addProduct() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setTitle("Captain america man robot");
        productRequest.setCategory("Robots");
        productRequest.setImage("marvel url");
        productRequest.setPrice(1230);
        productRequest.setDescription("marvel robot");

        Product productResponse = productControllers.addProduct(productRequest);

        System.out.println("kanti : " + productResponse.getId() + " " + productResponse.getName());
    }


    @Test
    void updateProduct() throws ProductNotFoundException {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setTitle("iron man");
        productRequest.setCategory("Marvels");
        productRequest.setImage("iron map url");
        productRequest.setPrice(452);
        productRequest.setDescription("strong robot");

        Product productResponse = productControllers.updateProduct(51L, productRequest);

        System.out.println("kanti : " + productResponse.getName() + " - " + productResponse.getPrice());
    }

    @Test
    void fetchProduct() {
        Product product = productRepo.fetchProduct(3L);
        System.out.println("kanti : fetch product - " + product.getDescription() + " " + product.getName());
    }

    @Test
    void fetchSpecificDetail() {
        ProductWithPriceAndUrl product = productRepo.fetchSpecificDetail(3L);
        System.out.println("kanti : fetch product - " + product.getUrl() + " " + product.getPrice());
    }

    @Test
    void fetchNativeProduct() {
        Product product = productRepo.productWithNativeQuery(52L);
        System.out.println("kanti native: fetch product - " + product.getDescription() + " " + product.getName());
    }

    @Test
    void findCategory(){
        categoryRepo.findById(1L);
    }

}
