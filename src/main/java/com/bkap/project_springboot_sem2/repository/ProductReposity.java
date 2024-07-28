package com.bkap.project_springboot_sem2.repository;

import com.bkap.project_springboot_sem2.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductReposity extends JpaRepository<Product, Integer> {
    //search namw admin product
    @Query("SELECT p FROM Product p WHERE p.productName LIKE %?1% ")
    List<Product> searchProduct(String key);

    //timf kieems nhieu danh muc  theo danh muc trang shop
    @Query("SELECT p FROM Product p WHERE p.category.categoryId In :ids")
    List<Product> findByIdIn(List<Integer> ids);

    //timf kieems 1 danh muc  theo danh muc trang shop
    @Query("SELECT p FROM Product p WHERE p.category.categoryId = :id")
    List<Product> findByCategoryId(Integer id);


    @Query("SELECT p FROM Product p WHERE p.category.categoryId  = :id ")
    List<Product> categoryId(Integer id);


    Page<Product> findAll(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.category.categoryId = :id and p.productName LIKE %:key% ")
    List<Product> searchProduct(Integer id, String key);

    @Query("SELECT p FROM Product p WHERE p.price >= :fromPrice")
    List<Product> searchProductFrom(double fromPrice);

    @Query("SELECT p FROM Product p WHERE p.price <= :toPrice")
    List<Product> searchProductTo(double toPrice);

    @Query("SELECT p FROM Product p WHERE p.price >= :fromPrice and p.price <= :toPrice ")
    List<Product> searchProduct(double fromPrice,double toPrice);

    @Query("SELECT p FROM Product p order by p.price DESC")
    List<Product> searchProductDESC();

    @Query("SELECT p FROM Product p order by p.price ASC")
    List<Product> searchProductDASC();

}
