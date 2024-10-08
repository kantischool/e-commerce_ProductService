package com.example.Product.controllers;

import com.example.Product.dtos.ProductRequest;
import com.example.Product.exception.InvalidProductException;
import com.example.Product.exception.ProductNotFoundException;
import com.example.Product.models.Category;
import com.example.Product.models.Product;
import com.example.Product.models.ProductWrapper;
import com.example.Product.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductControllers {

    private final IProductService productService;

    @Autowired
    public ProductControllers(@Qualifier("dbService") IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProduct() {
        productService.getAllProduct();
        return new ArrayList<>();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductWrapper> getProductByID(@PathVariable("id") Long id) throws InvalidProductException {

        ResponseEntity<ProductWrapper> responseEntity;

            Product product = productService.getSingleProduct(id);
            ProductWrapper productWrapper = new ProductWrapper(product, "successfully fetched response");
            responseEntity = new ResponseEntity<>(productWrapper, HttpStatus.OK);


        return responseEntity;
    }

    public Product getProductByName(@PathVariable("name") String id) throws InvalidProductException {

        ResponseEntity<ProductWrapper> responseEntity;

        Product product = productService.getProductByName(id);
//        ProductWrapper productWrapper = new ProductWrapper(product, "successfully fetched response");
//        responseEntity = new ResponseEntity<>(productWrapper, HttpStatus.OK);


        return product;
    }

    public List<Product> getProductPriceGreaterThan(int price){
       return productService.getProductPriceGreaterThan(price);
    }

  /*  @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<ErrorResponseDto> handeInvalidProductException(){
        return new ResponseEntity<>(new ErrorResponseDto("invalid product id from local", null), HttpStatus.NOT_FOUND);
    }*/

    @PostMapping("/products")
    public Product addProduct(@RequestBody ProductRequest productRequest) {

        Product product = new Product();
        product.setPrice(productRequest.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(productRequest.getCategory());
        product.setName(productRequest.getTitle());
        product.setImage(productRequest.getImage());
        product.setDescription(productRequest.getDescription());

       return productService.addProduct(product);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) throws ProductNotFoundException {
       return productService.updateProduct(id, productRequest);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {

    }
}
