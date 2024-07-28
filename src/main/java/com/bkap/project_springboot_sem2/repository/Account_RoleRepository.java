package com.bkap.project_springboot_sem2.repository;


import com.bkap.project_springboot_sem2.model.Account_Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Account_RoleRepository extends JpaRepository<Account_Role, Integer> {
}
