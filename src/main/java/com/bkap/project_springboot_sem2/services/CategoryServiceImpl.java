package com.bkap.project_springboot_sem2.services;

import com.bkap.project_springboot_sem2.model.Category;
import com.bkap.project_springboot_sem2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category getById(Integer id) {
        return this.categoryRepository.findById(id).get();
    }

    @Override
    public Boolean add(Category category) {
        try{
            this.categoryRepository.save(category);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(Category category) {
        try{
            this.categoryRepository.save(category);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Integer id) {
        try{
            this.categoryRepository.delete(getById(id));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Page<Category> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1,10);
        return this.categoryRepository.findAll(pageable);
    }
}
