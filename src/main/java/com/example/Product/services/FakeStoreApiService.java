package com.example.Product.services;

import com.example.Product.dtos.ProductRequest;
import com.example.Product.dtos.ProductResponse;
import com.example.Product.exception.InvalidProductException;
import com.example.Product.models.Category;
import com.example.Product.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier(value = "fakeStore")
public class FakeStoreApiService implements IProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Product getSingleProduct(Long id) throws InvalidProductException {

        // I should pass this 'id' to fakestore and get the details of this product.
        // "https://fakestoreapi.com/products/1"  --  this the Url to get product Data.

        // here we are using rest template to call third party APIs.

        // if we want ot throw an exception...

        if (id > 20) {
            throw new InvalidProductException("product is not available");
        }


        ProductResponse productResponse = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, ProductResponse.class);
        return convertIntoProduct(productResponse);
    }

    @Override
    public List<Product> getAllProduct() {

        // here we are using ParameterizedTypeReference to get the response as the List of our custom object...

        ParameterizedTypeReference<List<ProductResponse>> responseType = new ParameterizedTypeReference<>() {
        };

        ResponseEntity<List<ProductResponse>> productResponseList = restTemplate.exchange("https://fakestoreapi.com/products/", HttpMethod.GET, null, responseType);
        productResponseList.getBody();

        return new ArrayList<>();
    }

    @Override
    public Product addProduct(ProductRequest productRequest) {
        ProductRequest fakeData = new ProductRequest();

        fakeData.setDescription("this is fake product for testing purpose only");
        fakeData.setCategory("fake Category");
        fakeData.setImage("fakeImahe url");
        fakeData.setPrice(158);
        fakeData.setTitle("fake Category");

        ResponseEntity<ProductResponse> productResponse = restTemplate.postForEntity("https://fakestoreapi.com/products/", fakeData, ProductResponse.class);
        productResponse.getBody();
        return null;
    }

    @Override
    public Product updateProduct(Long id, ProductRequest requestBody) {

        RequestCallback requestCallback = restTemplate.httpEntityCallback(requestBody, ProductResponse.class);
        HttpMessageConverterExtractor<ProductResponse> responseExtractor = new HttpMessageConverterExtractor<>(ProductResponse.class, restTemplate.getMessageConverters());
        ProductResponse productResponse = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        return convertIntoProduct(productResponse);

    }

    @Override
    public Product getProductByName(String name) throws InvalidProductException {
        return null;
    }

    @Override
    public List<Product> getProductPriceGreaterThan(int price) {
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    private Product convertIntoProduct(ProductResponse productResponse) {
        Product product = new Product();
        Category category = new Category();

        category.setName(productResponse.getCategory());
        product.setCategory(category);
        product.setId(productResponse.getId());
        product.setImage(productResponse.getImage());
        product.setPrice(productResponse.getPrice());
        product.setDescription(productResponse.getDescription());
        product.setName(productResponse.getTitle());

        return product;
    }
}
