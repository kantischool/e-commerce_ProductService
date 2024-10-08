package com.example.Product.services;

import com.example.Product.dtos.ProductRequest;
import com.example.Product.exception.InvalidProductException;
import com.example.Product.exception.ProductNotFoundException;
import com.example.Product.models.Category;
import com.example.Product.models.Product;
import com.example.Product.repository.CategoryRepo;
import com.example.Product.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier(value = "dbService")
public class DBServices implements IProductService{


    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
   public DBServices(ProductRepo productRepo, CategoryRepo categoryRepo){
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }
    @Override
    public Product getSingleProduct(Long id) throws InvalidProductException {
      Optional<Product> productOptional = productRepo.findById(id);

      if (productOptional.isEmpty()){
          throw new InvalidProductException("this product is not available from db");
      }
        return productOptional.get();
    }

    @Override
    public List<Product> getAllProduct() {
        return null;
    }

    @Override
    public Product addProduct(ProductRequest productRequest) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, ProductRequest productRequest) throws ProductNotFoundException {


       Optional<Product> optionalProduct = productRepo.findById(id);

       if (optionalProduct.isEmpty()){
           throw new ProductNotFoundException("product does not exist");
       }
       else{

           Product oldProduct = optionalProduct.get();

           Product newProduct = new Product();

           newProduct.setId(id);
           newProduct.setName(
                   (productRequest.getTitle() != null || !productRequest.getTitle().isEmpty())  ? productRequest.getTitle() : oldProduct.getName()
           );

           newProduct.setImage(
                   (productRequest.getImage() != null || !productRequest.getImage().isEmpty())  ? productRequest.getImage() : oldProduct.getImage()

           );

           newProduct.setPrice(
                   (productRequest.getPrice() != 0)  ? productRequest.getPrice() : oldProduct.getPrice()
           );

           newProduct.setDescription(
                   (productRequest.getDescription() != null || !productRequest.getDescription().isEmpty())  ? productRequest.getDescription() : oldProduct.getDescription()

           );
           newProduct.setCategory(oldProduct.getCategory());

          Optional<Product> productResponse = Optional.of(productRepo.save(newProduct));

          return productResponse.orElse(null);
       }

    }

    @Override
    public Product getProductByName(String name) throws InvalidProductException {
       Optional<Product> product = productRepo.findByName(name);
       if (product.isEmpty()){
           throw new InvalidProductException("there is no product of this name");
       }
       return product.get();
    }

    @Override
    public List<Product> getProductPriceGreaterThan(int price) {
       Optional<List<Product>> optionalProduct = productRepo.findByPriceGreaterThan(price);
        return optionalProduct.orElse(null);
    }

    @Override
    public Product addProduct(Product product) {

//        Category category = checkCategoryExist(product.getCategory());
//        product.setCategory(category);
        Optional<Product> optionalProduct = Optional.of(productRepo.save(product));
        return optionalProduct.orElse(null);
    }

    private Category checkCategoryExist(Category category) {
        Optional<Category> optionalCategory =categoryRepo.findByName(category.getName());
        return optionalCategory.orElseGet(() -> Optional.ofNullable(categoryRepo.save(category)).get());
    }
}
