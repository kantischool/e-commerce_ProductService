package com.example.Product.inheritancedemo.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "ms_mentor")
public class Mentor extends User{
    private int numberOfSession;
    private float avgRating;
}
