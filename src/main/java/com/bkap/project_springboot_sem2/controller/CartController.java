package com.bkap.project_springboot_sem2.controller;

import com.bkap.project_springboot_sem2.model.CartItem;
import com.bkap.project_springboot_sem2.model.Product;
import com.bkap.project_springboot_sem2.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;



    /// them sp vao gio hang
    @GetMapping("/cart-add/{id}")
    public @ResponseBody String addToCart(@PathVariable("id") String id, HttpServletRequest req) {
        List<CartItem> carts = new ArrayList<>();
        HttpSession session = req.getSession();

        Integer proid= Integer.parseInt(id);

        Product pr = productService.getById(proid);

        if (session.getAttribute("cart") == null) {

            CartItem cart = new CartItem(pr.getProductId(), pr.getProductName(), pr.getImage(), pr.getPrice(),1);

            carts.add(cart);

            session.setAttribute("cart", carts);

            List<CartItem> data = (List<CartItem>) session.getAttribute("cart");
            for (CartItem cartItem : data) {
            }

        } else {
            carts = (List<CartItem>) session.getAttribute("cart");
            boolean duplicate = false;
            for (int i = 0; i < carts.size(); i++) {
                CartItem bs = carts.get(i);
                if (bs.getId() == proid) {
                    bs.setQuantity(bs.getQuantity() + 1);
                    duplicate = true;
                    break;

                }
            }
            if (duplicate) {
                session.setAttribute("cart", carts);
            }

            else {
                CartItem cart = new CartItem(pr.getProductId(), pr.getProductName(), pr.getImage(), pr.getPrice(),1);
                carts.add(cart);

            }
        }
        session.setAttribute("cart", carts);
        return String.valueOf(carts.size());
    }

    // hiển thị số lượng(countItem) sp ở cart
//    @ModelAttribute
//    public void addCountItem(Model model, HttpServletRequest req) {
//        List<CartItem> carts = new ArrayList<>();
//        HttpSession session = req.getSession();
//        if (session.getAttribute("cart") != null) {
//            carts = (List<CartItem>) session.getAttribute("cart");
//        }
//        var itemCount = String.valueOf(carts.size());
//        model.addAttribute("itemCount", itemCount);
//
//    }

    // view cart
    @RequestMapping("/cart")
    public String viewCart(Model model, HttpServletRequest req) {
        model.addAttribute("page", "cart");
        List<CartItem> carts = new ArrayList<>();
        HttpSession session = req.getSession();
        if (session.getAttribute("cart") != null) {
            carts = (List<CartItem>) session.getAttribute("cart");
            for (CartItem cart : carts) {
                System.out.println(cart.getId() + " " + cart.getName() + " " + cart.getQuantity());

            }

        }

//khơi tao bien total để tính tổng giá các sp trong gio hnag
        double total = carts.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        // Đưa danh sách sản phẩm và tổng giá trị vào model
        model.addAttribute("total", total);
        model.addAttribute("carts", carts);

        return "/user/cart";
    }


    //cap nhat gio hang
    @RequestMapping(value = "updateCart/{id}/{value}")
    public @ResponseBody String updateBasket(@PathVariable("id") Integer id, @PathVariable("value") Integer quantity,
                                             Model model, HttpServletRequest req) {
        List<CartItem> datacart = new ArrayList<>();
        HttpSession session = req.getSession();
        if (session.getAttribute("cart") != null) {
            datacart = (List<CartItem>) session.getAttribute("cart");
            for (int i = 0; i < datacart.size(); i++) {
                CartItem cs = datacart.get(i);
                if (cs.getId().equals(id)) {
                    cs.setQuantity(quantity);
                    break;

                }
            }

        }
        return "";
    }

    //xoa sp trong gio hang
    @RequestMapping(value = "removeItem/{id}")
    public @ResponseBody String removeItem(@PathVariable("id") String id, HttpServletRequest req) {
        List<CartItem> Removecarts = new ArrayList<>();
        HttpSession session = req.getSession();
        Integer proid= Integer.parseInt(id);
        boolean find = false;
        if (session.getAttribute("cart") != null) {
            int i;
            Removecarts = (List<CartItem>) session.getAttribute("cart");
            for (i = 0; i < Removecarts.size(); i++) {
                CartItem bs = Removecarts.get(i);
                if (bs.getId() == proid) {
                    find = true;
                    break;
                }
            }
            if (find) {
                Removecarts.remove(i);
            }
            session.setAttribute("cart", Removecarts);
        }
        return "";
    }



}