package com.almaximoti.prueba.models;
import javax.persistence.*;


@Entity
@Table(name = "product_supplier")
public class ProductSupplierModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // supplierProductKey VARCHAR(255),
    // supplierCost DECIMAL(10, 2),
    @Column(name = "supplier_product_key", length = 255, nullable = false)
    private String supplierProductKey;
    @Column(name = "supplier_cost", nullable = false)
    private Double supplierCost;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductModel product;

    @ManyToOne
    @JoinColumn(name = "supplier_id" , referencedColumnName = "id")
    private SupplierModel supplier;


    public ProductSupplierModel() {
    }

    public ProductSupplierModel(Integer id, String supplierProductKey, Double supplierCost, ProductModel product, SupplierModel supplier) {
        this.id = id;
        this.supplierProductKey = supplierProductKey;
        this.supplierCost = supplierCost;
        this.product = product;
        this.supplier = supplier;
    }


    // Getters and Setters
    // id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    // product
    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    // supplier
    public SupplierModel getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierModel supplier) {
        this.supplier = supplier;
    }

    




}

