package com.bkap.project_springboot_sem2.controller;

import com.bkap.project_springboot_sem2.model.Account;
import com.bkap.project_springboot_sem2.model.Account_Role;
import com.bkap.project_springboot_sem2.model.Role;
import com.bkap.project_springboot_sem2.repository.AccountRepository;
import com.bkap.project_springboot_sem2.repository.Account_RoleRepository;
import com.bkap.project_springboot_sem2.repository.RoleRepository;
import com.bkap.project_springboot_sem2.services.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
public class LoginRegisterController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private Account_RoleRepository account_roleRepository;
    @Autowired
    private Account_RoleRepository account_RoleRepository;

    //đăng ký trang người dùng
    @PostMapping("/dang-ky")
    public String dang_ky(String password, String email, String address, String phone, String username , String fullname, Model model, HttpServletRequest req){
//        if (bindingResult.hasErrors()) {
////            model.addAttribute("account", account);
//            return "/user/register";
//        }
//        @Valid  @ModelAttribute("account") Account account, BindingResult bindingResult,
        Account acc = this.accountService.findByEmail(email);
        String pass = passwordEncoder.encode(password);
        if(acc != null) {
            model.addAttribute("msg", "Email đã tồn tại");
            return "/user/home";
        }
        System.out.println("873468734");
        Account ac = new Account();
        String uuidString = UUID.randomUUID().toString();
        ac.setAccountId(uuidString.substring(0, 3));
        ac.setUserName(username);
        ac.setFullName(fullname);
        ac.setEmail(email);
        ac.setAddress(address);
        ac.setPassword(pass);
        ac.setPhone(phone);

        Account user = accountRepository.save(ac);
        Account_Role role = new Account_Role();
        role.setAccount(user);
        Role role1 = roleRepository.findById(2).get();
        role.setRole(role1);
        account_RoleRepository.save(role);

        return "/user/login";
    }


    //đăng nhập trang người dùng
    @PostMapping("/dang-nhap")
    public String dang_nhap( String password, String username, Model model, HttpServletRequest req){
        System.out.println("dang nhap " + " thong tin passửod cu dang " + password + " mk " + username );
        Account acc = this.accountService.findByUserName(username);
        if(acc == null ||!passwordEncoder.matches(password, acc.getPassword())) {
            model.addAttribute("msg", "Tài khoản hoặc mật khẩu không chính xác");
            return "/user/login";
        }
        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(3600);
//        String uuidString = UUID.randomUUID().toString();
        session.setAttribute("account_id", acc.getAccountId());
        session.setAttribute("fullname", acc.getFullName());
        session.setAttribute("email", acc.getEmail());
        session.setAttribute("username", acc.getUserName());
        session.setAttribute("phone", acc.getPhone());
        session.setAttribute("address", acc.getAddress());

        return "redirect:/";
    }


    //GET: thoat trang user
    @RequestMapping(value = "thoat")
    public String logout(Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.invalidate();
        return "redirect:/";
    }





}
