package com.xiaojun.whut.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id",length = 11,nullable = false)
    private int roleId;

    @Column(length = 10,nullable = false)
    private String name;

    @Column(name = "name_zh",length = 10,nullable = false)
    private String nameZH;

    @ManyToMany(mappedBy = "roles")
    @Basic(fetch = FetchType.LAZY)
    private Set<User> user=new HashSet<>();

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getNameZH() {
        return nameZH;
    }

    public void setNameZH(String nameZH) {
        this.nameZH = nameZH;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
