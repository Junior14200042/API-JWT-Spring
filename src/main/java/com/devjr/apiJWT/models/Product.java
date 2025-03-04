package com.devjr.apiJWT.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String image;
    private Double price;
    private Integer quantity;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "id_category")
    private Category category;
}
