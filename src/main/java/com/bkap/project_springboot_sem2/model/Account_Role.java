package com.bkap.project_springboot_sem2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="Account_Roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account_Role {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "Account_Id", referencedColumnName = "AccountId")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "Role_Id", referencedColumnName = "RoleId")
    private Role role;
}
