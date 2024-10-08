package com.example.Product.inheritancedemo.tableperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "tpc_mentor")
public class Mentor extends User {
    private int numberOfSession;
    private float avgRating;
}
