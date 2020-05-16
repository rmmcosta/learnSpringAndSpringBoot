package com.rmmcosta.MyCrud.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@SqlResultSetMapping(name="CartProducts",
        entities={
                @EntityResult(entityClass=Product.class, fields={
                        @FieldResult(name="id", column="id"),
                        @FieldResult(name="description", column="description"),
                        @FieldResult(name="name", column="name"),
                        @FieldResult(name="price", column="price")
                })}
)
public class Product implements DomainObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Product() {
    }

    public Product(int id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Version
    private Integer version;

    private String name, description, imageUrl;
    private BigDecimal price;
    private Date createdOn, UpdatedOn;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getUpdatedOn() {
        return UpdatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        UpdatedOn = updatedOn;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
