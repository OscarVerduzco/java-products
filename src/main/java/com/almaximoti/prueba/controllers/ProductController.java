package com.almaximoti.prueba.controllers;
// imports 
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.HashMap;

import com.almaximoti.prueba.dtos.TypeProductSimpleDto;
import com.almaximoti.prueba.models.ProductModel;
import com.almaximoti.prueba.models.ProductSupplierModel;
import com.almaximoti.prueba.services.ProductService;
import com.almaximoti.prueba.dtos.ProductSimpleDto;
import com.almaximoti.prueba.dtos.ProductSupplierSimpleDto;
import com.almaximoti.prueba.dtos.SupplierSimpleDto;
import java.util.Map;

import java.util.List;




@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // use procedures
    @PostMapping("/create")
    public ProductModel createProduct(@RequestBody ProductModel product) {
        return productService.createProduct(product.getName(), product.getPrice(), product.getProductKey(), product.getTypeProduct().getId());
        
    }//Create json to test this method
    // { "name": "product 1", "price": 1000, "typeProduct": { "id": 1 } }

    @PostMapping("/update")
    public ProductModel updateProduct(@RequestBody ProductModel product) {
       return productService.updateProduct(product.getId(), product.getName(), product.getPrice(), product.getProductKey(), product.getTypeProduct().getId());
    }

    @PostMapping("/delete")
    public ResponseEntity<Map<String, Object>> deleteProduct(@RequestBody ProductModel product) {
        productService.deleteProduct(product.getId());
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Product deleted successfully");
        return ResponseEntity.ok(response);
    } 

    // getAll procedure
    @GetMapping("/get-all")
    public ArrayList<ProductSimpleDto> getAllProducts() {
        ArrayList<ProductSimpleDto> products = (ArrayList<ProductSimpleDto>) productService.getAllProducts();
        return products;
    }   


    // getOne procedure
    @PostMapping("/get-one")
    public ProductSimpleDto getOneProduct(@RequestBody ProductModel product) {
        
        return productService.getOneProduct(product.getId());
        
    }

    // Select all type products
    @GetMapping("/type-products")
    public ResponseEntity<List<TypeProductSimpleDto>> getAllTypeProducts() {
        List<TypeProductSimpleDto> typeProducts = productService.getAllTypeProducts();
        return ResponseEntity.ok(typeProducts);
    }

    //Select all suppliers
    @GetMapping("/suppliers")
    public ResponseEntity<List<SupplierSimpleDto>> getAllSuppliers() {
        List<SupplierSimpleDto> suppliers = productService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    // Asignar un producto a un proveedor
    @PostMapping("/assign-product-to-supplier")
    public ResponseEntity<String> assignProductToSupplier(
            @RequestParam Integer productId,
            @RequestParam Integer supplierId,
            @RequestParam String supplierProductKey,
            @RequestParam Double supplierCost) {
        productService.assignProductToSupplier(productId, supplierId, supplierProductKey, supplierCost);
        return ResponseEntity.ok("Producto asignado al proveedor con éxito");
    }
    // Create json to test this method
    // { "id": 1, "supplier": { "id": 1 }, "supplierProductKey": "key 1", "supplierCost": 1000, "product": { "id": 1 } }

    // Get all suppliers of a product
    @PostMapping("/suppliers-assigned")
    public ResponseEntity<List<ProductSupplierSimpleDto>> getSuppliersByProductId(@RequestParam Integer productId) {
        List<ProductSupplierSimpleDto> suppliers = productService.getSuppliersByProductId(productId);
        return ResponseEntity.ok(suppliers);
    }

    // Search products by product key
    @GetMapping("/search")
    public ResponseEntity<List<ProductSimpleDto>> searchProductsByProductKey(@RequestParam String search) {
        List<ProductSimpleDto> products = productService.searchByProductKey(search);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search-type")
    public ResponseEntity<List<ProductSimpleDto>> searchProductsByType(@RequestParam Integer type) {
        List<ProductSimpleDto> products = productService.searchByTypeProductId(type);
        return ResponseEntity.ok(products);
    }


}
