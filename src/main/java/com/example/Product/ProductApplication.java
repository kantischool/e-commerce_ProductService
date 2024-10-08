package com.example.Product;

import com.example.Product.controllers.ProductControllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ProductApplication.class, args);

		ProductControllers productControllers = context.getBean(ProductControllers.class);
	//	productControllers.addProduct(null);

	}

}
