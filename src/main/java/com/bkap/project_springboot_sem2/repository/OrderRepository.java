package com.bkap.project_springboot_sem2.repository;

import com.bkap.project_springboot_sem2.model.Order;
import com.bkap.project_springboot_sem2.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query("SELECT o FROM OrderDetail o  WHERE o.order.orderId = :id")
    public List<OrderDetail> getId(String id);

    @Query("SELECT ac FROM Account ac WHERE ac.fullName LIKE %?1% ")
    List<Order> searchOrder(String key);

    @Query("SELECT od FROM OrderDetail od WHERE od.order.orderId  = :id ")
    List<OrderDetail> OrderId(Integer id);

//    @Query("insert INTO Order (order_id, customer_id, order_date, total_amount) VALUES (?, ?, ?, ?)")
//    public boolean insertOrderDetail(Order order, List<OrderDetail> details );
}
