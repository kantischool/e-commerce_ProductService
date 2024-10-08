package com.example.Product.inheritancedemo.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "ms_instructor")
public class Instructor extends User{
    private String specialization;
}
