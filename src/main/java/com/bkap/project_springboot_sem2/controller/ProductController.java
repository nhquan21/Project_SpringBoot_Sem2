package com.bkap.project_springboot_sem2.controller;

import com.bkap.project_springboot_sem2.model.Category;
import com.bkap.project_springboot_sem2.model.Product;
import com.bkap.project_springboot_sem2.repository.CategoryRepository;
import com.bkap.project_springboot_sem2.services.CategoryService;
import com.bkap.project_springboot_sem2.services.ProductService;
import com.bkap.project_springboot_sem2.services.StorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private StorageService storageService;
    public static List<Product> products=new ArrayList<>();
    @Autowired
    private CategoryRepository categoryRepository;

    //hien thi list sp, phân trang
    @RequestMapping("admin/product")
    public String index(Model model, @Param("key") String key, @RequestParam(name="pageNo",defaultValue = "1") Integer pageNo){
        Page<Product> list =this.productService.getAll(pageNo);
        if(key != null){
            list = this.productService.searchProduct(key, pageNo);
            model.addAttribute("key", key);
        }
        model.addAttribute("totalPage", list.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("list", list);
        return "admin/product/index";
    }

    //thêm mới sp
    @RequestMapping("admin/addproduct")
    public String add(Model model ){
        Product product = new Product();
        model.addAttribute("product", product);
        List<Category> listCate = this.categoryService.getAll();
        model.addAttribute("listCate", listCate);
        return "admin/product/addproduct";
    }

    @PostMapping("admin/addproduct")
    public String save(Model model , @Valid @ModelAttribute("product") Product product, BindingResult result, @RequestParam("file") MultipartFile file) {

        System.out.println(product.getCategory().getCategoryName() + " category name");
        if(result.hasErrors()) {
            model.addAttribute("listCate", categoryService.getAll());
            return "admin/product/addproduct";
        }

        this.storageService.store(file);
        String fileName = file.getOriginalFilename();
        product.setImage(fileName);
        if (this.productService.add(product)) {

            return "redirect:/admin/product";
        }else {
            model.addAttribute("listCate", categoryService.getAll());
            return "admin/product/addproduct";
        }
    }

    //sửa sp theo
    @RequestMapping("admin/editproduct/{id}")
    public String edit(@Valid  Model model , @PathVariable("id") Integer id){
        Product product = this.productService.getById(id);
        model.addAttribute("product", product);
        model.addAttribute("listCate", categoryService.getAll());
        return "admin/product/editproduct";
    }

    @PostMapping ("admin/editproduct")
    public String update(@ModelAttribute("product") Product product,@RequestParam("file") MultipartFile file, @RequestParam("oldPicture") String oldPicture){
        String fileName = file.getOriginalFilename();
        if(fileName.equals("")){
            product.setImage(oldPicture);
        }else{
            product.setImage(fileName);
            this.storageService.store(file);
        }

        if(this.productService.update(product)){
            return "redirect:/admin/product";
        }else{
            return "admin/category/editproduct";
        }

    }

    //xoá sp
    @RequestMapping("admin/deleteproduct/{id}")
    public String delete( @PathVariable("id") Integer id){
        if(this.productService.delete(id)){
            return "redirect:/admin/product";
        }else{
            return "admin/product/index";
        }
    }
}
