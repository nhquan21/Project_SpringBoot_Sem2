package com.bkap.project_springboot_sem2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="Roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @Column(name="RoleId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;
    @Column(name="RoleName")
    private String roleName;
    @Column(name="Active")
    private boolean active;

    @OneToMany(mappedBy = "role")
    private Set<Account_Role> roleAccount;

}
