package com.example.Product.inheritancedemo.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "st_tas")
@DiscriminatorValue(value = "3")
public class TAs extends User {
    private float avgRating;
    private int numberOfCall;
}
