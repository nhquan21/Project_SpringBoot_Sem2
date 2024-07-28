package com.bkap.project_springboot_sem2.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public String index(){
        return "redirect:/admin/";
    }


    @RequestMapping("/")
    public String admin(HttpServletRequest request, Model model){

        return "/admin/index";
    }

}