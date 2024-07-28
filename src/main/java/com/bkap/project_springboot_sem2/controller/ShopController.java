package com.bkap.project_springboot_sem2.controller;

import com.bkap.project_springboot_sem2.model.Product;
import com.bkap.project_springboot_sem2.repository.CategoryRepository;
import com.bkap.project_springboot_sem2.services.CategoryService;
import com.bkap.project_springboot_sem2.services.ProductService;
import com.bkap.project_springboot_sem2.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShopController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private StorageService storageService;
    public static List<Product> products = new ArrayList<>();
    @Autowired
    private CategoryRepository categoryRepository;

    //phan trang, tim kiem ten
    @RequestMapping("/shop")
    public String shop(Model model, @Param("key") String key, @RequestParam(name = "cate", defaultValue = "") Integer id, @Param("fromPrice") Double fromPrice, @Param("toPrice") Double toPrice, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Product> list = this.productService.getAll(pageNo);
        if (key != null && id == null) {
            list = this.productService.searchProduct(key, pageNo);
            model.addAttribute("key", key);
        } else if (toPrice != null && toPrice > 0.0) {
            list = this.productService.searchProductTo(toPrice, pageNo);
            model.addAttribute("toPrice", toPrice);
        } else if (fromPrice != null && fromPrice > 0.0) {
            list = this.productService.searchProductFrom(fromPrice, pageNo);
            model.addAttribute("fromPrice", fromPrice);
        } else if (fromPrice != null && toPrice != null) {
            list = this.productService.searchProduct(fromPrice, toPrice, pageNo);
            model.addAttribute("fromPrice", fromPrice);
            model.addAttribute("toPrice", toPrice);
        } else if (key != null && id != null) {
            list = this.productService.searchProduct(id, key, pageNo);
            model.addAttribute("key", key);
            model.addAttribute("cate", id);
        } else if (id != null && key == null) {
            list = this.productService.searchProduct(id, pageNo);
            model.addAttribute("cate", id);
        }
        model.addAttribute("totalPage", list.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("products", list);
        return "/user/shop";
    }

    //lọc sp theo danh mục
    @GetMapping("/shop/categories")
    public String listProducts(@RequestParam("cate") Integer categoryId, Model model, @RequestParam("cate") String cate) {
        List<Integer> ids = new ArrayList<>();
        if (cate != null) {
            if (cate.indexOf(',') > 0) {
                String[] dataSplit = cate.split(",");
                System.out.println(dataSplit);
                for (int i = 0; i < dataSplit.length; i++) {
                    ids.add(Integer.valueOf(dataSplit[i]));
                }
                products = this.productService.findProductByIds(ids);
            } else {
                products = this.productService.findProductById(Integer.valueOf(cate));
            }
        } else {
            products = this.productService.getAll();
        }

        model.addAttribute("categories", categoryService.getById(categoryId));
        model.addAttribute("products", products);
        return "/user/shop";
    }


    // sort name A-Z, price a-z

    @PostMapping("/shop-sort")
    public String sort(Model model,@RequestParam(name = "sort",defaultValue = "") Integer sort,@RequestParam(name="pageNo",defaultValue = "1") Integer pageNo) {

        Page<Product> list = this.productService.getAll(pageNo);

        if (sort == 1){
            list = this.productService.sortPriceDASC(pageNo);
        }else if (sort == 2){
            list = this.productService.sortPriceDESC(pageNo);
        }
        model.addAttribute("totalPage", list.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("products",list);
        return "/user/shop";
    }

    @RequestMapping("/shop-cateId")
    public String shopcate(Model model,@RequestParam(name = "id",defaultValue = "0") Integer id, @RequestParam(name="pageNo",defaultValue = "1") Integer pageNo){
        Page<Product> list = this.productService.getAll(pageNo);
        if (id != null){
            list = this.productService.searchProduct(id,pageNo);
        }
        model.addAttribute("totalPage", list.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("products",list);
        return "/user/shop";
    }
}
