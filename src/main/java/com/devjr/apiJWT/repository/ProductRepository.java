package com.devjr.apiJWT.repository;

import com.devjr.apiJWT.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    @Query("SELECT SUM(p.price) AS priceTotal, SUM(p.quantity) AS quantityTotal from Product p where p.category.id=?1")
    List<Object[]> getPriceAndQuantityTotal(Long id_category);


}
