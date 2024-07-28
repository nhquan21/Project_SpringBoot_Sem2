package com.bkap.project_springboot_sem2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="OrderDetails")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetail {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="order_id",referencedColumnName = "orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_id",referencedColumnName = "productId")
    private Product product;
    private int quantity;
    private Float price;
//    private double price;
//    private Integer quantity;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "productId", referencedColumnName = "productId", insertable= false, updatable= false )
//    private Product product;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "orderId", referencedColumnName = "id", insertable= false, updatable= false )
//    private Order order;

}
