package com.almaximoti.prueba.repositories;

import com.almaximoti.prueba.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IProductRepository extends JpaRepository<ProductModel, Integer> {

    // use procedures
    @Transactional
    @Procedure("sp_create_product")
    ProductModel createProduct(@Param("name") String name, @Param("price") Double price, @Param("productKey") String productKey, @Param("typeProductId") Integer typeProductId);

    @Transactional
    @Procedure("sp_update_product")
    ProductModel updateProduct(@Param("id") Integer id, @Param("name") String name, @Param("price") Double price, @Param("productKey") String productKey,  @Param("typeProductId") Integer typeProductId);

    @Transactional
    @Procedure("sp_delete_product")// update deleted to 1
    void deleteProduct(Integer id);

    // getAll procedure
    @Procedure("sp_get_all_products")
    List<ProductModel> getAllProducts();


    // getOne procedure
    @Procedure("sp_get_one_product")
    ProductModel getOneProduct(Integer id);

    // get all products not deleted
    List<ProductModel> findByDeletedFalse();

    // find one product not deleted
    ProductModel findByIdAndDeletedFalse(Integer id);

    // Asignar un producto a un proveedor
    @Transactional
    @Procedure("sp_assign_product_to_supplier")
    void assignProductToSupplier(@Param("product_id") Integer productId,
                                @Param("supplier_id") Integer supplierId,
                                @Param("supplier_product_key") String supplierProductKey,
                                @Param("supplier_cost") Double supplierCost );
                                                            

    // Search products like %productKey% and not deleted
    List<ProductModel> findByProductKeyContainingAndDeletedFalse(String productKey);

    // Search by type product id and not deleted
    List<ProductModel> findByTypeProductIdAndDeletedFalse(Integer typeProductId);




    

}
