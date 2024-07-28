package com.bkap.project_springboot_sem2.repository;

import com.bkap.project_springboot_sem2.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
//    List<CartItem> findByCart(Integer cartId);
//    CartItem findByProductIdAndCartId(Integer productId , Integer cartId);
//    public CartItem findByProductId(Integer productId , Integer orderId);
//
//    public CartItem findByAccountId(String accId);
//    public void deleteCart(String accId);
}