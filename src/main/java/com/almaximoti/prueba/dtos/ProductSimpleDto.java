package com.almaximoti.prueba.dtos;

public class ProductSimpleDto {
    
    private Integer id;
    private String name;
    private String productKey;
    private String price;
    private String typeProduct;
    private String active;

    public ProductSimpleDto() {
    }

    public ProductSimpleDto(Integer id, String name, String productKey, String price, String typeProduct, String active) {
        this.id = id;
        this.name = name;
        this.productKey = productKey;
        this.price = price;
        this.typeProduct = typeProduct;
        this.active = active;
    }
    
    // Getters and Setters
    // id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // price
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    // name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // productKey
    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public void setTypeProduct(String typeProduct) {
        this.typeProduct = typeProduct;
    }

    public String getTypeProduct() {
        return typeProduct;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getActive() {
        return active;
    }



}
