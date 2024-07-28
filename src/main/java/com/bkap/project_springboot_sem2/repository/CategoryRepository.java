package com.bkap.project_springboot_sem2.repository;

import com.bkap.project_springboot_sem2.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {


}
