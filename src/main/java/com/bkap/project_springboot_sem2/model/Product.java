package com.bkap.project_springboot_sem2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name="Products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @Column(name="ProductId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    @Column(name="Code")
    @NotEmpty(message = "Mã sản phẩm không được trống")
    private String code;
    @NotEmpty(message = "Tên sản phẩm không được trống")
    @Column(name="ProductName" , columnDefinition="Nvarchar(200)")
    private String productName;
    //    @NotEmpty(message = "Ảnh không được trống")
    @Column(name="Image")
    private String image;
    @DecimalMin(value = "0.0", inclusive = false, message = "Giá không được để trống")
    private double price;
    //    @Column(name="salePrice", columnDefinition="float")
    private double salePrice;

    @Column(name="Desciption", columnDefinition="NTEXT")
    private String desciption;
    //    private  Integer categoryId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
    private Category category;

//    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY , cascade = CascadeType.ALL)
//    private List<Cart_items> cart_items;

}
