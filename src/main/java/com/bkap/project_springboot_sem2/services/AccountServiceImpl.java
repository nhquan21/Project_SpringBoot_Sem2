package com.bkap.project_springboot_sem2.services;

import com.bkap.project_springboot_sem2.model.Account;
import com.bkap.project_springboot_sem2.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public List<Account> getAll() {
        return this.accountRepository.findAll();
    }

    @Override
    public Account getById(String id) {
        return this.accountRepository.findById(id).get();
    }


    @Override
    public Boolean delete(String id) {
        try{
            this.accountRepository.delete(getById(id));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Account> searchAccount(String key) {
        return this.accountRepository.searchAccount(key);
    }

    @Override
    public Page<Account> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1,5);
        return this.accountRepository.findAll(pageable);
    }

    @Override
    public Page<Account> searchAccount(String key, Integer pageNo) {
        List list = this.searchAccount(key);

        Pageable pageable = PageRequest.of(pageNo-1,5);

        Integer start = (int) pageable.getOffset();

        Integer end =(int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size():pageable.getOffset() + pageable.getPageSize());

        list = list.subList(start, end);

        return new PageImpl<>(list, pageable, this.searchAccount(key).size());
    }

    @Override
    public Account findByUserName(String userName) {
        return accountRepository.findByUserName(userName);
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }




}