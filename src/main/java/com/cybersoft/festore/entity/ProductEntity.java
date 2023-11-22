package com.cybersoft.festore.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private CategoryEntity categoryEntity;

    @ManyToOne
    @JoinColumn(name = "id_size")
    private SizeEntity sizeEntity;

    @ManyToOne
    @JoinColumn(name = "id_color")
    private ColorEntity colorEntity;

    @OneToMany(mappedBy = "productEntity")
    private List<CartEntity> listCart;

    public List<CartEntity> getListCart() {
        return listCart;
    }

    public void setListCart(List<CartEntity> listCart) {
        this.listCart = listCart;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public SizeEntity getSizeEntity() {
        return sizeEntity;
    }

    public void setSizeEntity(SizeEntity sizeEntity) {
        this.sizeEntity = sizeEntity;
    }

    public ColorEntity getColorEntity() {
        return colorEntity;
    }

    public void setColorEntity(ColorEntity colorEntity) {
        this.colorEntity = colorEntity;
    }
}
