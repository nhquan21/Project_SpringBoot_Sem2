package com.bkap.project_springboot_sem2.services;

import com.bkap.project_springboot_sem2.model.Order;
import com.bkap.project_springboot_sem2.model.OrderDetail;
import com.bkap.project_springboot_sem2.repository.OrderDetailRepository;
import com.bkap.project_springboot_sem2.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderSevice {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public List<Order> getAll() {
        return this.orderRepository.findAll();
    }


    @Override
    public List<OrderDetail> getId(String id) {
        return this.orderRepository.getId(id);
    }

    @Override
    public Order getById(Integer id) {
        return this.orderRepository.findById(id).get();
    }

    @Override
    public Boolean add(Order order) {
        this.orderRepository.save(order);
        return true;
    }

    @Override
    public Boolean update(Order order) {
        this.orderRepository.save(order);
        return true;
    }

    @Override
    public Boolean delete(Integer id) {
        this.orderRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean insertOrderDetail(Order order, List<OrderDetail> details) {
        try {
            orderRepository.save(order);
            for (OrderDetail orderDetail : details) {
                orderDetailRepository.save(orderDetail);
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public List<Order> searchOrder(String key) {
        return this.orderRepository.searchOrder(key);
    }


    @Override
    public Page<Order> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1,5);
        return this.orderRepository.findAll(pageable);
    }

    @Override
    public Page<Order> searchOrder(String key, Integer pageNo) {
        List list = this.searchOrder(key);

        Pageable pageable = PageRequest.of(pageNo-1,5);

        Integer start = (int) pageable.getOffset();

        Integer end =(int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size():pageable.getOffset() + pageable.getPageSize());

        list = list.subList(start, end);

        return new PageImpl<>(list, pageable, this.searchOrder(key).size());
    }
}