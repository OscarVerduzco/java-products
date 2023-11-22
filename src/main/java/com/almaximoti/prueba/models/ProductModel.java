package com.almaximoti.prueba.models;

import javax.persistence.*;


@Entity
@Table(name = "product")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name", length = 64, nullable = false)
    private String name;
    
    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "product_key", nullable = false)
    private String productKey;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;
    
    @ManyToOne
    @JoinColumn(name = "type_product_id", referencedColumnName = "id")
    private TypeProductModel typeProduct;
    


    public ProductModel() {
    }

    public ProductModel(Integer id, String name, Double price, String productKey, Boolean deleted, TypeProductModel typeProduct) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productKey = productKey;
        this.deleted = deleted;
        this.typeProduct = typeProduct;
    }

    // Getters and Setters
    // id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // name

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // price
    public Double getPrice() {
        return price;
    }


    public void setPrice(Double price) {
        this.price = price;
    }
    

    // productKey
    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;

    }

    
    // typeProductModel
    public TypeProductModel getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProductModel typeProduct) {
        this.typeProduct = typeProduct;
    }

    // deleted
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    


}
