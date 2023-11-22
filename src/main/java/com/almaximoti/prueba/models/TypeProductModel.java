package com.almaximoti.prueba.models;
import javax.persistence.*;


@Entity 
@Table(name = "typeProduct")
public class TypeProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "type_name", length = 64, nullable = false)
    private String name;
    @Column(name = "description", length = 255, nullable = false)
    private String description;

    public TypeProductModel() {
    }

    public TypeProductModel(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        
    }

    public TypeProductModel(String name, String description) {
        this.name = name;
        this.description = description;
        
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

    // description
    public String getDescription() {
        return description;
    }

    
}
