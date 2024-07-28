package com.bkap.project_springboot_sem2.controller;

import com.bkap.project_springboot_sem2.model.CartItem;
import com.bkap.project_springboot_sem2.model.Product;
import com.bkap.project_springboot_sem2.services.CategoryService;
import com.bkap.project_springboot_sem2.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    //views
    //trang chủ/home
    @RequestMapping({"/", "/home"})
    public String home(Model model, HttpServletRequest req, @RequestParam(name = "cate",defaultValue = "") Integer id, @Param("key") String key, @RequestParam(name="pageNo",defaultValue = "1") Integer pageNo){
        Page<Product> listview =this.productService.getAll(pageNo);
        if(key != null && id == null){
            listview = this.productService.searchProduct(key, pageNo);
            model.addAttribute("key", key);
        }else if(key == null && id != null){
            listview = this.productService.searchProduct(id,pageNo);
            model.addAttribute("cate", id);
        }else if(key != null && id != null){
            listview = this.productService.searchProduct(id,key,pageNo);
            model.addAttribute("key", key);
            model.addAttribute("cate", id);
        }
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("totalPage", listview.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listview", listview);

//        model.addAttribute("products", productService.getAll());

        return "/user/home";
    }

    @RequestMapping("/about")
    public String about(){
        return "/user/about";
    }

    @RequestMapping("/page")
    public String page(){
        return "/user/page";
    }
    @RequestMapping("/loginUser")
    public String logiUser(Model model){
//        model.addAttribute("account", new Account());
        return "/user/login";
    }
    @RequestMapping("/registerUser")
    public String registerUser(Model model){
//        model.addAttribute("account", new Account());
        return "/user/register";
    }

    //chi tiet sp
    @RequestMapping("/product_detail/{id}")
    public String product_detail(@PathVariable Integer id, Model model){
        Product product = this.productService.getById(id);
        model.addAttribute("product", product);
        return "/user/product_detail";
    }


    @RequestMapping("/blog")
    public String blog(){
        return "/user/blog";
    }

    @RequestMapping("/faqs")
    public String faq(){

        return "/user/FAQs";
    }

    @RequestMapping("/contact")
    public String contact(){

        return "/user/contact";
    }

    // lấy dữ liệu ở cart sang trang thanh toán
    @RequestMapping("/checkout")
    public String checkout(Model model, HttpServletRequest req){
        model.addAttribute("page", "cart");
        List<CartItem> carts = new ArrayList<>();
        HttpSession session = req.getSession();
        if (session.getAttribute("cart") != null) {

            carts = (List<CartItem>) session.getAttribute("cart");
            for (CartItem cart : carts) {
                System.out.println(cart.getId() + " " + cart.getName() + " " + cart.getQuantity());

            }

        }

        double total = carts.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        // Đưa danh sách sản phẩm và tổng giá trị vào model

        model.addAttribute("total", total);
        model.addAttribute("carts", carts);

        return "/user/checkout";
    }
    @RequestMapping("/cateId")
    public String cate(Model model,@RequestParam("id") Integer id, @RequestParam(name="pageNo",defaultValue = "1") Integer pageNo){
        Page<Product> list = this.productService.getAll(pageNo);
        if (id != null){
            list = this.productService.searchProduct(id,pageNo);
        }
        model.addAttribute("totalPage", list.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("list",list);
        return "/user/home";
    }
    @RequestMapping("/search")
    public String search(Model model){
        model.addAttribute("categories", categoryService.getAll());
        return "/layout-user/aside";
    }
}

