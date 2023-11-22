package com.almaximoti.prueba.dtos;


public class ProductSupplierSimpleDto {

    private Integer id;
    private String supplierName;
    private String supplierProductKey;
    private Double supplierCost;

    public ProductSupplierSimpleDto() {
    }

    public ProductSupplierSimpleDto(Integer id, String supplierName, String supplierProductKey, Double supplierCost) {
        this.id = id;
        this.supplierName = supplierName;
        this.supplierProductKey = supplierProductKey;
        this.supplierCost = supplierCost;
    }

    // Getters and Setters
    // id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // supplierName
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    // supplierProductKey
    public String getSupplierProductKey() {
        return supplierProductKey;
    }

    public void setSupplierProductKey(String supplierProductKey) {
        this.supplierProductKey = supplierProductKey;
    }

    // supplierCost
    public Double getSupplierCost() {
        return supplierCost;
    }

    public void setSupplierCost(Double supplierCost) {
        this.supplierCost = supplierCost;
    }
    
   

}
