package com.bkap.project_springboot_sem2.services;

import com.bkap.project_springboot_sem2.model.Product;
import com.bkap.project_springboot_sem2.repository.ProductReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductReposity productReposity;
    @Override
    public List<Product> getAll() {
        return this.productReposity.findAll();
    }

    @Override
    public Product getById(Integer id) {
        return this.productReposity.findById(id).get();
    }

    @Override
    public Boolean add(Product product) {
        try{
            this.productReposity.save(product);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(Product product) {
        try{
            this.productReposity.save(product);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Integer id) {
        try{
            this.productReposity.delete(getById(id));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Product> searchProduct(String key) {
        return this.productReposity.searchProduct(key);
    }

    @Override
    public Page<Product> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1,8);
        return this.productReposity.findAll(pageable);
    }

    @Override
    public Page<Product> searchProduct(String key, Integer pageNo) {
        List list = this.searchProduct(key);

        Pageable pageable = PageRequest.of(pageNo-1,8);

        Integer start = (int) pageable.getOffset();

        Integer end =(int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size():pageable.getOffset() + pageable.getPageSize());

        list = list.subList(start, end);

        return new PageImpl<>(list, pageable, this.searchProduct(key).size());
    }

    @Override
    public List<Product> findProductByIds(List<Integer> ids) {
        return this.productReposity.findByIdIn(ids);
    }

    @Override
    public List<Product> findProductById(Integer id) {
        return this.productReposity.findByCategoryId(id);
    }

    @Override
    public Page<Product> searchProduct(Integer idCate, Integer pageNo) {
        List list = this.findProductById(idCate);
        Pageable pageable = PageRequest.of(pageNo-1,5);

        Integer start = (int) pageable.getOffset();

        Integer end =(int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size():pageable.getOffset() + pageable.getPageSize());

        list = list.subList(start, end);
        return new PageImpl<>(list,pageable,this.findProductById(idCate).size());
    }

    @Override
    public List<Product> searchProduct(Integer id, String key) {
        return this.productReposity.searchProduct(id,key);
    }



    @Override
    public List<Product> searchProduct(double fromPrice, double toPrice) {
        return this.productReposity.searchProduct(fromPrice, toPrice);
    }

    @Override
    public List<Product> searchProductFrom(double fromPrice) {
        return this.productReposity.searchProductFrom(fromPrice);
    }

    @Override
    public List<Product> searchProductTo(double toPrice) {
        return this.productReposity.searchProductTo(toPrice);
    }

    @Override
    public Page<Product> searchProductFrom(double fromPrice, Integer pageNo) {
        List list = this.searchProductFrom(fromPrice);

        Pageable pageable = PageRequest.of(pageNo-1,5);

        Integer start = (int) pageable.getOffset();

        Integer end =(int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size():pageable.getOffset() + pageable.getPageSize());

        list = list.subList(start, end);
        return new PageImpl<Product>(list,pageable,this.searchProductFrom(fromPrice).size());
    }

    @Override
    public Page<Product> searchProductTo(double toPrice, Integer pageNo) {
        List list = this.searchProductTo(toPrice);

        Pageable pageable = PageRequest.of(pageNo-1,5);

        Integer start = (int) pageable.getOffset();

        Integer end =(int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size():pageable.getOffset() + pageable.getPageSize());

        list = list.subList(start, end);
        return new PageImpl<Product>(list,pageable,this.searchProductTo(toPrice).size());
    }

    @Override
    public Page<Product> searchProduct(double fromPrice, double toPrice, Integer pageNo) {
        List list = this.searchProduct(fromPrice,toPrice);

        Pageable pageable = PageRequest.of(pageNo-1,5);

        Integer start = (int) pageable.getOffset();

        Integer end =(int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size():pageable.getOffset() + pageable.getPageSize());

        list = list.subList(start, end);
        return new PageImpl<Product>(list,pageable, this.searchProduct(fromPrice, toPrice).size());
    }

    @Override
    public Page<Product> searchProduct(Integer id, String key, Integer pageNo) {
        List list = this.searchProduct(id,key);
        Pageable pageable = PageRequest.of(pageNo-1,5);

        Integer start = (int) pageable.getOffset();

        Integer end =(int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size():pageable.getOffset() + pageable.getPageSize());

        list = list.subList(start, end);
        return new PageImpl<Product>(list,pageable,this.searchProduct(id,key).size());
    }

    @Override
    public List<Product> sortPriceDASC() {
        return this.productReposity.searchProductDASC();
    }

    @Override
    public List<Product> sortPriceDESC() {
        return this.productReposity.searchProductDESC();
    }

    @Override
    public Page<Product> sortPriceDASC(Integer pageNo) {
        List list = this.sortPriceDASC();
        Pageable pageable = PageRequest.of(pageNo-1,5);

        Integer start = (int) pageable.getOffset();

        Integer end =(int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size():pageable.getOffset() + pageable.getPageSize());

        list = list.subList(start, end);
        return new PageImpl<>(list,pageable,this.sortPriceDASC().size());
    }

    @Override
    public Page<Product> sortPriceDESC(Integer pageNo) {
        List list = this.sortPriceDESC();
        Pageable pageable = PageRequest.of(pageNo-1,5);

        Integer start = (int) pageable.getOffset();

        Integer end =(int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size():pageable.getOffset() + pageable.getPageSize());

        list = list.subList(start, end);
        return new PageImpl<>(list,pageable,this.sortPriceDESC().size());
    }

}