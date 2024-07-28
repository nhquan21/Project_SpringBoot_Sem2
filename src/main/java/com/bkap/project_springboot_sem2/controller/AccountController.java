package com.bkap.project_springboot_sem2.controller;

import com.bkap.project_springboot_sem2.model.Account;
import com.bkap.project_springboot_sem2.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("admin/account")
    public String index(Model model, @Param("key") String key, @RequestParam(name="pageNo",defaultValue = "1") Integer pageNo) {
        Page<Account> list = this.accountService.getAll(pageNo);
        if (key != null) {
            list = this.accountService.searchAccount(key, pageNo);
            model.addAttribute("key", key);
        }
        model.addAttribute("totalPage", list.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("list", list);
        return "admin/account/index";
    }
    //xoas tai khoan tren admin
    @RequestMapping("admin/deleteaccount/{id}")
    public String delete( @PathVariable("id") String id){
        if(this.accountService.delete(id)){
            return "redirect:/admin/account";
        }else{
            return "admin/account/index";
        }
    }

}