package com.bkap.project_springboot_sem2.services;

import com.bkap.project_springboot_sem2.model.Account;
import com.bkap.project_springboot_sem2.model.Account_Role;
import com.bkap.project_springboot_sem2.repository.AccountRepository;
import com.bkap.project_springboot_sem2.security.CustomerAccountDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomeAccountDetailService implements UserDetailsService {
    @Autowired
    private AccountService accountService;




    private AccountRepository accountRepository;

    @Autowired
    public CustomeAccountDetailService (AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserName(username);
        if(account == null){
            throw new UsernameNotFoundException("sai");
        }
        Collection<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        Set<Account_Role> roles = account.getAccountRoles();
        for (Account_Role account_role : roles){
            grantedAuthoritySet.add(new SimpleGrantedAuthority(account_role.getRole().getRoleName()));
        }
        return new CustomerAccountDetail(account, grantedAuthoritySet);
    }
}
