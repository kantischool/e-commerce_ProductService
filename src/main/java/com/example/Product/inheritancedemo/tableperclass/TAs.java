package com.example.Product.inheritancedemo.tableperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "tpc_tas")
public class TAs extends User {
    private float avgRating;
    private int numberOfCall;
}
