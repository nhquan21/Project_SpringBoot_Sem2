package com.bkap.project_springboot_sem2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="Accounts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Account {
    @Id
    @Column(name = "AccountId")
    private String accountId;
    @Column(name = "UserName" , columnDefinition = "NVARCHAR(50)")
//    @NotBlank(message = "UserName không được để trống")
    private String userName;
    @Column(name = "Password")
//    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;
    @Column(name = "FullName", columnDefinition = "NVARCHAR(50)")
//    @NotBlank(message = "FullName không được để trống")
    private String fullName;
    @Column(name = "Picture")
    private String picture;
    @Column(name = "Email")
//    @Email(message = "email không đúng định dạng")
//    @NotBlank(message = "email không được trống")
    private String email;
    @Column(name = "Gender")
    private boolean gender;
    @Column(name = "Birthday")
    private Date birthday;
    @Column(name = "Address" , columnDefinition = "NVARCHAR(255)")
//    @NotBlank(message = "Address không được trống")
    private String address;
    @Column(name = "Phone")
//    @Length(min = 8, max = 12)
//    @NotBlank(message = "số điện thoại không được trống")
//    @Pattern(regexp = "^[+]?[0-9]{10,13}$", message = "số điện thoại không đúng định dạng")
    private String phone;
    @Column(name = "Active")
    private boolean active;
    @Column(name = "Createdate")
    private Date createDate;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account_Role> accountRoles;



}