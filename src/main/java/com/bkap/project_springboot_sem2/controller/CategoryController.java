package com.bkap.project_springboot_sem2.controller;

import com.bkap.project_springboot_sem2.model.Category;
import com.bkap.project_springboot_sem2.repository.ProductReposity;
import com.bkap.project_springboot_sem2.services.CategoryService;
import com.bkap.project_springboot_sem2.services.StorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private StorageService storageService;

    @Autowired
    private ProductReposity productReposity;


    // hien thi danh sách danh mục
    @RequestMapping("admin/category")
    public String index(Model model, @RequestParam(name="pageNo",defaultValue = "1") Integer pageNo){
        Page<Category> list =this.categoryService.getAll(pageNo);
        model.addAttribute("totalPage", list.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("list", list);
        return "admin/category/index";
    }

    // thêm mới danh mục
    @RequestMapping("admin/addcategory")
    public String add(Model model ){
        Category category = new Category();
        model.addAttribute("category", category);
        category.setActive(true);

        return "admin/category/addcategory";
    }

    @PostMapping("admin/addcategory")
    public String save(Model model, @Valid @ModelAttribute("category") Category category, BindingResult result, @RequestParam("file") MultipartFile file){

        if(result.hasErrors()) {
            model.addAttribute("listCate", categoryService.getAll());
            return "admin/category/addcategory";
        }

        this.storageService.store(file);
        String fileName = file.getOriginalFilename();
        category.setImage(fileName);
        if(this.categoryService.add(category)){
            return "redirect:/admin/category";
        }
        else{
            return "admin/category/addcategory";
        }

    }

    //sửa danh mục theo id
    @RequestMapping("admin/editcategory/{id}")
    public String edit(Model model , @PathVariable("id") Integer id){
        Category category = this.categoryService.getById(id);
        model.addAttribute("category", category);
        return "admin/category/editcategory";
    }

    @PostMapping("admin/editcategory")
    public String update(@ModelAttribute("category") Category category, @RequestParam("file") MultipartFile file, @RequestParam("oldPicture") String oldPicture){
        String fileName = file.getOriginalFilename();
        if(fileName.equals("")){
            category.setImage(oldPicture);
        }else{

            category.setImage(fileName);
            this.storageService.store(file);
        }
        if(this.categoryService.update(category)){
            return "redirect:/admin/category";
        }else{
            return "admin/category/editcategory";
        }
    }

    //xoá danh mục
    @RequestMapping("admin/deletecategory/{id}")
    public String delete( @PathVariable("id") Integer id, Model model,@RequestParam(name="pageNo",defaultValue = "1") Integer pageNo){
        if(this.productReposity.categoryId(id).stream().findFirst().isPresent()){
            model.addAttribute("mes", "không thể xoá danh mục khi đã có sp thuộc danh mục này");
            Page<Category> list =this.categoryService.getAll(1);
            model.addAttribute("totalPage", list.getTotalPages());
            model.addAttribute("currentPage", 1);
            model.addAttribute("list", list);
            return "admin/category/index";
        }
        if(this.categoryService.delete(id)){
            return "redirect:/admin/category";
        }else{
            return "admin/category/index";
        }
    }

}