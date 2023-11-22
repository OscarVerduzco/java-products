package com.almaximoti.prueba.repositories;
import com.almaximoti.prueba.models.TypeProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeProductRepository extends JpaRepository<TypeProductModel, Integer> {

    // use procedures
    
}
