package com.cybersoft.festore.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "create_date")
    private Date createDate;

    @OneToMany(mappedBy = "roleEntity")
    private List<UserEntity> listUserEntities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<UserEntity> getListUserEntities() {
        return listUserEntities;
    }

    public void setListUserEntities(List<UserEntity> listUserEntities) {
        this.listUserEntities = listUserEntities;
    }
}
