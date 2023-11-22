package com.almaximoti.prueba.repositories;

import com.almaximoti.prueba.models.SupplierModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISupplierRepository extends JpaRepository<SupplierModel, Integer> {

    // use procedures
    
}
