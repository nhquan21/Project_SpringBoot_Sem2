package com.bkap.project_springboot_sem2.repository;

import com.bkap.project_springboot_sem2.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {


}

