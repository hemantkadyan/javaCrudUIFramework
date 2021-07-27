package com.example.crudwithvaadin.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Item")
public class Item extends SecureEntity<Long>{

    private String itemName;

    private String itemCategory;

    private Integer price;

    protected Item() {
        this.itemName="";
        this.itemCategory="";
        this.price=0;
    }

    public Item(String itemName, String itemCategory, Integer price) {
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Item [id=%d, itemName='%s', itemCategory='%s', price='%s']", getId(),
                itemCategory, itemCategory,price);
    }

}
