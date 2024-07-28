package com.bkap.project_springboot_sem2.services;

import com.bkap.project_springboot_sem2.model.Account;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccountService {
    Account findByUserName(String userName);
    Account findByEmail(String email);
    List<Account> getAll();
    Account getById(String id);
    Boolean delete(String id);
    List<Account> searchAccount(String key);
    Page<Account> getAll(Integer pageNo);
    Page<Account>  searchAccount(String key, Integer pageNo);
}