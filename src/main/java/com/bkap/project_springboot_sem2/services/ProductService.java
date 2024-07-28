package com.bkap.project_springboot_sem2.services;

import com.bkap.project_springboot_sem2.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product getById(Integer id);
    Boolean add(Product product);
    Boolean update(Product product);
    Boolean delete(Integer id);
    List<Product> searchProduct(String key);
    Page<Product> getAll(Integer pageNo);
    Page<Product>  searchProduct(String key, Integer pageNo);

    List<Product> findProductByIds(List<Integer> ids);

    List<Product> findProductById(Integer id);

    Page<Product>  searchProduct(Integer idCate, Integer pageNo);
    List<Product> searchProduct(Integer id,String key);
    List<Product> searchProduct(double fromPrice,double toPrice);
    List<Product> searchProductFrom(double fromPrice);
    List<Product> searchProductTo(double toPrice);
    Page<Product> searchProductFrom(double fromPrice,Integer pageNo);
    Page<Product> searchProductTo(double toPrice,Integer pageNo);
    Page<Product> searchProduct(double fromPrice,double toPrice,Integer pageNo);


    Page<Product> searchProduct(Integer id,String key,Integer pageNo);
    List<Product> sortPriceDASC();
    List<Product> sortPriceDESC();
    Page<Product>  sortPriceDASC( Integer pageNo);
    Page<Product>  sortPriceDESC( Integer pageNo);


}