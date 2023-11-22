package com.almaximoti.prueba.dtos;

public class SupplierSimpleDto {
    private Integer id;
    private String name;

    public SupplierSimpleDto() {
    }

    public SupplierSimpleDto(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}
