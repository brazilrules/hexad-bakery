package com.hexad.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Leonardo Rodrigues
 */
@Entity
public class Product implements Serializable {
    
    @Id
    private String code;
    private String name;

    public Product() {
        //Default Constructor for hibernate
    }
    
    public Product(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
