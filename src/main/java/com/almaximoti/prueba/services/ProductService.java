package com.almaximoti.prueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almaximoti.prueba.dtos.TypeProductSimpleDto;
import com.almaximoti.prueba.models.ProductModel;
import com.almaximoti.prueba.models.ProductSupplierModel;
import com.almaximoti.prueba.models.TypeProductModel;
import com.almaximoti.prueba.repositories.IProductRepository;
import com.almaximoti.prueba.repositories.IProductSupplierRepository;
import com.almaximoti.prueba.repositories.ISupplierRepository;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import com.almaximoti.prueba.repositories.ITypeProductRepository;
import java.util.Collections;
import com.almaximoti.prueba.dtos.SupplierSimpleDto;
import com.almaximoti.prueba.models.SupplierModel;
import com.almaximoti.prueba.dtos.ProductSimpleDto;
import com.almaximoti.prueba.dtos.ProductSupplierSimpleDto;



@Service
public class ProductService {

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ITypeProductRepository typeProductRepository;
    @Autowired
    private ISupplierRepository supplierRepository;
    @Autowired
    private IProductSupplierRepository productSupplierRepository;

    

    // use procedures
    @Transactional
    public ProductModel createProduct(String name, Double price, String productKey, Integer typeProductId) {
        return productRepository.createProduct(name, price, productKey, typeProductId);
    }

    @Transactional
    public ProductModel updateProduct(Integer id, String name, Double price, String productKey, Integer typeProductId) {
        return productRepository.updateProduct(id, name, price, productKey, typeProductId);
    }

    @Transactional
    public void deleteProduct(Integer id) {
        productRepository.deleteProduct(id);
    }

    // getAll procedure
   @Transactional  // Indica que es una transacción de solo lectura
    public List<ProductSimpleDto> getAllProducts() {
        List<ProductModel> products = productRepository.findByDeletedFalse();
        // Asegúrate de que products no sea null
        if (products == null) {
            // Manejar la situación según tus requisitos
            return Collections.emptyList();
        }
    
        return products.stream()
                .map(product -> new ProductSimpleDto(product.getId(), 
                                                     product.getName(),
                                                    product.getProductKey(), 
                                                    product.getPrice().toString(),
                                                    product.getTypeProduct().getName(), 
                                                    product.getDeleted() ? "No" : "Si"))
                .collect(Collectors.toList());
    }


    // getOne procedure
    public ProductSimpleDto getOneProduct(Integer id) {
        // return productRepository.findByIdAndDeletedFalse(id);
        ProductModel product = productRepository.findByIdAndDeletedFalse(id);
        if (product == null) {
            // Manejar la situación según tus requisitos
            return null;
        }
        return new ProductSimpleDto(product.getId(), 
                                    product.getName(), 
                                    product.getProductKey(), 
                                    product.getPrice().toString(),
                                    product.getTypeProduct().getName(), 
                                    product.getDeleted() ? "No" : "Si");
    }

    //Select all type products
    public List<TypeProductSimpleDto> getAllTypeProducts() 
    {
        List<TypeProductModel> typeProducts = typeProductRepository.findAll();
        // Asegúrate de que typeProducts no sea null
        if (typeProducts == null) {
            // Manejar la situación según tus requisitos
            return Collections.emptyList();
        }
    
        return typeProducts.stream()
                .map(typeProduct -> new TypeProductSimpleDto(typeProduct.getId(), typeProduct.getName()))
                .collect(Collectors.toList());
    }

    // Select all suppliers
    public List<SupplierSimpleDto> getAllSuppliers() 
    {
        List<SupplierModel> suppliers = supplierRepository.findAll();
        // Asegúrate de que suppliers no sea null
        if (suppliers == null) {
            // Manejar la situación según tus requisitos
            return Collections.emptyList();
        }
    
        return suppliers.stream()
                .map(supplier -> new SupplierSimpleDto(supplier.getId(), supplier.getName()))
                .collect(Collectors.toList());
    }

    // Asignar un producto a un proveedor
    @Transactional
    public void assignProductToSupplier(Integer productId, Integer supplierId, String supplierProductKey, Double supplierCost) {
        productRepository.assignProductToSupplier(productId, supplierId, supplierProductKey, supplierCost);
    }

    // Get all suppliers of a product
    // this mehtod returns all ProductSupplierModel objects
    
    public List<ProductSupplierSimpleDto> getSuppliersByProductId(Integer productId) {
        List<ProductSupplierModel> productSuppliers = productSupplierRepository.findByProductIdAndSupplierDeletedFalse(productId);
        // Asegúrate de que productSuppliers no sea null
        if (productSuppliers == null) {
            // Manejar la situación según tus requisitos
            return Collections.emptyList();
        }
    
        return productSuppliers.stream()
                .map(productSupplier -> new ProductSupplierSimpleDto(productSupplier.getId(), 
                                            productSupplier.getSupplier().getName(),
                                             productSupplier.getSupplierProductKey(), 
                                            productSupplier.getSupplierCost()))
                .collect(Collectors.toList());
    }

    // SEARCH BY PRODUCT KEY 
    public List<ProductSimpleDto> searchByProductKey(String productKey) {
        List<ProductModel> products = productRepository.findByProductKeyContainingAndDeletedFalse(productKey);
        // Asegúrate de que products no sea null
        if (products == null) {
            // Manejar la situación según tus requisitos
            return Collections.emptyList();
        }
    
        return products.stream()
                // .map(product -> new ProductSimpleDto(product.getId(), product.getName(), product.getProductKey(), product.getTypeProduct().getName(), product.getDeleted() ? "No" : "Si"))
                .map(product -> new ProductSimpleDto(product.getId(), 
                                                    product.getName(), 
                                                    product.getProductKey(), 
                                                    product.getPrice().toString(),
                                                    product.getTypeProduct().getName(),
                                                     product.getDeleted() ? "No" : "Si"))
                .collect(Collectors.toList());
    }

    // SEARCH BY TYPE PRODUCT ID
    public List<ProductSimpleDto> searchByTypeProductId(Integer typeProductId) {
        List<ProductModel> products = productRepository.findByTypeProductIdAndDeletedFalse(typeProductId);
        // Asegúrate de que products no sea null
        if (products == null) {
            // Manejar la situación según tus requisitos
            return Collections.emptyList();
        }
    
        return products.stream()
                .map(product -> new ProductSimpleDto(product.getId(),
                                                     product.getName(), 
                                                     product.getProductKey(), 
                                                     product.getPrice().toString(),
                                                     product.getTypeProduct().getName(), 
                                                     product.getDeleted() ? "No" : "Si"))
                .collect(Collectors.toList());
    }


    



    
}
