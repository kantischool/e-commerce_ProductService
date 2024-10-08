package com.example.Product.inheritancedemo.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "ms_tas")
public class TAs extends User{
    private float avgRating;
    private int numberOfCall;
}
