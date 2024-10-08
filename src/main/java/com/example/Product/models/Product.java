package com.example.Product.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product extends  BaseModel {
    private String name;
    private String Description;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) //cascade - persist mean when user create product with new category id, that category wil l created automatically
    private Category category;
    private int price;
    private String image;
}
