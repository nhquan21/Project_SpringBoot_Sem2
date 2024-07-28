package com.bkap.project_springboot_sem2.services;

import com.bkap.project_springboot_sem2.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category getById(Integer id);
    Boolean add(Category category);
    Boolean update(Category category);
    Boolean delete(Integer id);
    Page<Category> getAll(Integer pageNo);


}