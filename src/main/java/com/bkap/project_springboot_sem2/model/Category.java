package com.bkap.project_springboot_sem2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="Categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @Column(name="CategoryId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @NotEmpty(message = "Tên danh mục không được trống")
    @Column(name="CategoryName" ,columnDefinition="Nvarchar(100)")
    private String categoryName;
    //    @NotEmpty(message = "Ảnh không được trống")
    @Column(name="Image" , columnDefinition="Nvarchar(255)")
    private String image;
    @Column(name="Active")
    private boolean active;

//    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
//    private List<Product> products;

}
