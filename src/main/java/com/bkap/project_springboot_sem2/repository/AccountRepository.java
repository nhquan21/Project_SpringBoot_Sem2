package com.bkap.project_springboot_sem2.repository;


import com.bkap.project_springboot_sem2.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;


public interface AccountRepository extends  JpaRepository<Account, String> {
    @Query("SELECT ac FROM Account ac WHERE ac.fullName LIKE %?1% ")
    List<Account> searchAccount(String key);


    Account findByUserName(String userName);
    Account findByEmail(String email);


}
