package com.hexad.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Leonardo Rodrigues
 */
@Entity
public class ProductPack implements Comparable<ProductPack>, Serializable{
    @Id
    private Long id;
    
    @ManyToOne
    private Product product;
    private Integer ammount;
    private Double price;

    public ProductPack() {
        //Default constructor for Hibernate
    }
    
    public ProductPack(Long id, Product product, int ammount, double price) {
        this.id = id;
        this.product = product;
        this.ammount = ammount;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAmmount() {
        return ammount;
    }

    public void setAmmount(Integer ammount) {
        this.ammount = ammount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public int compareTo(ProductPack other) {
        return Integer.compare(this.getAmmount(), other.getAmmount());
    }
}
