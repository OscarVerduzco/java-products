package com.almaximoti.prueba.repositories;

import com.almaximoti.prueba.models.ProductSupplierModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface IProductSupplierRepository extends JpaRepository<ProductSupplierModel, Integer> {

    // use procedures
    
    // find suppliers assigned to a product
    // Find all join suppliers assigned to a product
    //    
    // SELECT
    // s.id,
    // s.supplier_name
    // FROM product_supplier ps
    // join supplier s on ps.supplier_id = s.id and ps.product_id = 1
    // where s.deleted = 0; 
    List<ProductSupplierModel> findByProductIdAndSupplierDeletedFalse(Integer productId);

}
