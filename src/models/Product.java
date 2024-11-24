/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ADMIN
 */
public class Product {
    private String productId;
    private String productPName;
    private String location;
    private double price;
    private String brand;
    private String typeId;
    private String supId;

    public Product() {
    }

    public Product(String productId, String productPName, String location, double price, String brand, String typeId, String supId) {
        this.productId = productId;
        this.productPName = productPName;
        this.location = location;
        this.price = price;
        this.brand = brand;
        this.typeId = typeId;
        this.supId = supId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductPName() {
        return productPName;
    }

    public void setProductPName(String productPName) {
        this.productPName = productPName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }
    
    
}
