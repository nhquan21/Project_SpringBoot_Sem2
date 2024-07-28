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
@Table(name="Orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private String name;
    private String email;
    private String address;
    private String phone;
    //    private String account_id;
    private boolean active;
    private Date order_date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accountId", referencedColumnName = "accountId")
    private Account account;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;



//    @Column(name="OrderDate")
//    @CreationTimestamp
//    private Date orderDate;
//
//    @ManyToOne
//    @JoinColumn(name="statusId",referencedColumnName = "id", insertable= false, updatable= false )
//    private OrderStatus orderStatusId;
//
//    @ManyToOne
//    @JoinColumn(name="accountId", referencedColumnName = "accountId", insertable= false, updatable= false )
//    private Account account;
//
//    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<OrderDetail> orderDetails;




}
