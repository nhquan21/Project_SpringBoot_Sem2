package com.bkap.project_springboot_sem2.services;

import com.bkap.project_springboot_sem2.model.Order;
import com.bkap.project_springboot_sem2.model.OrderDetail;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderSevice {
    List<Order> getAll();
    Order getById(Integer id);
    //don hnagf
    public List<OrderDetail> getId(String id);
    //	public boolean delete_don_hang(int id);
    Boolean add(Order order);
    Boolean update(Order order);
    Boolean delete(Integer id);
    public boolean insertOrderDetail(Order order, List<OrderDetail> details );


    List<Order> searchOrder(String key);
    Page<Order> getAll(Integer pageNo);
    Page<Order> searchOrder(String key, Integer pageNo);
}
